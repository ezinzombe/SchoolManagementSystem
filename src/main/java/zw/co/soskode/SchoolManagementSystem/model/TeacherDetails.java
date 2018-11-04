package zw.co.soskode.SchoolManagementSystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "teacher")
public class TeacherDetails implements Serializable {

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
    private String email;
    private Long userId;
    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private User user;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    @Enumerated
    private Gender gender;


    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
