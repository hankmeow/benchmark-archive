package com.hankmew.benchmark.retrofit.dnspod;

import retrofit2.Call;
import retrofit2.http.POST;

public interface InfoVersionService {
    @POST("Info.Version")
    Call<InfoVersionRes> infoVersion();
}
