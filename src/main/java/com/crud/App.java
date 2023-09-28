package com.crud;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


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


    public static void main(String[] args) {
        // Genenrating Session
        Session session = generateSession();
        ServiceRepoImpl operationImpl = new ServiceRepoImpl();

        // Populating  Sample Data
        operationImpl.populateData(session);

        // Retriving The Sample Data
        System.out.println("Printing the Present Data");
        operationImpl.printTable(session);

        // Aggregate Functions
        operationImpl.aggeregateFunctiions(session);

        Employee empObject = new Employee(210, "SampleData", 25, 25000);
        operationImpl.insertData(session, empObject);

        //Updating THe Name Of the empid 210
        operationImpl.updateName(session, 210, "New Name");
        
        //deleting THe Record 210 
        operationImpl.deleteRecord(session, 210);

        // Closing The Session And SessionFactory
        session.close();
        sFactory.close();
    }

}
