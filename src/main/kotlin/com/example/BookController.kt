package com.example

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller
class BookController(
    private val service: BookService
) {
    @Get(value = "/book/nothing")
    fun getNothing(): ResponseModel<Int> = ResponseModel(123456)

    @Get(value = "/book/list")
    fun listAccount(): ResponseModel<List<Book>> = ResponseModel(1, service.listAll())

}