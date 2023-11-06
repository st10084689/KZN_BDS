package com.example.bds_kzn;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Event {

    private int id;
    private String title;
    private String description;
    private String images;
    public String eventTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }


    public String getEventTime() {
        String inputDateString = eventTime;
        DateTimeFormatter inputFormatter = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        }
        LocalDateTime dateTime = null;
            dateTime = LocalDateTime.parse(inputDateString, inputFormatter);
        DateTimeFormatter outputFormatter = null;
            outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String formattedDate = dateTime.format(outputFormatter);
        return formattedDate;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }
}
