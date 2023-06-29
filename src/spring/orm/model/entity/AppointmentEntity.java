package spring.orm.model.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import spring.orm.model.PatientModel;

@Entity
@Table(name = "appointments")
public class AppointmentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "appn_id")
	private int appn_id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "appn_doct_id")
	private DoctorTemp doctor;

	@ManyToOne
	@JoinColumn(name = "appn_patn_id")
	private PatientModel pm;
	@Column(name = "appn_booked_Date")
	private Timestamp appn_booked_Date;
	@Column(name = "appn_sch_Date", unique = true)

	private Timestamp appn_sch_date;
	@Column(name = "appn_paymode")
	private String appn_paymode;
	@Column(name = "appn_payreference")
	private String appn_payreference;
	@Column(name = "appn_payamount")
	private double appn_payamount;
	@Column(name = "appn_status")
	private String appn_status;

	@Transient
	private String apdateFormetted;

	@Transient
	private String aptimeFormetted;

	public int getAppn_id() {
		return appn_id;
	}

	public void setAppn_id(int appn_id) {
		this.appn_id = appn_id;
	}

	public DoctorTemp getDoctor() {
		return doctor;
	}

	public void setDoctor(DoctorTemp doctor) {
		this.doctor = doctor;
	}

	public PatientModel getPm() {
		return pm;
	}

	public void setPm(PatientModel pm) {
		this.pm = pm;
	}

	public Timestamp getAppn_booked_Date() {
		return appn_booked_Date;
	}

	public void setAppn_booked_Date(Timestamp appn_booked_Date) {

		this.appn_booked_Date = appn_booked_Date;
	}

	public Timestamp getAppn_sch_date() {
		return appn_sch_date;
	}

	public void setAppn_sch_date(Timestamp appn_sch_date) {
		this.appn_sch_date = appn_sch_date;
	}

	public String getAppn_paymode() {
		return appn_paymode;
	}

	public void setAppn_paymode(String appn_paymode) {
		this.appn_paymode = appn_paymode;
	}

	public String getAppn_payreference() {
		return appn_payreference;
	}

	public void setAppn_payreference(String appn_payreference) {
		this.appn_payreference = appn_payreference;
	}

	public double getAppn_payamount() {
		return appn_payamount;
	}

	public void setAppn_payamount(double appn_payamount) {
		this.appn_payamount = appn_payamount;
	}

	public String getAppn_status() {
		return appn_status;
	}

	public void setAppn_status(String appn_status) {
		this.appn_status = appn_status;
	}

	public String getDateFormetted() {
		return apdateFormetted;
	}

	public void setDateFormetted(String dateFormetted) {
		this.apdateFormetted = dateFormetted;
	}

	public String getTimeFormetted() {
		return aptimeFormetted;
	}

	public void setTimeFormetted(String timeFormetted) {
		this.aptimeFormetted = timeFormetted;
	}

	public AppointmentEntity() {
		// TODO Auto-generated constructor stub

	}

	@Override
	public String toString() {
		return "AppointmentEntity [appn_id=" + appn_id + ", doctor=" + doctor + ", pm=" + pm + ", appn_booked_Date="
				+ appn_booked_Date + ", appn_sch_date=" + appn_sch_date + ", appn_paymode=" + appn_paymode
				+ ", appn_payreference=" + appn_payreference + ", appn_payamount=" + appn_payamount + ", appn_status="
				+ appn_status + ", apdateFormetted=" + apdateFormetted + ", aptimeFormetted=" + aptimeFormetted + "]";
	}

}
