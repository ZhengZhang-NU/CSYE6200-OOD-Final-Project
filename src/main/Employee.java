package main;

public class Employee extends Person {
    private String employeeId;

    public Employee(String name, String phoneNumber, String employeeId) {
        super(name, phoneNumber);
        this.employeeId = employeeId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    // Employee-specific methods...



}