package com.hankmew.library.retrofit;

import com.hankmew.retrofit.dnspod.InfoVersionRes;
import com.hankmew.retrofit.dnspod.InfoVersionService;
import org.junit.jupiter.api.Test;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;

public class RetrofitTest {
    @Test
    public void testRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.dnspod.com/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        InfoVersionService service = retrofit.create(InfoVersionService.class);
        Call<InfoVersionRes> call = service.infoVersion();
        try {
            InfoVersionRes body = call.execute().body();
            System.out.println(body);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
