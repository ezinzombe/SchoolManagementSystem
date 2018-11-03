package zw.co.soskode.SchoolManagementSystem.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import java.util.Calendar;

@Entity
public class Grades extends BaseEntityId{

    private Student student;
    private Subject subject;
    private Double mark;

    public Double getMark() {
        return mark;
    }

    public void setMark(Double mark) {
        this.mark = mark;
    }

    @ManyToOne
    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @ManyToOne
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }


    @Transient
    public String getMarkPercentage() {
        if (getMark() >= 80 && getMark() <= 100) {
            return "A";
        } else if (getMark() >= 70 && getMark() <= 79) {
            return "B";
        } else if (getMark() >= 60 && getMark() <= 69) {
            return "C";
        } else if (getMark() >= 50 && getMark() <= 59) {
            return "D";
        } else {
            return "FAIL";
        }
    }


}
