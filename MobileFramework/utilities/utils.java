package utilities;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class utils {
	public static void takeScreenShot() throws IOException {
		// Set folder name to store screenshots.
		String destDir = "screenshots";
		// Capture screenshot.
		File scrFile = ((TakesScreenshot) SetCapabilities.dr).getScreenshotAs(OutputType.FILE);
		// Set date format to set It as screenshot file name.
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
		// Create folder under project with name "screenshots" provided to destDir.
		new File(destDir).mkdirs();
		// Set file name using current date time.
		String destFile = dateFormat.format(new Date()) + ".png";

		try {
			// Copy paste file at destination folder location
			FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static String getPriority(String BucketId) throws FilloException {
		Fillo fillo = new Fillo();
		Connection connection = fillo
				.getConnection("D:\\AutomationScript\\Automation\\VFQA_V1\\MobileFramework\\db\\priority.xlsx");
		String strQuery = "Select * from sheet1 where Bucket_ID='" + BucketId + "'";
		Recordset recordset = connection.executeQuery(strQuery);
		String a;
		recordset.next();
		a = recordset.getField("Priority");
		System.out.println(a);
		connection.close();
		return a;

	}

	public static String getType(String BucketId) throws FilloException {
		Fillo fillo = new Fillo();
		Connection connection = fillo
				.getConnection("D:\\AutomationScript\\Automation\\VFQA_V1\\MobileFramework\\db\\priority.xlsx");
		String strQuery = "Select * from sheet1 where Bucket_ID='" + BucketId + "'";
		Recordset recordset = connection.executeQuery(strQuery);
		String a;
		recordset.next();
		a = recordset.getField("Type");
		System.out.println(a);
		connection.close();
		return a;
	}

	public static String findpriority(String Type) throws FilloException, ParseException {
		int counter = 0;
		String BuckId = null;
		Fillo fillo = new Fillo();
		Connection connection = fillo
				.getConnection("D:\\AutomationScript\\Automation\\VFQA_V1\\MobileFramework\\db\\result.xlsx");
		String strQuery = "Select * from pre where Type='"+Type+"'";
		Recordset recordset = connection.executeQuery(strQuery);
		TreeMap<Date, String> expiry = new TreeMap<Date, String>();
		while (recordset.next()) {
			Date date1 = new SimpleDateFormat("dd-MMM-yyyy").parse(recordset.getField(3).value());
			expiry.put(date1, recordset.getField(0).value());
		}
		Map.Entry<Date, String> entry1 = expiry.entrySet().iterator().next();
		String exp = entry1.getValue();
		for (Map.Entry<Date, String> entry : expiry.entrySet()) {
			Date key = entry.getKey();
			String value = entry.getValue();
			// do stuff
			System.out.println(key + " :: " + value);
			if (exp == value)
				counter++;
		}
		if (counter > 1) {
			System.out.println("Equal");
			recordset = connection.executeQuery(strQuery);
			TreeMap<Integer, String> priority = new TreeMap<Integer, String>();
			while (recordset.next()) {
				int a = Integer.parseInt(recordset.getField(4).value());
				priority.put(a, recordset.getField(0).value());
			}
			Map.Entry<Integer, String> entry2 = priority.entrySet().iterator().next();
			String pri = entry2.getValue();
			for (Map.Entry<Integer, String> entry : priority.entrySet()) {
				Integer key = entry.getKey();
				String value = entry.getValue();
				System.out.println(key + " :: " + value);
			} BuckId = pri;
		}else
			BuckId=exp;	
		return BuckId;		
	}
	
	public static double balance(String Bucket, String type) throws FilloException {
		double Balance;
		Fillo fillo = new Fillo();
		Connection connection = fillo
				.getConnection("D:\\AutomationScript\\Automation\\VFQA_V1\\MobileFramework\\db\\result.xlsx");
		String strQuery = "Select * from "+type+" where Bucket_Id='"+Bucket+"'";
		Recordset recordset = connection.executeQuery(strQuery);
		for(int i=0;i<=recordset.getCount();i++)
			recordset.next();
		String str = recordset.getField(2).value();
		Balance = convBal(str);
		return Balance;
	}
	
	public static double convBal(String a) {
		double val;
		a = a.substring(0, a.indexOf(" "));		
		val = Double.parseDouble(a);
		return val;
	}
	
	public static double getCharging(String Bucket) throws FilloException {
		double charging;
		Fillo fillo = new Fillo();
		Connection connection = fillo
				.getConnection("D:\\AutomationScript\\Automation\\VFQA_V1\\MobileFramework\\db\\priority.xlsx");
		String strQuery = "Select * from sheet1 where Bucket_Id='"+Bucket+"'";
		Recordset recordset = connection.executeQuery(strQuery);
		for(int i=0;i<=recordset.getCount();i++)
			recordset.next();
		String str = recordset.getField(3).value();
		charging = Double.parseDouble(str);
		return charging;
	}

	public static void clenaup() throws FilloException {
		Fillo fillo = new Fillo();
		Connection connection = fillo
				.getConnection("D:\\AutomationScript\\Automation\\VFQA_V1\\MobileFramework\\db\\result.xlsx");
		String strQuery = "Update pre Set Bucket_Id = '', Name='',Value='',Expiry='',Priority='',Type=''";
		connection.executeUpdate(strQuery);
		connection.close();
		connection = fillo
				.getConnection("D:\\AutomationScript\\Automation\\VFQA_V1\\MobileFramework\\db\\result.xlsx");
		strQuery = "Update post Set Bucket_Id = '', Name='',Value='',Expiry='',Priority='',Type=''";
		connection.executeUpdate(strQuery);
		connection.close();

	}
}
