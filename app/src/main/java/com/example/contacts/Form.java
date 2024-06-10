package com.example.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
                back();
            }
        });

        TextView textView = findViewById(R.id.formTitle);
        textView.setText(getIntent().getStringExtra("formTitle"));

        textView = findViewById(R.id.input_name);
        textView.setText(getIntent().getStringExtra("input_name"));

        textView = findViewById(R.id.input_phone);
        textView.setText(getIntent().getStringExtra("input_phone"));

        imageView = findViewById(R.id.selectUserImage);
        imageView.setImageResource(getIntent().getIntExtra("dp",R.drawable.add_profile_pic));

    }
    private void back(){
        Intent intent;
        TextView textView = findViewById(R.id.formTitle);
        if(textView.getText().toString().equals("New Contact")){

            intent = new Intent(Form.this, MainActivity.class);
            startActivity(intent);

        }else{
            intent = new Intent(Form.this, ViewContact.class);

            textView = findViewById(R.id.input_name);
            intent.putExtra("name", textView.getText().toString());

            textView = findViewById(R.id.input_phone);
            intent.putExtra("phone", textView.getText().toString());

            startActivity(intent);
        }




    }
}