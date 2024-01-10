package br.ifsul.retrofit;
import br.ifsul.model.Sorteio;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("lotomania")
    Call<Sorteio> getLotomania();
    @GET("megasena")
    Call<Sorteio> getMegasena();

    @GET("quina")
    Call<Sorteio> getQuina();

    @GET("lotofacil")
    Call<Sorteio> getLotoFacil();

    @GET("timemania")
    Call<Sorteio> getTimeMania();

    @GET("diadasorte")
    Call<Sorteio> getDiaDaSorte();
}
