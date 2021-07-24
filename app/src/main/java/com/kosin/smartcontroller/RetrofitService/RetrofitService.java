package com.kosin.smartcontroller.RetrofitService;

import com.kosin.smartcontroller.RetrofitService.callbacks.GetAllRoomsCallback;
import com.kosin.smartcontroller.RetrofitService.callbacks.GetLightsFromRoom;
import com.kosin.smartcontroller.RetrofitService.callbacks.LightOnOffCallback;
import com.kosin.smartcontroller.RetrofitService.interfaces.LightService;

import java.util.List;

import io.github.zeroone3010.yahueapi.Room;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private LightService restService;

    public RetrofitService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http:/192.168.0.53:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        restService = retrofit.create(LightService.class);
    }

    public void turnLightOnOff(String roomName, String lightName, boolean on, LightOnOffCallback onOffCallback) {
        Call<Void> lightOnOff = restService.lightOnOff(roomName, lightName, on);
        lightOnOff.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                System.out.println("Success");
                onOffCallback.onSucces(response.body());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                onOffCallback.onError();
                t.printStackTrace();
            }
        });
    }

    public void getAllRooms(GetAllRoomsCallback callback) {
        Call<List<String>> getAllRooms = restService.getAllRooms();
        getAllRooms.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                System.out.println("Rooms loaded");
                callback.onSucces(response.body());
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getLightsFromRoom(String roomName, GetLightsFromRoom callback) {
        Call<List<String>> getLightsFromRoomCallback = restService.getLightsOfRoom(roomName);
        getLightsFromRoomCallback.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                System.out.println("lights of room " + roomName + " loaded");
                callback.onSucces(response.body());
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
