package com.example.dailyquote;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    DBHandler db;
    Button addQuoteButton;
    Button editQuotesButton;
    Button showRandomButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: make the application send a notification every morning..

        db = new DBHandler(MainActivity.this);

        addQuoteButton = findViewById(R.id.add_quote);
        editQuotesButton = findViewById(R.id.edit_quotes);
        showRandomButton = findViewById(R.id.show_random_quote);

        addQuoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AddNewQuote.class));
            }
        });

        editQuotesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Under Construction", Toast.LENGTH_SHORT).show();
            }
        });

        showRandomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RandomQuote.class));
            }
        });
    }
}