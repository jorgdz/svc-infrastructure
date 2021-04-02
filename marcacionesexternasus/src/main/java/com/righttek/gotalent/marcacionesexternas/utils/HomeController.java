
package com.righttek.gotalent.marcacionesexternas.utils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Righttek
 * @author CESAR GARCIA
 * @since 2020
 */
@Controller
public class HomeController {

	@RequestMapping(value = "/")
    public String index() {
        return "redirect:swagger-ui.html";
    }

}
