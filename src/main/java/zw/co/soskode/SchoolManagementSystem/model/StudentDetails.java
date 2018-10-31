package zw.co.soskode.SchoolManagementSystem.model;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "student_details")
public class StudentDetails extends  BaseEntityId{

	private static final long serialVersionUID = -5457596000511194801L;
	private Long userId;
	private User  user;
	private String birthId;
	private Integer classId;
	private Integer classRoel;
	private String comment;
	private String father;
	private String homeDistrict;
	private String image;
	private String mother;
	private String permaAddress;
	private String presentAddress;
	private Integer secId;
	private String session;
	private Integer shitId;
	private Integer stuTrackingId;
	private Integer upgradeClass;

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

	@Column(name = "birth_id", length = 45)
	public String getBirthId() {
		return this.birthId;
	}

	public void setBirthId(String birthId) {
		this.birthId = birthId;
	}

	@Column(name = "class_id")
	public Integer getClassId() {
		return this.classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	@Column(name = "class_roel")
	public Integer getClassRoel() {
		return this.classRoel;
	}

	public void setClassRoel(Integer classRoel) {
		this.classRoel = classRoel;
	}

	@Column(name = "comment", length = 45)
	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Column(name = "father", length = 45)
	public String getFather() {
		return this.father;
	}

	public void setFather(String father) {
		this.father = father;
	}

	@Column(name = "home_district", length = 45)
	public String getHomeDistrict() {
		return this.homeDistrict;
	}

	public void setHomeDistrict(String homeDistrict) {
		this.homeDistrict = homeDistrict;
	}

	@Column(name = "image", length = 45)
	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Column(name = "mother", length = 45)
	public String getMother() {
		return this.mother;
	}

	public void setMother(String mother) {
		this.mother = mother;
	}

	@Column(name = "perma_address", length = 45)
	public String getPermaAddress() {
		return this.permaAddress;
	}

	public void setPermaAddress(String permaAddress) {
		this.permaAddress = permaAddress;
	}

	@Column(name = "present_address", length = 45)
	public String getPresentAddress() {
		return this.presentAddress;
	}

	public void setPresentAddress(String presentAddress) {
		this.presentAddress = presentAddress;
	}

	@Column(name = "sec_id")
	public Integer getSecId() {
		return this.secId;
	}

	public void setSecId(Integer secId) {
		this.secId = secId;
	}

	@Column(name = "session", length = 45)
	public String getSession() {
		return this.session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	@Column(name = "shit_id")
	public Integer getShitId() {
		return this.shitId;
	}

	public void setShitId(Integer shitId) {
		this.shitId = shitId;
	}

	@Column(name = "stu_tracking_id")
	public Integer getStuTrackingId() {
		return this.stuTrackingId;
	}

	public void setStuTrackingId(Integer stuTrackingId) {
		this.stuTrackingId = stuTrackingId;
	}

	@Column(name = "upgrade_class")
	public Integer getUpgradeClass() {
		return this.upgradeClass;
	}

	public void setUpgradeClass(Integer upgradeClass) {
		this.upgradeClass = upgradeClass;
	}

}
