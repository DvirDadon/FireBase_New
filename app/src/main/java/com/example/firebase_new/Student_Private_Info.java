package com.example.firebase_new;

public class Student_Private_Info {
    private String Student_N;
    private String Address;
    private int Student_P;
    private int Home_Phone;
    private String Mom_N;
    private int Mom_P;
    private String Dad_N;
    private int Dad_P;
    private String stuID;

    public Student_Private_Info(){ }

    public Student_Private_Info(String Student_N,String Address, int Student_P,int home_Phone,String Mom_N , int Mom_P, String Dad_N,int Dad_P,String stuID){
        this.Student_N = Student_N;
        this.Address = Address;
        this.Student_P = Student_P;
        this.Home_Phone = home_Phone;
        this.Mom_N = Mom_N;
        this.Mom_P = Mom_P;
        this.Dad_N = Dad_N;
        this.Dad_P = Dad_P;
        this.stuID = stuID;
    }
    public String getStuID() {
        return stuID;
    }

    public void setStuID(String StuID) {
        this.stuID=StuID;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address=Address;
    }

    public int getStudent_P() {
        return Student_P;
    }

    public void setStudent_P(int Student_P) {
        this.Student_P=Student_P;
    }

    public String getStudent_N() {
        return Student_N;
    }

    public void setStudent_N(String Student_N) {
        this.Student_N=Student_N;
    }

    public int getHome_Phone() {
        return Home_Phone;
    }

    public void setHome_Phone(int Home_Phone) {
        this.Home_Phone=Home_Phone;
    }

    public String getMom_N() {
        return Mom_N;
    }

    public void setClassSubject(String Mom_N) {
        this.Mom_N=Mom_N;
    }

    public int getMom_P() {
        return Mom_P;
    }

    public void setMom_P(int Mom_P) {
        this.Mom_P=Mom_P;
    }

    public String getDad_N() {
        return Dad_N;
    }

    public void setDad_N(String Dad_N) {
        this.Dad_N=Dad_N;
    }

    public int getDad_P() {
        return Dad_P;
    }

    public void setDad_P(int Dad_P) {
        this.Dad_P=Dad_P;
    }
}
