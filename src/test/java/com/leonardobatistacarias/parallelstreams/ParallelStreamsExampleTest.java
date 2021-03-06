package com.leonardobatistacarias.parallelstreams;

import com.leonardobatistacarias.util.DataSet;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.leonardobatistacarias.util.CommonUtil.startTimer;
import static com.leonardobatistacarias.util.CommonUtil.timeTaken;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParallelStreamsExampleTest {

    ParallelStreamsExample parallelStreamsExample = new ParallelStreamsExample();

    @Test
    void stringTransform() {

        List<String> inputList = DataSet.namesList();

        startTimer();
        List<String> resultList = parallelStreamsExample.stringTransform(inputList);
        timeTaken();

        assertEquals(4, resultList.size());
        resultList.forEach(name -> {
            assertTrue(name.contains("-"));
        });
    }

}