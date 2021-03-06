package Libraries;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.Session;

@SuppressWarnings("rawtypes")
public class Driver {

	public static ThreadLocal<String> WorkingDir = new ThreadLocal<String>();
	public static ThreadLocal<String> Base_Path = new ThreadLocal<String>();
	public static ThreadLocal<String> Storage_FLD = new ThreadLocal<String>();
	public static ThreadLocal<String> OR_File = new ThreadLocal<String>();
	public static ThreadLocal<String> StoreDB_File = new ThreadLocal<String>();
	public static ThreadLocal<String> Directory_FLD = new ThreadLocal<String>();
	public static ThreadLocal<String> Database_File = new ThreadLocal<String>();
	public static ThreadLocal<String> TestDataDB_File = new ThreadLocal<String>();
	public static ThreadLocal<String> Result_FLD = new ThreadLocal<String>();
	public static ThreadLocal<String> Templete_FLD = new ThreadLocal<String>();
	public static ThreadLocal<String> XMLfilepth = new ThreadLocal<String>();
	public static ThreadLocal<String> UCscreenfilepth = new ThreadLocal<String>();
	public static ThreadLocal<String> masterrephtml = new ThreadLocal<String>();
	public static ThreadLocal<String> URL = new ThreadLocal<String>();

	public static int passUC = 0;
	public static int partialypassUC = 0;
	public static int failUC = 0;
	public static int totalUC = 0;
	public static String TestOutput;

	public static ThreadLocal<Boolean> Continue = new ThreadLocal<Boolean>();
	public static ThreadLocal<String> ExecutionStarttimestr = new ThreadLocal<String>();
	public static ThreadLocal<String> ExecutionEndtimestr = new ThreadLocal<String>();
	public static ThreadLocal<String> Environment = new ThreadLocal<String>();
	public static ThreadLocal<String> UseCaseName = new ThreadLocal<String>();
	public static ThreadLocal<String> UseCaseIDP = new ThreadLocal<String>();
	public static ThreadLocal<String> UseCaseDP = new ThreadLocal<String>();
	public static ThreadLocal<String> Dependancy = new ThreadLocal<String>();
	public static ThreadLocal<String> TestCaseIDP = new ThreadLocal<String>();
	public static ThreadLocal<String> TestCaseDP = new ThreadLocal<String>();
	public static ThreadLocal<String> TestCaseN = new ThreadLocal<String>();
	public static ThreadLocal<String> TestCaseData = new ThreadLocal<String>();
	public static ThreadLocal<String> ValidationData = new ThreadLocal<String>();
	public static ThreadLocal<String> TestCaseDes = new ThreadLocal<String>();
	public static ThreadLocal<String> currUCstatus = new ThreadLocal<String>();
	public static ThreadLocal<String> currKWstatus = new ThreadLocal<String>();
	public static ThreadLocal<String> currKW = new ThreadLocal<String>();
	public static ThreadLocal<String> currKW_DB = new ThreadLocal<String>();
	public static ThreadLocal<String> currKW_Des = new ThreadLocal<String>();
	public static ThreadLocal<String> browser = new ThreadLocal<String>();
	public static ThreadLocal<String> keywordstartdate = new ThreadLocal<String>();
	public static ThreadLocal<String> Planname = new ThreadLocal<String>();
	public static ThreadLocal<String> OrderDate = new ThreadLocal<String>();
	public static ThreadLocal<String> billDate = new ThreadLocal<String>();
	public static ThreadLocal<String> Def_Smart_limit = new ThreadLocal<String>();
	public static ThreadLocal<ChannelShell> nchannel = new ThreadLocal<ChannelShell>();
	public static ThreadLocal<Channel> channel = new ThreadLocal<Channel>();
	public static ThreadLocal<Session> nsession = new ThreadLocal<Session>();
	public static ThreadLocal<FileOutputStream> tergetFile = new ThreadLocal<FileOutputStream>();
	public static ThreadLocal<String> TCscreenfile = new ThreadLocal<String>();
	public static ThreadLocal<String> SalesOrder_No = new ThreadLocal<String>();
	public static ThreadLocal<String> contact = new ThreadLocal<String>();
	public static ThreadLocal<String> New_Account = new ThreadLocal<String>();
	public static ThreadLocal<String> InvoiceZip = new ThreadLocal<String>();
	public static ThreadLocal<String> Dunning = new ThreadLocal<String>();

	public static ThreadLocal<Dictionary> TestData = new ThreadLocal<Dictionary>();
	public static ThreadLocal<Dictionary> ValidateDT = new ThreadLocal<Dictionary>();
	public static ThreadLocal<Dictionary> database = new ThreadLocal<Dictionary>();
	protected static ThreadLocal<WebDriver> cDriver = new ThreadLocal<WebDriver>();
	public static HashMap<String, String> RTBOutputData = new HashMap<String, String>();
	public static HashMap<String, String> LineItemData = new HashMap<String, String>();
	// static String[] LineItemData = new String[15]; ;

	public static Map<String, String> DunningSchedule = new HashMap<String, String>();
	public static Map<String, String> BillSchedule = new HashMap<String, String>();
	// public static ThreadLocal<String> Billprofile_No = new ThreadLocal<String>();
	public static Map<String, String> CreditAlert = new HashMap<String, String>();

	public static void main(String[] args) throws IOException {
		System.out.println("Intialization");
		killexeTask();

		WorkingDir.set(System.getProperty("user.dir").replace("\\", "/"));
		Base_Path.set(WorkingDir.get() + "/Framework");
		Storage_FLD.set(Base_Path.get() + "/Storage");
		OR_File.set(Storage_FLD.get() + "/ObjectRepository.xlsx");
		StoreDB_File.set(Storage_FLD.get() + "/StoreDB.xlsx");
		Database_File.set(Storage_FLD.get() + "/CommonDirectory.xlsx");
		Directory_FLD.set(Base_Path.get() + "/Database");
		TestDataDB_File.set(Directory_FLD.get() + "/TestDataDB.xlsx");
		Dunning.set(Storage_FLD.get() + "/DunningCalendar.xlsx");
		Result_FLD.set(WorkingDir.get() + "/Results");
		Templete_FLD.set(Base_Path.get() + "/Templates");

		File resfold = new File(Result_FLD.get());
		if ((!resfold.exists()))
			resfold.mkdir();

		DateFormat For = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String SRT_Time = For.format(cal.getTime()).toString();
		ExecutionStarttimestr.set(SRT_Time);
		System.out.println("Execution initiated at --- " + SRT_Time);

		ArrayList<String[]> addUsecase = Utlities.floadUseCases();
		String[] IDP = addUsecase.get(0);
		String[] totUseCases = addUsecase.get(1);
		String[] totTestCases = addUsecase.get(2);
		String[] totTestcase_Des = addUsecase.get(3);
		String[] totUseCases_data = addUsecase.get(4);
		String[] totvalidation_data = addUsecase.get(5);
		totalUC = totUseCases.length;

		for (int currUseCase = 0; currUseCase < totalUC; currUseCase++) {
			String DP = IDP[currUseCase];
			Dependancy.set(DP);
			if (DP.equalsIgnoreCase("IDP")) {
				UseCaseIDP.set(totUseCases[currUseCase]);
				TestCaseIDP.set(totTestCases[currUseCase]);
			} else if (currUseCase != 0) {
				UseCaseDP.set(totUseCases[currUseCase - 1]);
				TestCaseDP.set(totTestCases[currUseCase - 1]);
			}
			UseCaseName.set(totUseCases[currUseCase]);
			TestCaseN.set(totTestCases[currUseCase]);
			TestCaseDes.set(totTestcase_Des[currUseCase]);
			TestCaseData.set(totUseCases_data[currUseCase]);
			ValidationData.set(totvalidation_data[currUseCase]);

			TestOutput = "";
			Result.fCreateReportFiles(currUseCase + 1, UseCaseName.get());
			ArrayList<String[]> addresses = Utlities.floadKeywords(UseCaseName.get());
			String totKeywords[] = addresses.get(0);
			String DataBinding[] = addresses.get(1);
			String Description[] = addresses.get(2);

			Result.fUpdateLog("No of Keywords to be executed in " + UseCaseName.get() + ":" + totKeywords.length);
			if (DP.equalsIgnoreCase("DP") && currUCstatus.get().equalsIgnoreCase("Fail")) {
				currUCstatus.set("Fail");
				Continue.set(false);
				TestOutput = "******* Interdependant Failure Blocked*******" + "<br/>";
				Result.fUpdateLog("******* Interdependant Failure Blocked******");
			} else if (DP.equalsIgnoreCase("DP") && currUCstatus.get().equalsIgnoreCase("PartiallyPass")) {
				Continue.set(true);
				currUCstatus.set("PartiallyPass");
				database.set((Dictionary<?, ?>) Utlities.fdatabase(UseCaseName.get()));
			} else {
				Continue.set(true);
				currUCstatus.set("Pass");
				database.set((Dictionary<?, ?>) Utlities.fdatabase(UseCaseName.get()));
			}

			Result.createTCScreenshotFold();
			ValidateDT.set((Dictionary<?, ?>) Utlities.freaddata(ValidationData.get()));
			for (int currKeyword = 0; currKeyword < totKeywords.length; currKeyword++) {
				String Keyword_Result = null;
				if (Continue.get() == true) {
					DateFormat currkeywordstartdate = new SimpleDateFormat("dd-MMM-yyyy");
					keywordstartdate.set(currkeywordstartdate.format(cal.getTime()).toString());

					currKW.set(totKeywords[currKeyword]);
					currKW_DB.set(DataBinding[currKeyword]);
					currKW_Des.set(Description[currKeyword]);
					Result.fUpdateLog("Current Keyword ----> " + currKW.get());
					currKWstatus.set("Pass");
					if (currKW_DB.get().toString().equalsIgnoreCase("Data")) {
						TestData.set((Dictionary<?, ?>) Utlities.freaddata(TestCaseData.get()));
					} else {
						TestData.set((Dictionary<?, ?>) Utlities.freaddata_diff(currKW_DB.get()));
						Environment.set(getdata("Environment"));
					}

					try {
						Class<?> cls = Class.forName("Libraries.KeyWord");
						Object obj = cls.newInstance();
						Method method = cls.getMethod(currKW.get());
						Keyword_Result = (String) method.invoke(obj);
					} catch (Exception e) {
						e.printStackTrace();
					}

					if (Keyword_Result != null) {
						String[] ResultandDes = Keyword_Result.split("@@");

						if (ResultandDes[0].equalsIgnoreCase("FAIL")) {
							currKWstatus.set("Fail");
							Continue.set(false);
						} else if (ResultandDes[0].equalsIgnoreCase("PartiallyPass")) {
							currKWstatus.set("PartiallyPass");
							Continue.set(true);
						} else {
							currKWstatus.set("Pass");
							Continue.set(true);
						}
						if (TestOutput == null) {
							if (!(ResultandDes[1].equals("<br/>"))) {
								TestOutput = ResultandDes[1];
							}
						} else {
							if (!(ResultandDes[1].equals("<br/>"))) {
								TestOutput = TestOutput + ResultandDes[1];
							}
						}

					}
				}

			}
			if (currKWstatus.get().equalsIgnoreCase("Fail")) {
				failUC = failUC + 1;
				currUCstatus.set("Fail");
			} else {
				passUC = passUC + 1;
			}
			Calendar cal1 = Calendar.getInstance();
			String End_Time = For.format(cal1.getTime()).toString();
			ExecutionEndtimestr.set(End_Time);
			System.out.println("Execution Completed at --- " + End_Time);
			Result.fcreateMasterHTML();
		}
		Browser.OpenBrowser(browser.get(), masterrephtml.get());
		//Result.DisplayHTMLReport();
	}

	public static String getdata(String colname) {
		String c = "";
		try {
			c = TestData.get().get(colname).toString();
			return c;
		} catch (Exception e) {
			return c;
		}

	}

	public static String Validatedata(String colname) {
		String c = "";
		try {
			c = ValidateDT.get().get(colname).toString();
			return c;
		} catch (Exception e) {
			return c;
		}

	}

	public static String pulldata(String colname) {
		String c = "";
		try {
			c = database.get().get(colname).toString();
			return c;
		} catch (Exception e) {
			return c;
		}

	}

	public static void killexeTask() {
		try {
			Runtime.getRuntime().exec("taskkill /im chrome.exe /f");
			Runtime.getRuntime().exec("taskkill /im chromedriver.exe /f");
			// Runtime.getRuntime().exec("taskkill /im conhost.exe /f");
			//Runtime.getRuntime().exec("taskkill /im Firefox.exe /f");
			Runtime.getRuntime().exec("taskkill /im geckodriver.exe /f");
			Runtime.getRuntime().exec("taskkill /im IEDriverServer.exe /f");
			// Runtime.getRuntime().exec("taskkill /im iexplore.exe /f");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
