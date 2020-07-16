package com.c0220i1.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

//@SpringBootTest(classes = ApplicationContext.class)
public class testSum {

    @Test
    public void testSum1(){
        Assertions.assertEquals(2, 1 + 1);
    }

    @Test
    public void testSum2(){
        Assertions.assertEquals(0, 0 + 0);
    }

}
