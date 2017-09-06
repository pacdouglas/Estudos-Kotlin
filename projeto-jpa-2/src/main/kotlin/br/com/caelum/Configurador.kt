package br.com.caelum

import br.com.caelum.dao.CategoriaDao
import br.com.caelum.dao.LojaDao
import br.com.caelum.dao.ProdutoDao
import br.com.caelum.model.Categoria
import br.com.caelum.model.Loja
import br.com.caelum.model.Produto
import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import org.springframework.context.support.ReloadableResourceBundleMessageSource
import org.springframework.core.convert.converter.Converter
import org.springframework.format.FormatterRegistry
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.web.servlet.ViewResolver
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import org.springframework.web.servlet.view.InternalResourceViewResolver

@Configuration
@EnableWebMvc
@ComponentScan("br.com.caelum")
@EnableTransactionManagement
class Configurador : WebMvcConfigurerAdapter() {

    @Bean
    @Scope("request")
    fun produtos(produtoDao: ProdutoDao): List<Produto> {

        return produtoDao.produtos
    }

    @Bean
    fun categorias(categoriaDao: CategoriaDao): List<Categoria> {

        return categoriaDao.categorias
    }

    @Bean
    fun lojas(lojaDao: LojaDao): List<Loja> {

        return lojaDao.lojas
    }

    @Bean
    fun messageSource(): MessageSource {
        val messageSource = ReloadableResourceBundleMessageSource()

        messageSource.setBasename("/WEB-INF/messages")
        messageSource.setCacheSeconds(1)
        messageSource.setDefaultEncoding("ISO-8859-1")

        return messageSource

    }

    val viewResolver: ViewResolver
        @Bean
        get() {
            val viewResolver = InternalResourceViewResolver()

            viewResolver.setPrefix("/WEB-INF/views/")
            viewResolver.setSuffix(".jsp")

            viewResolver.setExposeContextBeansAsAttributes(true)

            return viewResolver
        }

    override fun addFormatters(registry: FormatterRegistry) {
        registry.addConverter { categoriaId ->
            val categoria = Categoria()
            categoria.id = categoriaId.toString().toInt()
            categoria
        }
    }

}
