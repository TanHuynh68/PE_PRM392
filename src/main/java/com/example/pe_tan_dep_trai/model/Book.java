package com.example.pe_tan_dep_trai.model;

import java.io.Serializable;

public class Book implements Serializable {
    private String id;
    private String bookTitle;
    private String publicationDate;
    private String type;
    private String authorId;

    public Book(String bookTitle, String publicationDate, String type, String authorId) {
        this.bookTitle = bookTitle;
        this.publicationDate = publicationDate;
        this.type = type;
        this.authorId = authorId;
    }

    public String getBookId() {
        return id;
    }

    public void setBookId(String bookId) {
        this.id = id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }
}
