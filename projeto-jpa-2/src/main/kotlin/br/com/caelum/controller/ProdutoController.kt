package br.com.caelum.controller

import br.com.caelum.dao.ProdutoDao
import br.com.caelum.model.Produto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.transaction.annotation.Transactional
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import javax.validation.Valid

@Controller
@RequestMapping("/produto")
class ProdutoController @Autowired constructor(val produtoDao: ProdutoDao) {

    @Transactional
    @RequestMapping(method = arrayOf(RequestMethod.POST), name = "cadastraProduto")
    fun salvar(@ModelAttribute @Valid produto: Produto, result: BindingResult, atts: RedirectAttributes): String {

        if (result.hasErrors()) {
            return form(produto)
        }
        produtoDao.insere(produto)

        return "redirect:/"
    }

    @RequestMapping(value = "/form", method = arrayOf(RequestMethod.GET))
    fun form(produto: Produto): String {
        return "produto/form"
    }

    @RequestMapping(value = "/{id}/form", method = arrayOf(RequestMethod.GET))
    fun update(@PathVariable id: Int?, model: Model): String {
        val produto = produtoDao.getProduto(id)

        model.addAttribute("produto", produto)
        return form(produto)
    }

    @RequestMapping("/{id}")
    fun detalhe(@PathVariable id: Int?, model: Model): String {
        val produto = produtoDao.getProduto(id)

        model.addAttribute("produto", produto)
        return "produto/detalhe"
    }

    @RequestMapping(value = "/buscar", method = arrayOf(RequestMethod.POST), name = "buscarProdutos")
    fun buscarPor(model: Model,
                  @RequestParam nome: String,
                  @RequestParam categoriaId: Int?,
                  @RequestParam(required = false) lojaId: Int?): String {

        val produtos = produtoDao.getProdutos(nome, categoriaId, lojaId)

        model.addAttribute("produtos", produtos)

        return "home"

    }
}