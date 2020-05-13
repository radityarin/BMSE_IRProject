package com.example.booleanmodelsearchengine_irproject.response;

import com.example.booleanmodelsearchengine_irproject.model.News;

import java.util.ArrayList;

public class NewsResponse {
    private boolean success;
    private String message;
    private ArrayList<News> data;

    public NewsResponse(boolean success, String message, ArrayList<News> data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<News> getData() {
        return data;
    }

    public void setData(ArrayList<News> data) {
        this.data = data;
    }
}
