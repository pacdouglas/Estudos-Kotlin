package br.com.douglaspac.estudos.jpa.model

import javax.persistence.*

@Entity
data class Conta(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int = 0,
        var titular: String = "",
        var numero: String = "",
        var banco: String = "",
        var agencia: String = "",

        @OneToOne
        @JoinColumn(unique=true)
        var cliente: Cliente? = null,

        @OneToMany(mappedBy = "conta")
        var movimentacoes: List<Movimentacao> = emptyList()
)