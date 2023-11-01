package com.example.bds_kzn;

import java.util.ArrayList;
import java.util.List;

public class Utility {

    private static List<Shopping> shoppingItems;

    private static List<Event> eventItems;

    private static String BASE_URL= "https://testingsitewil.azurewebsites.net/";

    public Utility() {
        if (shoppingItems ==null){
            shoppingItems = new ArrayList<>();
        }
        if (eventItems ==null){
            eventItems = new ArrayList<>();
        }
    }

    public static List<Shopping> getShoppingItems() {
        return shoppingItems;
    }

    public static void setShoppingItems(List<Shopping> shoppingItems) {
        Utility.shoppingItems = shoppingItems;
    }

    public static List<Event> getEventItems() {
        return eventItems;
    }

    public static void setEventItems(List<Event> eventItems) {
        Utility.eventItems = eventItems;
    }


    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static void setBaseUrl(String baseUrl) {
        BASE_URL = baseUrl;
    }
}
