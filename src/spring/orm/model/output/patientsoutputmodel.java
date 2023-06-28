package spring.orm.model.output;

public class patientsoutputmodel {
	@Override
	public String toString() {
		return "patientsoutputmodel [patn_d=" + patn_d + ", patn_name=" + patn_name + "]";
	}

	int patn_d;
	String patn_name;

	public int getPatn_d() {
		return patn_d;
	}

	public void setPatn_d(int patn_d) {
		this.patn_d = patn_d;
	}

	public String getPatn_name() {
		return patn_name;
	}

	public void setPatn_name(String patn_name) {
		this.patn_name = patn_name;
	}

	public patientsoutputmodel(int patn_d, String patn_name) {
		super();
		this.patn_d = patn_d;
		this.patn_name = patn_name;
	}

	public patientsoutputmodel() {
		super();
		// TODO Auto-generated constructor stub
	}

}
