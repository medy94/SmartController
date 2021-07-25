package com.kosin.smartcontroller.RetrofitService.interfaces;

import com.kosin.smartcontroller.beans.LightStatus;

import java.util.List;

import io.github.zeroone3010.yahueapi.Room;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface LightService {
    @POST("/lights/lightOnOff/{roomName}/{lightName}/{on}")
    Call<Void> lightOnOff(
            @Path("roomName") String roomName,
            @Path("lightName") String lightName,
            @Path("on") boolean on
    );

    @GET("/lights/allRooms")
    Call<List<String>> getAllRooms();

    @GET("/lights/{roomName}")
    Call<List<String>> getLightsOfRoom(@Path("roomName") String roomName);

    @GET("/lights/getStatusOfLights/{roomName}")
    Call<List<LightStatus>> getStatusOfLights(@Path("roomName") String roomName);
}
