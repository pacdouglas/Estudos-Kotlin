package br.com.caelum.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import java.sql.SQLException

@Controller
class HomeController {

    @RequestMapping("/")
    @Throws(SQLException::class)
    fun home(): String {
        return "home"
    }

}