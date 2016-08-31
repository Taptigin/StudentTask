package com.mycomp.gwt.shared;

import java.io.Serializable;
import java.sql.Date;

/**
 * Copy model entities as DTO.
 * @see com.mycomp.model.User
 * Created by Alexandr on 08.08.2016.
 */
public class UserDTO implements Serializable {
    private Integer id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String sex;
    private Integer age;
    private String groupName;
    private String facultyName;
    private Date enrollmentDate;
    private Date releaseDate;

    public UserDTO() {
    }
    /**
     * @return  the value of column "ID" from the database for the current record.
     */
    public Integer getId() {
        return id;
    }
    /**
     * Sets the value of the column "id" in the database.
     * @param id column in the table.
     */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
     * @return the value of column "FirstName" from the database for the current record.
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * Sets the value of the column "FirstName" in the database.
     * @param firstName column in the table.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /**
     * @return  the value of column "LastName" from the database for the current record.
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * Sets the value of the column "LastName" in the database.
     * @param lastName column in the table.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /**
     * @return  the value of column "MiddleName" from the database for the current record.
     */
    public String getMiddleName() {
        return middleName;
    }
    /**
     * Sets the value of the column "MiddleName" in the database.
     * @param middleName column in the table.
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    /**
     * @return  the value of column "sex" from the database for the current record.
     */
    public String getSex() {
        return sex;
    }
    /**
     * Sets the value of the column "sex" in the database.
     * @param sex column in the table.
     */
    public void setSex(String sex) {
        this.sex = sex;
    }
    /**
     * @return  the value of column "age" from the database for the current record.
     */
    public Integer getAge() {
        return age;
    }
    /**
     * Sets the value of the column "age" in the database.
     * @param age column in the table.
     */
    public void setAge(Integer age) {
        this.age = age;
    }
    /**
     * @return  the value of column "GroupName" from the database for the current record.
     */
    public String getGroupName() {
        return groupName;
    }
    /**
     * Sets the value of the column "GroupName" in the database.
     * @param groupName column in the table.
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    /**
     * @return  the value of column "FacultyName" from the database for the current record.
     */
    public String getFacultyName() {
        return facultyName;
    }
    /**
     * Sets the value of the column "FacultyName" in the database.
     * @param facultyName column in the table.
     */
    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }
    /**
     * @return  the value of column "EnrollmentDate" from the database for the current record.
     */
    public Date getEnrollmentDate() {
        return enrollmentDate;
    }
    /**
     * Sets the value of the column "EnrollmentDate" in the database.
     * @param enrollmentDate column in the table.
     */
    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
    /**
     * @return  the value of column "ReleaseDate" from the database for the current record.
     */
    public Date getReleaseDate() {
        return releaseDate;
    }
    /**
     * Sets the value of the column "ReleaseDate" in the database.
     * @param releaseDate column in the table.
     */
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
}
