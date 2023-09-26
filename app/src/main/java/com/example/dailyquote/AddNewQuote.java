package com.example.dailyquote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewQuote extends AppCompatActivity {

    EditText quoteInput;
    EditText authorInput;
    Button submitButton;

    DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_quote);

        db = new DBHandler(AddNewQuote.this);

        quoteInput = findViewById(R.id.edQuote);
        authorInput = findViewById(R.id.edAuthor);
        submitButton = findViewById(R.id.btnSubmit);
        
        submitButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            db.insertQuote(
                                    quoteInput.getText().toString(),
                                    authorInput.getText().toString()
                            );
                            Toast.makeText(AddNewQuote.this, "Quote saved successfully", Toast.LENGTH_SHORT).show();
                        } catch ( Exception e ) {
                            Toast.makeText(AddNewQuote.this, "Something went wrong, error message: " + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }
}