package utilities;

import java.text.SimpleDateFormat;

import org.openqa.selenium.By;
import Libraries.Browser;
import Libraries.Common;
import Libraries.Driver;
import Libraries.Result;
import Libraries.Utlities;

public class Siebel extends Driver {
	Common CO = new Common();

	public String SearchAsset() {
		String Test_OutPut = "", Status = "";
		String MSISDN = utils.fetchData("MSISDN");

		try {
			CO.waitforload();
			int Row = 2, Col;
			CO.Title_Select("a", "Home");
			CO.waitforload();
			Browser.WebLink.waittillvisible("VQ_Assert");
			Browser.WebLink.click("VQ_Assert");
			CO.scroll("Assert_Search", "WebLink");
			Browser.WebLink.click("Assert_Search");
			CO.waitforload();
			Col = CO.Select_Cell("Assert", "Service ID");
			Browser.WebTable.SetDataE("Assert", Row, Col, "Serial_Number", MSISDN);
			Col = CO.Select_Cell("Assert", "Status");
			Browser.WebTable.SetDataE("Assert", Row, Col, "Status", "Active");
			Col = CO.Select_Cell("Assert", "Product");
			Browser.WebButton.waitTillEnabled("Assert_Go");
			Browser.WebButton.click("Assert_Go");
			CO.waitforload();
			// Result.takescreenshot("Account Status : " + Status);
			Col = CO.Select_Cell("Assert", "Account");
			int Assert_Row_Count = Browser.WebTable.getRowCount("Assert");
			if (Assert_Row_Count > 1)
				Browser.WebTable.clickL("Assert", Row, Col);
			else
				Continue.set(false);
			// Browser.WebLink.waittillvisible("Acc_Portal");
			// CO.waitforload();
			// Browser.WebLink.click("Acc_Portal");
			Browser.WebLink.waittillvisible("Inst_Assert_ShowMore");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return Status + "@@" + Test_OutPut + "<br/>";
	}

	public String CheckOrder() {
		String Test_OutPut = "", Status = "",OrderNo;
		int Col, Wait=0,Row_Count;

		try {
			do {
				Browser.WebButton.click("Orders_Tab");
				CO.waitforload();
				if (CO.isAlertExist())
					Browser.WebButton.click("Orders_Tab");
			} while (!Browser.WebTable.waitTillEnabled("Order_Table"));
			
			CO.waitforload();
			CO.Text_Select("div", "Order Date");
			CO.waitforload();
			cDriver.get().findElement(By.xpath("//span[@class='siebui-icon-arrowsm-sort']")).click();
			CO.Text_Select("div", "Order Date");
			CO.waitforload();
			cDriver.get().findElement(By.xpath("//span[@class='siebui-icon-arrowsm-sort']")).click();
			CO.waitforload();
			Col = CO.Select_Cell("Order_Table", "Order #");
			Col = CO.Select_Cell("Order_Table", "Order Date");
			String LastOrderTime = Browser.WebTable.getCellData("Order_Table", 2, Col);
			SimpleDateFormat sdf = new SimpleDateFormat("M/dd/YYYY hh:mm:ss aa");
			System.out.println(LastOrderTime + " and timestamp is " + utils.timestamp);
			do {
				if(sdf.parse(LastOrderTime).after(sdf.parse(utils.timestamp))) {
					Col = CO.Select_Cell("Order_Table", "Order #");
					OrderNo = Browser.WebTable.getCellData("Order_Table", 2, Col);
					Test_OutPut += "OrderNo : " + OrderNo + ",";
					Utlities.StoreValue("OrderNo", OrderNo);
					Browser.WebTable.clickL("Order_Table", 2, Col);
					CO.waitforload();
					Row_Count = Browser.WebTable.getRowCount("Line_Items");
					if (Row_Count <= 3) {
						Browser.WebButton.waittillvisible("Expand");
						Browser.WebButton.click("Expand");
					}
					LineItemData.clear();
					CO.Status("Add");
					
				}else {
					Result.fUpdateLog("Order has not reached to Siebel yet, Waiting...");
					Thread.sleep(30000);
					Wait += 5;
				}
			}while(sdf.parse(LastOrderTime).after(sdf.parse(utils.timestamp)) || Wait<100);

		} catch (Exception e) {
			Status = "FAIL";
			Result.fUpdateLog("Faile due to Exception "+ e);

		}
		return Status + "@@" + Test_OutPut + "<br/>";
	}
	
	public String CheckOrderStatus() {
		String Status="";
		
		return Status;
	}
	
}
