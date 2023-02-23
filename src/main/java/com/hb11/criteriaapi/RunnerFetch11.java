package com.hb11.criteriaapi;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class RunnerFetch11 {

    public static void main(String[] args) {

        Configuration con=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student11.class);
        SessionFactory sf=con.buildSessionFactory();
        Session session=sf.openSession();
        Transaction tx=session.beginTransaction();

        Student11 student= session.get(Student11.class,1L);
        student.setName("Guncellenmis "+student.getName());
        session.update(student);

        int sMathGrade=80;
        int lMathGrade=75;

        String hqlQuery="update Student11 s set s.mathGrade=:sMath where s.mathGrade<:lMath";
        Query query= session.createQuery(hqlQuery);
        query.setParameter("sMath",sMathGrade);
        query.setParameter("lMath",lMathGrade);
        int numOfRecords= query.executeUpdate();
        System.out.println("Degistirilen kayit sayisi: "+numOfRecords);

        // !!! CriteriaAPI
        CriteriaBuilder criteriaBuilder=session.getCriteriaBuilder();
        CriteriaQuery<Student11> criteriaQuery= criteriaBuilder.createQuery(Student11.class);
        Root<Student11> root= criteriaQuery.from(Student11.class);

//        // 1. ornek
//        criteriaQuery.select(root);
//        Query<Student11> query1= session.createQuery(criteriaQuery);
//        List<Student11> resultList= query1.getResultList();
//        resultList.forEach(System.out::println);
//
//        // 2. ornek
//        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("name"),"Student Name6"));
//        Query<Student11> query2=session.createQuery(criteriaQuery);
//        List<Student11> resultList2=query2.getResultList();
//        resultList2.forEach(System.out::println);
//
//        // 3.ornek: mathGrade degeri 80 den buyuk olan datalari getirelim
//        criteriaQuery.select(root).where(criteriaBuilder.greaterThan(root.get("mathGrade"),80));
//        Query<Student11> query3=session.createQuery(criteriaQuery);
//        List<Student11> resultList3=query3.getResultList();
//        resultList3.forEach(System.out::println);
//
//        // 4. ornek: mathGrade degeri 95 den kucuk olan datalari getirelim
//        criteriaQuery.select(root).where(criteriaBuilder.lessThan(root.get("mathGrade"),95));
//        Query<Student11> query4=session.createQuery(criteriaQuery);
//        List<Student11> resultList4=query4.getResultList();
//        resultList4.forEach(System.out::println);

        // 5. ornek: id si 1 veya mathGrade i 75 den buyuk olan recordu bulalim
        Long id=1L;
        Predicate predicateForId= criteriaBuilder.equal(root.get("id"),id);
        Predicate predicateForMathGrade=criteriaBuilder.greaterThan(root.get("mathGrade"),75);
        Predicate predicateQuery=criteriaBuilder.or(predicateForId,predicateForMathGrade);
        criteriaQuery.where(predicateQuery);
        Query<Student11> query5= session.createQuery(criteriaQuery);
        List<Student11> resultList6=query5.getResultList();
        resultList6.forEach(System.out::println);

        tx.commit();
        session.close();
        sf.close();

    }

}
