package com.hb07.bi_onetomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;
import java.util.List;

public class RunnerFetch07 {

    public static void main(String[] args) {

        Configuration con=new Configuration().configure("hibernate.cfg.xml").
                addAnnotatedClass(Student07.class).addAnnotatedClass(Book07.class);
        SessionFactory sf=con.buildSessionFactory();
        Session session=sf.openSession();
        Transaction tx=session.beginTransaction();

//        Student07 student= session.get(Student07.class,1001);
//        System.out.println(student.getBookList());
//        student.getBookList().forEach(t-> System.out.println(t));
//        student.getBookList().forEach(System.out::println);
//
//        // !!! sql ile ogrenci bilgilerini alalim ( ogrenci ismi ve kitap ismi )
//        String sqlQuery="select s.student_name,b.name from student07 s inner join book07 b on s.id=b.student_id";
//        List<Object[]> resultList1= session.createSQLQuery(sqlQuery).getResultList();
//        for (Object[] object: resultList1){
//            System.out.println(Arrays.toString(object));
//        }
//
//        // !!! hql ile ogrenci bilgilerini alalim
//        String hqlQuery="select s.name,b.name from Student07 s inner join fetch Book07 b on s.id=b.student.id";
//        List<Object[]> resultList2= session.createQuery(hqlQuery).getResultList();
//        resultList2.forEach(t-> System.out.println(Arrays.toString(t)));
//
//        // !!! sql ile delete islemi
//        String sqlQuery2="delete from book07";
//        int numOfDeletedRecords=session.createSQLQuery(sqlQuery2).executeUpdate();
//        System.out.println("silinen kayit sayisi: "+numOfDeletedRecords);
//
//        // !!! hql ile delete islemi
//        String hqlQuery2="delete from Student07";
//        int numOfDeletedRecords2= session.createQuery(hqlQuery2).executeUpdate();
//        System.out.println("silinen kayit sayisi: "+numOfDeletedRecords2);
//
//        // !!! hql ile kitap ismi A Book olan kitabi siliniz
//        String hqlQuery3="delete from Book07 b where b.name='A Book'";
//        int numOfDeletedRecords3= session.createQuery(hqlQuery3).executeUpdate();
//        System.out.println("silinen kayit sayisi: "+numOfDeletedRecords3);
//
//        // !!! 1001 id li student objemi delete methoduyla silelim
//        Student07 student2=session.get(Student07.class,1001);
//        session.delete(student2);

        // student2.getBookList().set(0,null); // listedeki 0. indexi komple siler

        // !!! book classinda isminin icinde Java gecen student kayitlarini alalim
        String sqlQuery4="select * from book07 b where b.name like '%Java%'";
        List<Object[]> resultList4= session.createSQLQuery(sqlQuery4).getResultList();
        resultList4.forEach(t-> System.out.println(Arrays.toString(t)));

        String hqlQuery5="select s from Student07 s join s.bookList b where b.name like '%Java%'";
        List<Student07> resultList5= session.createQuery(hqlQuery5,Student07.class).getResultList();
        resultList5.forEach(System.out::println);

        tx.commit();
        session.close();
        sf.close();

    }

}
