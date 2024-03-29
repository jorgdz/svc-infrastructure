package com.righttek.ms.importarmarcaciones.exception;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.righttek.ms.importarmarcaciones.exception.custom.ConflictException;
import com.righttek.ms.importarmarcaciones.exception.custom.MarcacionFechaException;
import com.righttek.ms.importarmarcaciones.exception.custom.NotFoundException;
import com.righttek.ms.importarmarcaciones.exception.dto.ErrorResponse;

/**
 * 
 * @author JORGE
 *
 */
@RestControllerAdvice
public class Handler {

	/**
	 * 
	 * @param request
	 * @param exception
	 * @return Error in JSON not found resource
	 */
	@ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({
    	NotFoundException.class
    })
    public ErrorResponse notFoundException(HttpServletRequest request, Exception exception) {
		return new ErrorResponse(exception, request.getRequestURI(), HttpStatus.NOT_FOUND.value());
	}
	
	/**
	 * 
	 * @param request
	 * @param exception
	 * @return Error in JSON bad request exception
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({
    	MarcacionFechaException.class,
    	MethodArgumentNotValidException.class,
    	MethodArgumentTypeMismatchException.class,
    	MissingServletRequestParameterException.class,
    	ConstraintViolationException.class
    })
	public ErrorResponse badRequestException (HttpServletRequest request, Exception exception) {
		if (exception instanceof MethodArgumentNotValidException) {
			BindingResult bindingResult = ((MethodArgumentNotValidException) exception).getBindingResult();
			
			String message = "Error de validación en: "+ bindingResult.getObjectName();
			List<FieldError> errors = bindingResult.getFieldErrors();
			List<String> listErrors = new ArrayList<String>();
			
			errors.forEach(e -> listErrors.add(e.getDefaultMessage()));
			return new ErrorResponse(message, listErrors, exception, request.getRequestURI(), HttpStatus.BAD_REQUEST.value());
		}
		
		if (exception instanceof MethodArgumentTypeMismatchException) {
			return new ErrorResponse("No se puede convertir el valor " +  ((MethodArgumentTypeMismatchException) exception).getValue() 
				+ " a un tipo de dato " + ((MethodArgumentTypeMismatchException)exception).getRequiredType().getSimpleName(),
				exception, request.getRequestURI(), HttpStatus.BAD_REQUEST.value());
		}
		
		if (exception instanceof ConstraintViolationException) {
			ConstraintViolationException ex = (ConstraintViolationException) exception;
			
			List<String> constraints = new ArrayList<>();
			
			ex.getConstraintViolations().forEach(c -> constraints.add(c.getMessage()));
			
			return new ErrorResponse("Ha ocurrido un error al validar", constraints, exception,
				request.getRequestURI(), HttpStatus.BAD_REQUEST.value());
		}
		
		return new ErrorResponse(exception.getMessage(), exception,
			request.getRequestURI(), HttpStatus.BAD_REQUEST.value());
	}
	
	/**
	 * 
	 * @param request
	 * @param exception
	 * @return error conflict in JSON 
	 */
	@ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({
    	ConflictException.class
    })
    public ErrorResponse conflictException(HttpServletRequest request, Exception exception) {
		return new ErrorResponse(exception, request.getRequestURI(), HttpStatus.CONFLICT.value());
    }
	
	/**
	 * 
	 * @param request
	 * @param exception
	 * @return Any unexpected error in JSON
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse unexpectedException(HttpServletRequest request, Exception exception) {
    	return new ErrorResponse(exception, request.getRequestURI(), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}
