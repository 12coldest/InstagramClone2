package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSignUpLoginActivity, btnLoginLoginActivity;
    private EditText edtLoginEmail, edtLoginPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Log In");

        btnSignUpLoginActivity = findViewById(R.id.btnSignUpLoginActivity);
        btnLoginLoginActivity = findViewById(R.id.btnLoginLoginActivity);

        edtLoginEmail = findViewById(R.id.edtLoginEmail);
        edtLoginPassword = findViewById(R.id.edtLoginPassword);

        btnLoginLoginActivity.setOnClickListener(this);
        btnSignUpLoginActivity.setOnClickListener(this);

        edtLoginPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction()==KeyEvent.ACTION_DOWN){
                    onClick(btnSignUpLoginActivity);
                }
                return false;
            }
        });

        if (ParseUser.getCurrentUser()!= null){

            ParseUser.getCurrentUser().logOut();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLoginLoginActivity:
                if (edtLoginEmail.getText().toString().equals("") ||
                        edtLoginPassword.getText().toString().equals("")){
                    FancyToast.makeText(LoginActivity.this,
                            "Email, Username, Password is required",
                            Toast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                } else {

                    ParseUser.logInInBackground(edtLoginEmail.getText().toString(),
                            edtLoginPassword.getText().toString(), new LogInCallback() {
                                @Override
                                public void done(ParseUser user, ParseException e) {

                                    if (user != null && e == null) {
                                        FancyToast.makeText(LoginActivity.this,
                                                user.getUsername() + " is logged in",
                                                FancyToast.LENGTH_LONG, FancyToast.SUCCESS,
                                                true).show();
                                        transitionToSocialMediaActivity();
                                    }
                                }
                            });
                }

            break;
            case R.id.btnSignUpLoginActivity:
                Intent intent = new Intent (LoginActivity.this, SignUp.class);
                startActivity(intent);
            break;
    }
    }

    public void rootLoginLayoutTapped(View view){
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch(Exception e) {
            e.printStackTrace();
        }

    }
    private void transitionToSocialMediaActivity() {

        Intent intent = new Intent(LoginActivity.this, SocialMediaActivity.class);
        startActivity(intent);
        finish();
    }
}
