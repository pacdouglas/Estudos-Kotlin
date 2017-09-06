package br.com.caelum.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Loja(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int = 0,
        var nome: String = ""
)
