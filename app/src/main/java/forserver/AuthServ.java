package forserver;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AuthServ {
    @FormUrlEncoded
    @POST("/auth/login")
    Call<Void> auth(@Field("login") String login,
                    @Field("password") String password);

    @POST("/auth/login")
    Call<ResponseExample> authByBody(@Body AuthBody authBody);

}
