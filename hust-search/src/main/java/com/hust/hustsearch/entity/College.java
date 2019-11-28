package com.hust.hustsearch.entity;


import org.json.JSONObject;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author :younghao
 * @ClassName: College
 * @Description: TODO
 * @date : 11/27/19  3:49 PM
 */

@Entity
public class College {
    private int id;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String index;
    private String introduction;
    private String img;
    private String introductionLink;
    private int wid;

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

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
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

    public int getWid() {
        return wid;
    }

    public void setWid(int wid) {
        this.wid = wid;
    }

    public College(String name, String phone, String email, String address, String index, String introduction, String img, String introductionLink, int wid) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.index = index;
        this.introduction = introduction;
        this.img = img;
        this.introductionLink = introductionLink;
        this.wid = wid;
    }

    public College() {
        JSONObject jsonObject = new JSONObject();
    }

    @Override
    public String toString() {
        return "College{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", index='" + index + '\'' +
                ", introduction='" + introduction + '\'' +
                ", img='" + img + '\'' +
                ", introLink='" + introductionLink + '\'' +
                ", wid=" + wid +
                '}';
    }
}

