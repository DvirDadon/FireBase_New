package com.example.firebase_new;

public class StuGrades {
    private String subject;
    private int quarter;
    private int grade;
    private String stuID;

    public StuGrades(){}

    public StuGrades(String subject , int quarter , int grade,String stuID){
        this.subject = subject;
        this.quarter = quarter;
        this.grade = grade;
        this.stuID = stuID;
    }

    public String getStuID() {
        return stuID;
    }

    public void setStuID(String StuID) {
        this.stuID=StuID;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject=subject;
    }

    public int getQuarter() {
        return quarter;
    }

    public void setQuarter(int quarter) {
        this.quarter=quarter;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int Grade) {
        this.grade=Grade;
    }
}
