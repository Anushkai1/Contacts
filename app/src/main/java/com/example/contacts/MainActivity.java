package com.example.contacts;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SelectListenr {
    private Button btnNewContact;
    private RecyclerView recyclerView, recyclerView2;

    ArrayList<ContactNew> contacts = new ArrayList<>();
    ArrayList<ContactNew> favContacts = new ArrayList<>();

    DatabaseHelper databaseHelper = new DatabaseHelper(this);
    EditText searchEditText;

    @SuppressLint("MissingInflatedId")
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

        contacts = databaseHelper.getAllContacts();

        if (contacts.isEmpty()) {
            databaseHelper.insertContact(new ContactNew("Alice Johnson", "+94 71 123 4567", "alice@sjp.ac.lk"));
            databaseHelper.insertContact(new ContactNew("Bob Smith", "+94 72 234 5678", "bob@sjp.ac.lk"));
            databaseHelper.insertContact(new ContactNew("Charlie Brown", "+94 73 345 6789", "charlie@sjp.ac.lk"));
            databaseHelper.insertContact(new ContactNew("David White", "+94 74 456 7890", "david@sjp.ac.lk"));
            databaseHelper.insertContact(new ContactNew("Eve Black", "+94 75 567 8901", "eve@sjp.ac.lk"));
            databaseHelper.insertContact(new ContactNew("Frank Green", "+94 76 678 9012", "frank@sjp.ac.lk"));
            databaseHelper.insertContact(new ContactNew("Grace Blue", "+94 77 789 0123", "grace@sjp.ac.lk"));
            databaseHelper.insertContact(new ContactNew("Hank Purple", "+94 78 890 1234", "hank@sjp.ac.lk"));
            databaseHelper.insertContact(new ContactNew("Ivy Red", "+94 79 901 2345", "ivy@sjp.ac.lk"));
            databaseHelper.insertContact(new ContactNew("Jack Orange", "+94 70 012 3456", "jack@sjp.ac.lk"));
        }

        //Setup Recyclerview
        ContactAdapter contactAdapter = new ContactAdapter(this, contacts, this, 1);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setAdapter(contactAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //End Recycle view

        //Setup Recyclerview for Favorite
        favContacts = databaseHelper.getFavoriteContacts();
        contactAdapter = new ContactAdapter(this, favContacts, this, 2);
        recyclerView2 = findViewById(R.id.recyclerview2);
        recyclerView2.setAdapter(contactAdapter);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        //End Recycle view


        // Set up the TextWatcher for the search EditText
        searchEditText = findViewById(R.id.search);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            //Search Function
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String searchText = searchEditText.getText().toString();
                ContactAdapter contactAdapter1;
                if (!searchText.isEmpty()) {
                    contacts = databaseHelper.searchContacts(searchText);
                    contactAdapter1 = new ContactAdapter(MainActivity.this, contacts, MainActivity.this, 1);
                } else {
                    contacts = databaseHelper.getAllContacts();
                    contactAdapter1 = new ContactAdapter(MainActivity.this, contacts, MainActivity.this, 1);
                }
                recyclerView.setAdapter(contactAdapter1);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


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

    @Override
    public void onItemCLicked(int Position) {
        Intent intent = new Intent(MainActivity.this, ViewContact.class);
        intent.putExtra("name", contacts.get(Position).name);
        intent.putExtra("phone", contacts.get(Position).phone);
        intent.putExtra("id", contacts.get(Position).id);
        intent.putExtra("email", contacts.get(Position).email);
        intent.putExtra("isFavorite", contacts.get(Position).isFavorite);
        startActivity(intent);
    }
}