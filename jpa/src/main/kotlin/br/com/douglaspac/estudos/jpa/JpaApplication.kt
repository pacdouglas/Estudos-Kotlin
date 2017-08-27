package br.com.douglaspac.estudos.jpa

import br.com.douglaspac.estudos.jpa.model.*
import br.com.douglaspac.estudos.jpa.utils.JPAUtils
import java.math.BigDecimal

fun main(args: Array<String>) {
    //insereConta()
    //insereMovimentacao()
    //testeMovimentacaoComCategoria()
    testeContaCliente()
}

fun testeContaCliente() {
    val cliente = Cliente(nome = "Dougras", endereco = "Longe pra carvalho", profissao = "Menino do TI")

    val emf = JPAUtils.emfFinancas
    val em = emf.createEntityManager()
    em.transaction.begin()

    cliente.conta = em.find(Conta::class.java, 1)
    em.persist(cliente)

    em.transaction.commit()
    em.close()
    emf.close()
}

fun testeMovimentacaoComCategoria() {
    val categoria1 = Categoria(nome = "Viagem")
    val categoria2 = Categoria(nome = "Negócios")

    val movimentacao1 = Movimentacao(descricao = "Viagem p SP", tipo = TipoMovimentacao.SAIDA,
            valor = BigDecimal("200.0"), categoria = listOf(categoria1, categoria2))
    val movimentacao2 = Movimentacao(descricao = "Viagem p RJ", tipo = TipoMovimentacao.SAIDA,
            valor = BigDecimal("300.0"), categoria = listOf(categoria1))

    val emf = JPAUtils.emfFinancas
    val em = emf.createEntityManager()
    em.transaction.begin()

    val conta = em.find(Conta::class.java, 1)
    movimentacao1.conta = conta
    movimentacao2.conta = conta

    em.persist(categoria1)
    em.persist(categoria2)
    em.persist(movimentacao1)
    em.persist(movimentacao2)

    em.transaction.commit()
    em.close()
    emf.close()
}

fun insereMovimentacao() {
    val emf = JPAUtils.emfFinancas
    val em = emf.createEntityManager()

    em.transaction.begin()
    val conta = em.find(Conta::class.java, 1)
    val movimentacao = Movimentacao(conta = conta)
    em.persist(movimentacao)
    em.transaction.commit()

    em.close()
    emf.close()
}

fun insereConta() {
    val emf = JPAUtils.emfFinancas
    val em = emf.createEntityManager()

    em.transaction.begin()
    em.persist(Conta(titular = "Douglas Martins", agencia = "456", banco = "Bradesco", numero = "123"))
    em.transaction.commit()
    em.close()
    emf.close()
}