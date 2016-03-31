package com.test2;
import java.util.List;

/**
 *auto generate
 *
 *@author Young
 *
 */
public class RootBean{
    Integer code;
    Data data;
    List<Integer> intList;
    List<Double> doubleList;
    List<List<List<String>>> multyList;
    String notify_id;

    public Integer getCode(){
        return code;
    }

    public void setCode(Integer code){
        this.code=code;
    }

    public Data getData(){
        return data;
    }

    public void setData(Data data){
        this.data=data;
    }

    public List<Integer> getIntList(){
        return intList;
    }

    public void setIntList(List<Integer> intList){
        this.intList=intList;
    }

    public List<Double> getDoubleList(){
        return doubleList;
    }

    public void setDoubleList(List<Double> doubleList){
        this.doubleList=doubleList;
    }

    public List<List<List<String>>> getMultyList(){
        return multyList;
    }

    public void setMultyList(List<List<List<String>>> multyList){
        this.multyList=multyList;
    }

    public String getNotify_id(){
        return notify_id;
    }

    public void setNotify_id(String notify_id){
        this.notify_id=notify_id;
    }

}