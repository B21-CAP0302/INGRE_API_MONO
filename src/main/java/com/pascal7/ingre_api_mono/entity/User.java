package com.pascal7.ingre_api_mono.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Entity
@Table(name ="mst_user")
public class User {

    @Id
    @GeneratedValue(generator = "user_uuid")
    @GenericGenerator(name = "user_uuid", strategy = "uuid")
    private String id;

    @NotBlank
    private String fullName;
    @NotBlank
    private String email;
    @NotBlank
    private String password;

    private String address;
    private String gender;
    private String phoneNumber;
    private Timestamp birthDate;
    private Timestamp dateCreated;
    private String role;
    private String verificationStat;
    private String photo;

    public User() {
    }

    public User setUser(User user){
        this.address = user.getAddress();
        this.phoneNumber = user.getPhoneNumber();
        this.photo = user.getPhoto();
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Timestamp getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Timestamp birthDate) {
        this.birthDate = birthDate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getVerificationStat() {
        return verificationStat;
    }

    public void setVerificationStat(String verificationStat) {
        this.verificationStat = verificationStat;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public String getPhoto() {
        return photo;
    }
}
