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
//        imageView.setImageResource(getIntent().getIntExtra("dp", 10));
        imageView.setImageResource(getDP(getIntent().getStringExtra("name")));

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
                int imageId = getDP(textView.getText().toString());
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

    public int getDP(String name) {
        int rid = R.drawable.add_profile_pic;
        if (Objects.equals(name, "Alice Johnson")) {
            rid = R.drawable.ne;
        } else if (Objects.equals(name, "Bob Smith")) {
            rid = R.drawable.nb;
        } else if (Objects.equals(name, "Charlie Brown")) {
            rid = R.drawable.nc;
        } else if (Objects.equals(name, "David White")) {
            rid = R.drawable.nd;
        } else if (Objects.equals(name, "Eve Black")) {
            rid = R.drawable.nf;
        } else if (Objects.equals(name, "Frank Green")) {
            rid = R.drawable.ng;
        } else if (Objects.equals(name, "Grace Blue")) {
            rid = R.drawable.nh;
        } else if (Objects.equals(name, "Hank Purple")) {
            rid = R.drawable.ni;
        } else if (Objects.equals(name, "Ivy Red")) {
            rid = R.drawable.nj;
        } else if (Objects.equals(name, "Jack Orange")) {
            rid = R.drawable.nl;
        } else if (Objects.equals(name, "Kevin")) {
            rid = R.drawable.nb;
        } else if (Objects.equals(name, "Gwen")) {
            rid = R.drawable.nc;
        } else if (Objects.equals(name, "Oliver")) {
            rid = R.drawable.ni;
        } else if (Objects.equals(name, "Natasha")) {
            rid = R.drawable.ng;
        } else if (Objects.equals(name, "John")) {
            rid = R.drawable.nd;
        }

        return rid;
    }
}