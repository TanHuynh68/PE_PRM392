package com.example.pe_tan_dep_trai.repository;

import com.example.pe_tan_dep_trai.model.Author;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AuthorService {
    String AUTHORS = "Author";

    @GET(AUTHORS)
    Call<Author[]> getAllAuthors();

    @GET(AUTHORS + "/{id}")
    Call<Author> getAuthorById(@Path("id") Object id);

    @POST(AUTHORS)
    Call<Author> createAuthor(@Body Author author);

    @PUT(AUTHORS + "/{id}")
    Call<Author> updateAuthor(@Path("id") Object id, @Body Author author);

    @DELETE(AUTHORS + "/{id}")
    Call<Void> deleteAuthor(@Path("id") Object id);
}
