package com.gunnampalli.constants;

public class FrameworkConstants {
	
	private static final String USERDIRECTORYPATH = System.getProperty("user.dir")+"/src/test/resources";
	private static final String CONFIGFILEPATH = USERDIRECTORYPATH+"/config/config.properties";
	private static final String EXCELFILEPATH = USERDIRECTORYPATH+"/excel/data.xlsx";
	private static final String CHROMEBROWSERTYPE = "chrome";
	private static final String FIREFOXBROWSERTYPE = "firefox";
	private static final String EDGEBROWSERTYPE = "edge";
	private static final String REPORTSPATH = USERDIRECTORYPATH+"/reports/Report.html";
	private static final int EXPLICITWAITTIME = 20;
	
	public static String getConfigfilepath() {
		return CONFIGFILEPATH;
	}

	public static String getChromebrowsertype() {
		return CHROMEBROWSERTYPE;
	}

	public static String getFirefoxbrowsertype() {
		return FIREFOXBROWSERTYPE;
	}

	public static String getEdgebrowsertype() {
		return EDGEBROWSERTYPE;
	}

	public static String getExcelfilepath() {
		return EXCELFILEPATH;
	}

	public static String getReportspath() {
		return REPORTSPATH;
	}

	public static int getExplicitwaittime() {
		return EXPLICITWAITTIME;
	}
	
	

}
