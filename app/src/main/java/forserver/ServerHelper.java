package forserver;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class ServerHelper {
    private static Retrofit retrofitIstance;
    public static Retrofit getRetrofit(){
        if (retrofitIstance == null) {
            retrofitIstance = new Retrofit.Builder().baseUrl("http://192.168.1.218:8080")
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofitIstance;

    }
}
