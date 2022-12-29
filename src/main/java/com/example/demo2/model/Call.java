package com.example.demo2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Call {

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("isParallel")
    public boolean isParallel() {
        return isParallel;
    }

    public void setParallel(boolean parallel) {
        isParallel = parallel;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    private String url;
    private boolean isParallel;
    private int count;
}
