package utilities;

import java.math.BigDecimal;
import java.math.RoundingMode;

import Libraries.Result;

public class Charging {
	public String LocalCallCharging() {
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
	
	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
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

}
