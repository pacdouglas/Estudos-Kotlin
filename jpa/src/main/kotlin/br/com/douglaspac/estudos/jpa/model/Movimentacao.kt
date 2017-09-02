package br.com.douglaspac.estudos.jpa.model

import java.math.BigDecimal
import java.util.*
import javax.persistence.*

@Entity
data class Movimentacao(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int = 0,

        var valor: BigDecimal = BigDecimal(0),

        @Enumerated(EnumType.STRING)
        var tipo: TipoMovimentacao = TipoMovimentacao.SAIDA,

        @Temporal(TemporalType.DATE)
        var data: Calendar = Calendar.getInstance(),

        var descricao: String = "",

        @ManyToOne
        var conta: Conta = Conta(),

        @ManyToMany
        var categoria: List<Categoria> = emptyList()
)
