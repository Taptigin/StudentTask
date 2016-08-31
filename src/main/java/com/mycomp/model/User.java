package com.mycomp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

/**
 * Model entity to the database.
 *
 * Created by Alexandr on 21.07.2016.
 */
@Entity
@Table(name = "users", schema = "public", catalog = "students")
public class User implements Serializable {
    @Id
    private Integer id;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "middlename")
    private String middleName;
    @Column(name = "sex")
    private String sex;
    @Column(name = "age")
    private Integer age;
    @Column(name = "groupname")
    private String groupName;
    @Column(name = "facultyname")
    private String facultyName;
    @Column(name = "enrollmentdate")
    private Date enrollmentDate;
    @Column(name = "releasedate")
    private Date releaseDate;

    /**
     * @return  the value of column "ID" from the database for the current record.
     */
    @Column(name = "id")
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

    /**
     * To override a method of comparing.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User that = (User) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (middleName != null ? !middleName.equals(that.middleName) : that.middleName != null) return false;
        if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;
        if (age != null ? !age.equals(that.age) : that.age != null) return false;
        if (groupName != null ? !groupName.equals(that.groupName) : that.groupName != null) return false;
        if (facultyName != null ? !facultyName.equals(that.facultyName) : that.facultyName != null) return false;
        if (enrollmentDate != null ? !enrollmentDate.equals(that.enrollmentDate) : that.enrollmentDate != null)
            return false;
        if (releaseDate != null ? !releaseDate.equals(that.releaseDate) : that.releaseDate != null) return false;

        return true;
    }

    /**
     * To override method hashCode.
     */
    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (groupName != null ? groupName.hashCode() : 0);
        result = 31 * result + (facultyName != null ? facultyName.hashCode() : 0);
        result = 31 * result + (enrollmentDate != null ? enrollmentDate.hashCode() : 0);
        result = 31 * result + (releaseDate != null ? releaseDate.hashCode() : 0);
        return result;
    }

    /**
     *
     * @return the rows from the table.
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", groupName='" + groupName + '\'' +
                ", facultyName='" + facultyName + '\'' +
                ", enrollmentDate=" + enrollmentDate +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
