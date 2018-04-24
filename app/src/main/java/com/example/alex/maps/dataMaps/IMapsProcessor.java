package com.example.alex.maps.dataMaps;


import java.util.Map;

public interface IMapsProcessor {

    int add(Map<Integer, String> map);

    int search(Map<Integer, String> map);

    int remove(Map<Integer, String> map);
}