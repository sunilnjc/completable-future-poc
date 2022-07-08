package com.sunil.async.service;

import com.sunil.async.dto.Employee;
import com.sunil.async.repository.EmployeeRepo;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public interface EmployeeService {
      default CompletableFuture<Void> sendRemindersToEmployee() {

          CompletableFuture<Void> future = CompletableFuture.supplyAsync(() ->{
            System.out.println("Fetch Employee : " + Thread.currentThread().getName());
            return EmployeeRepo.retrieveEmployees();
        }).thenApply((employees) -> {
            System.out.println("Filter new joiners Employee : " + Thread.currentThread().getName());
            return employees.parallelStream()
                    .filter(employee -> "TRUE".equalsIgnoreCase(employee.getNewJoiner()))
                    .collect(Collectors.toList());
        }).thenApply((employees) -> {
            System.out.println("Filter new joiners learning pending : " + Thread.currentThread().getName());
            return employees.parallelStream()
                    .filter(employee -> "TRUE".equalsIgnoreCase(employee.getLearningPending()))
                    .collect(Collectors.toList());
        }).thenApply((employees) -> {
            System.out.println("retrieve emails : " + Thread.currentThread().getName());
            return employees.parallelStream()
                    .map(Employee::getEmail)
                    .collect(Collectors.toList());
        }).thenAccept((emails) ->{
            System.out.println("Sending Emails : " + Thread.currentThread().getName());
            emails.parallelStream().forEach(EmployeeService::sendEmail);
        });

        return future;
    }

    default void sendRemindersToEmployee2() {
      EmployeeRepo.retrieveEmployees()
              .stream()
                .filter(employee -> "TRUE".equalsIgnoreCase(employee.getNewJoiner()))
                .filter(employee -> "TRUE".equalsIgnoreCase(employee.getLearningPending()))
                .map(Employee::getEmail)
                .forEach(EmployeeService::sendEmail);
    }

    static void sendEmail(String email){
        System.out.println("Training Reminder email sent " + email);
    }
}
