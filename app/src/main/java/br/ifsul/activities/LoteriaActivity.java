package br.ifsul.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Date;
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

//    private GridLayout gridLayout;

    private GridView gridView;
    private Spinner dropdown;
    private ApiService lotteryService;
    private TextView concurso, title;
    private Button btn, logoutButton;
    private List<Integer> numbers;
    private ImageView imagem;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loteria);

        mAuth = FirebaseAuth.getInstance();
        btn = findViewById(R.id.shuffle_btn);
        dropdown = findViewById(R.id.conteudoDropdown);
        concurso = findViewById(R.id.nomeConcurso);
        title = findViewById(R.id.nomeSorteio);
        imagem = findViewById(R.id.imagem);
        logoutButton = findViewById(R.id.logoutButton);

        imagem.setImageDrawable(getDrawable(R.drawable.megasena));

        lotteryService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

//        gridLayout = findViewById(R.id.gridLayout);

        gridView = (GridView) findViewById(R.id.gridLayout);
        gridView.setBackgroundColor(getColor(R.color.cream));

        List<String> sorteios = new ArrayList<>();
        sorteios.add("MegaSena");
        sorteios.add("LotoFacil");
        sorteios.add("Quina");
        sorteios.add("TimeMania");
        sorteios.add("DiaDaSorte");
        sorteios.add("LotoMania");

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
                        if(numbers != null){
                            numbers.clear();
                            gridView.setAdapter(null);
                            title.setText(null);
                            concurso.setText(null);
                        }

                        callMega.enqueue(new Callback<Sorteio>() {
                            @Override
                            public void onResponse(Call<Sorteio> call, Response<Sorteio> response) {
                                if (response.isSuccessful()) {
                                    Sorteio result = response.body();
                                    btn.setBackgroundColor(getColor(R.color.megasena));
                                    numbers = result.getNumerosSorteados();
                                    title.setText(result.getTema());
                                    concurso.setText("Concurso N." + result.getNumero());

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
                        if(numbers != null){
                            numbers.clear();
                            gridView.setAdapter(null);
                            title.setText(null);
                            concurso.setText(null);
                        }
                        callLoto.enqueue(new Callback<Sorteio>() {
                            @Override
                            public void onResponse(Call<Sorteio> call, Response<Sorteio> response) {
                                if (response.isSuccessful()) {
                                    Sorteio result = response.body();
                                    btn.setBackgroundColor(getColor(R.color.lotofacil));
                                    numbers = result.getNumerosSorteados();
                                    title.setText(result.getTema());
                                    concurso.setText("Concurso N." + result.getNumero());
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
                        if(numbers != null){
                            numbers.clear();
                            gridView.setAdapter(null);
                            title.setText(null);
                            concurso.setText(null);
                        }
                        callQuina.enqueue(new Callback<Sorteio>() {
                            @Override
                            public void onResponse(Call<Sorteio> call, Response<Sorteio> response) {
                                if (response.isSuccessful()) {
                                    Sorteio result = response.body();
                                    btn.setBackgroundColor(getColor(R.color.quina));
                                    numbers = result.getNumerosSorteados();
                                    title.setText(result.getTema());
                                    concurso.setText("Concurso N." + result.getNumero());
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
                        if(numbers != null){
                            numbers.clear();
                            gridView.setAdapter(null);
                            title.setText(null);
                            concurso.setText(null);
                        }
                        Call<Sorteio> callTMania = lotteryService.getTimeMania();
                        callTMania.enqueue(new Callback<Sorteio>() {
                            @Override
                            public void onResponse(Call<Sorteio> call, Response<Sorteio> response) {
                                if (response.isSuccessful()) {
                                    Sorteio result = response.body();
                                    btn.setBackgroundColor(getColor(R.color.timemania));
                                    numbers = result.getNumerosSorteados();
                                    title.setText(result.getTema());
                                    concurso.setText("Concurso N." + result.getNumero());
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
                        if(numbers != null){
                            numbers.clear();
                            gridView.setAdapter(null);
                            title.setText(null);
                            concurso.setText(null);
                        }
                        callSorte.enqueue(new Callback<Sorteio>() {
                            @Override
                            public void onResponse(Call<Sorteio> call, Response<Sorteio> response) {
                                if (response.isSuccessful()) {
                                    Sorteio result = response.body();
                                    btn.setBackgroundColor(getColor(R.color.diadesorte));
                                    numbers = result.getNumerosSorteados();
                                    title.setText(result.getTema());
                                    concurso.setText("Concurso N." + result.getNumero());
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

                    case "lotomania":
                        Log.d("onItemSelected: ", "Selected:LotoMania");
                        Call<Sorteio> callMania = lotteryService.getLotomania();
                        if(numbers != null){
                            numbers.clear();
                            gridView.setAdapter(null);
                            title.setText(null);
                            concurso.setText(null);
                        }
                        callMania.enqueue(new Callback<Sorteio>() {
                            @Override
                            public void onResponse(Call<Sorteio> call, Response<Sorteio> response) {
                                if (response.isSuccessful()) {
                                    Sorteio result = response.body();
                                    btn.setBackgroundColor(getColor(R.color.lotomania));
                                    numbers = result.getNumerosSorteados();
                                    title.setText(result.getTema());
                                    concurso.setText("Concurso N." + result.getNumero());
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


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shuffle();
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout(view);
            }
        });

    }

    public void shuffle() {
        gridView.setAdapter(new ArrayAdapter<>(getApplicationContext(),
                R.layout.grid_item,
                numbers
        ));
    }

    public void logout(View view) {
        mAuth.signOut();
        // Redirect to your login or home activity
        Intent intent = new Intent(LoteriaActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

}

