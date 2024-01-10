package br.ifsul;

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

public class EntrarActivity extends AppCompatActivity {
    private Button botaoLogin;
    private EditText email;
    private EditText senha;
    private FirebaseAuth autenticacao;
    private String emailSelecionado;
    private String senhaSelecionada;

    @Override
    public void onStart(){
        super.onStart();
        FirebaseUser usuarioFirebase = autenticacao.getCurrentUser();

        if(usuarioFirebase != null){
            Intent i = new Intent(getApplicationContext(), LoteriaActivity.class);
            startActivity(i);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrar);

        autenticacao = FirebaseAuth.getInstance();

        email = findViewById(R.id.emailLogin);
        senha = findViewById(R.id.senhaLogin);
        botaoLogin = findViewById(R.id.botaoLogin);

        botaoLogin.setText("Entrar");
        botaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailSelecionado = email.getText().toString().trim();
                senhaSelecionada = senha.getText().toString().trim();
                autenticacao.signInWithEmailAndPassword(emailSelecionado, senhaSelecionada)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Intent i = new Intent(getApplicationContext(), LoteriaActivity.class);
                                    startActivity(i);
                                    finish();
                                }
                                    Toast.makeText(EntrarActivity.this, "Senha incorreta.", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}