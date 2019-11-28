package com.hust.hustsearch.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author :younghao
 * @ClassName: Lab
 * @Description: TODO
 * @date : 11/27/19  4:56 PM
 */

@Entity
public class Lab {
    private int id;
    private String name;
    private int collegeId;
    private String collegeName;
    private String phone;
    private String email;
    private String address;
    private String introduction;
    private String introductionLink;
    private String img;
    private String website;

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getIntroductionLink() {
        return introductionLink;
    }

    public void setIntroductionLink(String introductionLink) {
        this.introductionLink = introductionLink;
    }

    public Lab(String name, int collegeId, String collegeName, String phone, String email, String address, String introduction, String img, String introductionLink, String website) {
        this.name = name;
        this.collegeId = collegeId;
        this.collegeName = collegeName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.introduction = introduction;
        this.img = img;
        this.introductionLink = introductionLink;
        this.website = website;
    }

    public Lab() {

    }

    @Override
    public String toString() {
        return "Lab{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", collegeId=" + collegeId +
                ", collegeName='" + collegeName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", introduction='" + introduction + '\'' +
                ", introductionLink='" + introductionLink + '\'' +
                ", img='" + img + '\'' +
                ", website='" + website + '\'' +
                '}';
    }
}
