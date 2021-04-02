package com.righttek.es.marcacion.utils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 
 * @author JORGE
 *
 */
@Controller
public class HomeController {

	@GetMapping(value = "/")
	public String index() {
		return "redirect:swagger-ui.html";
	}
}
