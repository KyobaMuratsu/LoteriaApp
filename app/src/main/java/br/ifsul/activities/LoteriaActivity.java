package br.ifsul.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.ifsul.R;
import br.ifsul.model.Sorteio;
import br.ifsul.retrofit.ApiService;
import br.ifsul.retrofit.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoteriaActivity extends AppCompatActivity {
    private Button botao;
    private List<Integer> numeros;
    private List<String> listaSorteios = new ArrayList<>();
    private Spinner spinner;
    private ApiService loteriaService = RetrofitClient.obterInstanciaRetrofit().create(ApiService.class);
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loteria);

        botao = findViewById(R.id.shuffle_btn);

        spinner = findViewById(R.id.conteudoDropdown);

        gridView = (GridView) findViewById(R.id.gridLayout);

        alimentarSpinner();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listaSorteios);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gridView.setAdapter(new ArrayAdapter<>(getApplicationContext(),
                        android.R.layout.simple_list_item_1,
                        numeros
                ));
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selecionado = adapterView.getSelectedItem().toString().toLowerCase();

                switch (selecionado){
                    case "megasena":
                        Call<Sorteio> chamadaMega = loteriaService.getSorteioMegasena();
                        chamadaMega.enqueue(new Callback<Sorteio>() {
                            @Override
                            public void onResponse(Call<Sorteio> call, Response<Sorteio> response) {
                                if (response.isSuccessful()) {
                                    numeros = response.body().getNumerosSorteados();
                                } else {
                                    Toast.makeText(LoteriaActivity.this, "Response not successful", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Sorteio> call, Throwable t) {
                                Toast.makeText(LoteriaActivity.this, "Network Problem", Toast.LENGTH_SHORT).show();
                            }
                        });
                        break;

                    case "lotofacil":
                        Call<Sorteio> chamadaLoto = loteriaService.getSorteioLotofacil();
                        chamadaLoto.enqueue(new Callback<Sorteio>() {
                            @Override
                            public void onResponse(Call<Sorteio> call, Response<Sorteio> response) {
                                if (response.isSuccessful()) {
                                    numeros = response.body().getNumerosSorteados();
                                } else {
                                    Toast.makeText(LoteriaActivity.this, "Response not successful", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Sorteio> call, Throwable t) {
                                Log.e( "onFailure: ", "Failed network", t);
                                Toast.makeText(LoteriaActivity.this, "Network Problem", Toast.LENGTH_SHORT).show();
                            }
                        });
                        break;

                    case "quina":
                        Call<Sorteio> chamadaQuina = loteriaService.getSorteioQuina();
                        chamadaQuina.enqueue(new Callback<Sorteio>() {
                            @Override
                            public void onResponse(Call<Sorteio> call, Response<Sorteio> response) {
                                if (response.isSuccessful()) {
                                    numeros = response.body().getNumerosSorteados();
                                } else {
                                    Toast.makeText(LoteriaActivity.this, "Response not successful", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Sorteio> call, Throwable t) {
                                Log.e( "onFailure: ", "Failed network", t);
                                Toast.makeText(LoteriaActivity.this, "Network Problem", Toast.LENGTH_SHORT).show();
                            }
                        });
                        break;
                    case "timemania":
                        Call<Sorteio> chamadaMania = loteriaService.getSorteioTimemania();
                        chamadaMania.enqueue(new Callback<Sorteio>() {
                            @Override
                            public void onResponse(Call<Sorteio> call, Response<Sorteio> response) {
                                if (response.isSuccessful()) {
                                    numeros = response.body().getNumerosSorteados();
                                } else {
                                    Toast.makeText(LoteriaActivity.this, "Response not successful", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Sorteio> call, Throwable t) {
                                Log.e( "onFailure: ", "Failed network", t);
                                Toast.makeText(LoteriaActivity.this, "Network Problem", Toast.LENGTH_SHORT).show();
                            }
                        });
                        break;
                    case "diadasorte":
                        Call<Sorteio> chamadaSorte = loteriaService.getSorteioDiaDaSorte();
                        chamadaSorte.enqueue(new Callback<Sorteio>() {
                            @Override
                            public void onResponse(Call<Sorteio> call, Response<Sorteio> response) {
                                if (response.isSuccessful()) {
                                    numeros = response.body().getNumerosSorteados();
                                } else {
                                    Toast.makeText(LoteriaActivity.this, "Response not successful", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Sorteio> call, Throwable t) {
                                Log.e( "onFailure: ", "Failed network", t);
                                Toast.makeText(LoteriaActivity.this, "Network Problem", Toast.LENGTH_SHORT).show();
                            }
                        });
                        break;

                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });
    }

    public void alimentarSpinner() {
        listaSorteios.add("MEGASENA");
        listaSorteios.add("LOTOFACIL");
        listaSorteios.add("QUINA");
        listaSorteios.add("TIMEMANIA");
        listaSorteios.add("DIADASORTE");
    }
}

