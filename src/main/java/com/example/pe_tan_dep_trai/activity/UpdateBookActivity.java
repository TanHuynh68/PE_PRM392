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

public class UpdateBookActivity extends AppCompatActivity {

    private EditText editTextTitle, editTextDate, editTextType, editTextAuthorId;
    private Button buttonUpdate, btnBack;
    private BookService bookService;
    private Book currentBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatebook);

        editTextTitle = findViewById(R.id.editTextTitle);
        editTextDate = findViewById(R.id.editTextDate);
        editTextType = findViewById(R.id.editTextType);
        editTextAuthorId = findViewById(R.id.editTextAuthorId);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        bookService = BookRepository.getBookService();

        currentBook = (Book) getIntent().getSerializableExtra("book");

        if (currentBook != null) {
            editTextTitle.setText(currentBook.getBookTitle());
            editTextDate.setText(currentBook.getPublicationDate());
            editTextType.setText(currentBook.getType());
            editTextAuthorId.setText(String.valueOf(currentBook.getAuthorId()));
        }

        buttonUpdate.setOnClickListener(v -> {
            String title = editTextTitle.getText().toString();
            String date = editTextDate.getText().toString();
            String type = editTextType.getText().toString();
            String authorId = editTextAuthorId.getText().toString();

            Book updatedBook = new Book(title, date, type, authorId);
            updateBook(String.valueOf(currentBook.getBookId()), updatedBook);
        });

        btnBack = findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(UpdateBookActivity.this, BookActivity.class);
            startActivity(intent);
        });

    }

    private void updateBook(String id ,Book book) {
        bookService.updateBook(id, book).enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(UpdateBookActivity.this, "Update book oke", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                Toast.makeText(UpdateBookActivity.this, "Update book fail", Toast.LENGTH_SHORT).show();
            }
        });
    }
}