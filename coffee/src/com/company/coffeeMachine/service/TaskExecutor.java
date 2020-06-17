package com.company.coffeeMachine.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// fixedThreadpul of outlets
public class TaskExecutor {

    private static ExecutorService commonExecutor;

    public static ExecutorService getCommonExecutor() {
        return commonExecutor;
    }

    public static void setCommonExecutor(int size) {
        commonExecutor = Executors.newFixedThreadPool(size);
    }
}
