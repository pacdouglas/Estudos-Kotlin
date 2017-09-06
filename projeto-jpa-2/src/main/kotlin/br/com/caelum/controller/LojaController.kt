package br.com.caelum.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/loja")
class LojaController {

    @RequestMapping("/form")
    fun form(): String {
        return "loja/form"
    }
}