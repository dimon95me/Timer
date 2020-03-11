package com.example.timer;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.timer.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    MainActivityViewModel viewModel;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);
        viewModel.complete.observe(this, this::timerIsComplete);
        viewModel.secondsLeft.observe(this, this::onSecondsLeft);
        viewModel.percents.observe(this, this::currentPercents);


        binding.btnActionStart.setOnClickListener(this::startTimer);
        binding.btnActionStop.setOnClickListener(this::stopTimer);
    }

    private void stopTimer(View view) {
        viewModel.stopTimer();
    }

    private void currentPercents(Integer percents) {
        binding.pbLoading.setValue(percents);
    }

    private void onSecondsLeft(Integer count) {
        binding.tvSecondsLeft.setText("Seconds left: " + count);
    }

    private void timerIsComplete(Boolean aBoolean) {
        showProgress(aBoolean);
    }

    private void startTimer(View view) {
        if (!binding.editText.getText().toString().isEmpty()) {
            viewModel.setTimer(Integer.valueOf(binding.editText.getText().toString()));
        } else {
            Toast.makeText(this, R.string.enter_second_count, Toast.LENGTH_SHORT).show();
        }
    }

    private void showProgress(boolean showProgress) {
        binding.btnActionStart.setVisibility(showProgress ? View.GONE : View.VISIBLE);
        binding.editText.setVisibility(showProgress ? View.GONE : View.VISIBLE);
        binding.llProgress.setVisibility(showProgress ? View.VISIBLE : View.GONE);
    }


}
