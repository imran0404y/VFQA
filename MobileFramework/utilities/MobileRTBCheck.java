package utilities;
import Libraries.*;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;

public class MobileRTBCheck extends Driver {
	//public static String Rate_LocalCall_OnNet;
	Common CO = new Common();
	Keyword_API ObjApi = new Keyword_API();
	String BalanceStr;
	double PreBal, PostBal, Charging, Balance, Rate, Actual, Expected;
	
	public String RTBLogin() {

		String Test_OutPut = "", Status = "";
		Result.fUpdateLog("------RTB Login Event Details------");
		try {
				if (!(getdata("URL").equals(""))) {
					URL.set(getdata("URL"));
				} else {
					URL.set(pulldata("URL"));
				}

				if (!URL.get().equals("")) {
					Result.fUpdateLog("Successfully set the End Point URL: " + URL.get());
					Status = "PASS";
				} else {
					Result.fUpdateLog("Failed to set the End Point URL");
					Status = "FAIL";
				}
		} catch (Exception e) {
			Test_OutPut += "Failed at URL Fetch" + ",";
			Result.fUpdateLog("Exception occurred *** " + e.getMessage());
			Status = "FAIL";
			e.printStackTrace();
		}
		Result.fUpdateLog("------RTB Login Event Details - Completed------");
		return Status + "@@" + Test_OutPut + "<br/>";
	}

	public String PrevCheckBalance() {
		String Type="",Priority="",BucketName="", BucketID = "", BucketValue="", BucketExpiry="",MSISDN, SOAP_Action, XMLResponse_Path = "", XMLRequest_Path = "",Test_OutPut = "", Status = "";
		Result.fUpdateLog("------Checking Balance Prior to Usage------");

		try {
			if (!(getdata("MSISDN").equals(""))) {
				MSISDN = getdata("MSISDN");
				SOAP_Action = "QueryRealTimeBalance";
				String Templatefile = Templete_FLD.get() + "/XML/RTBQuery_Temp.xml";
				
				/* Print the request message */
				Result.fUpdateLog("Request SOAP Message = ");

				// Get and Store Request XML File Path
				String XMLfilepath = XMLfilepth.get();
				XMLRequest_Path = XMLfilepath + "/" + currKW.get() + "_" + MSISDN + "_Request.xml";
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(new File(Templatefile));
				doc.getDocumentElement().normalize();

				// Get a set of the entries
				if (MSISDN != null) {
					String Tagname = "cmu:AccountId";
					CO.Setvalue(doc, Tagname, MSISDN);
				}

				// Save Request XMl into XMLRequest_Path
				Transformer xformer = TransformerFactory.newInstance().newTransformer();
				xformer.transform(new DOMSource(doc), new StreamResult(new File(XMLRequest_Path)));

				// Read the request XML File
				SOAPMessage message = CO.readSoapMessage(XMLRequest_Path, SOAP_Action);
				message.writeTo(System.out);
				// String
				// URL="http://10.162.53.91:8001/soa-infra/services/vfqamrgdomain/QueryRealTimeBalanceSiebelReqABCSImpl/queryrealtimebalancesiebelreqabcsimpl_client_ep?WSDL";
				// Establish SOAP Connection and send request to End Point URL
				SOAPMessage soapResponse = CO.XML_Request(message, URL.get());

				// Process the SOAP Response and store it
				XMLResponse_Path = XMLfilepath + "/" + currKW.get() + "_" + MSISDN + "_Response.xml";
				File Responsefile = new File(XMLResponse_Path);
				Responsefile.createNewFile();

				FileOutputStream fileOutputStream = new FileOutputStream(Responsefile);
				soapResponse.writeTo(fileOutputStream);
				fileOutputStream.flush();
				fileOutputStream.close();

				// Fetch Data from Soap Response
				DocumentBuilderFactory dbFactory1 = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder1 = dbFactory1.newDocumentBuilder();
				Document doc1 = dBuilder1.parse(new File(XMLResponse_Path));
				doc1.getDocumentElement().normalize();

				// Det count of Node : rtbrespabo:CmuBalanceSummaryVbc
				NodeList list = doc1.getElementsByTagName("rtbrespabo:CmuBalanceSummaryVbc");
				Result.fUpdateLog("Total of elements : " + list.getLength());
				utils.clenaup();
				Fillo fillo = new Fillo();
				Connection connection = fillo
						.getConnection("D:\\AutomationScript\\Automation\\VFQA_V1\\MobileFramework\\db\\result.xlsx");
				String strQuery;

				for (int i = 0; i < list.getLength(); i++) {
					BucketName = CO.getvalue(doc1, "rtbrespabo:CmuBalanceSummaryVbc", "rtbrespabo:English", i);
					BucketID = CO.getvalue(doc1, "rtbrespabo:CmuBalanceSummaryVbc", "rtbrespabo:BucketID", i);
					BucketValue = CO.getvalue(doc1, "rtbrespabo:CmuBalanceSummaryVbc", "rtbrespabo:Total", i);
					BucketExpiry = CO.getvalue(doc1, "rtbrespabo:CmuBalanceSummaryVbc", "rtbrespabo:DueNow", i);
					if(BucketID=="") {
						BucketName="Primary Balance";
						BucketID="1";
					}
					Priority = utils.getPriority(BucketID);
					Type = utils.getType(BucketID);
					strQuery = "INSERT INTO pre(Bucket_Id,Name,Value,Expiry,Priority,Type) VALUES('" + BucketID + "','" + BucketName + "','" + BucketValue + "','" + BucketExpiry + "','" + Priority + "','" + Type + "')";
					connection.executeUpdate(strQuery);
					}
				connection.close();
				Status = "PASS";
			}
		} catch (Exception e) {
			Result.fUpdateLog("Exception occurred *** " + e.getMessage());
			e.printStackTrace();
			Status = "FAIL";
		}
		
		return Status + "@@" + Test_OutPut + "<br/>";
	}

	public String PostCheckBalance() {
		String Type="",Priority="",BucketName="", BucketID = "", BucketValue="", BucketExpiry="",MSISDN, SOAP_Action, XMLResponse_Path = "", XMLRequest_Path = "",Test_OutPut = "", Status = "";
		Result.fUpdateLog("------Checking Balance After Usage------");
		
		try {
			if (!(getdata("MSISDN").equals(""))) {
				MSISDN = getdata("MSISDN");
				SOAP_Action = "QueryRealTimeBalance";
				String Templatefile = Templete_FLD.get() + "/XML/RTBQuery_Temp.xml";
				
				/* Print the request message */
				Result.fUpdateLog("Request SOAP Message = ");

				// Get and Store Request XML File Path
				String XMLfilepath = XMLfilepth.get();
				XMLRequest_Path = XMLfilepath + "/" + currKW.get() + "_" + MSISDN + "_Request.xml";
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(new File(Templatefile));
				doc.getDocumentElement().normalize();

				// Get a set of the entries
				if (MSISDN != null) {
					String Tagname = "cmu:AccountId";
					CO.Setvalue(doc, Tagname, MSISDN);
				}

				// Save Request XMl into XMLRequest_Path
				Transformer xformer = TransformerFactory.newInstance().newTransformer();
				xformer.transform(new DOMSource(doc), new StreamResult(new File(XMLRequest_Path)));

				// Read the request XML File
				SOAPMessage message = CO.readSoapMessage(XMLRequest_Path, SOAP_Action);
				message.writeTo(System.out);
				// String
				// URL="http://10.162.53.91:8001/soa-infra/services/vfqamrgdomain/QueryRealTimeBalanceSiebelReqABCSImpl/queryrealtimebalancesiebelreqabcsimpl_client_ep?WSDL";
				// Establish SOAP Connection and send request to End Point URL
				SOAPMessage soapResponse = CO.XML_Request(message, URL.get());

				// Process the SOAP Response and store it
				XMLResponse_Path = XMLfilepath + "/" + currKW.get() + "_" + MSISDN + "_Response.xml";
				File Responsefile = new File(XMLResponse_Path);
				Responsefile.createNewFile();

				FileOutputStream fileOutputStream = new FileOutputStream(Responsefile);
				soapResponse.writeTo(fileOutputStream);
				fileOutputStream.flush();
				fileOutputStream.close();

				// Fetch Data from Soap Response
				DocumentBuilderFactory dbFactory1 = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder1 = dbFactory1.newDocumentBuilder();
				Document doc1 = dBuilder1.parse(new File(XMLResponse_Path));
				doc1.getDocumentElement().normalize();

				// Det count of Node : rtbrespabo:CmuBalanceSummaryVbc
				NodeList list = doc1.getElementsByTagName("rtbrespabo:CmuBalanceSummaryVbc");
				Result.fUpdateLog("Total of elements : " + list.getLength());
				Fillo fillo = new Fillo();
				Connection connection = fillo
						.getConnection("D:\\AutomationScript\\Automation\\VFQA_V1\\MobileFramework\\db\\result.xlsx");
				String strQuery;
				for (int i = 0; i < list.getLength(); i++) {
					BucketName = CO.getvalue(doc1, "rtbrespabo:CmuBalanceSummaryVbc", "rtbrespabo:English", i);
					BucketID = CO.getvalue(doc1, "rtbrespabo:CmuBalanceSummaryVbc", "rtbrespabo:BucketID", i);
					BucketValue = CO.getvalue(doc1, "rtbrespabo:CmuBalanceSummaryVbc", "rtbrespabo:Total", i);
					BucketExpiry = CO.getvalue(doc1, "rtbrespabo:CmuBalanceSummaryVbc", "rtbrespabo:DueNow", i);
					if(BucketID=="") {
						BucketName="Primary Balance";
						BucketID="1";
					}
					Priority = utils.getPriority(BucketID);
					Type = utils.getType(BucketID);
					strQuery = "INSERT INTO post(Bucket_Id,Name,Value,Expiry,Priority,Type) VALUES('" + BucketID + "','" + BucketName + "','" + BucketValue + "','" + BucketExpiry + "','" + Priority + "','" + Type + "')";
					connection.executeUpdate(strQuery);
				}
				Status = "PASS";
			}
		} catch (Exception e) {
			Result.fUpdateLog("Exception occurred *** " + e.getMessage());
			e.printStackTrace();
			Status = "FAIL";
		}
		
		return Status + "@@" + Test_OutPut + "<br/>";
	}

	public double ExtractBal(String s) {
		// String s = "Primary Balance : 11.5500 Your account has primary balance
		// 0.0000QAR expires on Date Not Given";
		int qrIndex = s.indexOf(": ");
		//System.out.println("index is: " + qrIndex);
		//System.out.println("Text at " + qrIndex + " is:" + s.substring(qrIndex + 1, s.indexOf("Your")));
		String balancestr = s.substring(qrIndex + 1, s.indexOf("Your"));
		double balance = Double.parseDouble(balancestr);
		balance = round(balance, 2);
		System.out.println("Your balance is QR " + balance);
		return balance;
	}

	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	public String LocalOnNetCharging() {
		double pre, post, charging,actual;
		String Test_OutPut = "", Status = "",Bucket;
		try {
			System.out.println("test");
			Bucket=utils.findpriority("Local");
			Result.fUpdateLog(Bucket + "Bucket is having high priority, hence will consume first. ");
			Test_OutPut+= Bucket + " Bucket is having high priority, hence will consume first.\n";
			pre = utils.balance(Bucket,"pre");
			Result.fUpdateLog(pre + " is the previous balance ");
			Test_OutPut+=pre + " is the previous balance\n";
			post = utils.balance(Bucket,"post");
			Result.fUpdateLog(post + " is the balance after usage");
			Test_OutPut+=post + " is the balance after usage.\n";
			actual = round(pre-post,2);
			Result.fUpdateLog(actual + " is the actual charging happened.");
			Test_OutPut+= actual + " is the actual charging happened.\n";
			charging = utils.getCharging(Bucket);
			Result.fUpdateLog(charging + " is the charging expected.");
			Test_OutPut+=charging + " is the charging expected.\n";
			if(actual==charging) {
				Status = "PASS";
			System.out.println("Pass");}
			else {
				Status = "FAIL";
				System.out.println("Fail");
			}
		} catch (Exception e) {
			Status = "FAIL";
			Test_OutPut+= "Exception Occured" + e ;	
		}
		return Status + "@@" + Test_OutPut + "<br/>";
	}

}
