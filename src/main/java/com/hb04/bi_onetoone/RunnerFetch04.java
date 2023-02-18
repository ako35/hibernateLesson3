package com.hb04.bi_onetoone;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;
import java.util.List;

public class RunnerFetch04 {

    public static void main(String[] args) {

        Configuration con=new Configuration().configure("hibernate.cfg.xml").
                addAnnotatedClass(Student04.class).addAnnotatedClass(Diary04.class);
        SessionFactory sf= con.buildSessionFactory();
        Session session=sf.openSession();
        Transaction tx= session.beginTransaction();

//        Student04 student1= session.get(Student04.class,1001);
//        Diary04 diary1= session.get(Diary04.class,101);
//        System.out.println(student1);
//        System.out.println(diary1);
//        System.out.println(diary1.getStudent());
//        System.out.println(student1.getDairy());

//        String hqlQuery="select s.name, d.name from Student04 s inner join fetch Diary04 d on s.id=d.student.id";
//        List<Object[]> resultList= session.createQuery(hqlQuery).getResultList();
//        resultList.stream().forEach(t-> System.out.println(Arrays.toString(t)));

//        String hqlQuery2="select s.name, d.name from Student04 s left join fetch Diary04 d on s.id=d.student.id";
//        List<Object[]> resultList2=session.createQuery(hqlQuery2).getResultList();
//        resultList2.forEach(t-> System.out.println(Arrays.toString(t)));

//        String hqlQuery3="select d.name, s.name from Student04 s right join fetch Diary04 d on s.id=d.student";
//        List<Object[]> resultList3=session.createQuery(hqlQuery3).getResultList();
//        resultList3.forEach(t-> System.out.println(Arrays.toString(t)));

        String hqlQuery4="select s.name, d.name from Student04 s full join fetch Diary04 d on s.id=d.student.id";
        List<Object[]> resultList4=session.createQuery(hqlQuery4).getResultList();
        resultList4.forEach(t-> System.out.println(Arrays.toString(t)));

        tx.commit();
        session.close();
        sf.close();

    }

}
