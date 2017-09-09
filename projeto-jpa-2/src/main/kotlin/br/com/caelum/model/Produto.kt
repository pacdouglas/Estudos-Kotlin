package br.com.caelum.model

import javax.persistence.*
import javax.validation.Valid

@Entity
data class Produto(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int? = null,
        var nome: String = "",
        var linkDaFoto: String = "",
        @Column(columnDefinition = "TEXT")
        var descricao: String? = null,
        var preco: Double = 20.toDouble(),
        @Valid
        @ManyToOne
        var loja: Loja = Loja(),
        @ManyToMany
        var categorias: List<Categoria> = emptyList()
)
