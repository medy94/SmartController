package com.kosin.smartcontroller.RetrofitService.callbacks;

import java.util.List;

public interface GetLightsFromRoom {
    void onSucces(List<String> lights);
    void onError();
}
