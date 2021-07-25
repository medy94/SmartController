package com.kosin.smartcontroller.RetrofitService.callbacks;

import com.kosin.smartcontroller.beans.LightStatus;

import java.util.List;

public interface GetStatusOfLightsFromRoomCallback {
    void onSucces(List<LightStatus> lights);
    void onError();
}
