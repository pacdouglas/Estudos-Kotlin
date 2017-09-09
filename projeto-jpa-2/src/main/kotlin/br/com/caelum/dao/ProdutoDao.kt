package br.com.caelum.dao

import br.com.caelum.model.Loja
import br.com.caelum.model.Produto
import org.hibernate.FetchMode
import org.hibernate.Session
import org.hibernate.criterion.Restrictions
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.ArrayList
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.criteria.Predicate

@Repository
class ProdutoDao {

    @PersistenceContext
    private lateinit var em: EntityManager

    val produtos: List<Produto>
        get() = em.createQuery("from Produto", Produto::class.java).resultList

    fun getProduto(id: Int?): Produto {
        return em.find(Produto::class.java, id)
    }

    @Transactional
    fun getProdutos2(nome: String, categoriaId: Int?, lojaId: Int?): List<Produto> {
        val session = em.unwrap(Session::class.java)
        val criteria = session.createCriteria(Produto::class.java)

        if (!nome.isEmpty()) {
            criteria.add(Restrictions.like("nome", "%$nome%"))
        }

        if (lojaId != null) {
            criteria.add(Restrictions.like("loja.id", lojaId))
        }

        if (categoriaId != null) {
            criteria.setFetchMode("categorias", FetchMode.JOIN)
                    .createAlias("categorias", "c")
                    .add(Restrictions.like("c.id", categoriaId))
        }

        return criteria.list() as List<Produto>

    }

    fun getProdutos(nome: String, categoriaId: Int?, lojaId: Int?): List<Produto> {
        val criteriaBuilder = em.criteriaBuilder
        val query = criteriaBuilder.createQuery(Produto::class.java)
        val root = query.from(Produto::class.java)

        val nomePath = root.get<String>("nome")
        val lojaPath = root.get<Loja>("loja").get<Int>("id")
        val categoriaPath = root.join<Any, Any>("categorias").get<Int>("id")

        val predicates = ArrayList<Predicate>()

        if (!nome.isEmpty()) {
            val nomeIgual = criteriaBuilder.like(nomePath, nome)
            predicates.add(nomeIgual)
        }
        if (categoriaId != null) {
            val categoriaIgual = criteriaBuilder.equal(categoriaPath, categoriaId)
            predicates.add(categoriaIgual)
        }
        if (lojaId != null) {
            val lojaIgual = criteriaBuilder.equal(lojaPath, lojaId)
            predicates.add(lojaIgual)
        }

        query.where(*predicates.toTypedArray())

        val typedQuery = em.createQuery(query)
        return typedQuery.resultList

    }

    fun insere(produto: Produto) {
        if (produto.id == null)
            em.persist(produto)
        else
            em.merge(produto)
    }
}
