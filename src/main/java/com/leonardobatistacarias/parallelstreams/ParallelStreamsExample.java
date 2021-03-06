package com.leonardobatistacarias.parallelstreams;

import com.leonardobatistacarias.util.DataSet;

import java.util.List;
import java.util.stream.Collectors;

import static com.leonardobatistacarias.util.CommonUtil.delay;
import static com.leonardobatistacarias.util.CommonUtil.startTimer;
import static com.leonardobatistacarias.util.CommonUtil.timeTaken;
import static com.leonardobatistacarias.util.LoggerUtil.log;

public class ParallelStreamsExample {

    public List<String> stringTransform(List<String> namesList) {
        return namesList
                .parallelStream()
                .map(this::addNameLengthTransform)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<String> nameList = DataSet.namesList();
        ParallelStreamsExample parallelStreamsExample = new ParallelStreamsExample();
        startTimer();
        List<String> resultList = parallelStreamsExample.stringTransform(nameList);
        log("resultList: " + resultList);
        timeTaken();
    }

    private String addNameLengthTransform(String name) {
        delay(500);
        return name.length()+" - "+name ;
    }
}
