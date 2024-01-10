package br.ifsul.retrofit;
import br.ifsul.model.SorteioResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("megasena")
    Call<SorteioResponse> getSorteioMegasena();
    @GET("lotofacil")
    Call<SorteioResponse> getSorteioLotofacil();
    @GET("quina")
    Call<SorteioResponse> getSorteioQuina();
    @GET("timemania")
    Call<SorteioResponse> getSorteioTimemania();
    @GET("diadasorte")
    Call<SorteioResponse> getSorteioDiaDaSorte();
}
