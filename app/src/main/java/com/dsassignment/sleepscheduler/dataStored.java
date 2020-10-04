package com.dsassignment.sleepscheduler;

public class dataStored {
    private String date;
    private String time;
    private int age;

    public dataStored() {
    }

    public dataStored(String date, String time, int age) {
        this.date = date;
        this.time = time;
        this.age = age;
    }

    @Override
    public String toString() {
        return "dataStored{" +
                "date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", age=" + age +
                '}';
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
