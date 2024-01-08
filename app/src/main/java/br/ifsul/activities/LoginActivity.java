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
import com.google.firebase.auth.FirebaseUser;

import br.ifsul.R;

public class LoginActivity extends AppCompatActivity {

    private EditText email, password;

    private Button loggInButton;

    private FirebaseAuth mAuth;

    private String emailString, passwordString;

    @Override
    public void onStart(){
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent i = new Intent(getApplicationContext(), LoteriaActivity.class);
            startActivity(i);
            finish();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.emailLogin);
        password = findViewById(R.id.passwordLogin);
        loggInButton = findViewById(R.id.buttonLoggin);

        loggInButton.setText("Login");
        loggInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailString = email.getText().toString().trim();
                passwordString = password.getText().toString().trim();
                mAuth.signInWithEmailAndPassword(emailString, passwordString)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(LoginActivity.this, "Logado com sucesso :D", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(getApplicationContext(), LoteriaActivity.class);
                                    startActivity(i);
                                    finish();
                                }else
                                    Toast.makeText(LoginActivity.this, "Senha incorreta ou Conta Inexistente! >:v", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });


    }
}