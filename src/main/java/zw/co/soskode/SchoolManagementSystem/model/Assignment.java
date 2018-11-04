package zw.co.soskode.SchoolManagementSystem.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created by zinzombe on Nov
 */

@Entity
public class Assignment extends BaseEntityId {

    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dueDate;
    private Double marks;
    private Subject subject;
    private School school;
    private String description;




    @ManyToOne
    public School getSchool() {
        return school;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    @ManyToOne
    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Double getMarks() {
        return marks;
    }

    public void setMarks(Double marks) {
        this.marks = marks;
    }

//    @ManyToOne
//    public Student getStudent() {
//        return student;
//    }
//
//    public void setStudent(Student student) {
//        this.student = student;
//    }


    @Override
    public String toString() {
        return "Assignment{" +
                "name='" + name + '\'' +
                ", dueDate=" + dueDate +
                ", marks=" + marks +
                ", subject=" + subject +
                ", school=" + school +
                ", description='" + description + '\'' +
                '}';
    }
}
