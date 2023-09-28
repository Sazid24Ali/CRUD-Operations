package com.crud;
// Enitity class

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "hibernate_employee")
public class Employee {
    @Id
    private int eid;
    private String ename;
    private int age;
    private int salary;

    public Employee(int eid, String ename, int age, int salary) {
        this.eid = eid;
        this.ename = ename;
        this.age = age;
        this.salary = salary;
    }

    public int getEid() {
        return eid;
    }

    public String getEname() {
        return ename;
    }

    public int getAge() {
        return age;
    }

    public int getSalary() {
        return salary;
    }

    public Employee() {
        // Empty COnstructor
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return this.eid + " : " + this.ename + " : " + this.age + " : " + this.salary;
    }

}
