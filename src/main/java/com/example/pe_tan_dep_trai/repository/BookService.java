package com.example.pe_tan_dep_trai.repository;

import com.example.pe_tan_dep_trai.model.Book;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface BookService {
    String BOOKS = "Book";

    @GET(BOOKS)
    Call<Book[]> getAllBooks();

    @GET(BOOKS + "/{id}")
    Call<Book> getBookById(@Path("id") Object id);

    @POST(BOOKS)
    Call<Book> createBook(@Body Book book);

    @PUT(BOOKS + "/{id}")
    Call<Book> updateBook(@Path("id") Object id, @Body Book book);

    @DELETE(BOOKS + "/{id}")
    Call<Void> deleteBook(@Path("id") Object id);
}
