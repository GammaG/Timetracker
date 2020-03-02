package com.timetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.timetracker.controller.TimeController;
import com.timetracker.model.DaoMaster;
import com.timetracker.model.DaoSession;

public class MainActivity extends AppCompatActivity {

    private EditText timeEditText;
    private Button addHourButton;
    private Button reduceHourButton;
    private Button addQuarterButton;
    private Button reduceQuarterButton;
    private Button addFiveMinutesButton;
    private Button reduceFiveMinutesButton;
    private DaoSession mDaoSession;
    private TimeController timeController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeUIElements();
        setUpGreenDaoSession();
        setUpController();
    }

    private void initializeUIElements() {
        timeEditText = findViewById(R.id.timeEditText);
        timeEditText.setFocusable(false);
        addHourButton = findViewById(R.id.addHourButton);
        reduceHourButton = findViewById(R.id.reduceHourButton);
        addQuarterButton = findViewById(R.id.addQuarterButton);
        reduceQuarterButton = findViewById(R.id.reduceQuarterButton);
        reduceFiveMinutesButton = findViewById(R.id.reduceFiveMinutesButton);
        addFiveMinutesButton = findViewById(R.id.addFiveMinutesButton);
    }

    private void setUpGreenDaoSession() {
        mDaoSession = new DaoMaster(
                new DaoMaster.DevOpenHelper(this, "overtime.db").getWritableDb()).newSession();

    }

    private void setUpController() {
        timeController = new TimeController(mDaoSession, timeEditText);
        addHourButton.setOnClickListener(x -> timeController.addOneHour());
        reduceHourButton.setOnClickListener(x -> timeController.reduceOneHour());
        addQuarterButton.setOnClickListener(x -> timeController.addQuarter());
        reduceQuarterButton.setOnClickListener(x -> timeController.reduceQuarter());
        addFiveMinutesButton.setOnClickListener(x -> timeController.addFiveMinutes());
        reduceFiveMinutesButton.setOnClickListener(x -> timeController.reduceFiveMinutes());
    }


}
