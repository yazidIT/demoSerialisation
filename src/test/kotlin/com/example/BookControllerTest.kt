package com.example

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.kotest.MicronautKotestExtension.getMock
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.jupiter.api.Assertions

@MicronautTest
class BookControllerTest(

    @Client("/")
    val client: HttpClient,

    val service: BookService,

) : FunSpec({

    test("GET /book/nothing should work") {

        getMock(service)
        // given

        val request = HttpRequest.GET<Any>("/book/nothing")

        // when

        val httpresponse = client.toBlocking().exchange(request, ResponseModel::class.java)

        // then
        Assertions.assertEquals(HttpStatus.OK, httpresponse.status)
        Assertions.assertEquals(1, httpresponse.body.get().code)
    }

    test("GET /book/list returning empty list should work") {

        getMock(service)
        // given

        val request = HttpRequest.GET<Any>("/book/list")

        // when
        coEvery { service.listAll() } returns emptyList()

        val httpresponse = client.toBlocking().exchange(request, ResponseModel::class.java)

        // then
        Assertions.assertEquals(HttpStatus.OK, httpresponse.status)
        Assertions.assertEquals(1, httpresponse.body.get().code)
    }

    test("GET /book/list returning non-empty list should work") {

        getMock(service)
        // given
        val book1 = Book(title = "Book Title", author = "Jane Doe")

        val request = HttpRequest.GET<Any>("/book/list")

        // when
        coEvery { service.listAll() } returns listOf(book1)

        val httpresponse = client.toBlocking().exchange(request, ResponseModel::class.java)

        // then
        Assertions.assertEquals(HttpStatus.OK, httpresponse.status)
        Assertions.assertEquals(1, httpresponse.body.get().code)
    }

}) {
    @MockBean(BookService::class)
    fun bookService(): BookService = mockk()
}
