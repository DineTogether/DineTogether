package com.example.mtrsliit.dine;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;





public class MainActivity extends AppCompatActivity {
    private static final String TAG = "login";
    private static final int REQUEST_SIGNUP = 0;

    private Button btnlogin;
    private EditText etemail, etpassword;
    private TextView register,forgot;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

        btnlogin = (Button) findViewById(R.id.button_login);
        //btnGetall = (Button) findViewById(R.id.btnget);
        etemail = (EditText) findViewById(R.id.edittext_email);
        etpassword = (EditText) findViewById(R.id.edittext_pasword);
        register = (TextView) findViewById(R.id.register);
        forgot = (TextView) findViewById(R.id.forgot);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moveToregister = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(moveToregister);

            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moveToregister = new Intent(MainActivity.this,FogotPassword.class);
                startActivity(moveToregister);

            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    private void login() {
        Log.d(TAG,"login");

        if(!validate()){
            onLoginFailed();
            return;
        }
        btnlogin.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this,R.style.Theme_AppCompat_DayNight_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String email = etemail.getText().toString();
        String password = etpassword.getText().toString();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        onLoginSuccess();
                        progressDialog.dismiss();
                    }
                },3000);

    }

    public void onLoginSuccess() {
        btnlogin.setEnabled(true);
        Intent intent =new Intent(MainActivity.this,HomeActivity.class);
        startActivity(intent);
        finish();
    }
    public  void onLoginFailed(){
        Toast.makeText(getBaseContext(),"Login failed",Toast.LENGTH_LONG).show();

        btnlogin.setEnabled(true);
    }
    public boolean validate() {
        boolean valid = true;

        String email = etemail.getText().toString();
        String password = etpassword.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etemail.setError("enter a valid email address");
            valid = false;
        } else {
            etemail.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            etpassword.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            etpassword.setError(null);
        }

        return valid;
    }
}




