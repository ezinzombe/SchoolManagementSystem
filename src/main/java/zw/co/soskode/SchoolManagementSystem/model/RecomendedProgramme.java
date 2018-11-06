package zw.co.soskode.SchoolManagementSystem.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by zinzombe on Nov
 */
@Entity
@Table(name = "programme")
public class RecomendedProgramme extends BaseEntityId {

    private Student student;
    private String name;
    private String duration;

    public RecomendedProgramme() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @ManyToOne
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public RecomendedProgramme(Long id, String name, String duration) {
        this.name = name;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "RecomendedProgramme{" +
                "student=" + student +
                ", name='" + name + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
