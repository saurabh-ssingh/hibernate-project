package org.example;

import java.util.List;
import org.example.entity.Certificate;
import org.example.entity.Student;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Main {

  public static void main(String[] args) {

    final Student student = new Student();
    student.setName("Saurabh");
    student.setPhoneNumber("+919792733299");
    student.setAbout("I am student....");

    Certificate certificate = new Certificate();
    certificate.setTitle("java certification");
    certificate.setAbout("This is java certification....");
    certificate.setLink("http://loalhost:8080/java_certificatte");
    certificate.setStudent(student);

    student.setCertificateList(List.of(certificate));


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