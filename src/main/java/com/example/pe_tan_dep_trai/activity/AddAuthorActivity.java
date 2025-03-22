package com.example.pe_tan_dep_trai.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class AddAuthorActivity extends AppCompatActivity {

    private EditText edtName, edtEmail, edtPhone, edtAddress;
    private Button btnSave, btnBackAuthorList;

    private AuthorService authorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addauthor);

        edtName = findViewById(R.id.edtAuthorName);
        edtEmail = findViewById(R.id.edtAuthorEmail);
        edtPhone = findViewById(R.id.edtAuthorPhone);
        edtAddress = findViewById(R.id.edtAuthorAddress);
        btnSave = findViewById(R.id.btnSaveAuthor);



        authorService = AuthorRepository.getAuthorService();

        btnSave.setOnClickListener(v -> {
            String name = edtName.getText().toString();
            String email = edtEmail.getText().toString();
            String phone = edtPhone.getText().toString();
            String address = edtAddress.getText().toString();

            Author author = new Author(name, email, phone, address);

            authorService.createAuthor(author).enqueue(new Callback<Author>() {
                @Override
                public void onResponse(Call<Author> call, Response<Author> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(AddAuthorActivity.this, "Author added", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AddAuthorActivity.this, "Failed to add", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Author> call, Throwable t) {
                    Toast.makeText(AddAuthorActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });

        btnBackAuthorList = findViewById(R.id.btnBackAuthorList);
        btnBackAuthorList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddAuthorActivity.this, AuthorActivity.class);
                startActivity(intent);
            }
        });
    }
}