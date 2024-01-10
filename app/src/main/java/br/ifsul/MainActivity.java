package br.ifsul;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView titulo;

    private Button botaoEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titulo = findViewById(R.id.titulo);
        botaoEntrar = findViewById(R.id.botaoEntrarId);

        titulo.setText("bem-vindo");
        botaoEntrar.setText("entre na loteria!");

        botaoEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, LoteriaActivity.class);
                startActivity(i);
            }
        });
    }
}