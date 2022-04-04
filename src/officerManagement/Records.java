package officerManagement;

public class Records {
	
	String TRN;
	String fname;
	String lname;
	String company;
	String position;
	String serviceLength;
	String avsec_date;
	String avsec_grade;
	String med_doc_exp;
	String pol_doc_exp;
	String rec_date;
	//String id;
	String psra_exp;
	
	public Records (String TRN,String fname, String lname, String company, String position, String serviceLength, String avsec_date, String avsec_grade, String med_doc_exp, String pol_doc_exp, String rec_date, String psra_exp) {
		this.fname=fname;
		this.lname=lname;
		this.company=company;
		this.position=position;
		this.serviceLength=serviceLength;
		this.avsec_date=avsec_date;
		this.avsec_grade=avsec_grade;
		this.med_doc_exp=med_doc_exp;
		this.pol_doc_exp=pol_doc_exp;
		this.rec_date=rec_date;
		//this.id=id;
		this.psra_exp=psra_exp;
		this.TRN=TRN;
	}

	public String getTRN() {
		return TRN;
	}

	public void setTRN(String tRN) {
		TRN = tRN;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getServiceLength() {
		return serviceLength;
	}

	public void setServiceLength(String serviceLength) {
		this.serviceLength = serviceLength;
	}

	public String getAvsec_date() {
		return avsec_date;
	}

	public void setAvsec_date(String avsec_date) {
		this.avsec_date = avsec_date;
	}

	public String getAvsec_grade() {
		return avsec_grade;
	}

	public void setAvsec_grade(String avsec_grade) {
		this.avsec_grade = avsec_grade;
	}

	public String getMed_doc_exp() {
		return med_doc_exp;
	}

	public void setMed_doc_exp(String med_doc_exp) {
		this.med_doc_exp = med_doc_exp;
	}

	public String getPol_doc_exp() {
		return pol_doc_exp;
	}

	public void setPol_doc_exp(String pol_doc_exp) {
		this.pol_doc_exp = pol_doc_exp;
	}

	public String getRec_date() {
		return rec_date;
	}

	public void setRec_date(String rec_date) {
		this.rec_date = rec_date;
	}

	/*public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}*/

	public String getPsra_exp() {
		return psra_exp;
	}

	public void setPsra_exp(String psra_exp) {
		this.psra_exp = psra_exp;
	}
	
	

}
