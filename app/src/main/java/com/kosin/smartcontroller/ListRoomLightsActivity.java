package com.kosin.smartcontroller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import com.kosin.smartcontroller.RetrofitService.RetrofitService;
import com.kosin.smartcontroller.RetrofitService.callbacks.GetLightsFromRoom;

import java.util.List;

public class ListRoomLightsActivity extends AppCompatActivity {
    private String roomName;
    private List<String> lightNames;
    private RetrofitService retrofitService = new RetrofitService();
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_room_lights);
        init();
    }

    private void getLights() {
        retrofitService.getLightsFromRoom(roomName, new GetLightsFromRoom() {
            @Override
            public void onSucces(List<String> lights) {
                lightNames = lights;
                fillLinearLayoutWithLights();
            }

            @Override
            public void onError() {

            }
        });
    }

    private void fillLinearLayoutWithLights() {
        for (String lightName : lightNames) {
            Button child = new Button(this);
            child.setText(lightName);

            linearLayout.addView(child);
        }
    }

    private void init() {
        Bundle extras = getIntent().getExtras();
        linearLayout = findViewById(R.id.linearLayoutLights);
        roomName = (String) extras.get("roomName");
        getLights();
    }
}