package com.example.pe_tan_dep_trai.repository;

public class AuthorRepository {
    public static AuthorService getAuthorService(){
        return APIClient.getClient().create(AuthorService.class);
    }
}