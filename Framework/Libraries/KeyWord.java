package Libraries;

import utilities.Dialers;
import utilities.MobileRTBCheck;
import utilities.SetCapabilities;

public class KeyWord {

	Keyword_CRM KC = new Keyword_CRM();
	Keyword_API KA = new Keyword_API();
	Keyword_SIPT KS = new Keyword_SIPT();
	Keyword_Validations KV = new Keyword_Validations();
	Keyword_Putty KP = new Keyword_Putty();
	Keyword_Guided KG = new Keyword_Guided();
	Keyword_FixedLine KF = new Keyword_FixedLine();
	Keyword_OSM KO = new Keyword_OSM();
	Dialers KD = new Dialers();
    SetCapabilities KSC = new SetCapabilities();
    MobileRTBCheck RTB = new MobileRTBCheck();


	// ------------------Keyword CRM -------------------//
	public String Siebel_Login() {
		return KC.Siebel_Login();
	}

	public String Siebel_Logout() {
		return KC.Siebel_Logout();
	}

	public String ContactCreation() {
		return KC.ContactCreation();
	}

	public String AccountCreation() {
		return KC.AccountCreation();
	}

	public String BillingProfileCreation() {
		return KC.BillingProfileCreation();
	}

	public String SalesOrder() {
		return KC.SalesOrder();
	}

	public String PlanSelection() {
		return KC.PlanSelection();
	}

	public String OrderSubmission() {
		return KC.OrderSubmission();
	}

	public String Entp_AccountCreation() {
		return KC.Entp_AccountCreation();
	}

	public String Entp_ContactCreation() {
		return KC.Entp_ContactCreation();
	}

	public String Modify() {
		return KC.Modify();
	}

	public String Plan_UpgradeDowngrade() {
		return KC.Plan_UpgradeDowngrade();
	}

	public String SIMSwap() {
		return KC.SIMSwap();
	}

	public String Change_MSISDN() {
		return KC.Change_MSISDN();
	}

	public String Consumer_Migration() {
		return KC.Consumer_Migration();
	}

	public String Enterprise_Migration() {
		return KC.Enterprise_Migration();
	}

	public String RealTimeBalance_Screen() {
		return KC.RealTimeBalance_Screen();
	}

	public String Change_SmartLimit() {
		return KC.Change_SmartLimit();
	}

	public String BillPayment() {
		return KC.BillPayment();
	}

	public String Disconnection() {
		return KC.Disconnection();
	}

	public String TroubleTicket() {
		return KC.TroubleTicket();
	}

	public String Discounts() {
		return KC.Discounts();
	}

	public String TransferOfService() {
		return KC.TransferOfService();
	}

	public String SiebleValidation() {
		return KC.SiebleValidation();
	}

	public String TransferOfOwnership() {
		return KC.TransferOfOwnership();
	}

	public String ServicePoint() {
		return KC.ServicePoint();
	}

	public String UpgradePromotion_Account360() {
		return KC.UpgradePromotion_Account360();
	}

	public String Account360() {
		return KC.Account360();
	}

	// ------------------Keyword CRM -------------------//

	// ------------------Keyword Guided -------------------//

	public String SIMSwap_Guided_Journey() {
		return KG.SIMSwap_Guided_Journey();
	}

	public String ConsumerPostpaid_GuidedJourney() {
		return KG.ConsumerPostpaid_GuidedJourney();
	}

	public String SIMSwap_Global_Search() {
		return KG.SIMSwap_Global_Search();
	}
	// ------------------Keyword Guided -------------------//

	// ---------------------Keyword API------------------------//

	public String RTB_Login() {
		return KA.RTB_Login();
	}

	public String RTB() {
		return KA.RTB();
	}

	public String RTB_Check() {
		return KA.RTB_Check();
	}

	public String RTB_Compare() {
		return KA.RTB_Compare();
	}

	// ---------------------Keyword API------------------------//

	// ---------------------Keyword Validation------------------------//

	public String RTB_Validation() {
		return KV.RTB_Validation();
	}

	// ---------------------Keyword Validation------------------------//

	// ---------------------Keyword SIPT------------------------//

	public String SIPT() {
		return KS.SIPT();
	}

	// ---------------------Keyword SIPT------------------------//

	// ---------------------Keyword Putty------------------------//

	public String LoginSSH() {
		return KP.LoginSSH();
	}

	public String LogoutSSH() {
		return KP.LogoutSSH();
	}

	public String BillGeneration_AccountLevel() {
		return KP.BillGeneration_AccountLevel();
	}
	// ---------------------Keyword Putty------------------------//

	// ---------------------Keyword_OSM------------------------//

	public String PlanSelection_FL() {
		return KF.PlanSelection_FL();
	}

	public String PlanSelection_FL_ENT() {
		return KF.PlanSelection_FL_ENT();
	}

	// ---------------------Keyword_OSM------------------------//
	// ---------------------Keyword_FixedLine------------------------//

	public String OSM_Login() {
		return KO.OSM_Login();
	}

	public String OSM_SearchFL() {
		return KO.OSM_SearchFL();
	}

	// ---------------------Keyword_OSM------------------------//
	// ---------------------Mobile Usages------------------------//
	public String LocalDialerOnNet() {
		return KD.LocalDialerOnNet();
	}

	public String SetCapabilities() {
		return KSC.setDialerCapabilities();
	}

	public String PrevCheckBalance() {
		return RTB.PrevCheckBalance();
	}

	public String PostCheckBalance() {
		return RTB.PostCheckBalance();
	}

	public String LocalOnNetCharging() {
		return RTB.LocalOnNetCharging();
	}

	public String RTBLogin() {
		return RTB.RTBLogin();
	}
	// ---------------------Mobile Usages------------------------//

}
