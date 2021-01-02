package com.muralijha.sup;

public class UserProfile {
    public String userSemester;
    public String userEmail;
    public String userName;
    public String userSchool;
    public String userYear;
    public String userSection;
    public String userCollege;

    public UserProfile(){

    }

    public UserProfile(String userSemester, String userEmail, String userName,String userSchool,String userYear,String userSection,String userCollege) {
        this.userSemester = userSemester;
        this.userEmail = userEmail;
        this.userName = userName;
        this.userSchool = userSchool;
        this.userYear = userYear;
        this.userSection = userSection;
        this.userCollege = userCollege;
    }

    public String getUserSemester() {
        return userSemester;
    }

    public void setUserSemester(String userSemester) {
        this.userSemester = userSemester;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSchool() {
        return userSchool;
    }

    public void setUserSchool(String userSchool) {
        this.userSchool = userSchool;
    }

    public String getUserYear() {
        return userYear;
    }

    public void setUserYear(String userYear) {
        this.userYear = userYear;
    }

    public String getUserSection() {
        return userSection;
    }

    public void setUserSection(String userSection) {
        this.userSection = userSection;
    }
    public String getUserCollege() {
        return userCollege;
    }

    public UserProfile(String userCollege) {
        this.userCollege = userCollege;
    }
}
