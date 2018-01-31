package Libraries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Keyword_DB extends Driver {

	Connection con = null;

	public String DBConnection() {
		String Test_OutPut = "", Status = "";
		Result.fUpdateLog("------DB connection Event Details------");
		String ServiceName = "";

		if (getdata("Application_Details").equals("BRM_DB")) {
			ServiceName = "BRMPORD";
		} else if (getdata("Application_Details").equals("CRM_DB")) {
			ServiceName = "CRMPORD";
		}
		String Username = getdata("VQ_Login_User");
		String password = getdata("VQ_Login_Pswd");
		String Host = getdata("URL/HOST");
		int Port = 1521;

		String driver = "oracle.jdbc.OracleDriver";
		try {
			Class.forName(driver);
			con = DriverManager.getConnection("jdbc:oracle:thin:@" + Host + ":" + Port + ":" + ServiceName, Username,
					password);
			Result.fUpdateLog("connected to DB");

			Result.fUpdateLog("Connected to DB: " + Host);
			Test_OutPut += "Connected to DB: " + Host + ",";
			Status = "PASS";
		} catch (Exception e) {
			Test_OutPut += "Unable to connect to DB: " + Host + ServiceName + ",";
			Result.fUpdateLog("Unable to connect to DB *** " + e.getMessage());
			Status = "FAIL";
			e.printStackTrace();
		}
		Result.fUpdateLog("------DB connection Event Details - Completed------");
		return Status + "@@" + Test_OutPut + "<br/>";
	}

	public String DBDisconnection() {
		String Test_OutPut = "", Status = "";
		Result.fUpdateLog("------DB Disconnection Event Details------");
		try {
			con.close();
			Result.fUpdateLog("DB disconnected successfully");
			Test_OutPut += "DB disconnected successfully" + ",";
			Status = "PASS";
		} catch (Exception e) {
			Test_OutPut += "Failed to disconnect DB" + ",";
			Result.fUpdateLog("Exception occurred *** " + e.getMessage());
			Status = "FAIL";
			e.printStackTrace();
		}
		Result.fUpdateLog("------DB Disconnection Event Details - Completed------");
		return Status + "@@" + Test_OutPut + "<br/>";
	}

	public String BillPoID() {
		String Test_OutPut = "", Status = "", AccountNo = "";
		String BillingProfileName = "";
		Result.fUpdateLog("------BillPoID Event Details------");
		
		AccountNo = getdata("AccountNo");
		
		if (!(getdata("BillingProfileName").equals(""))) {
			BillingProfileName = getdata("BillingProfileName");	
		} else {
			BillingProfileName = Utlities.FetchStoredValue(UseCaseName.get(), TestCaseN.get(), "BillingProfileName");
		}
		
		try {
			Statement statement = con.createStatement();
			String queryString = "Select a.account_no,b.poid_id0,b.bill_info_id from pin.account_t a,pin.billinfo_t b where a.account_no in '"
					+ AccountNo + "' and b.account_obj_id0=a.poid_id0 and b.bill_info_id='"+BillingProfileName+"'";
			
			ResultSet rs = statement.executeQuery(queryString);
			while (rs.next()) {
				Test_OutPut = "BillPoID: " +rs.getString(2)+" BillingProfileName: "+ rs.getString(3) +",";
				Utlities.StoreValue("BillPoID", rs.getString(2));
			}
			Result.fUpdateLog("DB disconnected successfully");
			Test_OutPut += "DB disconnected successfully" + ",";
			Status = "PASS";
		} catch (Exception e) {
			Test_OutPut += "Failed to disconnect DB" + ",";
			Result.fUpdateLog("Exception occurred *** " + e.getMessage());
			Status = "FAIL";
			e.printStackTrace();
		}
		Result.fUpdateLog("------BillPoID Event Details - Completed------");
		return Status + "@@" + Test_OutPut + "<br/>";
	}

}
