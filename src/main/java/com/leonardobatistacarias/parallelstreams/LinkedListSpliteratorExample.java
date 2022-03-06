package com.leonardobatistacarias.parallelstreams;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.leonardobatistacarias.util.CommonUtil.startTimer;
import static com.leonardobatistacarias.util.CommonUtil.timeTaken;

public class LinkedListSpliteratorExample {

    public List<Integer> multiplyEachValue(LinkedList<Integer> inputList, int multiplyValue, boolean isParallel) {
        startTimer();
        Stream<Integer> integerStream = inputList.stream();

        if(isParallel)
            integerStream.parallel();

        List<Integer> resultList = integerStream
                .map(integer -> integer*multiplyValue)
                .collect(Collectors.toList());
        timeTaken();
        return resultList;
    }

}
