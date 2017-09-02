package br.com.douglaspac.estudos.jpa.utils

import br.com.douglaspac.estudos.jpa.model.Categoria
import br.com.douglaspac.estudos.jpa.model.Conta
import br.com.douglaspac.estudos.jpa.model.Movimentacao
import br.com.douglaspac.estudos.jpa.model.TipoMovimentacao
import java.math.BigDecimal
import java.util.*

fun populaMovimentacoesComCategoria() {
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
    val amanha = Calendar.getInstance()
    amanha.add(Calendar.DAY_OF_MONTH, 1)
    movimentacao2.data = amanha
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