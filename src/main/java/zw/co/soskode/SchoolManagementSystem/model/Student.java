package zw.co.soskode.SchoolManagementSystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "student")
public class Student extends  BaseEntityId{

	private static final long serialVersionUID = -5457596000511194801L;
	private Long userId;
	private User  user;
    private FormType formType;
    private Classes classes;
    private List<Address> addresses;
    private List<Grades> grades;

    @OneToMany(mappedBy = "student")
    @JsonIgnore
    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
    @OneToMany(mappedBy = "student")
    @JsonIgnore
    public List<Grades> getGrades() {
        return grades;
    }

    public void setGrades(List<Grades> grades) {
        this.grades = grades;
    }

    @Enumerated(EnumType.STRING)
    public FormType getFormType() {
        return formType;
    }

    public void setFormType(FormType formType) {
        this.formType = formType;
    }
    @ManyToOne
    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    @OneToOne(fetch=FetchType.LAZY)
    @PrimaryKeyJoinColumn
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
