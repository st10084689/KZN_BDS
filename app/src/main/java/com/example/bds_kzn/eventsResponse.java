package com.example.bds_kzn;
import java.util.List;


public class eventsResponse {
        private String status;
        private int results;
        private List<Event> events;

        public String getStatus() {
            return status;
        }

        public int getResults() {
            return results;
        }

        public List<Event> getEvents() {
            return events;
        }
    }

