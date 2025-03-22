package com.example.pe_tan_dep_trai.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pe_tan_dep_trai.R;
import com.example.pe_tan_dep_trai.model.Book;
import com.example.pe_tan_dep_trai.repository.BookRepository;
import com.example.pe_tan_dep_trai.repository.BookService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddBookActivity extends AppCompatActivity {

    private EditText edtTitle, edtPubDate, edtType, edtAuthorId;
    private Button btnSaveBook, btnBackToBookList;
    private BookService bookService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbook);

        edtTitle = findViewById(R.id.edtBookTitle);
        edtPubDate = findViewById(R.id.edtPublicationDate);
        edtType = findViewById(R.id.edtBookType);
        edtAuthorId = findViewById(R.id.edtAuthorId);

        btnSaveBook = findViewById(R.id.btnSaveBook);
        btnBackToBookList = findViewById(R.id.btnBackBookList);

        bookService = BookRepository.getBookService();

        btnSaveBook.setOnClickListener(view -> {
            String title = edtTitle.getText().toString();
            String pubDate = edtPubDate.getText().toString();
            String type = edtType.getText().toString();
            String authorId = edtAuthorId.getText().toString();

            Book book = new Book(title, pubDate, type, authorId);

            bookService.createBook(book).enqueue(new Callback<Book>() {
                @Override
                public void onResponse(Call<Book> call, Response<Book> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(AddBookActivity.this, "Book added", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AddBookActivity.this, "Failed to add book", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Book> call, Throwable t) {
                    Toast.makeText(AddBookActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });

        btnBackToBookList.setOnClickListener(view -> {
            Intent intent = new Intent(AddBookActivity.this, BookActivity.class);
            startActivity(intent);
        });
    }
}
