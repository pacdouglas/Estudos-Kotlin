package br.com.caelum.controller

import org.hibernate.stat.Statistics
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Controller
@RequestMapping("/estatisticas")
class EstatisticasController {

    @PersistenceContext
    private lateinit var em: EntityManager

    @Autowired(required = false)
    private lateinit var statistics: Statistics

    @RequestMapping
    fun index(model: Model): String {
        return "estatisticas/index"
    }

    @RequestMapping("/limpar")
    fun invalidar(): String {
        statistics.clear()

        return "redirect:/estatisticas"
    }
}