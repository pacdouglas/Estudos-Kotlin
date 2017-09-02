package br.com.douglaspac.estudos.jpa.utils

import javax.persistence.Persistence

class JPAUtils {
    companion object {
        val emFinancas = Persistence.createEntityManagerFactory("financas").createEntityManager()
    }
}