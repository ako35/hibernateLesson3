package com.hb01.annotation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RunnerFetch01 {

    public static void main(String[] args) {

        Configuration con=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student01.class);
        SessionFactory sf=con.buildSessionFactory();
        Session session=sf.openSession();
        Transaction tx=session.beginTransaction();

//        Student01 student1=session.get(Student01.class,1001);
//        Student01 student2=session.get(Student01.class,1002);
//        Student01 student3=session.get(Student01.class,1003);
//        System.out.println(student1);
//        System.out.println(student2);
//        System.out.println(student3);

//        String sqlQuery="select * from t_student01";
//        List<Object[]> resultList=session.createSQLQuery(sqlQuery).getResultList();
//        for (Object[] object: resultList){
//            System.out.println(Arrays.toString(object));
//        }

//        String hqlQuery="from Student01";
//        List<Student01> resultList2= session.createQuery(hqlQuery,Student01.class).getResultList();
//        resultList2.stream().forEach(t-> System.out.println(t));

//        String sqlQuery2="select * from t_student01 where student_name='Samet Guler'";
//        Object[] uniqueResult1= (Object[]) session.createSQLQuery(sqlQuery2).uniqueResult();
//        System.out.println(Arrays.toString(uniqueResult1));
//
//        System.out.println(uniqueResult1[0]+" "+uniqueResult1[1]+" "+uniqueResult1[2]);

//        String hqlQuery3="from Student01 where name='Ali Baskan'";
//        Student01 student01= session.createQuery(hqlQuery3,Student01.class).uniqueResult();
//        System.out.println(student01);

//        String hqlQuery4="from Student01 std where std.name='Ali Guler'";
//        List<Student01> resultList= session.createQuery(hqlQuery4,Student01.class).getResultList();
//        resultList.stream().forEach(t-> System.out.println(t));

//        String hqlQuery5="from Student01 s where s.grade=90";
//        List<Student01> resultList5= session.createQuery(hqlQuery5,Student01.class).getResultList();
//        resultList5.stream().forEach(t-> System.out.println(t));

        String hqlQuery6="select s.id, s.name from Student01 s where s.grade=90";
        List<Object[]> resultList6=session.createQuery(hqlQuery6).getResultList();
        resultList6.stream().forEach(t-> System.out.println(Arrays.toString(t)));



        tx.commit();
        session.close();
        sf.close();

    }

}
