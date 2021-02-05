package io.micronaut.test.kotest

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.kotest.MicronautKotestExtension.getMock
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import io.mockk.every
import io.mockk.spyk

@MicronautTest
class GreeterTest(@Client("/") val client: HttpClient, private val service: GreeterServiceImpl) : StringSpec({

    "test endpoint" {
        val mock = getMock(service)

        // this works
        client.toBlocking().retrieve("/greet") shouldBe "Hello"


        every { mock getProperty "greet" } returns "World"
        //this test case fails
        client.toBlocking().retrieve("/greet") shouldBe "World"
    }

}) {

    @MockBean(GreeterServiceImpl::class)
    fun mockerino() = spyk<GreeterServiceImpl>()
}


