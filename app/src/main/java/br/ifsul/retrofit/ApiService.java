package br.ifsul.retrofit;
import br.ifsul.model.Sorteio;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("megasena")
    Call<Sorteio> getSorteioMegasena();
    @GET("lotofacil")
    Call<Sorteio> getSorteioLotofacil();
    @GET("quina")
    Call<Sorteio> getSorteioQuina();
    @GET("timemania")
    Call<Sorteio> getSorteioTimemania();
    @GET("diadasorte")
    Call<Sorteio> getSorteioDiaDaSorte();
}
