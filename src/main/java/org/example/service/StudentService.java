package org.example.service;

import java.util.List;
import org.example.entity.Student;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class StudentService {
  private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

  //save
  public void saveStudent(Student student){
    try(Session session = sessionFactory.openSession()) {
      Transaction beginTransaction = session.beginTransaction();
      session.persist(student);
      beginTransaction.commit();
    }catch (Exception e){
      e.printStackTrace();
    }
  }


  //get by id
  public Student getById(Integer studentId){
    Student student = null;
    try(Session session = sessionFactory.openSession()) {
       student = session.get(Student.class,studentId);
      return student;
    }catch (Exception e){
      e.printStackTrace();
    }
    return student;
  }

  //update
  public Student updateStudent(Integer studentId,Student student){
    Student savedStudentDetails = null;
     try(Session session = sessionFactory.openSession()) {
       Transaction transaction = session.beginTransaction();
       savedStudentDetails = session.get(Student.class,studentId);
       if(savedStudentDetails != null){
         savedStudentDetails.setName(student.getName());
         savedStudentDetails.setAbout(student.getAbout());
         savedStudentDetails.setPhoneNumber(student.getPhoneNumber());
         session.merge(savedStudentDetails);
       }
       transaction.commit();
     }
     return savedStudentDetails;
  }

  //delete student
  public void deleteStudent(Integer studentId){
     try(Session session = sessionFactory.openSession()) {
       Transaction transaction = session.beginTransaction();
       Student student = session.get(Student.class,studentId);
       if(student!= null){
         session.remove(student);
       }
       transaction.commit();
     } catch (Exception e) {
       e.printStackTrace();
     }
  }

  //get all student using HQL
  public List<Student> getAllStudentUsingHQL(){
    try(Session session = sessionFactory.openSession()) {
      String getHQL = "FROM Student";
      Query<Student> query = session.createQuery(getHQL, Student.class);
      return query.list();

    }
  }

  //get student by name

  public Student getStudentByNameHQL(String studentName){
    try(Session session = sessionFactory.openSession()) {
      String getByHQLName = "FROM Student WHERE name = :studentName";
      Query<Student> query = session.createQuery(getByHQLName, Student.class);
      query.setParameter("studentName", studentName);
      return query.uniqueResult();
    }
  }

}
