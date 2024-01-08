package br.ifsul.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import br.ifsul.R;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private EditText email, password;

    private Button buttonRegister;
    private String emailString, passwordString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        buttonRegister = findViewById(R.id.buttonInstanceRegister);

        buttonRegister.setText("Registrar");
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailString = email.getText().toString().trim();
                passwordString = password.getText().toString().trim();

                mAuth.createUserWithEmailAndPassword(emailString, passwordString)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(RegisterActivity.this, "Registrado Com Sucesso! :D", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                                    startActivity(i);
                                    finish();
                                } else {
                                    Toast.makeText(RegisterActivity.this, "Não foi Possível Registrar! >:v", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }
}