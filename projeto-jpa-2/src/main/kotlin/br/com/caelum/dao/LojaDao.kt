package br.com.caelum.dao

import br.com.caelum.model.Loja
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
class LojaDao {

    @PersistenceContext
    private lateinit var em: EntityManager

    val lojas: List<Loja>
        get() {
            val query = em.createQuery("from Loja", Loja::class.java)

            return query.resultList
        }

    fun getLoja(lojaId: Int?): Loja {
        return em.find(Loja::class.java, lojaId)
    }

}