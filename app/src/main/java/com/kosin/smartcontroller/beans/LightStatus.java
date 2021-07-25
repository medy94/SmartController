package com.kosin.smartcontroller.beans;

public class LightStatus {
    private String lightName;
    private String roomName;
    private boolean isOn;

    public LightStatus() {
    }

    public LightStatus(String lightName, String roomName, boolean isOn) {
        this.lightName = lightName;
        this.roomName = roomName;
        this.isOn = isOn;
    }

    public String getLightName() {
        return lightName;
    }

    public String getRoomName() {
        return roomName;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setLightName(String lightName) {
        this.lightName = lightName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setOn(boolean on) {
        isOn = on;
    }
}

