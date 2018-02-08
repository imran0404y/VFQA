package utilities;

import java.util.concurrent.TimeUnit;
import utilities.SetCapabilities;
import utilities.utils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Libraries.Result;

public class Dialers {
	public String MobileNumber;

	public String Dialer() {
		String Test_OutPut = "", Status = "";
		try {
			System.out.println("*** Dialing Number on Mobiles ***");
			MobileNumber = utils.fetchData("CallingNumber");
			SetCapabilities.dr.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			SetCapabilities.dr.findElement(By.id("com.android.dialer:id/floating_action_button")).click();
			SetCapabilities.dr.findElement(By.id("com.android.dialer:id/digits")).click();
			SetCapabilities.dr.findElement(By.id("com.android.dialer:id/digits")).sendKeys(MobileNumber);
			Result.fUpdateLog("Dialing on Mobile Number: " + MobileNumber);
			Test_OutPut += "Dailed Number is: " + MobileNumber;
			SetCapabilities.dr.findElement(By.id("com.android.dialer:id/dialpad_floating_action_button")).click();
			utils.takeScreenShot();
			try {
				WebDriverWait wait = new WebDriverWait(SetCapabilities.dr, 30);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.incallui:id/elapsedTime")));
				Result.fUpdateLog("*** Call Was Successful ***");
				Thread.sleep(2000);
				SetCapabilities.dr.findElement(By.id("com.android.incallui:id/floating_end_call_action_button"))
						.click();
				SetCapabilities.dr.quit();
				Status = "PASS";
			} catch (Exception e) {
				Status = "FAIL";
				Test_OutPut += "Call was failed due to Other Party Has not Picked Up the Call or No Network/SIM Found on the mobile";
				Result.fUpdateLog(
						"Call was failed due to Other Party Has not Picked Up the Call or No Network/SIM Found on the mobile or \n");
			}
		} catch (Exception e) {
			System.out.println(e);
			Status = "FAIL";
		}
		return Status + "@@" + Test_OutPut + "<br/>";
	}

	public String smsSender() {
		String Test_OutPut = "", Status = "";
		try {
			MobileNumber = utils.fetchData("CallingNumber");
			Result.fUpdateLog("*** Sending SMS on Mobiles ***");
			SetCapabilities.dr.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			SetCapabilities.dr.findElement(By.id("com.google.android.apps.messaging:id/start_new_conversation_button"))
					.click();
			SetCapabilities.dr.findElement(By.id("com.google.android.apps.messaging:id/recipient_text_view"))
					.sendKeys(MobileNumber);
			// SetCapabilities.dr.findElement(By.xpath("//*[@class='android.widget.TextView'
			// and @text='FREQUENTS']")).click();
			SetCapabilities.dr.findElement(By.id("com.google.android.apps.messaging:id/contact_picker_create_group"))
					.click();
			SetCapabilities.dr.findElement(By.id("com.google.android.apps.messaging:id/compose_message_text"))
					.sendKeys("This is a test message send by automation script.");
			SetCapabilities.dr.findElement(By.id("com.google.android.apps.messaging:id/send_message_button_icon"))
					.click();
			Thread.sleep(10000);
			try {
				SetCapabilities.dr.findElement(By.xpath("//*[@class='android.widget.TextView' and @text='Now ? SMS']"));
				Result.fUpdateLog("*** Message Successfully Sent ***");
				Status = "PASS";
			} catch (Exception e) {
				Result.fUpdateLog("SMS was not sent, Please check SMS Center/Network in Mobile");
			}
		} catch (Exception e) {
			System.out.println(e);
			Status = "FAIL";
		}
		return Status + "@@" + Test_OutPut + "<br/>";
	}

	public String BalanceCheckDialer() {
		String Test_OutPut = "", Status = "";
		try {
			System.out.println("*** Dialing Balance Check Code on Mobiles ***");
			MobileNumber = utils.fetchData("BalanceCheckCode");
			SetCapabilities.dr.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			SetCapabilities.dr.findElement(By.id("com.android.dialer:id/floating_action_button")).click();
			SetCapabilities.dr.findElement(By.id("com.android.dialer:id/digits")).click();
			SetCapabilities.dr.findElement(By.id("com.android.dialer:id/digits")).sendKeys(MobileNumber);
			Result.fUpdateLog("Dialing on Mobile Number: " + MobileNumber);
			Test_OutPut += "Balance Check Code is: " + MobileNumber;
			SetCapabilities.dr.findElement(By.id("com.android.dialer:id/dialpad_floating_action_button")).click();
			utils.takeScreenShot();
			try {
				WebDriverWait wait = new WebDriverWait(SetCapabilities.dr, 30);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("android:id/button1")));
				SetCapabilities.dr.findElement(By.id("android:id/button1")).click();
				SetCapabilities.dr.quit();
				Status = "PASS";
			} catch (Exception e) {
				Status = "FAIL";
				Test_OutPut += "Balance Check was failed due to USSD Code is not working or No Network/SIM Found on the mobile";
				Result.fUpdateLog(
						"Balance Check was failed due to USSD Code is not working or No Network/SIM Found on the mobile");
			}
		} catch (Exception e) {
			System.out.println(e);
			Status = "FAIL";
		}
		return Status + "@@" + Test_OutPut + "<br/>";
	}

	public String RechargeDialer() {
		String Test_OutPut = "", Status = "",RechargePIN;
		try {
			System.out.println("*** Dialing Balance Check Code on Mobiles ***");
			RechargePIN = utils.fetchData("RechargePIN");
			SetCapabilities.dr.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			SetCapabilities.dr.findElement(By.id("com.android.dialer:id/floating_action_button")).click();
			SetCapabilities.dr.findElement(By.id("com.android.dialer:id/digits")).click();
			SetCapabilities.dr.findElement(By.id("com.android.dialer:id/digits")).sendKeys("*127*"+RechargePIN+"#");
			Result.fUpdateLog("Recharge PIN used is: " + RechargePIN);
			Test_OutPut += "Recharge PIN used is: " + RechargePIN+"</br>";
			SetCapabilities.dr.findElement(By.id("com.android.dialer:id/dialpad_floating_action_button")).click();
			utils.takeScreenShot();
			try {
				WebDriverWait wait = new WebDriverWait(SetCapabilities.dr, 30);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("android:id/button1")));
				SetCapabilities.dr.findElement(By.id("android:id/button1")).click();
				SetCapabilities.dr.quit();
				Status = "PASS";
			} catch (Exception e) {
				Status = "FAIL";
				Test_OutPut += "Recharge didn't worked to USSD Code is not working or No Network/SIM Found on the mobile";
				Result.fUpdateLog(
						"Recharge didn't worked to USSD Code is not working or No Network/SIM Found on the mobile");
			}
		} catch (Exception e) {
			System.out.println(e);
			Status = "FAIL";
		}
		return Status + "@@" + Test_OutPut + "<br/>";
	}
	
}
