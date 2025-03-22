package com.example.pe_tan_dep_trai.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pe_tan_dep_trai.R;
import com.example.pe_tan_dep_trai.adapter.AuthorAdapter;
import com.example.pe_tan_dep_trai.model.Author;
import com.example.pe_tan_dep_trai.repository.AuthorRepository;
import com.example.pe_tan_dep_trai.repository.AuthorService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthorActivity extends AppCompatActivity {
    private ListView lvAuthor;
    private AuthorAdapter authorAdapter;
    private AuthorService authorService;
    private Button btnAddAuthor, btnDeleteAuthor, btnUpdateAuthor;
    private List<Author> authorList;
    private int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manageauthor);

        lvAuthor = findViewById(R.id.lvAuthor);

        btnDeleteAuthor = findViewById(R.id.btnDeleteAuthor); // Khởi tạo btnDeleteAuthor
        authorService = AuthorRepository.getAuthorService();

        getAllAuthors();



        lvAuthor.setOnItemClickListener((parent, view, position, id) -> {
            if (authorList != null && !authorList.isEmpty()) {
                selectedPosition = position; // Cập nhật selectedPosition
                Toast.makeText(AuthorActivity.this, "Selected: " + authorList.get(position).getName(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(AuthorActivity.this, "No authors available", Toast.LENGTH_SHORT).show();
            }
        });
        btnAddAuthor  = findViewById(R.id.btnAddAuthor);
        btnAddAuthor.setOnClickListener(v -> {
            Intent intent = new Intent(AuthorActivity.this, AddAuthorActivity.class);
            startActivity(intent);
        });
        btnUpdateAuthor = findViewById(R.id.btnUpdateAuthor);
        btnUpdateAuthor.setOnClickListener(v -> {
            if (selectedPosition == -1) {
                Toast.makeText(AuthorActivity.this, "Please select an author first", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(AuthorActivity.this, UpdateAuthorActivity.class);
            intent.putExtra("author", authorList.get(selectedPosition)); // Truyền đối tượng tác giả đã chọn
            startActivity(intent);
        });


        btnDeleteAuthor.setOnClickListener(v -> {
            if (selectedPosition == -1) {
                Toast.makeText(this, "Please select an author first", Toast.LENGTH_SHORT).show();
                return;
            }

            String authorId = authorList.get(selectedPosition).getAuthorId();

            authorService.deleteAuthor(authorId).enqueue(new Callback<Void>() {

                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(AuthorActivity.this, "Author deleted successfully", Toast.LENGTH_SHORT).show();
                        selectedPosition = -1;
                        getAllAuthors();
                    } else {
                        Toast.makeText(AuthorActivity.this, "Delete failed", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(AuthorActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        getAllAuthors(); // Gọi lại để làm mới danh sách tác giả
    }
    private void getAllAuthors() {
        authorService.getAllAuthors().enqueue(new Callback<Author[]>() {
            @Override
            public void onResponse(Call<Author[]> call, Response<Author[]> response) {
                if (response.isSuccessful() && response.body() != null) {
                    authorList = Arrays.asList(response.body()); // Khởi tạo authorList
                    Log.d("AuthorActivity", "Authors: " + authorList.toString());
                    authorAdapter = new AuthorAdapter(AuthorActivity.this, authorList);
                    lvAuthor.setAdapter(authorAdapter);
                } else {
                    Log.d("AuthorActivity", "Response not successful or body is null");
                    authorList = new ArrayList<>(); // Khởi tạo một danh sách rỗng
                }
            }

            @Override
            public void onFailure(Call<Author[]> call, Throwable t) {
                Toast.makeText(AuthorActivity.this, "Get all authors failed", Toast.LENGTH_SHORT).show();
                authorList = new ArrayList<>(); // Khởi tạo một danh sách rỗng
            }
        });
    }

}