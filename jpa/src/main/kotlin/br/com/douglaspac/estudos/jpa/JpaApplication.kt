package br.com.douglaspac.estudos.jpa

import br.com.douglaspac.estudos.jpa.model.Categoria
import br.com.douglaspac.estudos.jpa.model.Conta
import br.com.douglaspac.estudos.jpa.model.Movimentacao
import br.com.douglaspac.estudos.jpa.model.TipoMovimentacao
import br.com.douglaspac.estudos.jpa.utils.JPAUtils
import java.math.BigDecimal
import java.util.*


fun main(args: Array<String>) {
    //testaContaCliente()
    //testeBuscaConta()
    //testeConta()
    //testeJPQL()
    testeJPARelacionamento()
    //testeMovimentacaoConta()
    //testeMovimentacoesComCategoria()
    //testeMovimentacoesPorCategoria()
}

fun testaContaCliente() {
    val em = JPAUtils.emFinancas

    em.transaction.begin()
    em.persist(Conta(titular = "Douglas Martins", agencia = "456", banco = "Bradesco", numero = "123"))
    em.persist(Conta(titular = "Letícia de Oliveira", agencia = "487", banco = "Itau", numero = "763"))
    em.transaction.commit()
    em.close()
}

fun testeBuscaConta() {
    val em = JPAUtils.emFinancas
    em.transaction.begin()

    print(em.find(Conta::class.java, 1))

    em.transaction.commit()
    em.close()
}

fun testeConta () {
    var conta = Conta(id = 1,titular = "Douglas Martins", agencia = "456", banco = "Bradesco", numero = "123")

    val em = JPAUtils.emFinancas
    em.transaction.begin()
    conta = em.find(Conta::class.java, conta.id)
    em.remove(conta)
    em.transaction.commit()
    em.transaction.begin()
    conta.titular = "Douglas Martins Martins"
    em.merge(conta)
    em.transaction.commit()
    em.close()
}

fun testeJPQL() {
    val em = JPAUtils.emFinancas
    em.transaction.begin()

    val conta = Conta()
    conta.id = 1

    val query = em
            .createQuery("select m from Movimentacao m where m.conta=:pConta " + "and m.tipo = :pTipo order by m.valor desc")

    query.setParameter("pConta", conta)
    query.setParameter("pTipo", TipoMovimentacao.ENTRADA)

    val movimentacoes: List<Movimentacao> = query.resultList as List<Movimentacao>

    for(movimentacao in movimentacoes){
        println("Descrição: ${movimentacao.descricao}")
        println("Valor: ${movimentacao.valor}")
    }

    em.transaction.commit()
    em.close()
}

fun testeJPARelacionamento() {
    val em = JPAUtils.emFinancas
    em.transaction.begin()

    val conta = Conta()
    conta.agencia = "111"
    conta.banco = "Itau"
    conta.numero = "54321"
    conta.titular = "Leonardo"

    val movimentacao = Movimentacao()
    movimentacao.data = Calendar.getInstance()
    movimentacao.descricao = "Churrascaria"
    movimentacao.tipo = TipoMovimentacao.SAIDA
    movimentacao.valor = BigDecimal("200.0")
    movimentacao.conta = conta

    em.persist(conta)
    em.persist(movimentacao)

    em.transaction.commit()
    em.close()
}

fun testeMovimentacaoConta() {
    val em = JPAUtils.emFinancas
    em.transaction.begin()

    val query = em.createQuery("select distinct c from Conta c left join fetch c.movimentacoes")

    val contas: List<Conta> = query.resultList as List<Conta>
    for(conta in contas){
        println("Titular: ${conta.titular}")
        println("Número de movimentações ...: ${conta.movimentacoes.size}")
    }

    em.transaction.commit()
    em.close()
}

fun testeMovimentacoesComCategoria() {
    val em = JPAUtils.emFinancas
    em.transaction.begin()

    val categoria1 = Categoria(nome = "Viagem")
    val categoria2 = Categoria(nome = "Negócios")

    val conta = Conta()
    conta.id = 3

    val movimentacao1 = Movimentacao()
    movimentacao1.data = Calendar.getInstance() // hoje
    movimentacao1.descricao = "Viagem à SP"
    movimentacao1.tipo = TipoMovimentacao.SAIDA
    movimentacao1.valor = BigDecimal(100.0)
    movimentacao1.categoria = listOf(categoria1, categoria2)

    movimentacao1.conta = conta

    val movimentacao2 = Movimentacao()
    movimentacao2.data = Calendar.getInstance() // hoje
    movimentacao2.descricao = "Viagem ao RJ"
    movimentacao2.tipo = TipoMovimentacao.SAIDA
    movimentacao2.valor = BigDecimal(300.0)
    movimentacao2.categoria = listOf(categoria1, categoria2)

    movimentacao2.conta = conta


    em.persist(categoria1) // Agora a 'categoria1' é Managed
    em.persist(categoria2) // Agora a 'categoria2' é Managed

    em.persist(movimentacao1)
    em.persist(movimentacao2)


    em.transaction.commit()
    em.close()
}

fun testeMovimentacoesPorCategoria() {
    val em = JPAUtils.emFinancas
    em.transaction.begin()

    val categoria = Categoria(1)

    val query = em
            .createQuery("select m from Movimentacao m join m.categoria c where c = :pCategoria")
    query.setParameter("pCategoria", categoria)

    val movimentacoes: List<Movimentacao> = query.resultList as List<Movimentacao>

    for(movimentacao in movimentacoes){
        println("Descricao ..: " + movimentacao.descricao)
        println("Valor ......: R$ " + movimentacao.valor)
    }

    em.transaction.commit()
    em.close()
}


