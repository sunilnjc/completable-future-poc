package com.sunil.async.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunil.async.dto.Employee;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class EmployeeRepo {

    public static List<Employee> retrieveEmployees() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper
                    .readValue(new File("employees.json"),
                            new TypeReference<List<Employee>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
