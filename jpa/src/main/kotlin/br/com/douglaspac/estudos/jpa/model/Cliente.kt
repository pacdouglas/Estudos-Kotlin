package br.com.douglaspac.estudos.jpa.model

import javax.persistence.*

@Entity
data class Cliente(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int = 0,
        var nome: String = "",
        var endereco: String = "",
        var profissao: String = "",

        @OneToOne(mappedBy="cliente")
        @JoinColumn(unique=true)
        var conta: Conta? = null
)