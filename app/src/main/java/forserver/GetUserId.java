package forserver;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface GetUserId {
    @POST("auth/login")
    Call<CompleteResponce> complete(
            @Body AuthBody body

    );
}
