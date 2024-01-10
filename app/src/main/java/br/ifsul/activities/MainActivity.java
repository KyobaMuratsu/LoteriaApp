package br.ifsul.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.ifsul.R;

public class MainActivity extends AppCompatActivity {

    private TextView titulo;

    private Button botaoLogin;

    private Button botaoRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titulo = findViewById(R.id.titulo);
        botaoLogin = findViewById(R.id.botaoLoginId);
        botaoRegistrar = findViewById(R.id.botaoRegistrarId);

        titulo.setText("bem-vindo");
        botaoLogin.setText("entrar");
        botaoRegistrar.setText("registrar");

        botaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, EntrarActivity.class);
                startActivity(i);
            }
        });

        botaoRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, RegistrarActivity.class);
                startActivity(i);
            }
        });
    }
}