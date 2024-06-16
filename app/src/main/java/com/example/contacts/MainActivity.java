package com.example.contacts;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SelectListenr {
    private Button btnNewContact, clearSearch;
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

        int maxId = databaseHelper.getMaxId();
        if (maxId == 1) {
            databaseHelper.insertContact(new ContactNew("Stephen Amell", "+94 71 123 4567", "st Amell@arrow.com"));
            databaseHelper.insertContact(new ContactNew("Emma Stone", "+94 73 345 6789", "emmaStone@lalaland.com"));
            databaseHelper.insertContact(new ContactNew("1", "Tom Cruise", "+94 72 234 5678", "tomCruise@mission.com", "1"));
            databaseHelper.insertContact(new ContactNew("Roy Harper", "+94 74 456 7890", "royHarper@arrow.com"));
            databaseHelper.insertContact(new ContactNew("1", "Natasha Romanoff", "+94 76 678 9012", "natasha@shield.com", "1"));
            databaseHelper.insertContact(new ContactNew("Megan Fox", "+94 77 789 0123", "meganFox@trans.com"));
            databaseHelper.insertContact(new ContactNew("John Diggle", "+94 75 567 8901", "johnDiggle@arrow.com"));
            databaseHelper.insertContact(new ContactNew("Milar Blunt", "+94 78 890 1234", "emilyBlunt@edge.com"));
            databaseHelper.insertContact(new ContactNew("Hermione Granger", "+94 70 012 3456", "hermione@hogwarts.com"));
            databaseHelper.insertContact(new ContactNew("1", "Jason Statham", "+94 79 901 2345", "jasonStatham@tran.com", "1"));
        }

        contacts.clear();
        contacts = databaseHelper.getAllContacts();

        //Setup Recyclerview
        ContactAdapter contactAdapter = new ContactAdapter(this, contacts, this, 1);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setAdapter(contactAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //End Recycle view

        //Setup Recyclerview for Favorite
        favContacts.clear();
        favContacts = databaseHelper.getFavoriteContacts();
        contactAdapter = new ContactAdapter(this, favContacts, this, 2);
        recyclerView2 = findViewById(R.id.recyclerview2);
        recyclerView2.setAdapter(contactAdapter);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        //End Recycle view

        toggleFavSection(1);

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
                ContactAdapter contactAdapter1, contactAdapter2;
                if (!searchText.isEmpty()) {
                    contacts = databaseHelper.searchContacts(searchText);
                    contactAdapter1 = new ContactAdapter(MainActivity.this, contacts, MainActivity.this, 1);

                    toggleFavSection(0);
                    toggleVisibleBtnClearSearch(1);
                } else {
                    contacts = databaseHelper.getAllContacts();
                    contactAdapter1 = new ContactAdapter(MainActivity.this, contacts, MainActivity.this, 1);

                    contactAdapter2 = new ContactAdapter(MainActivity.this, favContacts, MainActivity.this, 2);
                    recyclerView2.setAdapter(contactAdapter2);
                    recyclerView2.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));

                    toggleFavSection(1);
                    toggleVisibleBtnClearSearch(0);
                }
                recyclerView.setAdapter(contactAdapter1);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        clearSearch = findViewById(R.id.clearSearch);
        clearSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchEditText.setText("");
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
    public void onItemCLicked(int Position, int isFav) {
        Intent intent = new Intent(MainActivity.this, ViewContact.class);
        if (isFav == 1) {
            intent.putExtra("name", contacts.get(Position).name);
            intent.putExtra("phone", contacts.get(Position).phone);
            intent.putExtra("id", contacts.get(Position).id);
            intent.putExtra("email", contacts.get(Position).email);
            intent.putExtra("isFavorite", contacts.get(Position).isFavorite);
        } else {
            intent.putExtra("name", favContacts.get(Position).name);
            intent.putExtra("phone", favContacts.get(Position).phone);
            intent.putExtra("id", favContacts.get(Position).id);
            intent.putExtra("email", favContacts.get(Position).email);
            intent.putExtra("isFavorite", favContacts.get(Position).isFavorite);
        }
        startActivity(intent);
    }

    public void toggleFavSection(int state) {
        ViewGroup.LayoutParams paramsRec, paramsTitle;
        ViewGroup.MarginLayoutParams mParamsR, mParamsT;

        TextView favTitle, allTitle;
        favTitle = findViewById(R.id.favTitle);
        allTitle = findViewById(R.id.allc);

        paramsRec = recyclerView2.getLayoutParams();
        paramsTitle = favTitle.getLayoutParams();

        mParamsT = (ViewGroup.MarginLayoutParams) favTitle.getLayoutParams();
        mParamsR = (ViewGroup.MarginLayoutParams) recyclerView2.getLayoutParams();

        paramsTitle.height = paramsRec.height = 0;
        mParamsT.topMargin = mParamsR.topMargin = 0;

        if (state == 1 && !favContacts.isEmpty()) {
            paramsTitle.height = Utils.dpToPx(this, 14);
            mParamsT.topMargin = Utils.dpToPx(this, 8);

            paramsRec.height = Utils.dpToPx(this, 78);
            mParamsR.topMargin = Utils.dpToPx(this, 8);
        }

        recyclerView2.setLayoutParams(paramsRec);
        favTitle.setLayoutParams(paramsTitle);
        allTitle.setLayoutParams(paramsTitle);

        recyclerView2.setLayoutParams(mParamsR);
        favTitle.setLayoutParams(mParamsT);
        allTitle.setLayoutParams(mParamsT);
    }

    public void toggleVisibleBtnClearSearch(int state) {
        ViewGroup.MarginLayoutParams params;
        params = (ViewGroup.MarginLayoutParams) clearSearch.getLayoutParams();
        params.rightMargin = Utils.dpToPx(this, -74);

        if (state == 1) {
            params.rightMargin = Utils.dpToPx(this, 74);
        }

        clearSearch.setLayoutParams(params);
    }
}