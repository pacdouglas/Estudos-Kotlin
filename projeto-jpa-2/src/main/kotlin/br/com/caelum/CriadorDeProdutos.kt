package br.com.caelum

import javax.annotation.PostConstruct
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.stereotype.Component
import org.springframework.transaction.TransactionStatus
import org.springframework.transaction.support.TransactionCallbackWithoutResult
import org.springframework.transaction.support.TransactionTemplate

import br.com.caelum.model.Categoria
import br.com.caelum.model.Loja
import br.com.caelum.model.Produto

@Component
class CriadorDeProdutos {

    @PersistenceContext
    private lateinit var em: EntityManager

    @Autowired
    private lateinit var transactionManager: JpaTransactionManager

    @PostConstruct
    fun init() {
        val template = TransactionTemplate(transactionManager)
        template.execute(object : TransactionCallbackWithoutResult() {
            override fun doInTransactionWithoutResult(status: TransactionStatus) {

                val casaDoCodigo = Loja()
                casaDoCodigo.nome = "Casa do Código"

                em.persist(casaDoCodigo)

                val musicalAlegre = Loja()
                musicalAlegre.nome = "Musical Alegre"

                em.persist(musicalAlegre)

                val tecnologia = Categoria(nome ="Tecnologia")
                em.persist(tecnologia)

                val musica = Categoria(nome = "Música")
                em.persist(musica)

                val cursoDeViolao = Produto()
                cursoDeViolao.nome = "Curso de Violão"
                cursoDeViolao.loja = musicalAlegre
                cursoDeViolao.preco = 49.0
                cursoDeViolao.descricao = "Estude com os melhores professores e aprenda no seu ritmo, sem sair de casa."
                //				cursoDeViolao.adicionarCategorias(tecnologia, musica);
                cursoDeViolao.linkDaFoto = "https://pbs.twimg.com/profile_images/378800000825434860/12136ee913ed4f44860914d44650144e.png"

                em.persist(cursoDeViolao)

                val livroDeArquitetura = Produto()
                livroDeArquitetura.nome = "Introduçao a Arquitetura Java e Design de projetos com Java"
                livroDeArquitetura.loja = casaDoCodigo
                livroDeArquitetura.preco = 30.0
                //				livroDeArquitetura.adicionarCategorias(tecnologia);
                livroDeArquitetura.descricao = "Neste livro, os autores, conhecidos especialistas da "+" área, apresentam muitos tópicos que aparecem com frequência"+" na plataforma Java, incluindo desde modelagem e design das "+"classes, até detalhes importantes das tecnologias mais utilizadas."+"  Sempre com uma visão técnica e prática capaz de elucidar muitas "+"questões enfrentadas tanto pelo profissional iniciante quanto por "+"aquele que está adquirindo mais experiência na plataforma."

                livroDeArquitetura.linkDaFoto = "http://www.arquiteturajava.com.br/img/capa-livro.png"

                em.persist(livroDeArquitetura)

                val livroDeSpring = Produto()
                livroDeSpring.nome = "Vire o jogo com Spring Framework"
                livroDeSpring.loja = casaDoCodigo
                livroDeSpring.preco = 30.0
                //				livroDeSpring.adicionarCategorias(tecnologia);
                livroDeSpring.descricao = "Criado para simplificar o desenvolvimento de aplicações Java, "+"o Spring se tornou um dos frameworks de mais destaque dentro desse grande ambiente.  "+"Aprenda muito mais que o básico do Spring, desde o tradicional Container de Inversão "+"de Controle e Injeção de Dependências, passando pelos robustos módulos de segurança, "+"transações, programação orientada a aspectos e também o fantástico módulo MVC, o SpringMVC."

                livroDeSpring.linkDaFoto = "http://cdn.shopify.com/s/files/1/0155/7645/products/spring-framework-featured_large.png?v=1411567960"

                em.persist(livroDeSpring)

                val violao = Produto()
                violao.nome = "Violão"
                violao.loja = musicalAlegre
                violao.descricao = "Excelente violão"
                violao.preco = 500.0
                //				violao.adicionarCategorias(musica);
                violao.linkDaFoto = "http://www.marillac.g12.br/imgs/atividade%20complementar/violao.jpg"

                em.persist(violao)

                val flauta = Produto()
                flauta.nome = "Flauta Doce"
                flauta.loja = musicalAlegre
                flauta.descricao = "Flauta doce"
                flauta.preco = 300.0
                //				flauta.adicionarCategorias(musica);
                flauta.linkDaFoto = "http://i.mlcdn.com.br/1500x1500/flauta-doce-germanicayamaha-yrs-23g-204013000.jpg"

                em.persist(flauta)

            }
        })
    }

}
