package org.example;

import org.example.entity.Student;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Main {

  public static void main(String[] args) {

    final Student student = new Student();
    student.setName("Name");
    student.setPhoneNumber("+919792733298");
    student.setAbout("I am student..");


    final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    final Session session = sessionFactory.openSession();
    Transaction transaction = null;
    try {
      transaction = session.beginTransaction();
      session.persist(student);
      transaction.commit();
      System.out.println("Student saved successfully in database........");
    }catch (Exception e){
      if(transaction != null){
        transaction.rollback();
      }
      e.printStackTrace();
    }finally {
      session.close();
    }



  }
}