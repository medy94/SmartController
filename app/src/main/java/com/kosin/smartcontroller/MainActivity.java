package com.kosin.smartcontroller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.kosin.smartcontroller.RetrofitService.RetrofitService;
import com.kosin.smartcontroller.RetrofitService.callbacks.LightOnOffCallback;

public class MainActivity extends AppCompatActivity {

    private RetrofitService retrofitService = new RetrofitService();
    private EditText roomNameTextfield;
    private EditText lightNameTextfield;
    private Switch onOffSwitch;
    private Button sendButton;
    private Button goToRoomsActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        addClickHandlers();
    }

    private void addClickHandlers() {
        sendButton.setOnClickListener(view -> {
            onSendButtonClick();
        });
        goToRoomsActivity.setOnClickListener(vie -> {
            Intent intent = new Intent(MainActivity.this, RoomsActivity.class);
            startActivity(intent);
        });
    }

    private void onSendButtonClick() {
        String roomName = roomNameTextfield.getText().toString();
        String lightName = lightNameTextfield.getText().toString();
        boolean onOff = onOffSwitch.isChecked();
        if(roomName.equals("") || lightName.equals("")) {

        } else {
            retrofitService.turnLightOnOff(roomName, lightName, onOff, new LightOnOffCallback() {
                @Override
                public void onSucces(Void voiddd) {
                    System.out.println("Yeah!");
                }

                @Override
                public void onError() {
                    System.out.println("oh no....");
                }
            });
        }
    }

    private void init() {
        roomNameTextfield = findViewById(R.id.roomNameInput);
        lightNameTextfield = findViewById(R.id.lightNameInput);
        onOffSwitch = findViewById(R.id.onOffSwitch);
        sendButton = findViewById(R.id.sendButton);
        goToRoomsActivity = findViewById(R.id.goToRoomsButton);
    }
}