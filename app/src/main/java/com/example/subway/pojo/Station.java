package com.example.subway.pojo;

import java.util.List;
public class Station {
    private int stationid;
    private String stationname;
    private List<String> lines;
    private List<Integer> next;
    private int provideid;

    public Station() {
    }

    public Station(int stationid, String stationname, List<String> lines, List<Integer> next, int provideid) {
        this.stationid = stationid;
        this.stationname = stationname;
        this.lines = lines;
        this.next = next;
        this.provideid = provideid;
    }

    public int getStationid() {
        return stationid;
    }

    public void setStationid(int stationid) {
        this.stationid = stationid;
    }

    public String getStationname() {
        return stationname;
    }

    public void setStationname(String stationname) {
        this.stationname = stationname;
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    public List<Integer> getNext() {
        return next;
    }

    public void setNext(List<Integer> next) {
        this.next = next;
    }

    public int getProvideid() {
        return provideid;
    }

    public void setProvideid(int provideid) {
        this.provideid = provideid;
    }
}
