package com.example.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Form extends AppCompatActivity {
    private Button btnBack, btnCancel, btnSave;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_form);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView textView = findViewById(R.id.formTitle);
        textView.setText(getIntent().getStringExtra("formTitle"));

        textView = findViewById(R.id.input_name);
        textView.setText(getIntent().getStringExtra("name"));

        textView = findViewById(R.id.input_phone);
        textView.setText(getIntent().getStringExtra("phone"));

        textView = findViewById(R.id.input_email);
        textView.setText(getIntent().getStringExtra("email"));


        textView = findViewById(R.id.data_name);
        textView.setText(getIntent().getStringExtra("name"));

        textView = findViewById(R.id.data_phone);
        textView.setText(getIntent().getStringExtra("phone"));

        textView = findViewById(R.id.data_email);
        textView.setText(getIntent().getStringExtra("email"));

        textView = findViewById(R.id.data_id);
        textView.setText(getIntent().getStringExtra("id"));

        textView = findViewById(R.id.data_isFavorite);
        textView.setText(getIntent().getStringExtra("isFavorite"));

        imageView = findViewById(R.id.selectUserImage);
        imageView.setImageResource(getIntent().getIntExtra("dp", R.drawable.add_profile_pic));

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });

        btnCancel = findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                TextView textView, name, phone, email;
                DatabaseHelper databaseHelper = new DatabaseHelper(Form.this);

                textView = findViewById(R.id.formTitle);
                name = findViewById(R.id.input_name);
                phone = findViewById(R.id.input_phone);
                email = findViewById(R.id.input_email);

                if (textView.getText().toString().equals("New Contact")) {
                    databaseHelper.insertContact(
                            new ContactNew(
                                    name.getText().toString(),
                                    phone.getText().toString(),
                                    email.getText().toString()
                            )
                    );
                    Toast.makeText(Form.this, "Contact Saved", 3).show();
                    intent = new Intent(Form.this, MainActivity.class);
                } else {
                    textView = findViewById(R.id.data_id);

                    databaseHelper.updateContact(
                            new ContactNew(
                                    textView.getText().toString(),
                                    name.getText().toString(),
                                    phone.getText().toString(),
                                    email.getText().toString(),
                                    "0"
                            )
                    );

                    Toast.makeText(Form.this, "Contact Saved", 3).show();

                    intent = new Intent(Form.this, ViewContact.class);

                    intent.putExtra("name", name.getText().toString());
                    intent.putExtra("phone", phone.getText().toString());
                    intent.putExtra("email", email.getText().toString());

                    intent.putExtra("id", textView.getText().toString());

                    textView = findViewById(R.id.data_isFavorite);
                    intent.putExtra("isFavorite", textView.getText().toString());
                }
                startActivity(intent);
            }
        });

    }

    private void back() {
        Intent intent;
        TextView textView = findViewById(R.id.formTitle);
        if (textView.getText().toString().equals("New Contact")) {

            intent = new Intent(Form.this, MainActivity.class);
            startActivity(intent);

        } else {
            intent = new Intent(Form.this, ViewContact.class);

            textView = findViewById(R.id.data_name);
            intent.putExtra("name", textView.getText().toString());

            textView = findViewById(R.id.data_phone);
            intent.putExtra("phone", textView.getText().toString());

            textView = findViewById(R.id.data_email);
            intent.putExtra("email", textView.getText().toString());

            textView = findViewById(R.id.data_id);
            intent.putExtra("id", textView.getText().toString());

            textView = findViewById(R.id.data_isFavorite);
            intent.putExtra("isFavorite", textView.getText().toString());

            startActivity(intent);
        }


    }
}