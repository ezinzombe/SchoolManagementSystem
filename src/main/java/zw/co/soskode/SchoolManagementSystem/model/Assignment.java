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
    @DateTimeFormat(pattern = "dd/M/yyyy hh:mm:ss")
    private Date dueDate;
    private Double marks;
    //    private Student student;
    private Subject subject;

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
}
