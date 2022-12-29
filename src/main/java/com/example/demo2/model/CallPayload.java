package com.example.demo2.model;

import java.util.List;

public class CallPayload {

    public List<Call> getCallList() {
        return callList;
    }

    public void setCallPayloadList(List<Call> callList) {
        this.callList = callList;
    }

    List<Call> callList;
}
