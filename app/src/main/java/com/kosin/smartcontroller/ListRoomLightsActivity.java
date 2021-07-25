package com.kosin.smartcontroller;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.kosin.smartcontroller.RetrofitService.RetrofitService;
import com.kosin.smartcontroller.RetrofitService.callbacks.GetStatusOfLightsFromRoomCallback;
import com.kosin.smartcontroller.RetrofitService.callbacks.LightOnOffCallback;
import com.kosin.smartcontroller.beans.LightStatus;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ListRoomLightsActivity extends AppCompatActivity {
    private String roomName;
    private List<LightStatus> lightStatusList;
    private RetrofitService retrofitService = new RetrofitService();
    private LinearLayout linearLayout;
    private ScheduledExecutorService scheduleTaskExecutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_room_lights);
        init();
    }

    private void getLights() {
        retrofitService.getStatusOfLightsFromRoom(roomName, new GetStatusOfLightsFromRoomCallback() {
            @Override
            public void onSucces(List<LightStatus> lights) {
                lightStatusList = lights;
                addRunnableToUpdateLights();
            }

            @Override
            public void onError() {
                System.out.println("Something went wrong");
            }
        });
    }

    private void addRunnableToUpdateLights() {
        scheduleTaskExecutor = Executors.newScheduledThreadPool(5);

        //Schedule a task to run every 5 seconds (or however long you want)
        scheduleTaskExecutor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                // Do stuff here!

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        fillLinearLayoutWithLights();
                    }
                });

            }
        }, 0, 1, TimeUnit.SECONDS); // or .MINUTES, .HOURS etc.
    }

    private void fillLinearLayoutWithLights(){
        linearLayout.removeAllViews();
        for (LightStatus light : lightStatusList) {
            Button child = new Button(this);
            child.setText(light.getLightName());
            child.setBackgroundColor(light.isOn() ? Color.GREEN : Color.RED);
            addClickListenerToButton(light, child);
            linearLayout.addView(child);
        }
    }

    private void addClickListenerToButton(LightStatus lightStatus, Button button){
        button.setOnClickListener(view -> {
            retrofitService.turnLightOnOff(
                    lightStatus.getRoomName(),
                    lightStatus.getLightName(),
                    !lightStatus.isOn(),
                    new LightOnOffCallback() {
                        @Override
                        public void onSucces(Void voiddd) {
                            lightStatus.setOn(!lightStatus.isOn());
                            Toast.makeText(ListRoomLightsActivity.this, "Light status changed", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError() {

                        }
                    }
            );
        });
    }

    private void init() {
        Bundle extras = getIntent().getExtras();
        linearLayout = findViewById(R.id.linearLayoutLights);
        roomName = (String) extras.get("roomName");
        getLights();
    }
}