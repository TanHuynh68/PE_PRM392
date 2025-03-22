package com.example.pe_tan_dep_trai.repository;

public class BookRepository {
    public static BookService getBookService(){
        return APIClient.getClient().create(BookService.class);
    }
}
