package br.com.douglaspac.estudos.jpa.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Conta(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int = 0,
        var titular: String = "",
        var numero: String = "",
        var banco: String = "",
        var agencia: String) {
    @Suppress("unused")
    constructor() : this(agencia = "")
}