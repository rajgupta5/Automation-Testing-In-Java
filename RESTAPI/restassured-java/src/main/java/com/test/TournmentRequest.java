package com.test;

public class TournmentRequest {


    private String name;
    private long entry;
    private long numQuestions;
    private String startTime;
    private String endTime;

    public TournmentRequest() {
        System.out.println("No arg Constructor");
    }

    public TournmentRequest(String name, long entryPrice, long numQuestions, String startTime, String endTime) {
        this.name = name;
        this.entry = entryPrice;
        this.numQuestions = numQuestions;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getEntry() {
        return entry;
    }

    public void setEntry(long entry) {
        this.entry = entry;
    }

    public long getNumQuestions() {
        return numQuestions;
    }

    public void setNumQuestions(long numQuestions) {
        this.numQuestions = numQuestions;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
