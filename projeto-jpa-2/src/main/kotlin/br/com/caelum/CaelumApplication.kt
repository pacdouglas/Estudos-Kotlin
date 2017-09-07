package br.com.caelum

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class CaelumApplication

fun main(args: Array<String>) {
    SpringApplication.run(CaelumApplication::class.java, *args)
}
