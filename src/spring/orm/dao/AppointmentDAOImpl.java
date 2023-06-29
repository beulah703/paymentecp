package spring.orm.dao;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import spring.orm.contract.AppointmentDAO;
import spring.orm.contract.DoctorsDAO;
import spring.orm.contract.PatientDAO;
import spring.orm.customexceptions.SlotAlreadyBookedException;
import spring.orm.model.PatientModel;
import spring.orm.model.entity.AppointmentEntity;
import spring.orm.model.entity.DoctorTemp;
import spring.orm.model.input.AppointmentForm;
import spring.orm.model.input.BookedAppForm;
import spring.orm.model.input.RescheduleAppointmentModel;
import spring.orm.model.output.AdminAppOutModel;
import spring.orm.model.output.AdminDashOut;
import spring.orm.model.output.AdminProfitAppOut;
import spring.orm.model.output.OutputBookedAppointmnets;

@Component
public class AppointmentDAOImpl implements AppointmentDAO {

	@PersistenceContext
	private EntityManager entityManager;

	private PatientDAO patientDAO;

	private DoctorsDAO doctorDAO;

	@Autowired
	public AppointmentDAOImpl(PatientDAO patdao, DoctorsDAO doctdao) {
		this.doctorDAO = doctdao;
		this.patientDAO = patdao;
	}

	@Override
	@Transactional
	public List<Object[]> fetchUpcomingAppointmentData() {
		// This method fetches upcoming appointment data and returns a list of Object
		// arrays.

		String hql = "SELECT a.appn_id, d.name, p.patn_name, a.appn_sch_date " + "FROM Appointmentmodel a "
				+ "JOIN a.doctor d " + "JOIN a.pm p " + "WHERE a.appn_sch_date > :date";
		// This HQL query retrieves the appointment ID, doctor name, patient name, and
		// scheduled date for upcoming appointments.

		Query query = entityManager.createQuery(hql);
		// Create a Query object using the EntityManager and the HQL query.

		query.setParameter("date", LocalDate.now());
		// Set the value of the "date" parameter in the HQL query to today's date.

		List<Object[]> data = query.getResultList();
		// Execute the query and retrieve the result as a List of Object arrays.

		for (Object[] x : data) {
			for (Object y : x) {
				System.out.println(y + " ");
			}
			System.out.println();
		}
		// Iterate over each Object array in the result list and print each element.

		return data;
		// Return the fetched appointment data as a List of Object arrays.
	}

	public List<Object[]> fetchBookingAppointmentDataDateWise(String from, String to) {
		// This method retrieves appointment data based on a date range.

		String hql = "select a.appn_id, d.name, p.patn_name, a.appn_sch_date, a.appn_status "
				+ "from Appointmentmodel a " + "join a.doctor d" + "join a.pm p"
				+ "where date_trunc('day', a.appn_sch_date) between :startDate and :endDate";
		/*
		 * The HQL query to fetch the required appointment data is defined here. It selects specific fields from the
		 * Appointmentmodel entity and joins with the doctor and pm entities. The date range is specified using the
		 * "between" clause with placeholders for start and end dates.
		 */

		Query query = entityManager.createQuery(hql);
		// Create a query object using the EntityManager and the defined HQL query.

		query.setParameter("startDate", LocalDate.parse(from));
		/*
		 * Set the value of the "startDate" parameter in the query with the parsed "from" date string.
		 */
		query.setParameter("endDate", LocalDate.parse(to));
		/*
		 * Set the value of the "endDate" parameter in the query with the parsed "to" date string.
		 */

		List<Object[]> data = query.getResultList();
		/*
		 * Execute the query and retrieve the result as a List of Object arrays. Each array represents a row of data
		 * with the selected fields.
		 */

		return data;

	}

	@Override
	@Transactional
	public List<AppointmentEntity> getAllAppointments() {
		// This method retrieves all appointment entities from the database.

		return entityManager.createQuery("select a from AppointmentEntity a", AppointmentEntity.class).getResultList();
		/*
		 * Execute a JPQL query to select all appointment entities from the database and return the result list.
		 */
	}

	@Override
	@Transactional
	public boolean isSlotBooked(int doctor_id, String date, String time) {
		/*
		 * This method checks if a time slot is booked for a given doctor on a specific date and time.
		 */
		String scheduled_date = date + " " + time; // Combine the provided date and time to create a schedule date
													// string.
		System.out.println(doctor_id + " " + scheduled_date); // Print the doctor ID and schedule date for debugging
																// purposes.

		String queryString = "select a from AppointmentEntity a where a.doctor.id= : doc_id and a.appn_sch_date=:sch_date ";
		/*
		 * Define a JPQL query to retrieve appointment entities for the given doctor ID and schedule date.
		 */
		Query query = entityManager.createQuery(queryString, AppointmentEntity.class); // Create a query object using
																						// the defined
		// query and
		// entity class.
		query.setParameter("doc_id", doctor_id); // Set the "doc_id" parameter in the query to the provided doctor ID.
		query.setParameter("sch_date", Timestamp.valueOf(scheduled_date)); // Set the "sch_date" parameter in the query
																			// to the
		// converted schedule date.

		List<AppointmentEntity> appointments = query.getResultList(); // Execute the query and retrieve the list of
																		// appointment
		// entities.

		if (appointments.size() > 0) {
			return true; // If the list of appointment entities is not empty, it means the slot is
							// booked.
		}

		return false; // If the list of appointment entities is empty, it means the slot is available.
	}

	@Override
	@Transactional
	public int bookAppointment(PatientModel existingPatientid, DoctorTemp doctor, String bookedDate, String payref,
			double payamount) throws SlotAlreadyBookedException {
		/*
		 * This method is used to book an appointment and takes the necessary parameters.
		 */

		AppointmentEntity appointment = new AppointmentEntity(); // Create a new instance of AppointmentEntity.

		appointment.setDoctor(doctor); // Set the doctor for the appointment.

		appointment.setAppn_booked_Date(new Timestamp(System.currentTimeMillis())); // Set the booked date for the
																					// appointment to
		// the current timestamp.

		appointment.setPm(existingPatientid); // Set the existing patient for the appointment.

		appointment.setAppn_sch_date(Timestamp.valueOf(bookedDate)); // Set the scheduled date for the appointment using
																		// the
		// provided string value.

		appointment.setAppn_paymode("ONLINE"); // Set the payment mode for the appointment to "ONLINE".

		appointment.setAppn_payamount(payamount); // Set the payment amount for the appointment.

		appointment.setAppn_payreference(payref); // Set the payment reference for the appointment.

		appointment.setAppn_status("YETO"); // Set the status of the appointment to "YETO" (indicating it is not yet

		entityManager.persist(appointment);
		return appointment.getAppn_id(); // Return the appointment ID.
	}

	@Override
	public AppointmentEntity getAppointmentById(int app_id) {
		return entityManager.find(AppointmentEntity.class, app_id);
	}

	@Override
	@Transactional
	public void cancelAppointment(int appointment_id) {
		/*
		 * This method is used to cancel an appointment based on the provided appointment ID.
		 */
		AppointmentEntity appointment = entityManager.find(AppointmentEntity.class, appointment_id);
		/*
		 * Retrieve the AppointmentEntity object from the EntityManager based on the provided appointment ID and assign
		 * it to the variable 'a'.
		 */
		appointment.setAppn_status("CNL");
		/*
		 * Set the appointment status to "CNL" (indicating cancellation) for the retrieved AppointmentEntity object.
		 */
		entityManager.merge(appointment);
		/*
		 * Merge the changes made to the AppointmentEntity object back into the persistence context to update it in the
		 * database.
		 */
	}

	@Override
	public void reschduleAppointment(RescheduleAppointmentModel rescheduleAppointmentmodel) {
		/*
		 * This method is used to reschedule an appointment based on the provided RescheduleAppointmentModel object.
		 */
		AppointmentEntity appointment = entityManager.find(AppointmentEntity.class,
				rescheduleAppointmentmodel.getAppointmentId());
		/*
		 * Retrieve the appointment entity from the EntityManager based on the appointment ID provided in the
		 * RescheduleAppointmentModel object.
		 */
		DateTimeFormatter sqlFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
		/*
		 * Create a DateTimeFormatter object with the pattern "HH:mm:ss" to format the time in SQL format.
		 */
		System.out.println("in docdao appointment");
		/*
		 * Print a message to indicate that the code is in the "docdao appointment" section for debugging or logging
		 * purposes.
		 */
		appointment.setAppn_sch_date(Timestamp.valueOf(rescheduleAppointmentmodel.getRescheduleDate() + " "
				+ LocalTime.parse(rescheduleAppointmentmodel.getSlot(), DateTimeFormatter.ofPattern("hh:mm a"))
						.format(sqlFormat)));
		/*
		 * Set the rescheduled appointment date by combining the reschedule date from the RescheduleAppointmentModel
		 * object with the parsed time from the slot using the provided time format.
		 */
		entityManager.merge(appointment);
		// Merge the updated appointment entity back into the EntityManager.

	}

	@Override
	@Transactional
	public List<OutputBookedAppointmnets> fetchBookedAppointmentData(BookedAppForm bookedAppointmentFilter,
			Integer patient_id) {

		String hql = "SELECT new spring.orm.model.output.OutputBookedAppointmnets(a.appn_id, p.patn_name, d.doctName, a.appn_sch_date, s.title, a.appn_status)"
				+ "FROM AppointmentEntity a" + " JOIN a.pm p " + " JOIN a.doctor d"
				+ " JOIN d.specialization s where 1=1";
		List<OutputBookedAppointmnets> data = null;

		if (!bookedAppointmentFilter.getSpec().equals("select")) {
			hql += " and s.title = :spec";
		}
		if (!bookedAppointmentFilter.getDoctor().equals("select")) {
			hql += " and d.doctName = :doc";
		}
		if (!bookedAppointmentFilter.getStatus().equals("select")) {
			hql += " and a.appn_status = :status";
		}
		if (!bookedAppointmentFilter.getFrom().equals("") && !bookedAppointmentFilter.getTo().equals("")) {
			hql += " and a.appn_sch_date >:from and a.appn_sch_date< :to";
		}
		if (patient_id != null) {
			hql += " and (a.pm.patn_id in (select f.pfmbPatnId from FamilyMembers f where f.patnAccessPatnId=:p_id) or a.pm.patn_id=:p_id)";
		}

		hql += " order by a.appn_id";

		TypedQuery<OutputBookedAppointmnets> query = entityManager.createQuery(hql,
				spring.orm.model.output.OutputBookedAppointmnets.class);
		if (!bookedAppointmentFilter.getSpec().equals("select")) {
			query.setParameter("spec", bookedAppointmentFilter.getSpec());
		}
		if (!bookedAppointmentFilter.getDoctor().equals("select")) {
			query.setParameter("doc", bookedAppointmentFilter.getDoctor());
		}
		if (!bookedAppointmentFilter.getStatus().equals("select")) {
			query.setParameter("status", bookedAppointmentFilter.getStatus());
		}
		if (!bookedAppointmentFilter.getFrom().equals("") && !bookedAppointmentFilter.getTo().equals("")) {
			LocalDateTime fromDateTime = LocalDateTime.parse(bookedAppointmentFilter.getFrom() + "T00:00:00");
			LocalDateTime toDateTime = LocalDateTime.parse(bookedAppointmentFilter.getTo() + "T23:59:59");

			Date fromDate = Date.from(fromDateTime.atZone(ZoneId.systemDefault()).toInstant());
			Date toDate = Date.from(toDateTime.atZone(ZoneId.systemDefault()).toInstant());

			query.setParameter("from", fromDate, TemporalType.TIMESTAMP);
			query.setParameter("to", toDate, TemporalType.TIMESTAMP);
		}
		if (patient_id != null) {
			query.setParameter("p_id", patient_id);
		}

		data = query.getResultList();

		return data;

	}

	@Override
	public List<AppointmentEntity> getAppointmentsByPatientId(int patn_id) {
		/*
		 * This method retrieves a list of appointments based on the provided patient ID.
		 */
		String query = "SELECT a FROM AppointmentEntity a WHERE a.pm.patn_id = :patientId";
		/*
		 * This query selects all AppointmentEntity objects from the database where the associated patient's ID matches
		 * the provided patientId parameter.
		 */
		return entityManager.createQuery(query, AppointmentEntity.class).setParameter("patientId", patn_id)
				.getResultList();
		/*
		 * This line executes the query and returns a list of AppointmentEntity objects that match the specified patient
		 * ID.
		 */
	}

	@Override
	@Transactional
	public int bookAppointment(AppointmentForm appointment, int patientId) {
		// Retrieve the existing patient using the patientId
		PatientModel existingPatient = patientDAO.getPatientById(patientId);

		// Retrieve the doctor using the doctor ID from the appointment form
		DoctorTemp doctor = doctorDAO.getDoctor(appointment.getDoctor());

		// Create a new AppointmentEntity object and set its properties
		AppointmentEntity appointmentEntity = new AppointmentEntity();
		appointmentEntity.setDoctor(doctor);
		appointmentEntity.setAppn_booked_Date(new Timestamp(System.currentTimeMillis()));
		appointmentEntity.setPm(existingPatient);
		appointmentEntity.setAppn_sch_date(
				Timestamp.valueOf(appointment.getAppointmentDate().toString() + " " + appointment.getSlots()));
		appointmentEntity.setAppn_paymode(appointment.getAppnmode());
		appointmentEntity.setAppn_payamount(appointment.getAppnfee());
		appointmentEntity.setAppn_payreference(appointment.getAppnrefer());
		appointmentEntity.setAppn_status("YETO");

		// Persist the appointment entity in the database
		entityManager.persist(appointmentEntity);

		return appointmentEntity.getAppn_id();
	}

	@Override
	public List<AdminAppOutModel> getTopAppointments() {
		/*
		 * This method retrieves the top appointments and returns a list of AdminAppOutModel objects.
		 */
		List<AdminAppOutModel> topAppointments = entityManager.createQuery(
				"select new spring.orm.model.output.AdminAppOutModel(a.doctor.doctName,a.pm.patn_name,a.appn_sch_date) from AppointmentEntity a ORDER BY a.appn_sch_date DESC ",
				AdminAppOutModel.class).setMaxResults(10).getResultList();
		/*
		 * This statement uses a JPQL query to retrieve appointment details from the AppointmentEntity table. The query
		 * selects specific fields (doctor name, patient name, appointment scheduled date) and constructs
		 * AdminAppOutModel objects using the selected fields. The appointments are ordered by the scheduled date in
		 * descending order. The 'setMaxResults(10)' limits the result to the top 10 appointments. The query result is
		 * stored in the 'toplist' variable, which is a List<AdminAppOutModel>.
		 */

		return topAppointments; // Return the list of top appointments.
	}

	@Override
	public List<AdminProfitAppOut> getTopprof() {
		List<AdminProfitAppOut> topprof = entityManager.createQuery(
				"select new spring.orm.model.output.AdminProfitAppOut(a.appn_payreference,a.appn_paymode,(a.appn_payamount),a.doctor.doctName) from AppointmentEntity a ORDER BY a.appn_booked_Date DESC",
				AdminProfitAppOut.class).setMaxResults(10).getResultList();
		System.out.println(topprof.size());
		return topprof;
	}

	@Override
	public AdminDashOut getDashreport() {
		// This method retrieves the dashboard report for the administrator.

		Long doccount = (Long) entityManager.createQuery("select count(d) from DoctorTemp d where d.isDeleted=false")
				.getSingleResult();
		/*
		 * Retrieve the count of active doctors from the "DoctorTemp" entity where "isDeleted" is false and store it in
		 * the "doccount" variable.
		 */
		Long appointments = (Long) entityManager.createQuery("select count(a) from AppointmentEntity a ")
				.getSingleResult();
		/*
		 * Retrieve the count of all appointments from the "AppointmentEntity" and store it in the "appointments"
		 * variable.
		 */
		Long specs = (Long) entityManager.createQuery("select count(s) from Specialization s").getSingleResult();
		/*
		 * Retrieve the count of all specializations from the "Specialization" entity and store it in the "specs"
		 * variable.
		 */
		Double payments = (Double) entityManager.createQuery("select sum(a.appn_payamount) from AppointmentEntity a")
				.getSingleResult();
		/*
		 * Retrieve the sum of payment amounts from the "AppointmentEntity" and store it in the "payments" variable.
		 */
		AdminDashOut a = new AdminDashOut(doccount, appointments, specs, payments);
		// Create a new instance of the "AdminDashOut" class using the retrieved data.

		return a;
		// Return the "AdminDashOut" object representing the dashboard report.
	}

	@Override
	@Transactional
	public void updateAppStatusComp(int app_id) {
		/*
		 * This method updates the status of an appointment to "CMPL" (completed) based on the provided appointment ID.
		 */
		AppointmentEntity a = entityManager.find(AppointmentEntity.class, app_id);
		/*
		 * Retrieve the AppointmentEntity object from the EntityManager based on the provided appointment ID and store
		 * it in 'a' variable.
		 */
		a.setAppn_status("CMPL");
		/*
		 * Set the appointment status of the retrieved AppointmentEntity object to "CMPL" (completed).
		 */
		entityManager.merge(a);
		/*
		 * Update the AppointmentEntity object in the EntityManager with the changes made.
		 */
	}

}