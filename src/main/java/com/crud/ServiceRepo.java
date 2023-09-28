package com.crud;

import org.hibernate.Session;

public interface ServiceRepo {
    abstract void aggeregateFunctiions(Session session);

    abstract public void populateData(Session session);

    abstract public void printTable(Session session);

    abstract public void updateName(Session session, int empId, String Name);

    abstract public void deleteRecord(Session session, int empId);

    abstract public void insertData(Session session, Employee empObject);
}
