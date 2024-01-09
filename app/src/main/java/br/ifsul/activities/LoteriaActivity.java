package br.ifsul.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import br.ifsul.R;
import br.ifsul.adapter.GridAdapter;
import br.ifsul.model.Sorteio;
import br.ifsul.retrofit.ApiService;
import br.ifsul.retrofit.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoteriaActivity extends AppCompatActivity {

    private GridLayout gridLayout;

    private Spinner dropdown;
    private ApiService lotteryService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loteria);

        dropdown = findViewById(R.id.conteudoDropdown);

        lotteryService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

        gridLayout = findViewById(R.id.gridLayout);

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
                                    createGridByDataFromApi(result.getNumerosSorteados());
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

    private void createGridByDataFromApi(List<Integer> numerosSorteio) {
        gridLayout.removeAllViews();

        for (Integer item : numerosSorteio) {
            TextView textView = createGridItem(item);
            gridLayout.addView(textView);
        }
    }

    private TextView createGridItem(Integer number) {
        TextView textView = new TextView(this);
        textView.setLayoutParams(new GridLayout.LayoutParams());
        textView.setText(String.valueOf(number)); // Convert integer to string
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        textView.setGravity(Gravity.CENTER);
        textView.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
        textView.setPadding(4, 4, 4, 4);

        return textView;
    }
}

