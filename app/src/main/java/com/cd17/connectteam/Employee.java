package com.cd17.connectteam;

public class Employee {
    private String id;
    private String fname;
    private String lname;
    private String mail;
    private String phone;
    private String city;
    private String doj;
    private String gender;
    private String age;
    private String qualification;
    private String domain;
    private String yoe;
    private String role;
    private String salary;
    private String leaves;
    private String dept;
    private String project;

    public Employee() {
    }

    public Employee(String id, String fname, String lname, String mail, String phone, String city, String doj, String gender, String age, String qualification, String domain, String yoe, String role, String salary, String leaves, String dept, String project) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.mail = mail;
        this.phone = phone;
        this.city = city;
        this.doj = doj;
        this.gender = gender;
        this.age = age;
        this.qualification = qualification;
        this.domain = domain;
        this.yoe = yoe;
        this.role = role;
        this.salary = salary;
        this.leaves = leaves;
        this.dept = dept;
        this.project = project;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDoj() {
        return doj;
    }

    public void setDoj(String doj) {
        this.doj = doj;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getYoe() {
        return yoe;
    }

    public void setYoe(String yoe) {
        this.yoe = yoe;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getLeaves() {
        return leaves;
    }

    public void setLeaves(String leaves) {
        this.leaves = leaves;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }
}