package com.example.kurmato_pr;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class NumberScreen extends AppCompatActivity {
    private TextInputEditText textInputEditText;
    private TextInputLayout textInputLayout;
    private String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_screen);

        // Check if USB debugging is enabled and show the warning dialog if needed
//        if (isUsbDebuggingEnabled()) {
//            showUsbDebuggingWarningDialog();
//        }

        textInputLayout = findViewById(R.id.numberLayout);
        textInputEditText = textInputLayout.findViewById(R.id.number);

        MaterialButton materialButton = findViewById(R.id.sendOTP);
        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the phone number as a string
                phoneNumber = textInputEditText.getText().toString();

                // Check if the phone number is valid
                if (validatePhoneNumber()) {
                    // Perform phone number verification logic here

                    // Proceed with the Intent only if the phone number is valid
                    Intent intent = new Intent(NumberScreen.this, otpActivity.class);

                    // Put data into the Intent
                    intent.putExtra("mobile", phoneNumber);
                    startActivity(intent);
                } else {
                    // Show an error message if the phone number is not valid
                    textInputLayout.setError("Invalid phone number");
                }
            }
        });
    }

    private boolean validatePhoneNumber() {
        // Define a regular expression for a valid phone number
        String phonePattern = "^[+]?[0-9]{10,13}$";

        if (phoneNumber.isEmpty()) {
            textInputLayout.setError("Enter phone number");
            return false;
        } else if (!Pattern.matches(phonePattern, phoneNumber)) {
            textInputLayout.setError("Invalid phone number");
            return false;
        } else {
            textInputLayout.setError(null);
            return true;
        }
    }

//    private void showUsbDebuggingWarningDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Warning: USB Debugging Enabled")
//                .setMessage("USB debugging is enabled. This may expose your device to security risks. Disable USB debugging when not needed.")
//                .setPositiveButton("Yes", (dialog, which) -> {
//                    // Handle Yes button click if needed
//                    // You can perform additional actions here if the user clicks Yes
//                    dialog.dismiss();
//                    openDeveloperOptionsSettings(); //// Dismiss the dialog
//                })
//                .setNegativeButton("No", (dialog, which) -> {
//                    // Handle No button click if needed
//                    // You can perform additional actions here if the user clicks No
//                    dialog.dismiss();
//                    finish();// Dismiss the dialog
//                })
//                .setCancelable(false)  // Optional: Set to false if you want to force the user to click "Yes" or "No"
//                .show();
//    }
//
//
//    private boolean isUsbDebuggingEnabled() {
//        return Settings.Secure.getInt(getContentResolver(), Settings.Secure.ADB_ENABLED, 0) == 1;
//    }
//    private void openDeveloperOptionsSettings() {
////        Intent intent = new Intent(Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS);
////        startActivity(intent);
//    }

    @Override
    public void onBackPressed() {

        showBackPressWarningDialog();
    }

    private void showBackPressWarningDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Warning")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Handle "Yes" button click
                        finish(); // Close the activity
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Handle "No" button click
                        dialog.dismiss(); // Dismiss the dialog
                    }
                })
                .show();
    }
}
