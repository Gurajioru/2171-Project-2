package officerManagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

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
	public RecordsController() {
		
	}
	
	public boolean checkTRN(){
		String sqlName="root";
		String sqlPassword="sg-epk@jtk931.048596";
		String url = "jdbc:mysql://localhost:3306/users";
		Connection conn=null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		
		try {
			conn = DriverManager.getConnection(url,sqlName,sqlPassword);
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
		}finally {
			try {
				conn.close();
			}catch (SQLException iX){
				iX.printStackTrace();
			}
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
			//iX.printStackTrace();
			JOptionPane.showMessageDialog(null,"Incorrect Format Used.","Entry error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void deleteRecord(String id) {
		String q="DELETE from officers WHERE id="+id;
		Connection conn=null;
		PreparedStatement prep=null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users","root","sg-epk@jtk931.048596");
			prep = conn.prepareStatement(q);
			prep.execute();
			
			conn.close();
			prep.close();
			
		}catch (SQLException | ClassNotFoundException iX) {
			iX.printStackTrace();
		}finally {
			try {
				conn.close();
				prep.close();
			}catch(Exception iX) {
				
			}
		}
	}
	
	public String[][] viewRecord(String id) {
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet res=null;
		String[][] row = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users","root","sg-epk@jtk931.048596");
			String q = "SELECT ID, fname, lname, med_doc_exp, psra_exp, pol_doc_exp from officers WHERE id="+id;
			prep=conn.prepareStatement(q);
			
			res= prep.executeQuery();
			row = new String[1][6];
			if (res.next()) {
				//String id = res.getString(1);
				String fname=res.getString(2);
				String lname=res.getString(3);
				String medExp=res.getString(4);
				String psraExp=res.getString(5);
				String polExp = res.getString(6);
				
				String[] rowIns = {id,fname,lname,medExp,psraExp,polExp};
				row[0]=rowIns;
			}
			//conn.close();
			return row;
			
		}catch (SQLException | ClassNotFoundException iX) {
			//iX.printStackTrace();
			JOptionPane.showMessageDialog(null,"Incorrect Format Used.","Entry error",JOptionPane.ERROR_MESSAGE);
		}finally {
			try {
				prep.close();
				res.close();
				conn.close();
			}catch (SQLException iX) {
				//iX.printStackTrace();
				JOptionPane.showMessageDialog(null,"Incorrect Format Used.","Entry error",JOptionPane.ERROR_MESSAGE);
			}
		}
		String[][] none = {};
		return none;
		
	}
	
	
	public String[][] generateRandom() {
		ResultSet rs=null;
		PreparedStatement prep= null;
		Connection conn=null;
		String[][] arr= null;
		
		try {
			String sql = "SELECT COUNT(*) from officers";
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/users","root","sg-epk@jtk931.048596");
			prep=conn.prepareStatement(sql);
			rs=prep.executeQuery();
			rs.next();
			int rowCount=rs.getInt(1);
			//System.out.println(rowCount);
			int twentyFive = (int) Math.ceil(rowCount/4.00);
			
			
			//conn.close();
			//System.out.println(twentyFive);
			//conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/users","root","sg-epk@jtk931.048596");
			String sql2= "SELECT id,fname,lname,med_doc_exp, psra_exp, pol_doc_exp FROM officers order by RAND() LIMIT "+String.valueOf(twentyFive);
			PreparedStatement prep2=conn.prepareStatement(sql2);
			
			ResultSet res2=prep2.executeQuery();
			arr=new String[twentyFive][6];
			for (int i =0; i<twentyFive; i++) {
				
				res2.next();
				
				String id=res2.getString(1);
				//System.out.println(id);
				String fname=res2.getString(2);
				String lname=res2.getString(3);
				String med_doc_exp=res2.getString(4);
				String psra_doc_exp=res2.getString(5);
				String pol_doc_exp=res2.getString(6);
				
				String[] row = {id, fname, lname, med_doc_exp, psra_doc_exp, pol_doc_exp};
				//System.out.println(row.toString());
				arr[i]=row;
			}
			return arr;
			
			//res2.close();
			//rs.close();
			//prep2.close();
			//prep.close();
			//conn.close();
			//return res2;
			
			
		}catch (SQLException iX) {
			iX.printStackTrace();
		}finally {
			try {
				conn.close();
				prep.close();
				rs.close();
				
			}catch (SQLException iX) {
				iX.printStackTrace();
			}
		}
		
		return arr;
		
	}
	
	public void editRecord(String id, String fname, String lname, String medExp, String psraExp, String polExp) {
		
		Connection conn=null;;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/users","root","sg-epk@jtk931.048596");
			String sql= "UPDATE officers SET fname=?,lname=?,med_doc_exp=?,psra_exp=?,pol_doc_exp=? WHERE id="+id;
			PreparedStatement prep = conn.prepareStatement(sql);
			
			prep.setString(1, fname);
			prep.setString(2, lname);
			prep.setString(3, medExp);
			prep.setString(4, psraExp);
			prep.setString(5, polExp);
			
			prep.executeUpdate();
			
			prep.close();
			
		}catch (SQLException | ClassNotFoundException iX) {
			//iX.printStackTrace();
			JOptionPane.showMessageDialog(null,"Incorrect Format Used.","Entry error",JOptionPane.ERROR_MESSAGE);
		}finally {
			try {
				conn.close();
			}catch (SQLException iX) {
				//iX.printStackTrace();
				JOptionPane.showMessageDialog(null,"Incorrect Format Used.","Entry error",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public boolean exportRecords() {
		Connection conn=null;
		ResultSet res = null;
		PreparedStatement prep = null;
		
		try {
			String docpath=new JFileChooser().getFileSystemView().getDefaultDirectory().toString();
			String fileName = new SimpleDateFormat("yyyyMMddHHmm'.csv'").format(new Date());
		    //PrintWriter pw= new PrintWriter(new File("C:\\Users\\aubry\\Documents\\testExport.csv"));
			makeDir(docpath);
			PrintWriter pw = new PrintWriter(new File(docpath+"\\"+"officerExports"+"\\"+fileName));
		    StringBuilder sb = new StringBuilder();
		    conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/users","root","sg-epk@jtk931.048596");
		    String sql = "Select * from officers";
		    prep = conn.prepareStatement(sql);
		    res=prep.executeQuery();
		    
		    while(res.next()){
		        sb.append(res.getString("TRN"));
		        sb.append(","); 
		        sb.append(res.getString("fname"));
		        sb.append(",");
		        sb.append(res.getString("lname"));
		        sb.append(",");
		        sb.append(res.getString("company"));
		        sb.append(",");
		        sb.append(res.getString("position"));
		        sb.append(",");
		        sb.append(res.getString("serviceLength"));
		        sb.append(","); 
		        sb.append(res.getString("avsec_date"));
		        sb.append(",");
		        sb.append(res.getString("avsec_grade"));
		        sb.append(",");
		        sb.append(res.getString("med_doc_exp"));
		        sb.append(",");
		        sb.append(res.getString("pol_doc_exp"));
		        sb.append(",");
		        sb.append(res.getString("rec_date"));
		        sb.append(",");
		        sb.append(res.getString("id"));
		        sb.append(",");
		        sb.append(res.getString("psra_exp"));
		        sb.append("\r\n");
		       }
		    pw.write(sb.toString());
		    pw.close();
		    return true;
		}catch (SQLException | FileNotFoundException iX) {
			iX.printStackTrace();
			return false;
		}catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}finally {
			try {
				res.close();
				prep.close();
				conn.close();
			}catch (Exception iX) {
				iX.printStackTrace();
				
			}
		}
		
	}
	
	private void makeDir(String path) {
		File dir = new File(path+"\\"+"officerExports");
		
		if(!dir.exists()) {
			dir.mkdir();
		}
	}
	
	public boolean addToSchedule(String id, String date, String reason, String message) {
		
		String url = "jdbc:mysql://localhost:3306/users";
		String username = "root";
		String password= "sg-epk@jtk931.048596";
		Connection conn= null;
		PreparedStatement prep = null;
		try {
			conn = DriverManager.getConnection(url,username,password);
			String q = "INSERT into training (id, date, reason, message) VALUES (?,?,?,?)";
			prep = conn.prepareStatement(q);
			
			prep.setString(1, id);
			prep.setString(2, date);
			prep.setString(3, reason);
			prep.setString(4, message);
			
			prep.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally {
			try {
				prep.close();
				conn.close();
			}catch (SQLException iX) {
				iX.printStackTrace();
			}
		}
		
	}
	
	
	
	
}
