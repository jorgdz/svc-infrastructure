package com.righttek.es.marcacion.repository.contract;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.righttek.es.marcacion.repository.model.MarcacionExterna;

/**
 * 
 * @author JORGE
 *
 */
@Repository
public interface IMarcacionRepository extends JpaRepository<MarcacionExterna, BigInteger>{
	
	@Query("SELECT m FROM MarcacionExterna m WHERE m.fechaHora BETWEEN :fechaInicio AND :fechaFin")
	List<MarcacionExterna> consultarMarcaciones(@Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin);
	
	@Query("SELECT m FROM MarcacionExterna m WHERE m.emailEmpleado = :email AND m.fechaHora BETWEEN :fechaInicio AND :fechaFin")
	List<MarcacionExterna> consultarMarcacionesPorEmpleado(@Param("email") String email, @Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin);
	
}
