package com.hb07.bi_onetomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class RunnerFetch07 {

    public static void main(String[] args) {

        Configuration con=new Configuration().configure("hibernate.cfg.xml").
                addAnnotatedClass(Student07.class).addAnnotatedClass(Book07.class);
        SessionFactory sf=con.buildSessionFactory();
        Session session=sf.openSession();
        Transaction tx=session.beginTransaction();

        Student07 student= session.get(Student07.class,1001);
        System.out.println(student.getBookList());
        student.getBookList().forEach(t-> System.out.println(t));
        student.getBookList().forEach(System.out::println);

        // !!! sql ile ogrenci bilgilerini alalim ( ogrenci ismi ve kitap ismi )
        String sqlQuery="select s.student_name,b.name from student07 s inner join book07 b on s.id=b.student_id";
        List<Object[]> resultList1= session.createSQLQuery(sqlQuery).getResultList();
        for (Object[] object: resultList1){
            System.out.println(Arrays.toString(object));
        }

        // !!! hql ile ogrenci bilgilerini alalim
        String hqlQuery="select s.name,b.name from Student07 s inner join fetch Book07 b on s.id=b.student.id";
        List<Object[]> resultList2= session.createQuery(hqlQuery).getResultList();
        resultList2.forEach(t-> System.out.println(Arrays.toString(t)));


        tx.commit();
        session.close();
        sf.close();

    }

}
