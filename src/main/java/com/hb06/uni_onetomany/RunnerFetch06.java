package com.hb06.uni_onetomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;
import java.util.List;

public class RunnerFetch06 {

    public static void main(String[] args) {

        Configuration configuration=new Configuration().configure("hibernate.cfg.xml").
                addAnnotatedClass(Student06.class).addAnnotatedClass(Book06.class);
        SessionFactory sf=configuration.buildSessionFactory();
        Session session=sf.openSession();
        Transaction tx=session.beginTransaction();

        Student06 student1=session.get(Student06.class,1001);
        System.out.println(student1);

        // !!! hql ile id si 101 olan kitabi getirelim
        String hqlQuery1="from Book06 b where b.id=101";
        Book06 book1=session.createQuery(hqlQuery1, Book06.class).uniqueResult();
        System.out.println(book1);

        // !!! bir ogrencinin kitaplarini ogrenci id ye gore getirme
        Student06 student2=session.get(Student06.class,1001);
        System.out.println(student2.getBookList());

        String hqlQuery2="select b.id, b.name from Student06 s inner join s.bookList b where s.id=1001";
        List<Object[]> resultList1=session.createQuery(hqlQuery2).getResultList();
        resultList1.forEach(t-> System.out.println(Arrays.toString(t)));

        tx.commit();
        session.close();
        sf.close();

    }

}
