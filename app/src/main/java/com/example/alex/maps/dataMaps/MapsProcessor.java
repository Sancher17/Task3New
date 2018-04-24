package com.example.alex.maps.dataMaps;


import com.example.alex.utils.Logger;

import java.util.Map;

import javax.inject.Inject;

import static com.example.alex.maps.MapsFragment.INPUT_NUMBER_MAP;


public class MapsProcessor implements IMapsProcessor {

    private static Logger LOGGER = new Logger(MapsProcessor.class);

    @Inject
    public MapsProcessor() {
    }

    public int add(Map<Integer, String> map) {
        map.clear();
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < INPUT_NUMBER_MAP; i++) {
            map.put(i, "Map" + i);
        }
        int result = (int) (System.currentTimeMillis() - t1);
        LOGGER.log("add // map size: " + map.size() + "// result " + result + getClassName(map));
        return result;
    }

    public int search(Map<Integer, String> map) {
        map.clear();
        add(map);
        long t1 = System.currentTimeMillis();
        int value = INPUT_NUMBER_MAP / 2;
        int result = -1;
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            if (entry.getKey().equals(value)) {
                result = (int) (System.currentTimeMillis() - t1);
            }
        }
        LOGGER.log("search // get value: " + value + "//map size " + map.size() + "// result " + result + getClassName(map));
        return result;
    }

    public int remove(Map<Integer, String> map) {
        map.clear();
        add(map);
        LOGGER.log("remove // map size: " + map.size());
        int size = map.size();
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            map.remove(i);
        }
        int result = (int) (System.currentTimeMillis() - t1);
        LOGGER.log("remove // map size: " + map.size() + "// result " + result + getClassName(map));
        return result;
    }

    private static String getClassName(Map map) {
        return " // " + map.getClass().getSimpleName();
    }
}

