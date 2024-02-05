package com.example.kurmato_pr;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.example.kurmato_pr.utils.SmsBroadcastReceiver;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class otpActivity extends AppCompatActivity {
    private static final int REQ_USER_CONSENT = 200;
    SmsBroadcastReceiver smsBroadcastReceiver;
    private String mVerificationId;
    private PinView pinView;
    private FirebaseAuth mAuth;

    private String getmVerificationId;

    Button button;
    String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        mAuth = FirebaseAuth.getInstance();
        pinView = findViewById(R.id.pinview);

        button = findViewById(R.id.show_otp);
        mAuth = FirebaseAuth.getInstance();

        Intent intent = getIntent();
        phoneNumber = intent.getStringExtra("mobile");
        sendVerificationCode("+91" +phoneNumber);

        findViewById(R.id.show_otp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = pinView.getText().toString().trim();
                if (code.isEmpty() || code.length() < 6) {
                    pinView.setError("Enter valid code");
                    pinView.requestFocus();
                    return;
                }
                verifyVerificationCode(code);
            }
        });
        startSmartUserConsent();
    }

    private void startSmartUserConsent() {
        SmsRetrieverClient client = SmsRetriever.getClient(this);
        client.startSmsUserConsent(null);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_USER_CONSENT){

            if ((resultCode == RESULT_OK) && (data != null)){

                String message = data.getStringExtra(SmsRetriever.EXTRA_SMS_MESSAGE);
                getOtpFromMessage(message);


            }


        }

    }

    private void getOtpFromMessage(String message) {
        Pattern otpPattern = Pattern.compile("(|^)\\d{6}");
        Matcher matcher = otpPattern.matcher(message);
        if (matcher.find()){

            pinView.setText(matcher.group(0));
//            Intent i = new Intent(otpActivity.this,HomePage.class);
//            startActivity(i);

        }
    }
    private void registerBroadcastReceiver(){

        smsBroadcastReceiver = new SmsBroadcastReceiver();

        smsBroadcastReceiver.smsBroadcastReceiverListener = new SmsBroadcastReceiver.SmsBroadcastReceiverListener() {
            @Override
            public void onSuccess(Intent intent) {

                startActivityForResult(intent,REQ_USER_CONSENT);

            }

            @Override
            public void onFailure() {

            }
        };

        IntentFilter intentFilter = new IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION);
        registerReceiver(smsBroadcastReceiver,intentFilter);

    }
    @Override
    protected void onStart() {
        super.onStart();
        registerBroadcastReceiver();

    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(smsBroadcastReceiver);
    }


    private void sendVerificationCode(String mobile) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                mobile,
                60,
                TimeUnit.SECONDS,
                this,
                mCallbacks);
    }

    // the callback to detect the verification status
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if (code != null) {
                // Corrected the line to set the code to pinView
                pinView.setText(code);
                // verifying the code
                verifyVerificationCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(otpActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            // storing the verification id that is sent to the user
            mVerificationId = s;
        }
    };

    private void verifyVerificationCode(String code) {
        // creating the credential
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);
        // signing the user
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(otpActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // verification successful, start the profile activity
                            Intent intent = new Intent(otpActivity.this, HomePage.class);
                            intent.putExtra("mobile", phoneNumber);
                            startActivity(intent);
                            finish();
                        } else {
                                // verification unsuccessful, display an error message
                            String message = "Something is wrong, we will fix it soon...";

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                message = "Invalid code entered...";
                            }

                            // Display the error message
                            Toast.makeText(otpActivity.this, message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
