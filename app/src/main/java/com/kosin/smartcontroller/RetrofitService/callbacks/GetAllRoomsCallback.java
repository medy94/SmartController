package com.kosin.smartcontroller.RetrofitService.callbacks;

import java.util.List;

import io.github.zeroone3010.yahueapi.Room;

public interface GetAllRoomsCallback {
    void onSucces(List<String> rooms);
    void onError();
}
