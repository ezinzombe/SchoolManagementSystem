package zw.co.soskode.SchoolManagementSystem.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.*;

/**
 * Created by zinzombe on Oct
 */
@Entity(name = "subject")
public class Subject extends BaseEntityId {

    @NotEmpty
    private String name;
    @NotEmpty
    private String subjectCode;
    private Set<Assignment> assignments = new HashSet<>();

    @OneToMany(mappedBy = "subject")
    public Set<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(Set<Assignment> assignments) {
        this.assignments = assignments;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "name='" + name + '\'' +
                ", subjectCode='" + subjectCode + '\'' +
                '}';
    }
}
