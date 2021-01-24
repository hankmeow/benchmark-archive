package com.hankmew.benchmark.library.retrofit;

import com.hankmew.benchmark.retrofit.dnspod.DynamicTimeout;
import com.hankmew.benchmark.retrofit.dnspod.InfoVersionRes;
import com.hankmew.benchmark.retrofit.dnspod.InfoVersionService;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.jupiter.api.Test;
import retrofit2.Call;
import retrofit2.Invocation;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import javax.net.ssl.SSLException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

public class RetrofitTest {
    @Test
    public void testRetrofit() {
        //连接池。最大800个连接，连接空闲超过5分钟后将其收回
        ConnectionPool connectionPool = new ConnectionPool(800, 5, TimeUnit.MINUTES);
        //okhttpclient
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectionPool(connectionPool)
                .addInterceptor(chain -> {
                    //使用注解设置动态超时和重试，也可以在这里增加全局拦截
                    Request request = chain.request();
                    final Invocation tag = request.tag(Invocation.class);
                    final Method method = tag != null ? tag.method() : null;
                    final DynamicTimeout dynamicTimeout = method != null ? method.getAnnotation(DynamicTimeout.class) : null;
                    if(dynamicTimeout !=null){
                        int tryCount = 0;
                        while (tryCount <= dynamicTimeout.maxRetry()) {
                            try {
                                return chain
                                        .withConnectTimeout(dynamicTimeout.connectTimeout(), TimeUnit.MILLISECONDS)
                                        .withWriteTimeout(dynamicTimeout.writeTimeout(), TimeUnit.MILLISECONDS)
                                        .withReadTimeout(dynamicTimeout.readTimeout(), TimeUnit.MILLISECONDS)
                                        .proceed(request);
                            }catch (SocketTimeoutException | SSLException e) {
                                if (tryCount == dynamicTimeout.maxRetry()) {
                                    throw e;
                                }
                                tryCount++;
                            }
                        }
                    }
                    return chain.proceed(request);
                })
                //设置为false则不重试，设置为true则重试一次，若需要重试N次，则要实现拦截器。
                //全局超时设置
                .retryOnConnectionFailure(false)
                .connectTimeout(3000, TimeUnit.MILLISECONDS)
                .writeTimeout(3000, TimeUnit.MILLISECONDS)
                .readTimeout(3000, TimeUnit.MILLISECONDS)
                .build();
        //基础retrofit
        Retrofit baseRetrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(JacksonConverterFactory.create())
                //必须要有一个baseurl
                .baseUrl("http://127.0.0.1")
                //没有接口基本的拦截器用来设置鉴权
                .build();

        Retrofit dnspodRetrofit = baseRetrofit.newBuilder()
                .baseUrl("https://api.dnspod.com/")
                .build();
        InfoVersionService service = dnspodRetrofit.create(InfoVersionService.class);
        Call<InfoVersionRes> call = service.infoVersion();
        try {
            InfoVersionRes body = call.execute().body();
            System.out.println(body);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
