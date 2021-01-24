package com.hankmew.benchmark.retrofit.dnspod;

import retrofit2.Call;
import retrofit2.http.POST;

public interface InfoVersionService {
    @DynamicTimeout(maxRetry = 1, connectTimeout = 3000, writeTimeout = 3000, readTimeout = 3000)
    @POST("Info.Version")
    Call<InfoVersionRes> infoVersion();
}
