package com.example.kurmato_pr;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class NumberScreen extends AppCompatActivity {
    private TextInputEditText textInputEditText;
    private TextInputLayout textInputLayout;
    private String string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_screen);

        // Check if USB debugging is enabled and show the warning dialog if needed
        if (isUsbDebuggingEnabled()) {
            showUsbDebuggingWarningDialog();
        }

        textInputLayout = findViewById(R.id.numberLayout);
        textInputEditText = textInputLayout.findViewById(R.id.number);

        MaterialButton materialButton = findViewById(R.id.sendOTP);
        materialButton.setOnClickListener(new View.OnClickListener() {
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
    private void showUsbDebuggingWarningDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Warning: USB Debugging Enabled")
                .setMessage("USB debugging is enabled. This may expose your device to security risks. Disable USB debugging when not needed.")
                .setPositiveButton("OK", (dialog, which) -> {
                    // Handle OK button click if needed
                    dialog.dismiss();
                })
                .setCancelable(false)  // Optional: Set to false if you want to force the user to click "OK"
                .show();
    }
    private boolean isUsbDebuggingEnabled() {
        return Settings.Secure.getInt(getContentResolver(), Settings.Secure.ADB_ENABLED, 0) == 1;
    }


}
