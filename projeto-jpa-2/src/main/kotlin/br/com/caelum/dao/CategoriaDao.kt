package br.com.caelum.dao

import br.com.caelum.model.Categoria
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
class CategoriaDao {

    @PersistenceContext
    private lateinit var em: EntityManager

    val categorias: List<Categoria>
        get() {
            val query = em.createQuery("from Categoria", Categoria::class.java)

            return query.resultList
        }
}