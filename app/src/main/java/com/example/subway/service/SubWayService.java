package com.example.subway.service;

import com.example.subway.dao.SubWay;
import com.example.subway.pojo.Station;
import com.example.subway.utils.SqlUtils;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SubWayService implements SubWay {

    @Override
    public List<String> stations(String line) {
        List<String> stations=new ArrayList<>();
        try {
            SqlUtils.getConnection();
            String sql="select * from line where "+line+"!=-1 order by stationid";
            ResultSet resultSet=SqlUtils.query(sql,null);
            if (!resultSet.next()) return stations;
            resultSet.previous();
            while (resultSet.next()){
                stations.add(resultSet.getString("stationname"));
            }
            SqlUtils.closeConnection();
            return stations;
        }catch (Exception e){
            e.printStackTrace();
        }
        return stations==null? new ArrayList<>():stations;
    }

    @Override
    public List<String> lines(String stationname) {
        List<String> lines=new ArrayList<>();
        try {
            SqlUtils.getConnection();
            System.out.println(stationname);
            String sql="select * from line where stationname = ?";
            ResultSet resultSet=SqlUtils.query(sql,new String[]{stationname});
            if (!resultSet.next()) return lines;
            resultSet.previous();
            resultSet.next();
            lines=res_line(resultSet);
            SqlUtils.closeConnection();
            return lines;
        }catch (Exception e){
            e.printStackTrace();
        }
        return  lines==null? new ArrayList<>():lines;
    }

    @Override
    public List<Station> getShortLine(String start, String end) {
        List<Station> res=new ArrayList<>();
        try {
            SqlUtils.getConnection();
            String sql="select * from line";;
            HashMap<Integer,Station> stationHashMap=new HashMap<>();
            HashMap<Integer,Integer> dis=new HashMap<>();
            ResultSet resultSet=SqlUtils.query(sql,null);
            if (!resultSet.next()) return res;
            resultSet.previous();
            int st=0;
            int en=0;
            while (resultSet.next()){
                dis.put(resultSet.getInt(1),0x3f3f3f3f);
                if(resultSet.getString(2).equals(start)) st=resultSet.getInt(1);
                if(resultSet.getString(2).equals(end)) en=resultSet.getInt(1);
                stationHashMap.put(resultSet.getInt(1),
                        new Station(resultSet.getInt(1),
                                resultSet.getString(2),
                                res_line(SqlUtils.query("select * from line where stationid=?",new Integer[]{resultSet.getInt(1)})),
                                res_stationid(SqlUtils.query("select * from transfer where startid=?",new Integer[]{resultSet.getInt(1)})),
                                -1
                        ));
            }
            Queue<Integer> queue=new LinkedList<>();
            queue.offer(st);
            dis.put(st,0);
            List<Integer> temp=null;
            while (!queue.isEmpty()){
                int t= queue.poll();
                if(t==en) break;
                temp=stationHashMap.get(t).getNext();
                if(temp==null) continue;
                for(int i:temp){
                    if(dis.get(i)>dis.get(t)+1){
                        dis.put(i,dis.get(t)+1);
                        stationHashMap.get(i).setProvideid(t);
                        if(!queue.contains(i)) queue.add(i);
                    }
                }
            }
            int k=en;
            while (k!=-1){
                res.add(stationHashMap.get(k));
                k=stationHashMap.get(k).getProvideid();
            }
            Collections.reverse(res);
            SqlUtils.closeConnection();
            return res;
        }catch (Exception e){
            e.printStackTrace();
        }
        return res==null? new ArrayList<>():res;
    }

    @Override
    public List<Station> getLessLines(String start, String end) {
        List<Station> res=new ArrayList<>();
        try {
            SqlUtils.getConnection();
            String sql="select * from line";
            HashMap<Integer,Station> stationHashMap=new HashMap<>();
            HashMap<Integer,Integer> dis=new HashMap<>();
            ResultSet resultSet=SqlUtils.query(sql,null);
            if (!resultSet.next()) return res;
            resultSet.previous();
            int st=0;
            int en=0;
            while (resultSet.next()){
                dis.put(resultSet.getInt(1),0x3f3f3f3f);
                if(resultSet.getString(2).equals(start)) st=resultSet.getInt(1);
                if(resultSet.getString(2).equals(end)) en=resultSet.getInt(1);
                stationHashMap.put(resultSet.getInt(1),
                        new Station(resultSet.getInt(1),
                                resultSet.getString(2),
                                res_line(SqlUtils.query("select * from line where stationid=?",new Integer[]{resultSet.getInt(1)})),
                                res_stationid(SqlUtils.query("select * from transfer where startid=?",new Integer[]{resultSet.getInt(1)})),
                                -1
                        ));
            }
            Queue<Integer> queue=new LinkedList<>();
            queue.offer(st);
            dis.put(st,0);
            List<Integer> temp=null;
            while (!queue.isEmpty()){
                int t= queue.poll();
                temp=stationHashMap.get(t).getNext();
                if(temp==null) continue;
                for(int i:temp){
                    if(Collections.disjoint(stationHashMap.get(t).getLines(),stationHashMap.get(i).getLines())){
                        if(dis.get(i)>dis.get(t)+1){
                            dis.put(i,dis.get(t)+1);
                            stationHashMap.get(i).setProvideid(t);
                            if(!queue.contains(i)) queue.add(i);
                        }
                    }
                    else{
                        if(dis.get(i)>dis.get(t)){
                            dis.put(i,dis.get(t));
                            stationHashMap.get(i).setProvideid(t);
                            if(!queue.contains(i)) queue.add(i);
                        }
                    }
                }
            }
            int k=en;
            while (k!=-1){
                res.add(stationHashMap.get(k));
                k=stationHashMap.get(k).getProvideid();
            }
            Collections.reverse(res);
            SqlUtils.closeConnection();
            return res;
        }catch (Exception e){
            e.printStackTrace();
        }
        return res==null? new ArrayList<>():res;
    }

    @Override
    public List<String> res_line(ResultSet resultSet) {
        try {
            resultSet.beforeFirst();
            resultSet.next();
            List<String> lines=new ArrayList<>();
            if(resultSet.getInt(3)>0) lines.add("地铁1号线");
            if(resultSet.getInt(4)>0) lines.add("地铁八通线");
            if(resultSet.getInt(5)>0) lines.add("地铁2号线");
            if(resultSet.getInt(6)>0) lines.add("地铁4号线");
            if(resultSet.getInt(7)>0) lines.add("地铁大兴线");
            if(resultSet.getInt(8)>0) lines.add("地铁5号线");
            if(resultSet.getInt(9)>0) lines.add("地铁6号线");
            if(resultSet.getInt(10)>0) lines.add("地铁7号线");
            if(resultSet.getInt(11)>0) lines.add("地铁8号线");
            if(resultSet.getInt(12)>0) lines.add("地铁9号线");
            if(resultSet.getInt(13)>0) lines.add("地铁10号线");
            if(resultSet.getInt(14)>0) lines.add("地铁13号线");
            if(resultSet.getInt(15)>0) lines.add("地铁14号线");
            if(resultSet.getInt(16)>0) lines.add("地铁15号线");
            if(resultSet.getInt(17)>0) lines.add("地铁房山线");
            if(resultSet.getInt(18)>0) lines.add("地铁昌平线");
            if(resultSet.getInt(19)>0) lines.add("地铁亦庄线");
            if(resultSet.getInt(20)>0) lines.add("机场线");
            return lines;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Integer> res_stationid(ResultSet resultSet) {
        try {
            List<Integer> id=new ArrayList<>();
            while (resultSet.next()){
                id.add(resultSet.getInt(2));
            }
            return id;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
