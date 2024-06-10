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

public class MainActivity extends AppCompatActivity implements SelectListenr {
    private Button btnNewContact, Kevin, Gwen, Oliver, Natasha, John;
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

    private final int[] imgs = {R.drawable.e, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.f, R.drawable.g, R.drawable.h, R.drawable.i, R.drawable.j, R.drawable.l};
    private final int[] imgs2 = {R.drawable.ne, R.drawable.nb, R.drawable.nc, R.drawable.nd, R.drawable.nf, R.drawable.ng, R.drawable.nh, R.drawable.ni, R.drawable.nj, R.drawable.nl};

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
        ContactAdapter contactAdapter = new ContactAdapter(this, contacts, imgs, this);
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

        //OnClick Fav
        Kevin = findViewById(R.id.favcontact1);
        Kevin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewContact.class);
                intent.putExtra("name", "Kevin");
                intent.putExtra("phone", "+94 70 012 3456");
                intent.putExtra("dp", R.drawable.nb);
                startActivity(intent);
            }
        });

        Gwen = findViewById(R.id.favcontact2);
        Gwen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewContact.class);
                intent.putExtra("name", "Gwen");
                intent.putExtra("phone", "+94 70 012 3456");
                intent.putExtra("dp", R.drawable.nc);
                startActivity(intent);
            }
        });

        Oliver = findViewById(R.id.favcontact3);
        Oliver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewContact.class);
                intent.putExtra("name", "Oliver");
                intent.putExtra("phone", "+94 70 012 3456");
                intent.putExtra("dp", R.drawable.ni);
                startActivity(intent);
            }
        });

        Natasha = findViewById(R.id.favcontact4);
        Natasha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewContact.class);
                intent.putExtra("name", "Natasha");
                intent.putExtra("phone", "+94 70 012 3456");
                intent.putExtra("dp", R.drawable.ng);
                startActivity(intent);
            }
        });

        John = findViewById(R.id.favcontact5);
        John.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewContact.class);
                intent.putExtra("name", "John");
                intent.putExtra("phone", "+94 70 012 3456");
                intent.putExtra("dp", R.drawable.nd);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemCLicked(int Position) {
        Intent intent = new Intent(MainActivity.this, ViewContact.class);
        intent.putExtra("name", contacts[Position].name);
        intent.putExtra("phone", contacts[Position].phone);
        intent.putExtra("dp", imgs2[Position]);
        startActivity(intent);
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