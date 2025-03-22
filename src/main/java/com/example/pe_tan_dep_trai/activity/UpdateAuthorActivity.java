package com.example.pe_tan_dep_trai.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pe_tan_dep_trai.R;
import com.example.pe_tan_dep_trai.model.Author;
import com.example.pe_tan_dep_trai.repository.AuthorRepository;
import com.example.pe_tan_dep_trai.repository.AuthorService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateAuthorActivity extends AppCompatActivity {

    private EditText editTextName, editTextEmail, editTextPhone, editTextAddress;
    private Button buttonUpdateAuthor, buttonBack;
    private AuthorService authorService;
    private Author currentAuthor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateauthor);

        editTextName = findViewById(R.id.editTextAuthorName);
        editTextEmail = findViewById(R.id.editTextAuthorEmail);
        editTextPhone = findViewById(R.id.editTextAuthorPhone);
        editTextAddress = findViewById(R.id.editTextAuthorAddress);
        buttonUpdateAuthor = findViewById(R.id.buttonUpdateAuthor);
        buttonBack = findViewById(R.id.buttonBack);
        authorService = AuthorRepository.getAuthorService();

        // Nhận thông tin tác giả từ Intent
        currentAuthor = (Author) getIntent().getSerializableExtra("author");

        if (currentAuthor != null) {
            editTextName.setText(currentAuthor.getName());
            editTextEmail.setText(currentAuthor.getEmail());
            editTextPhone.setText(currentAuthor.getPhone());
            editTextAddress.setText(currentAuthor.getAddress());
        }

        buttonUpdateAuthor.setOnClickListener(v -> {
            String name = editTextName.getText().toString();
            String email = editTextEmail.getText().toString();
            String phone = editTextPhone.getText().toString();
            String address = editTextAddress.getText().toString();

            // Cập nhật thông tin tác giả
            Author updatedAuthor = new Author(name, email, phone, address);

            updateAuthor(currentAuthor.getAuthorId(), updatedAuthor);
        });

        buttonBack.setOnClickListener(v -> {
            Intent intent = new Intent(UpdateAuthorActivity.this, AuthorActivity.class);
            startActivity(intent);
        });
    }

    private void updateAuthor(String id, Author author) {
        authorService.updateAuthor(id, author).enqueue(new Callback<Author>() {
            @Override
            public void onResponse(Call<Author> call, Response<Author> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(UpdateAuthorActivity.this, "Update author successful", Toast.LENGTH_SHORT).show();
                    finish(); // Quay lại AuthorActivity
                } else {
                    Log.d("UpdateAuthor", "Update failed: " + response.code() + " " + response.message());
                    Toast.makeText(UpdateAuthorActivity.this, "Update author failed: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Author> call, Throwable t) {
                Log.e("UpdateAuthor", "Error: " + t.getMessage());
                Toast.makeText(UpdateAuthorActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}