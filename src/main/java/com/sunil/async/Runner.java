package com.sunil.async;

import com.sunil.async.service.EmployeeService;
import com.sunil.async.service.EmployeeServiceImpl;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Runner {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();
        EmployeeService service = new EmployeeServiceImpl();
       service.sendRemindersToEmployee2();
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time taken to process the request "+ totalTime);
    }
}
