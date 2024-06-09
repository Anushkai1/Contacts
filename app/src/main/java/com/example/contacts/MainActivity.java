package com.example.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private Button btnNewContact;
    private RecyclerView recyclerView;
    private final Contact[] contacts = {
            new Contact("Alice Johnson", "+94 71 123 4567", "alice@sjp.ac.lk"),
            new Contact("Bob Smith", "+94 72 234 5678", "bob@sjp.ac.lk"),
            new Contact("Charlie Brown", "+94 73 345 6789", "charlie@sjp.ac.lk"),
            new Contact("David White", "+94 74 456 7890", "david@sjp.ac.lk"),
            new Contact("Eve Black", "+94 75 567 8901", "eve@sjp.ac.lk"),
            new Contact("Frank Green", "+94 76 678 9012", "frank@sjp.ac.lk"),
            new Contact("Grace Blue", "+94 77 789 0123", "grace@sjp.ac.lk"),
            new Contact("Hank Purple", "+94 78 890 1234", "hank@sjp.ac.lk"),
            new Contact("Ivy Red", "+94 79 901 2345", "ivy@sjp.ac.lk"),
            new Contact("Jack Orange", "+94 70 012 3456", "jack@sjp.ac.lk"),
    };

    private final int[] imgs = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.f, R.drawable.g, R.drawable.h, R.drawable.i, R.drawable.j, R.drawable.l};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Recycle view
        ContactAdapter contactAdapter = new ContactAdapter(this, contacts, imgs);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setAdapter(contactAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //End Recycle view

        btnNewContact = findViewById(R.id.btnNewContact);
        btnNewContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Form.class);
                intent.putExtra("formTitle", "New Contact");
                startActivity(intent);
            }
        });
    }

    class Contact {
        public String name;
        public String phone;
        public String email;

        public Contact(String name, String phone, String email) {
            this.name = name;
            this.phone = phone;
            this.email = email;
        }
    }
}