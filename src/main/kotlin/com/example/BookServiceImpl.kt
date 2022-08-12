package com.example

import jakarta.inject.Singleton

@Singleton
class BookServiceImpl : BookService {
    override fun listAll(): List<Book> {

        val booksample = Book(title = "Jungle Forest", author = "John Doe")
        return listOf(booksample)
    }
}