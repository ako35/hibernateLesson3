package com.hb10.idgeneration;

import javax.persistence.*;

@Entity
public class Student10 {

    @GeneratedValue(generator = "sequence",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "sequence",sequenceName = "student_seq",initialValue = 1000,allocationSize = 10)
    @Id
    private int id;

    @Column(name = "student_name",nullable = false)
    private String name;

    private int grade;

    public int getId() {
        return id;
    }

//    public void setId(int id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
