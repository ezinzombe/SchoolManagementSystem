package zw.co.soskode.SchoolManagementSystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "student")
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;
    @DateTimeFormat(pattern = "dd/M/yyyy hh:mm:ss")
    private Date dateCreated;
    private String firstName;
    private String lastName;
    private Long userId;
    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private User user;
    @Enumerated
    private FormType formType;
    private Classes classes;
    @OneToMany(mappedBy = "student")
    private List<RecomendedProgramme> programmes;
    @OneToMany(mappedBy = "student")
    private List<Address> addresses;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "student", cascade = CascadeType.ALL)
    private List<Grades> grades;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    @Enumerated
    private Gender gender;
    private Integer points;

    public List<RecomendedProgramme> getProgrammes() {
        return programmes;
    }

    public void setProgrammes(List<RecomendedProgramme> programmes) {
        this.programmes = programmes;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }


    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }



    @Temporal(TemporalType.DATE)
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    @JsonIgnore
    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    //    @OneToMany(mappedBy = "student")

    @JsonIgnore
    public List<Grades> getGrades() {
        return grades;
    }

    public void setGrades(List<Grades> grades) {
        this.grades = grades;
    }

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

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Transient
    public String getFullName() {
        return lastName + " " + firstName;
    }

    @Transient
    public String getAge() {
        if (getIntegerValueOfAge() < 1) {
            return " less 1 yr ";
        } else {
            return getIntegerValueOfAge() + " yrs";
        }
    }

    @Transient
    public int getIntegerValueOfAge() {
        if (getDateOfBirth() == null) {
            return 0;
        }

        Calendar birthCalendar = Calendar.getInstance();
        birthCalendar.setTime(getDateOfBirth());

        Calendar todayCalendar = Calendar.getInstance();

        int age = todayCalendar.get(Calendar.YEAR) - birthCalendar.get(Calendar.YEAR);

        if (todayCalendar.get(Calendar.MONTH) < birthCalendar.get(Calendar.MONTH)) {
            age--;
        } else if (todayCalendar.get(Calendar.MONTH) == birthCalendar.get(Calendar.MONTH)
                && todayCalendar.get(Calendar.DAY_OF_MONTH) < birthCalendar.get(Calendar.DAY_OF_MONTH)) {
            age--;
        }

        return age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "user=" + user +
                '}';
    }
}
