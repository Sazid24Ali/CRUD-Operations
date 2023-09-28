package com.crud;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class ServiceRepoImpl implements ServiceRepo {
    @Override
    public void aggeregateFunctiions(Session session) {
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
    @Override
    public  void populateData(Session session) {
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
    @Override
    public void printTable(Session session) {
        // Displaying the Whole Table
        Query<Employee> queryObject = session.createQuery("from Employee", Employee.class);
        List<Employee> resuList = queryObject.getResultList();
        for (Employee emp : resuList) {
            System.out.println(emp);
        }
    }
    @Override
    public void updateName(Session session, int empId, String Name) {
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
    @Override
    public void deleteRecord(Session session, int empId) {
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
    @Override
    public void insertData(Session session, Employee empObject) {
        // insert new Data Into Table

        // Begining Transaction
        Transaction transaction = session.beginTransaction();

        // Saving the Data To Table ( Used persist as save() method is deprecated !! )
        session.persist(empObject);

        // Commiting the Data To Table ( Permenant Save )
        transaction.commit();

        System.out.println(empObject.getEname() + " Added to The Table Sucessfully");
    }
}
