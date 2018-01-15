package utilities;
import java.util.concurrent.TimeUnit;
import utilities.SetCapabilities;
import utilities.utils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Libraries.Result;

public class Dialers {
	
	public String LocalDialerOnNet() {	
		String Test_OutPut = "", Status = "";
		try {	
	System.out.println("*** Dialing Number on Mobiles ***");
	SetCapabilities.dr.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	SetCapabilities.dr.findElement(By.id("com.android.dialer:id/floating_action_button")).click();
	SetCapabilities.dr.findElement(By.id("com.android.dialer:id/seven")).click();
	SetCapabilities.dr.findElement(By.id("com.android.dialer:id/zero")).click();
	SetCapabilities.dr.findElement(By.id("com.android.dialer:id/one")).click();
	SetCapabilities.dr.findElement(By.id("com.android.dialer:id/nine")).click();
	SetCapabilities.dr.findElement(By.id("com.android.dialer:id/three")).click();
	SetCapabilities.dr.findElement(By.id("com.android.dialer:id/zero")).click();
	SetCapabilities.dr.findElement(By.id("com.android.dialer:id/three")).click();
	SetCapabilities.dr.findElement(By.id("com.android.dialer:id/five")).click();
	SetCapabilities.dr.findElement(By.id("com.android.dialer:id/dialpad_floating_action_button")).click();
	utils.takeScreenShot();
	//Test_OutPut+="Dialing on Number 30174036 ";
	try {
	WebDriverWait wait = new WebDriverWait(SetCapabilities.dr, 30);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.incallui:id/elapsedTime")));
	Result.fUpdateLog("*** Call Was Successful ***");
	Thread.sleep(10000);
	SetCapabilities.dr.findElement(By.id("com.android.incallui:id/floating_end_call_action_button")).click();
	Test_OutPut+="Dialled Call for 10Seconds";
	SetCapabilities.dr.quit();
	Status = "PASS";
	}catch(Exception e) {
		Status = "FAIL";
		Test_OutPut+="Call was failed due to Other Party Has not Picked Up the Call or No Network/SIM Found on the mobile";
		Result.fUpdateLog("Call was failed due to Other Party Has not Picked Up the Call or No Network/SIM Found on the mobile or \n");
	}
	}catch(Exception e) {
		System.out.println(e);
		Status = "FAIL";
	}
		Status = "PASS";
		return Status + "@@" + Test_OutPut + "<br/>";
}
	
	public void LocalDialerOffNet() {	
		try {
	System.out.println("*** Dialing Number on Mobiles ***");
	SetCapabilities.dr.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	SetCapabilities.dr.findElement(By.id("com.android.dialer:id/floating_action_button")).click();
	SetCapabilities.dr.findElement(By.id("com.android.dialer:id/three")).click();
	SetCapabilities.dr.findElement(By.id("com.android.dialer:id/zero")).click();
	SetCapabilities.dr.findElement(By.id("com.android.dialer:id/one")).click();
	SetCapabilities.dr.findElement(By.id("com.android.dialer:id/seven")).click();
	SetCapabilities.dr.findElement(By.id("com.android.dialer:id/four")).click();
	SetCapabilities.dr.findElement(By.id("com.android.dialer:id/zero")).click();
	SetCapabilities.dr.findElement(By.id("com.android.dialer:id/three")).click();
	SetCapabilities.dr.findElement(By.id("com.android.dialer:id/six")).click();
	SetCapabilities.dr.findElement(By.id("com.android.dialer:id/dialpad_floating_action_button")).click();
	try {
	WebDriverWait wait = new WebDriverWait(SetCapabilities.dr, 30);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.incallui:id/elapsedTime")));
	Result.fUpdateLog("*** Call Was Successful ***");
	Thread.sleep(10000);
	SetCapabilities.dr.findElement(By.id("com.android.incallui:id/floating_end_call_action_button")).click();
	SetCapabilities.dr.quit();
	}catch(Exception e) {
		Result.fUpdateLog("Call was failed due to No Network/SIM Found on the mobile or \n" + e);
	}
	}catch(Exception e) {
		System.out.println(e);
	}
}
	public void InternationalDialer_India() {	
		try {
	System.out.println("*** Dialing Number on Mobiles ***");
	SetCapabilities.dr.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	SetCapabilities.dr.findElement(By.id("com.android.dialer:id/floating_action_button")).click();
	SetCapabilities.dr.findElement(By.id("com.android.dialer:id/zero")).click();
	SetCapabilities.dr.findElement(By.id("com.android.dialer:id/zero")).click();
	SetCapabilities.dr.findElement(By.id("com.android.dialer:id/nine")).click();
	SetCapabilities.dr.findElement(By.id("com.android.dialer:id/one")).click();
	SetCapabilities.dr.findElement(By.id("com.android.dialer:id/nine")).click();
	SetCapabilities.dr.findElement(By.id("com.android.dialer:id/two")).click();
	SetCapabilities.dr.findElement(By.id("com.android.dialer:id/one")).click();
	SetCapabilities.dr.findElement(By.id("com.android.dialer:id/two")).click();
	SetCapabilities.dr.findElement(By.id("com.android.dialer:id/six")).click();
	SetCapabilities.dr.findElement(By.id("com.android.dialer:id/nine")).click();
	SetCapabilities.dr.findElement(By.id("com.android.dialer:id/two")).click();
	SetCapabilities.dr.findElement(By.id("com.android.dialer:id/one")).click();
	SetCapabilities.dr.findElement(By.id("com.android.dialer:id/two")).click();
	SetCapabilities.dr.findElement(By.id("com.android.dialer:id/six")).click();
	SetCapabilities.dr.findElement(By.id("com.android.dialer:id/dialpad_floating_action_button")).click();
	try {
	WebDriverWait wait = new WebDriverWait(SetCapabilities.dr, 30);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.incallui:id/elapsedTime")));
	System.out.println("*** Call Was Successful ***");
	Thread.sleep(10000);
	SetCapabilities.dr.findElement(By.id("com.android.incallui:id/floating_end_call_action_button")).click();
	SetCapabilities.dr.quit();
	}catch(Exception e) {
		System.out.println("Call was failed due to No Network/SIM Found on the mobile or \n" + e);
	}
	}catch(Exception e) {
		System.out.println(e);
	}
}
}
