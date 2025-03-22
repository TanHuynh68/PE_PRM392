package com.example.pe_tan_dep_trai.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.pe_tan_dep_trai.R;
import com.example.pe_tan_dep_trai.adapter.BookAdapter;
import com.example.pe_tan_dep_trai.model.Book;
import com.example.pe_tan_dep_trai.repository.BookRepository;
import com.example.pe_tan_dep_trai.repository.BookService;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookActivity extends AppCompatActivity {
    private ListView lvBook;
    private BookAdapter bookAdapter;
    private BookService bookService;
    private Button btnAddBook, btnUpdateBook, btnDeleteBook;
    private List<Book> bookList;
    private int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managebook);
        lvBook = findViewById(R.id.lvBook);

        bookService = BookRepository.getBookService();
        getAllBooks();
        lvBook.setOnItemClickListener((parent, view, position, id) -> {
            selectedPosition = position;
            Toast.makeText(BookActivity.this, "Selected: " + bookList.get(position).getBookTitle(), Toast.LENGTH_SHORT).show();
        });
        btnAddBook = findViewById(R.id.btnAddBook);
        btnAddBook.setOnClickListener(v -> {
            Intent intent = new Intent(BookActivity.this, AddBookActivity.class);
            startActivity(intent);
        });

        btnUpdateBook = findViewById(R.id.btnUpdateBook);
        btnUpdateBook.setOnClickListener(v -> {
            if (selectedPosition == -1) {
                Toast.makeText(BookActivity.this, "Please select a book first", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(BookActivity.this, UpdateBookActivity.class);
            intent.putExtra("book", bookList.get(selectedPosition));
            startActivity(intent);
        });

        btnDeleteBook = findViewById(R.id.btnDeleteBook);
        btnDeleteBook.setOnClickListener(v -> {
            if (selectedPosition == -1) {
                Toast.makeText(BookActivity.this, "Please select a book first", Toast.LENGTH_SHORT).show();
                return;
            }

            String bookId = bookList.get(selectedPosition).getBookId();

            bookService.deleteBook(bookId).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(BookActivity.this, "Book deleted successfully", Toast.LENGTH_SHORT).show();
                        selectedPosition = -1;
                        getAllBooks();
                    } else {
                        Toast.makeText(BookActivity.this, "Delete failed", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(BookActivity.this, "Delete failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });

    }

    private void getAllBooks() {
        bookService.getAllBooks().enqueue(new Callback<Book[]>() {
            @Override
            public void onResponse(Call<Book[]> call, Response<Book[]> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Book> books = Arrays.asList(response.body());
                    bookAdapter = new BookAdapter(BookActivity.this, books);
                    lvBook.setAdapter(bookAdapter);
                    bookList = Arrays.asList(response.body());
                }
            }

            @Override
            public void onFailure(Call<Book[]> call, Throwable t) {
                Toast.makeText(BookActivity.this, "Get all book fails", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
