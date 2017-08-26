package br.com.douglaspac.estudos.jpa.utils

import javax.persistence.Persistence

class JPAUtils {
    companion object {
        val emfFinancas = Persistence.createEntityManagerFactory("financas")
    }
}