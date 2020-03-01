package com.timetracker.controller;

import android.annotation.SuppressLint;
import android.widget.EditText;

import com.timetracker.model.DaoSession;
import com.timetracker.model.Time;
import com.timetracker.model.TimeDao;

public class TimeController {

    private DaoSession mDaoSession;
    private EditText timeEditText;
    private TimeDao timeDao;
    private Time time;


    public TimeController(DaoSession mDaoSession, EditText timeEditText) {
        this.mDaoSession = mDaoSession;
        this.timeEditText = timeEditText;
        initialSetup();
    }

    @SuppressLint("SetTextI18n")
    private void initialSetup() {
        timeDao = mDaoSession.getTimeDao();
        if (timeDao.loadAll().size() > 0) {
            time = timeDao.loadAll().get(0);
            updateTextAndDatabase();
        } else {
            timeEditText.setText("0:00");
            time = new Time();
            time.setHours(0);
            time.setMinutes(0);
            timeDao.save(time);
        }
    }


    public void addOneHour() {
        time.setHours(time.getHours() + 1);
        updateTextAndDatabase();
    }


    public void reduceOneHour() {
        time.setHours(time.getHours() + -1);
        updateTextAndDatabase();
    }


    public void addQuarter() {
        int minutes = time.getMinutes();
        minutes += 15;
        if (minutes >= 60) {
            addOneHour();
            minutes -= 60;
            time.setMinutes(minutes);
        }
        updateTextAndDatabase();
    }


    public void reduceQuarter() {
        int minutes = time.getMinutes();
        minutes -= 15;
        if (minutes < 0) {
            reduceOneHour();
            minutes = 60 + minutes;
            time.setMinutes(minutes);
        }
        updateTextAndDatabase();
    }


    public void addFiveMinutes() {
        int minutes = time.getMinutes();
        minutes += 5;
        if (minutes > 60) {
            addOneHour();
            minutes -= 60;
            time.setMinutes(minutes);
        }
        updateTextAndDatabase();
    }


    public void reduceFiveMinutes() {
        int minutes = time.getMinutes();
        minutes -= 5;
        if (minutes < 0) {
            reduceOneHour();
            minutes = 60 + minutes;
            time.setMinutes(minutes);
        }
        updateTextAndDatabase();
    }

    @SuppressLint("SetTextI18n")
    private void updateTextAndDatabase() {
        String localText = time.getHours() + ":" + (time.getMinutes() > 9 ? time.getMinutes() : "0" + time.getMinutes());
        timeEditText.setText(localText);
        timeDao.save(time);
    }


}
