package com.example.petpider;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @GET("open")
    Call<Void> feedNow();
    @GET("set-schedule")
    Call<Void> feedSchedule(@Query("hour") String hour,
                            @Query("minute") String minute);
}
