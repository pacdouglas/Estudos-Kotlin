package br.com.caelum.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.validation.Valid
import javax.validation.constraints.Min

import org.hibernate.validator.constraints.NotEmpty

@Entity
data class Produto(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int = 0,
        var nome: String = "",
        var linkDaFoto: String = "",
        @Column(columnDefinition = "TEXT")
        var descricao: String? = null,
        var preco: Double = 20.toDouble(),
        @Valid
        @ManyToOne
        var loja: Loja = Loja()
)
