package com.androidcourse.manisha.instapost;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener{
    EditText editTextEmailLogin;
    EditText editTextPasswordLogin;
    Button login;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextEmailLogin = (EditText)findViewById(R.id.emailLogin);
        editTextPasswordLogin = (EditText)findViewById(R.id.passwordLogin);
        login = (Button)findViewById(R.id.login);
        findViewById(R.id.signup).setOnClickListener(this);
        login.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
    }
    private void userLogin() {
        final String email = editTextEmailLogin.getText().toString().trim();
        String password = editTextPasswordLogin.getText().toString().trim();

           if (email.isEmpty()) {
               editTextEmailLogin.setError(getString(R.string.input_error_email));
               editTextEmailLogin.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmailLogin.setError(getString(R.string.input_error_email_invalid));
            editTextEmailLogin.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPasswordLogin.setError(getString(R.string.input_error_password));
            editTextPasswordLogin.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPasswordLogin.setError(getString(R.string.input_error_password_length));
            editTextPasswordLogin.requestFocus();
            return;
        }
       mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("TAG", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(new Intent(MainActivity.this,ProfileActivity.class));
                        } else {
                            Log.w("TAG", "signInWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.signup){
            startActivity(new Intent(MainActivity.this,SignUpActivity.class));
        }else if(v.getId() == R.id.login){
            userLogin();
        }
    }
}
