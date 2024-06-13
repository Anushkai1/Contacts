package com.example.contacts;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

public class ViewContact extends AppCompatActivity {

    private Button btnBack, btnFav, btnEdit, btnDelete;
    private TextView textView;
    private ImageView imageView;

    Drawable drawable;
   int[] imgs = {R.drawable.ne, R.drawable.nb, R.drawable.nc, R.drawable.nd, R.drawable.nf, R.drawable.ng, R.drawable.nh, R.drawable.ni, R.drawable.nj, R.drawable.nl};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_contact);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textView = findViewById(R.id.nameText);
        textView.setText(getIntent().getStringExtra("name"));

        textView = findViewById(R.id.phoneText);
        textView.setText(getIntent().getStringExtra("phone"));

        imageView = findViewById(R.id.viewProfilePic);
        imageView.setImageResource(getProfilePic((getIntent().getStringExtra("id"))));

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });

        btnFav = findViewById(R.id.btnFav);
        btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawable = ContextCompat.getDrawable(ViewContact.this, R.drawable.heart2);
                btnFav.setCompoundDrawablesWithIntrinsicBounds(null, null, null, drawable);
            }
        });

        btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });

        btnEdit = findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ViewContact.this, Form.class);
                intent.putExtra("formTitle", "Edit Contact");
                textView = findViewById(R.id.nameText);
                intent.putExtra("input_name", textView.getText().toString());
                int imageId = getProfilePic(textView.getText().toString());
                intent.putExtra("dp",imageId);
                textView = findViewById(R.id.phoneText);
                intent.putExtra("input_phone", textView.getText().toString());

                startActivity(intent);
            }
        });

    }

    private void back() {
        Intent intent = new Intent(ViewContact.this, MainActivity.class);
        startActivity(intent);
    }

    public int getProfilePic(String id) {
        int idLastNum = Integer.parseInt(id.substring(id.length() - 1));
        return imgs[idLastNum];
    }
}