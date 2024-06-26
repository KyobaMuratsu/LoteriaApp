package br.ifsul.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.ifsul.R;
import br.ifsul.model.Sorteio;
import br.ifsul.retrofit.ApiService;
import br.ifsul.retrofit.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoteriaActivity extends AppCompatActivity {

    private Spinner dropdown;


    private ApiService lotteryService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loteria);

        dropdown = findViewById(R.id.conteudoDropdown);

        List<String> sorteios = new ArrayList<>();
        sorteios.add("MegaSena");
        sorteios.add("LotoFacil");
        sorteios.add("Quina");
        sorteios.add("TimeMania");
        sorteios.add("DiaDaSorte");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sorteios);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        dropdown.setAdapter(adapter);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selected = adapterView.getSelectedItem().toString().trim();
                selected = selected.toLowerCase();

                switch (selected){
                    case "megasena":
                        Log.d("onItemSelected: ", "Selected:MegaSena");
                        Call<Sorteio> callMega = lotteryService.getMegasena();
                        callMega.enqueue(new Callback<Sorteio>() {
                            @Override
                            public void onResponse(Call<Sorteio> call, Response<Sorteio> response) {
                                if (response.isSuccessful()) {
                                    Sorteio result = response.body();
                                    if (result != null) {
                                        Toast.makeText(LoteriaActivity.this, result.getTema(), Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(LoteriaActivity.this, "Response body is null", Toast.LENGTH_SHORT).show();
                                    }
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

                    case "lotofacil":
                        Log.d("onItemSelected: ", "Selected:LotoFacil");
                        Call<Sorteio> callLoto = lotteryService.getLotoFacil();
                        callLoto.enqueue(new Callback<Sorteio>() {
                            @Override
                            public void onResponse(Call<Sorteio> call, Response<Sorteio> response) {
                                if (response.isSuccessful()) {
                                    Sorteio result = response.body();
                                    if (result != null) {
                                        Toast.makeText(LoteriaActivity.this, result.getTema(), Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(LoteriaActivity.this, "Response body is null", Toast.LENGTH_SHORT).show();
                                    }
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
                        Log.d("onItemSelected: ", "Selected:Quina");
                        Call<Sorteio> callQuina = lotteryService.getQuina();
                        callQuina.enqueue(new Callback<Sorteio>() {
                            @Override
                            public void onResponse(Call<Sorteio> call, Response<Sorteio> response) {
                                if (response.isSuccessful()) {
                                    Sorteio result = response.body();
                                    if (result != null) {
                                        Toast.makeText(LoteriaActivity.this, result.getTema(), Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(LoteriaActivity.this, "Response body is null", Toast.LENGTH_SHORT).show();
                                    }
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
                        Log.d("onItemSelected: ", "Selected:TimeMania");
                        Call<Sorteio> callMania = lotteryService.getTimeMania();
                        callMania.enqueue(new Callback<Sorteio>() {
                            @Override
                            public void onResponse(Call<Sorteio> call, Response<Sorteio> response) {
                                if (response.isSuccessful()) {
                                    Sorteio result = response.body();
                                    if (result != null) {
                                        Toast.makeText(LoteriaActivity.this, result.getTema(), Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(LoteriaActivity.this, "Response body is null", Toast.LENGTH_SHORT).show();
                                    }
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
                        Log.d("onItemSelected: ", "Selected:DiaDaSorte");
                        Call<Sorteio> callSorte = lotteryService.getDiaDaSorte();
                        callSorte.enqueue(new Callback<Sorteio>() {
                            @Override
                            public void onResponse(Call<Sorteio> call, Response<Sorteio> response) {
                                if (response.isSuccessful()) {
                                    Sorteio result = response.body();
                                    if (result != null) {
                                        Toast.makeText(LoteriaActivity.this, result.getTema(), Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(LoteriaActivity.this, "Response body is null", Toast.LENGTH_SHORT).show();
                                    }
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
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void displaySorteioMegaSena(Sorteio sorteio) {




    }
}

