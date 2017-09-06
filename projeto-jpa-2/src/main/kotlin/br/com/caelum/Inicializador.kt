package br.com.caelum

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer
import org.springframework.web.util.Log4jConfigListener

import javax.servlet.ServletContext
import javax.servlet.ServletException

class Inicializador : AbstractAnnotationConfigDispatcherServletInitializer() {

    override fun getRootConfigClasses(): Array<Class<*>> {
        return arrayOf(Configurador::class.java)
    }

    override fun getServletConfigClasses(): Array<Class<*>>? {
        return null
    }

    override fun getServletMappings(): Array<String> {
        return arrayOf("/")
    }

    @Throws(ServletException::class)
    override fun onStartup(servletContext: ServletContext) {
        super.onStartup(servletContext)

        // Descomente abaixo para ligar log4j!

        servletContext.setInitParameter("log4jConfigLocation", "/WEB-INF/log4j.xml")
        servletContext.addListener(Log4jConfigListener())

    }

}
