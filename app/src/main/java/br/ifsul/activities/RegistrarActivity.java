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

public class RegistrarActivity extends AppCompatActivity {

    private Button botaoRegistrar;
    private String emailSelecionado, senhaSelecionada;
    private FirebaseAuth autenticacaoFirebase;
    private EditText email;
    private EditText senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        autenticacaoFirebase = FirebaseAuth.getInstance();

        email = findViewById(R.id.email);
        senha = findViewById(R.id.senha);
        botaoRegistrar = findViewById(R.id.botaoRegister);

        botaoRegistrar.setText("Registrar");
        botaoRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailSelecionado = email.getText().toString().trim();
                senhaSelecionada = senha.getText().toString().trim();

                autenticacaoFirebase.createUserWithEmailAndPassword(emailSelecionado, senhaSelecionada)
                        .addOnCompleteListener(RegistrarActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(RegistrarActivity.this, "Sucesso", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(RegistrarActivity.this, MainActivity.class);
                                    startActivity(i);
                                    finish();
                                } else {
                                    Toast.makeText(RegistrarActivity.this, "Erro ao registrar", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }
}