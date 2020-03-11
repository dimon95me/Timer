package com.example.timer;

import android.app.Application;
import android.os.CountDownTimer;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class MainActivityViewModel extends AndroidViewModel {

    private CountDownTimer timer;
    MutableLiveData<Boolean> complete = new MutableLiveData<>();
    MutableLiveData<Integer> secondsLeft = new MutableLiveData<>();
    MutableLiveData<Integer> percents = new MutableLiveData<>();

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public void setTimer(int secondsCount) {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }

        timer = new CountDownTimer(secondsCount * 1000, 10) {

            @Override
            public void onTick(long millisUntilFinished) {
                int millisUntilFinishedinSeconds = (int) millisUntilFinished / 1000;
                secondsLeft.setValue(millisUntilFinishedinSeconds);
                complete.setValue(true);
                percents.setValue((int) Math.round((1 - ((double) millisUntilFinished / (secondsCount*1000))) * 100));
            }

            @Override
            public void onFinish() {
                complete.setValue(false);
            }
        }.start();
    }

    public void stopTimer() {
        if (timer == null) {
            return;
        }
        timer.cancel();
        complete.setValue(false);
    }
}
