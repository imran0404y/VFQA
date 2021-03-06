package Libraries;

import utilities.Charging;
import utilities.Dialers;
import utilities.Handset;
import utilities.MobileRTBCheck;
import utilities.Notification;
import utilities.SetCapabilities;
import utilities.Siebel;
import utilities.USSD;

public class KeyWord {

	Keyword_CRM KC = new Keyword_CRM();
	Keyword_API KA = new Keyword_API();
	Keyword_SIPT KS = new Keyword_SIPT();
	Keyword_ETHERNET KM = new Keyword_ETHERNET();
	Keyword_LEASEDLINE KL = new Keyword_LEASEDLINE();
	Keyword_IPLC KI = new Keyword_IPLC();
	Keyword_Validations KV = new Keyword_Validations();
	Keyword_Putty KP = new Keyword_Putty();
	Keyword_Guided KG = new Keyword_Guided();
	Keyword_FixedLine KF = new Keyword_FixedLine();
	Keyword_DB KDB = new Keyword_DB();
	Keyword_OSM KO = new Keyword_OSM();

	Dialers KDialers = new Dialers();
	SetCapabilities KSetCapabilities = new SetCapabilities();
	MobileRTBCheck KRTB = new MobileRTBCheck();
	Charging KCharging = new Charging();
	Notification KNotification = new Notification();
	Handset KHandset = new Handset();
	USSD KUSSD = new USSD();
	Siebel KSiebel = new Siebel();

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

	public String RealTimeBalance_Screen() {
		return KC.RealTimeBalance_Screen();
	}

	public String SiebleValidation() {
		return KC.SiebleValidation();
	}

	public String ServicePoint() {
		return KC.ServicePoint();
	}

	public String Modify() {
		return KC.Modify();
	}

	public String SIMSwap() {
		return KC.SIMSwap();
	}

	public String Plan_UpgradeDowngrade() {
		return KC.Plan_UpgradeDowngrade();
	}

	public String Consumer_Migration() {
		return KC.Consumer_Migration();
	}

	public String Enterprise_Migration() {
		return KC.Enterprise_Migration();
	}

	public String ModifySmartLimit() {
		return KC.ModifySmartLimit();
	}

	public String ModifySmartLimit_Acc360() {
		return KC.ModifySmartLimit_Acc360();
	}
	
	public String BillPayment() {
		return KC.BillPayment();
	}

	public String Change_MSISDN() {
		return KC.Change_MSISDN();
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

	public String TransferOfOwnership_C2C() {
		return KC.TransferOfOwnership_C2C();
	}

	public String Suspension() {
		return KC.Suspension();
	}

	public String Resume() {
		return KC.Resume();
	}

	public String UpgradePromotion_Account360() {
		return KC.UpgradePromotion_Account360();
	}

	public String Account360() {
		return KC.Account360();
	}

	public String OpenUI_AccesRrights_6Segments() {
		return KC.OpenUI_AccesRrights_6Segments();
	}

	public String OrderPayments() {
		return KC.OrderPayments();
	}

	public String Barring() {
		return KC.Barring();
	}

	public String UnBarring() {
		return KC.UnBarring();
	}

	public String DunningProcess() {
		return KC.DunningProcess();
	}

	public String DunningAction() {
		return KC.DunningAction();
	}

	public String CollectionExit() {
		return KC.CollectionExit();
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

	// ---------------------Keyword ETHERNET------------------------//

	public String ETHERNET() {
		return KM.ETHERNET();
	}

	// ---------------------Keyword ETHERNET------------------------//

	// ---------------------Keyword LEASEDLINE------------------------//

	public String LEASEDLINE() {
		return KL.LEASEDLINE();
	}

	// ---------------------Keyword LEASEDLINE------------------------//

	// ---------------------Keyword LEASEDLINE------------------------//

	public String IPLC() {
		return KI.IPLC();
	}

	// ---------------------Keyword LEASEDLINE------------------------//

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

	public String Invoicegeneration() {
		return KP.Invoicegeneration();
	}

	public String Collections() {
		return KP.Collections();
	}
	// ---------------------Keyword Putty------------------------//

	// ---------------------Keyword_OSM------------------------//

	public String PlanSelection_FL() {
		return KF.PlanSelection_FL();
	}

	public String PlanSelection_FL_ENT() {
		return KF.PlanSelection_FL_ENT();
	}

	public String FL_ONT_CPE_Replacement() {
		return KF.FL_ONT_CPE_Replacement();
	}

	public String OrderVerfication() {
		return KF.OrderVerfication();
	}

	// ---------------------Keyword_FixedLine------------------------//
	// ---------------------Keyword_OSM------------------------//

	public String OSM_Login() {
		return KO.OSM_Login();
	}

	public String OSM_SearchFL() {
		return KO.OSM_SearchFL();
	}

	// ---------------------Keyword_OSM------------------------//

	// ---------------------Mobile Usages------------------------//
	public String Dialer() {
		return KDialers.Dialer();
	}

	public String CheckUnBarringCall() {
		return KDialers.CheckUnBarringCall();
	}

	public String CheckBarringCall() {
		return KDialers.CheckBarringCall();
	}

	public String smsSender() {
		return KDialers.smsSender();
	}

	public String BalanceCheckDialer() {
		return KDialers.BalanceCheckDialer();
	}

	public String RechargeDialer() {
		return KDialers.RechargeDialer();
	}

	public String setMessengerCapabilities() {
		return KSetCapabilities.setMessengerCapabilities();
	}

	public String SetCallCapabilities() {
		return KSetCapabilities.setDialerCapabilities();
	}

	public String PrevCheckBalance() {
		return KRTB.PrevCheckBalance();
	}

	public String PostCheckBalance() {
		return KRTB.PostCheckBalance();
	}

	public String LocalCallCharging() {
		return KCharging.LocalCallCharging();
	}

	public String LocalSMSCharging() {
		return KCharging.LocalSMSCharging();
	}

	public String InternationalCallCharging() {
		return KCharging.InternationalCallCharging();
	}

	public String InternationalSMSCharging() {
		return KCharging.InternationalSMSCharging();
	}

	public String BalanceCheck() {
		return KNotification.BalanceCheck();
	}

	public String FlexBalanceCheck() {
		return KNotification.FlexBalanceCheck();
	}

	public String ProductInfoNotification() {
		return KNotification.ProductInfoNotification();
	}

	public String ConfigureSMSC() {
		return KHandset.ConfigureSMSC();
	}

	public String RestartMobile() {
		return KHandset.RestartMobile();
	}

	public String VerifyRecharge() {
		return KUSSD.VerifyRecharge();
	}

	public String USSDJourney() {
		return KUSSD.USSDJourney();
	}

	public String InvokeUSSDMenu() {
		return KUSSD.InvokeUSSDMenu();
	}

	public String USSDCleaner() {
		return KUSSD.USSDCleaner();
	}

	public String PunchRechargePIN() {
		return KUSSD.PunchRechargePIN();
	}

	public String VerifyProductActivationPrepaid() {
		return KUSSD.VerifyProductActivationPrepaid();
	}

	public String SearchAsset() {
		return KSiebel.SearchAsset();
	}

	public String CheckOrder() {
		return KSiebel.CheckOrder();
	}
	// ---------------------Mobile Usages------------------------//

	// ---------------------Keyword CRM/BRM DB------------------------//
	public String DBConnection() {
		return KDB.DBConnection();
	}

	public String BillPoID() {
		return KDB.BillPoID();
	}

	public String DBDisconnection() {
		return KDB.DBDisconnection();
	}
	// ---------------------Keyword CRM/BRM DB------------------------//
}
