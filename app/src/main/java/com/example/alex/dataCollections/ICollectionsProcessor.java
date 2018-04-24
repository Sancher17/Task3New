package com.example.alex.dataCollections;

import java.util.List;

public interface ICollectionsProcessor {

    int addToStart(List<Integer> list);

    int addToMiddle(List<Integer> list);

    int addToEnd(List<Integer> list);

    int search(List<Integer> list);

    int removeStart(List<Integer> list);

    int removeMiddle(List<Integer> list);

    int removeEnd(List<Integer> list);
}