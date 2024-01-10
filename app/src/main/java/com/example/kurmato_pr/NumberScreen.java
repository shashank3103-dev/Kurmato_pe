package com.example.kurmato_pr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import androidx.appcompat.app.AppCompatActivity;

public class NumberScreen extends AppCompatActivity {
    private TextInputEditText textInputEditText;
    private TextInputLayout textInputLayout;
    private String string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_screen);

        textInputLayout = findViewById(R.id.numberLayout);
        textInputEditText = textInputLayout.findViewById(R.id.number);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                string = textInputEditText.getText().toString();

                Intent i = new Intent(NumberScreen.this, otpActivity.class);
                i.putExtra("mobile", string);

                startActivity(i);
            }
        });
    }
}
