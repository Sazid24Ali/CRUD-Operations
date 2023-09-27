package com.crud;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import org.hibernate.Transaction;

public class App {
    static SessionFactory sFactory;
    // static Session session;

    public static Session generateSession() {
        // Returns a Session Object
        Configuration cfg = new Configuration();
        cfg.configure();
        sFactory = cfg.buildSessionFactory();
        // session = sFactory.openSession();
        // return session;
        return sFactory.openSession();
    }

    public static void populateData(Session session) {
        /*
         * This Functions has Sample Data THat Can be USed For Initlasing the Table
         * Generating Sample Data And Storing The Data to the Table
         */
        // Using Constructor
        Employee empObject1 = new Employee(101, "Employee 1", 25, 25000);
        Employee empObject2 = new Employee(102, "Employee 2", 24, 24000);
        Employee empObject3 = new Employee(103, "Employee 3", 26, 26000);
        Employee empObject4 = new Employee(104, "Employee 4", 24, 24000);

        // Using Setter MEthods
        Employee empObject5 = new Employee();
        empObject5.setEid(105);
        empObject5.setEname("Employee 5");
        empObject5.setAge(23);
        empObject5.setSalary(23000);

        // Begining Transaction
        Transaction transaction = session.beginTransaction();

        // Saving the Data To Table ( Used persist as save() method is deprecated !! )
        session.persist(empObject1);
        session.persist(empObject2);
        session.persist(empObject3);
        session.persist(empObject4);
        session.persist(empObject5);

        // Commiting the Data To Table ( Permenant Save )
        transaction.commit();
    }

    public static void printTable(Session session) {
        // Displaying the Whole Table
        Query<Employee> queryObject = session.createQuery("from Employee", Employee.class);
        List<Employee> resuList = queryObject.getResultList();
        for (Employee emp : resuList) {
            System.out.println(emp);
        }
    }

    public static void aggeregateFunctiions(Session session) {
        // Aggerate Functions

        List<Long> result;
        Query<Long> queryObject;

        // Count
        queryObject = session.createQuery("select count(ename) from Employee", Long.class);
        result = queryObject.getResultList();
        System.out.println("THe Total Number Of Employees are : " + result.get(0));

        // Max
        queryObject = session.createQuery("select max(salary) from Employee", Long.class);
        result = queryObject.getResultList();
        System.out.println("THe Maximum Salary Of Employees are : " + result.get(0));

        // Min
        queryObject = session.createQuery("select min(salary) from Employee", Long.class);
        result = queryObject.getResultList();
        System.out.println("THe Minumum Salary Of Employees are : " + result.get(0));

        // Sum
        queryObject = session.createQuery("select sum(salary) from Employee", Long.class);
        result = queryObject.getResultList();
        System.out.println("THe Sum of the Salary Of Employees are : " + result.get(0));

        // Avg
        queryObject = session.createQuery("select avg(salary) from Employee", Long.class);
        result = queryObject.getResultList();
        System.out.println("THe Avegrage of the Salary Of Employees are : " + result.get(0));

    }

    public static void insertData(Session session, Employee empObject) {
        // insert new Data Into Table

        // Begining Transaction
        Transaction transaction = session.beginTransaction();

        // Saving the Data To Table ( Used persist as save() method is deprecated !! )
        session.persist(empObject);

        // Commiting the Data To Table ( Permenant Save )
        transaction.commit();

        System.out.println(empObject.getEname() + " Added to The Table Sucessfully");
    }

    public static void updateName(Session session, int empId, String Name) {
        // Updating THe EMployee Name Using EMployee Id
        Transaction transaction = session.beginTransaction();
        String hql = "UPDATE Employee SET ename = :NAME WHERE eid = :id";
        int updatedCount = session.createQuery(hql)
                .setParameter("id", empId)
                .setParameter("NAME", Name)
                .executeUpdate();
        if (updatedCount >= 1) {
            System.out.println("THe EmpId " + empId + "'s Names is Updated Successfully");
        } else {
            System.out.println("THe EmpId " + empId + " is Not Found");
        }
        transaction.commit();

    }

    public static void deleteRecord(Session session, int empId) {
        // Deleting the Record Using EMployee Id
        Transaction transaction = session.beginTransaction();
        String hql = "DELETE FROM Employee WHERE eid = :id";
        int deletedCount = session.createQuery(hql)
                .setParameter("id", empId)
                .executeUpdate();

        if (deletedCount >= 1) {
            System.out.println("THe EmpId " + empId + " is Deleted");
        } else {
            System.out.println("THe EmpId " + empId + " is Not Found");
        }
        transaction.commit();

    }

    public static void main(String[] args) {
        // Genenrating Session
        Session session = generateSession();

        // Populating  Sample Data
        populateData(session);

        // Retriving The Sample Data
        System.out.println("Printing the Present Data");
        printTable(session);

        // Aggregate Functions
        aggeregateFunctiions(session);

        Employee empObject = new Employee(210, "SampleData", 25, 25000);
        insertData(session, empObject);

        //Updating THe Name Of the empid 210
        updateName(session, 210, "New Name");
        
        //deleting THe Record 210 
        deleteRecord(session, 210);

        // Closing The Session And SessionFactory
        session.close();
        sFactory.close();
    }

}
