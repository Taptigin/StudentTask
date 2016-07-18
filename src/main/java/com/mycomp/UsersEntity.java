package com.mycomp;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Александр on 18.07.2016.
 */
@Entity
@Table(name = "users", schema = "public", catalog = "postgres")
public class UsersEntity {
    private Integer id;
    private String firstname;
    private String lastname;
    private String middlename;
    private String sex;
    private Integer age;
    private String groupname;
    private String facultyname;
    private Date dateenrollment;
    private Date daterelease;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "firstname")
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "lastname")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "middlename")
    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    @Basic
    @Column(name = "sex")
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "age")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Basic
    @Column(name = "groupname")
    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    @Basic
    @Column(name = "facultyname")
    public String getFacultyname() {
        return facultyname;
    }

    public void setFacultyname(String facultyname) {
        this.facultyname = facultyname;
    }

    @Basic
    @Column(name = "dateenrollment")
    public Date getDateenrollment() {
        return dateenrollment;
    }

    public void setDateenrollment(Date dateenrollment) {
        this.dateenrollment = dateenrollment;
    }

    @Basic
    @Column(name = "daterelease")
    public Date getDaterelease() {
        return daterelease;
    }

    public void setDaterelease(Date daterelease) {
        this.daterelease = daterelease;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersEntity that = (UsersEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
        if (middlename != null ? !middlename.equals(that.middlename) : that.middlename != null) return false;
        if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;
        if (age != null ? !age.equals(that.age) : that.age != null) return false;
        if (groupname != null ? !groupname.equals(that.groupname) : that.groupname != null) return false;
        if (facultyname != null ? !facultyname.equals(that.facultyname) : that.facultyname != null) return false;
        if (dateenrollment != null ? !dateenrollment.equals(that.dateenrollment) : that.dateenrollment != null)
            return false;
        if (daterelease != null ? !daterelease.equals(that.daterelease) : that.daterelease != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (middlename != null ? middlename.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (groupname != null ? groupname.hashCode() : 0);
        result = 31 * result + (facultyname != null ? facultyname.hashCode() : 0);
        result = 31 * result + (dateenrollment != null ? dateenrollment.hashCode() : 0);
        result = 31 * result + (daterelease != null ? daterelease.hashCode() : 0);
        return result;
    }
}
