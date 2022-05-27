package forserver;

import com.example.mylibrary.objects.Book;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SendBook {

    @POST("/book")
    Call<ResponseExample> saveBook(@Body Book authBody);

    @POST("/chosenBook")
    Call<ResponseExample> saveChosenBook(@Body Book authBody);
}
