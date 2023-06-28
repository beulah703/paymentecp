package spring.orm.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.orm.contract.DiagnosticBillDAO;
import spring.orm.contract.PatientDAO;
import spring.orm.contract.TestDAO;
import spring.orm.model.DiagnosticBillModel;
import spring.orm.model.DiagnosticBillModelId;
import spring.orm.model.Diagnostictestbill;
import spring.orm.model.TestBookStatusComposite;
import spring.orm.model.entity.TestBookStatus;
import spring.orm.model.input.BillInputModel;
import spring.orm.model.output.BookedTestIDs;
import spring.orm.model.output.OutputPatientTestReports;

@Repository
public class DiagnosticBillDAOImpl implements DiagnosticBillDAO {

	@PersistenceContext
	private EntityManager em;

	private PatientDAO patientDAO;

	@Autowired
	public DiagnosticBillDAOImpl(PatientDAO pd, TestDAO td) {
		super();
		this.patientDAO = pd;
		this.testDAO = td;
	}

	private TestDAO testDAO;
	private static final Logger logger = LoggerFactory.getLogger(DiagnosticBillDAOImpl.class);
	TestBookStatus st = new TestBookStatus();

	List<Integer> testi = new ArrayList<>();
	String con;
	BookedTestIDs btid = new BookedTestIDs(testi, con);

	public List<DiagnosticBillModel> getdbilldetails() {
		logger.info("Query to fetch diagnostic bill details");
		return em.createQuery("SELECT d FROM diagnosticBillModel d", DiagnosticBillModel.class).getResultList();
	}

	@Transactional
	public void bookDcTest(BillInputModel bi) {

		logger.info("Inside Book DC Test method");

		int test = bi.getTest();
		String type = bi.getType();

		int patn_id = bi.getPatient();
		logger.info("Booked Test id " + " " + test);
		logger.info("Patient type " + " " + type);
		logger.info("Patient Id " + " " + patn_id);
		TestBookStatusComposite id = new TestBookStatusComposite();
		id.setTbPatnId(patn_id);
		id.setTestId(test);

		st.setType(type);
		st.setId(id);
		st.setBookedDate(LocalDate.now());
		st.setStatus("pending");

		em.persist(st);
		logger.info("Saved/Persisted booked test to dataBase");

	}

	@Transactional
	public List<Object> getTotalBills(int patient) {
		logger.info("Total Bills Calculation for booked tests ");
		List<Object> tests1 = new ArrayList<>();
		List<Object> tests2 = new ArrayList<>();
		int amt = 0;

		int patn_id = patient;
		if (patn_id >= 0) {

			List<Integer> testh = (List<Integer>) em.createQuery(
					"select t.id.test_id  from TestBookStatus t where t.id.tb_patn_id=:patn_id and t.status= 'pending'")
					.setParameter("patn_id", patn_id).getResultList();
			logger.info("Number of Booked Tests " + " " + testh.size());
			for (int i = 0; i < testh.size(); i++) {
				int j = (int) testh.get(i);

				tests1 = em
						.createQuery("SELECT t.test_name, t.test_method, t.test_price "
								+ "FROM TestModel t, TestBookStatus tb " + "WHERE t.test_id = :testId "
								+ "AND tb.status = 'pending' " + "AND tb.id.test_id = t.test_id")
						.setParameter("testId", j).getResultList();

				int testsprice = (int) em.createQuery("select t.test_price from TestModel t where t.test_id=:j ")
						.setParameter("j", j).getSingleResult();
				logger.info("Booked Test Price " + " " + testsprice);

				amt = amt + testsprice;
				System.out.println(amt);
				tests2.add(tests1);

			}
			tests2.add(amt);
			logger.info("Total Amount for booked Tests " + " " + amt);
			btid.setAmt(amt);

		}

		return tests2;

	}

	@Transactional
	public int storeToDatabase(int patient) {
		logger.info("Storing Paid test details to Database");
		int patn_id = patient;
		int billid = 0;
		String type = (String) em.createQuery("select t.type from TestBookStatus t  where t.id.tb_patn_id=:patn_id")
				.setMaxResults(1).setParameter("patn_id", patn_id).getSingleResult();
		List<Integer> t1 = em.createQuery(
				"select t.id.test_id from TestBookStatus t  where t.id.tb_patn_id=:patn_id  and t.status= 'pending'")
				.setParameter("patn_id", patn_id).getResultList();

		if (patn_id >= 0) {

			int price = btid.getAmt();
			logger.info("Patient ID " + " " + (Integer) patn_id);

			logger.info("Total Amount " + " " + price);

			DiagnosticBillModel d1 = new DiagnosticBillModel((Integer) patn_id, LocalDate.now(), price, type);
			logger.info("Saving Paid Diagnostic Bill Details to Diagnostic Bills Table");
			em.persist(d1);

			for (int i = 0; i < t1.size(); i++) {

				int price2 = (int) em.createQuery("select t.test_price from TestModel t where t.test_id=:j ")
						.setParameter("j", t1.get(i)).getSingleResult();
				DiagnosticBillModelId id = new DiagnosticBillModelId((Integer) d1.getDgbl_id(), t1.get(i));
				billid = ((Integer) d1.getDgbl_id());
				logger.info("Bill Id generated is" + " " + billid);

				Diagnostictestbill d2 = new Diagnostictestbill(id, price2);
				logger.info("Saving Diagnostic Paid Test Bill Details to Diagnostic Test Bills Table");
				em.persist(d2);

				TestBookStatusComposite comid = new TestBookStatusComposite();
				comid.setTbPatnId(patn_id);
				comid.setTestId(t1.get(i));

				TestBookStatus st = new TestBookStatus();

				st.setId(comid);
				st.setBookedDate(LocalDate.now());
				st.setStatus("paid");
				st.setType(type);
				st = em.merge(st); // Merge the detached entity and capture the managed instance

				em.remove(st);

			}

		}
		logger.info("Generated Bill ID is  " + " " + billid);
		return billid;

	}

	@Override
	public List<OutputPatientTestReports> gettestdate(String date1, String date2, int pid) {

		// TODO Auto-generated method stub
		String hql = "SELECT new spring.orm.model.output.OutputPatientTestReports(pdr.compositeKey.dgbl_id, dbm.dgbl_date, pdr.dgdr_path)"
				+ " FROM PatientDiagnonisticReports pdr"
				+ " JOIN DiagnosticBillModel dbm on pdr.compositeKey.dgbl_id=dbm.dgbl_id "
				+ "WHERE 1=1 AND  (dbm.dgbl_patn_id in (select f.pfmbPatnId from FamilyMembers f where f.patnAccessPatnId=:p_id) or dbm.dgbl_patn_id=:p_id) and dbm.dgbl_date>=:d1 and dbm.dgbl_date<=:d2";

		List<OutputPatientTestReports> data = em
				.createQuery(hql, spring.orm.model.output.OutputPatientTestReports.class).setParameter("p_id", pid) // change
																													// it
																													// to
																													// pid
																													// later
				.setParameter("d1", LocalDate.parse(date1)).setParameter("d2", LocalDate.parse(date2)).getResultList();
		System.out.println(data.size());

		for (OutputPatientTestReports x : data) {
			System.out.println(x.getDgbl_date());
		}
		return data;
	}

}