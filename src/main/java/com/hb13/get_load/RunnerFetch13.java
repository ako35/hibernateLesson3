package com.hb13.get_load;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/*
    get() -->   gercek nesneyi dondurur,
                nesne yoksa null doner
                nesnenin oldugundan emin degilseniz get() kullanin
                donen nesneyi hemen kullanacaksaniz get() kullanin

    load() -->  proxy nesne dondurur, gercek nesnenin golgesi,
                nesne yoksa exception firlatir
                donen nesne uzerinde delete yapilacaksa kullanilabilir.
 */

public class RunnerFetch13 {

    public static void main(String[] args) {

        Configuration con=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student13.class);
        SessionFactory sf=con.buildSessionFactory();
        Session session=sf.openSession();
        Transaction tx=session.beginTransaction();

//        Student13 student1=session.get(Student13.class,1L);
//        System.out.println(student1.getId());
//        System.out.println(student1.getName());
//
//        System.out.println();
//
//        Student13 student2=session.load(Student13.class,2L);
//        System.out.println(student2.getName());

        // db de olmayan id yi cagirirsak?
//        Student13 student3=session.get(Student13.class,5L);
//        System.out.println(student3.getId());
//        if (student3!=null){
//            System.out.println(student3.getId());
//            System.out.println(student3.getName());
//        }

//        Student13 student4=session.load(Student13.class,10L);
//
//        if (student4!=null){
//            System.out.println(student4.getId());
//            System.out.println(student4.getName());
//        }

        Student13 student5=session.get(Student13.class,1L);
        session.delete(student5);

        Student13 student6=session.load(Student13.class,1L);
        session.delete(student6);

        tx.commit();
        session.close();
        sf.close();

    }

}
