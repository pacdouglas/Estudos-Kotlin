package br.com.douglaspac.estudos.jpa

import br.com.douglaspac.estudos.jpa.model.Conta
import br.com.douglaspac.estudos.jpa.model.Movimentacao
import br.com.douglaspac.estudos.jpa.utils.JPAUtils
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class JpaApplication

fun main(args: Array<String>) {
    //SpringApplication.run(JpaApplication::class.java, *args)
    val emf = JPAUtils.emfFinancas
    val em = emf.createEntityManager()

    em.transaction.begin()
    //em.persist(conta)
    val conta = em.find(Conta::class.java, 1)
    val movimentacao = Movimentacao(conta = conta)
    em.persist(movimentacao)
    em.transaction.commit()

    em.close()
    emf.close()

}
