package zw.co.soskode.SchoolManagementSystem.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
@Entity
public class Grades extends BaseEntityId{

    private Student student;
    private Subject subject;
    private Double mark;
    private String symbolResult;

    public Double getMark() {
        return mark;
    }

    public void setMark(Double mark) {
        this.mark = mark;
    }

    public String getSymbolResult() {
        return symbolResult;
    }

    public void setSymbolResult(String symbolResult) {
        this.symbolResult = symbolResult;
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


}
