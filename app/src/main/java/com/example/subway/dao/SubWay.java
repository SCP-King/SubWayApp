package com.example.subway.dao;

import com.example.subway.pojo.Station;
import java.sql.ResultSet;
import java.util.List;

public interface SubWay {
    List<String> stations(String line);
    List<String> lines(String stationname);
    List<Station> getShortLine(String start, String end);
    List<Station> getLessLines(String start, String end);
    List<String> res_line(ResultSet resultSet);
    List<Integer> res_stationid(ResultSet resultSet);
}
