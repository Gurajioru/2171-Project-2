package officerManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RecordsController {
	private String TRN;
	
	public String getTRN() {
		return TRN;
	}

	public void setTRN(String tRN) {
		TRN = tRN;
	}

	public RecordsController(String TRN) {
		this.TRN=TRN;
	}
	
	public boolean checkTRN(){
		String sqlName="root";
		String sqlPassword="sg-epk@jtk931.048596";
		String url = "jdbc:mysql://localhost:3306/users";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		
		try {
			Connection conn = DriverManager.getConnection(url,sqlName,sqlPassword);
			PreparedStatement pre = conn.prepareStatement("select * from officers WHERE trn=?");
			
			pre.setString(1, getTRN());
			
			ResultSet rst= pre.executeQuery();
			
			if (rst.next()) {
				return true;
			}else {
				return false;
			}
			
		}catch (SQLException iX) {
			iX.printStackTrace();
		}
		return false;
	}
	
	
	public void createRecord(Records record) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/users";
			String username = "root";
			String password= "sg-epk@jtk931.048596";
			String trn=record.getTRN();
			String fname= record.getFname();
			String lname = record.getLname();
			String company = record.getCompany();
			String position = record.getPosition();
			String serv= record.getServiceLength();
			String avsecDate=record.getAvsec_date();
			String avsecGrade= record.getAvsec_grade();
			String medExp= record.getMed_doc_exp();
			String polExp= record.getPol_doc_exp();
			String recDate= record.getRec_date();
			String psraExp= record.getPsra_exp();
			Connection conn = DriverManager.getConnection(url,username,password);
			
			String q = "INSERT into officers(TRN,fname,lname,company,position,serviceLength,avsec_date,avsec_grade,med_doc_exp,pol_doc_exp,rec_date,psra_exp) values (?,?,?,?,?,?,?,?,?,?,?,?)"; 
			PreparedStatement prsmt = conn.prepareStatement(q);
			
			prsmt.setString(1,trn);
			prsmt.setString(2, fname);
			prsmt.setString(3, lname);
			prsmt.setString(4, company);
			prsmt.setString(5, position);
			prsmt.setString(6, serv);
			prsmt.setString(7, avsecDate);
			prsmt.setString(8, avsecGrade);
			prsmt.setString(9, medExp);
			prsmt.setString(10, polExp);
			prsmt.setString(11, recDate);
			prsmt.setString(12, psraExp);
			
			prsmt.executeUpdate();
			
			conn.close();
		}catch (SQLException | ClassNotFoundException iX) {
			iX.printStackTrace();
		}
	}
	
	/*public void deleteRecord(String id) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		}catch (SQLException | ClassNotFoundException iX) {
			iX.printStackTrace();
		}
	}*/
	
	public String[] viewRecord() {
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet res=null;
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users","root","sg-epk@jtk931.048596");
			String q = "SELECT ID, fname, lname, med_doc_exp, psra_exp, pol_doc_exp from officers WHERE trn=?";
			prep=conn.prepareStatement(q);
			
			res= prep.executeQuery();
			
			String id = res.getString(1);
			String fname=res.getString(2);
			String lname=res.getString(3);
			String medExp=res.getString(4);
			String psraExp=res.getString(5);
			String polExp = res.getString(6);
			
			String[] row = {id,fname,lname,medExp,psraExp,polExp};
			return row;
			
		}catch (SQLException | ClassNotFoundException iX) {
			iX.printStackTrace();
		}
		String[] none = {};
		return none;
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
