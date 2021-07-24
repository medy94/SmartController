package com.kosin.smartcontroller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import com.kosin.smartcontroller.RetrofitService.RetrofitService;
import com.kosin.smartcontroller.RetrofitService.callbacks.GetAllRoomsCallback;
import com.kosin.smartcontroller.RetrofitService.callbacks.GetLightsFromRoom;

import java.util.List;

public class RoomsActivity extends AppCompatActivity {
    private RetrofitService retrofitService = new RetrofitService();
    private LinearLayout linearLayout;
    private List<String> allRoomNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms);
        init();
        getRooms();
    }

    private void getRooms() {
        retrofitService.getAllRooms(new GetAllRoomsCallback() {
            @Override
            public void onSucces(List<String> rooms) {
                allRoomNames = rooms;
                fillLinearLayoutWithRooms();
            }

            @Override
            public void onError() {

            }
        });
    }

    private void fillLinearLayoutWithRooms() {
        if (allRoomNames != null) {
            for (String roomName : allRoomNames) {
                Button child = new Button(this);
                child.setText(roomName);
                addClickHandlerToRoomButton(child, roomName);
                linearLayout.addView(child);
            }
        }
    }

    private void addClickHandlerToRoomButton(Button child, String roomName) {
        child.setOnClickListener(view -> {
            Intent intent = new Intent(RoomsActivity.this, ListRoomLightsActivity.class);
            intent.putExtra("roomName", roomName);
            startActivity(intent);
        });
    }

    private void init() {
        linearLayout = findViewById(R.id.linearLayout);
    }
}