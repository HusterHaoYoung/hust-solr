package com.hust.hustsearch.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author :younghao
 * @ClassName: Teacher
 * @Description: TODO
 * @date : 11/27/19  4:55 PM
 */

@Entity
public class Teacher {
    private int id;
    private String name;
    private String email;
    private String prorank;
    private String honor;
    private String address;
    private String phone;
    private String zipcode;
    private String introduction;
    private int subjectId;
    private String subjectName;
    private int collegeId;
    private String collegeName;
    private int labId;
    private String labName;
    private String labWebsite;
    private String labIntroduction;
    private int isTutor;
    private int isDtutor;
    private String img;
    private int tid;
    private String degree;
    private String homepage;

    private String dtutor;
    private String tutor;

    public String getDtutor() {
        return dtutor;
    }

    public void setDtutor(String dtutor) {
        this.dtutor = dtutor;
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    public String getLabWebsite() {
        return labWebsite;
    }

    public void setLabWebsite(String labWebsite) {
        this.labWebsite = labWebsite;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProrank() {
        return prorank;
    }

    public void setProrank(String prorank) {
        this.prorank = prorank;
    }

    public String getHonor() {
        return honor;
    }

    public void setHonor(String honor) {
        this.honor = honor;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(int collegeId) {
        this.collegeId = collegeId;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public int getLabId() {
        return labId;
    }

    public void setLabId(int labId) {
        this.labId = labId;
    }

    public String getLabName() {
        return labName;
    }

    public void setLabName(String labName) {
        this.labName = labName;
    }

    public String getLabIntroduction() {
        return labIntroduction;
    }

    public void setLabIntroduction(String labIntroduction) {
        this.labIntroduction = labIntroduction;
    }

    public int getIsTutor() {
        return isTutor;
    }

    public void setIsTutor(int isTutor) {
        this.isTutor = isTutor;
    }

    public int getIsDtutor() {
        return isDtutor;
    }

    public void setIsDtutor(int isDtutor) {
        this.isDtutor = isDtutor;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Teacher() {
    }

    public Teacher(String name, String email, String prorank, String honor, String address, String phone, String zipcode, String introduction, int subjectId, String subjectName, int collegeId, String collegeName, int labId, String labName, String labIntroduction, int isTutor, int isDtutor, String img, int tid, String degree, String homepage, String labWebsite) {
        this.name = name;
        this.email = email;
        this.prorank = prorank;
        this.honor = honor;
        this.address = address;
        this.phone = phone;
        this.zipcode = zipcode;
        this.introduction = introduction;
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.collegeId = collegeId;
        this.collegeName = collegeName;
        this.labId = labId;
        this.labName = labName;
        this.labIntroduction = labIntroduction;
        this.isTutor = isTutor;
        this.isDtutor = isDtutor;
        this.tid = tid;
        this.img = img;
        this.degree = degree;
        this.homepage = homepage;
        this.labWebsite = labWebsite;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", prorank='" + prorank + '\'' +
                ", honor='" + honor + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", introduction='" + introduction + '\'' +
                ", subjectId=" + subjectId +
                ", subjectName='" + subjectName + '\'' +
                ", collegeId=" + collegeId +
                ", collegeName='" + collegeName + '\'' +
                ", labId=" + labId +
                ", labName='" + labName + '\'' +
                ", labWebsite='" + labWebsite + '\'' +
                ", labIntroduction='" + labIntroduction + '\'' +
                ", isTutor=" + isTutor +
                ", isDTutor=" + isDtutor +
                ", img='" + img + '\'' +
                ", tid=" + tid +
                ", degree='" + degree + '\'' +
                ", homepage='" + homepage + '\'' +
                '}';
    }
}
