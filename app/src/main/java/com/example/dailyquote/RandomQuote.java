package com.example.dailyquote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RandomQuote extends AppCompatActivity {

    DBHandler db;
    TextView quote;
    Button roll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_quote);

        db = new DBHandler(RandomQuote.this);

        quote = findViewById(R.id.quote);
        roll = findViewById(R.id.roll);

        quote.setText(
            db.GetRandomQuote().get("quote")
        );


        roll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        quote.setText(
                                db.GetRandomQuote().get("quote")
                        );
                    }
                }
        );
    }
}