package zw.co.soskode.SchoolManagementSystem.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "teacher_details")
public class TeacherDetails extends BaseEntityId {

	private static final long serialVersionUID = -7947438187750407254L;
	private Long 	userId;
    private String firstName;
    private String lastName;
    private String email;
	private User    user;
	private Date 	promotionDate;
	private String 	designation;
	private String 	joinPosition;
	private String 	status;
    private School school;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateOfBirth;
    @Enumerated
    private Gender gender;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @ManyToOne
    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
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

	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "promotionDate", length = 19)
	public Date getPromotionDate() {
		return this.promotionDate;
	}
	
	public void setPromotionDate(Date promotionDate) {
		this.promotionDate = promotionDate;
	}

	@Column(name = "designation", length = 45)
	public String getDesignation() {
		return this.designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	@Column(name = "joinPosition", length = 45)
	public String getJoinPosition() {
		return this.joinPosition;
	}
	
	public void setJoinPosition(String joinPosition) {
		this.joinPosition = joinPosition;
	}
	
	@Column(name = "status", length = 45)
	public String getStatus() {
		return this.status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
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

}
