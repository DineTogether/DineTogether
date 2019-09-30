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

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";

    private EditText mtextname, mtextEmail, mtextPhone, mtextPassword, mTextcnfPassword;
    private Button mbuttonRegister;
    private TextView mtextviewLogin;
    private DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        databaseHelper = new DatabaseHelper(this);
        mtextname = (EditText) findViewById(R.id.edittext_name);
        mtextEmail = (EditText) findViewById(R.id.edittext_email);
        mtextPhone = (EditText) findViewById(R.id.edittext_Cphone);
        mtextPassword = (EditText) findViewById(R.id.edittext_password);
        mTextcnfPassword = (EditText) findViewById(R.id.edittext_cnfpassword);
        mtextviewLogin = (TextView)findViewById(R.id.login);
        mbuttonRegister = (Button) findViewById(R.id.button_register);
        mbuttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.addUserDetail(
                        mtextname.getText().toString(),
                        mtextEmail.getText().toString(),
                        mtextPhone.getText().toString(),
                        mtextPassword.getText().toString(),
                        mTextcnfPassword.getText().toString());


                mtextname.setText("");
                mtextEmail.setText("");
                mtextPhone.setText("");
                mtextPassword.setText("");
                mTextcnfPassword.setText("");

                Signup();
            }
        });
        mtextviewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });
    }
    public void Signup() {
        Log.d(TAG, "RegisterActivity");

      /*  if (!validate()) {
            onsignupFailed();
            return;
        }*/
        mbuttonRegister.setEnabled(false);
        //test


        final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this, R.style.Theme_AppCompat_DayNight_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("creating Account..");
        progressDialog.show();

        String name = mtextname.getText().toString();
        String email = mtextEmail.getText().toString();
        String phone = mtextPhone.getText().toString();
        String password = mtextPassword.getText().toString();
        String cnpassword = mTextcnfPassword.getText().toString();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        onSignupSuccess();

                        progressDialog.dismiss();
                    }
                }, 3000);
    }

    private void onSignupSuccess() {
        mbuttonRegister.setEnabled(true);
        //setResult(RESULT_OK,null);
        finish();

    }
    private void onsignupFailed() {
        Toast.makeText(getBaseContext(),"Registration failed",Toast.LENGTH_LONG).show();
        mbuttonRegister.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = mtextname.getText().toString();
        String email = mtextEmail.getText().toString();
        String phone = mtextPhone.getText().toString();
        String password = mTextcnfPassword.getText().toString();
        String cpassword = mTextcnfPassword.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            mtextname.setError("at least 3 characters");
            valid = false;
        } else {
            mtextname.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mtextEmail.setError("enter a valid email address");
            valid = false;
        } else {
            mtextEmail.setError(null);
        }
        if(phone.isEmpty()|| phone.length()>10){
            mtextPhone.setError("only 10 numbers");
            valid=false;
        }else{
            mtextPhone.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            mtextPassword.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            mtextPassword.setError(null);
        }
        if (cpassword.isEmpty() || cpassword.length() < 4 || cpassword.length() > 10) {
            mTextcnfPassword.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            mTextcnfPassword.setError(null);
        }


        return valid;
    }
}


