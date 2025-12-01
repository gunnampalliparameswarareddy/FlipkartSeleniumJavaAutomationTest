package com.gunnampalli.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.gunnampalli.enums.SheetNames;


public final class DataProviderUtils {
	
	private DataProviderUtils() {
		
	}
	
	
	private static List<Map<String,String>> list = new ArrayList<>();
	
	@DataProvider(parallel=true)
	public static Object[] getDataFromDatasheet(Method m)
	{
		String testName = m.getName();
		if(list.isEmpty())
		{
			list = ReadExcelUtils.getExcelData(SheetNames.DATA.toString());
			
		}
		
		List<Map<String,String>> smalllist = new ArrayList<>();
		System.out.println(list);
		for (int i=0;i<list.size();i++) {
			
			if(list.get(i).get("testname").equalsIgnoreCase(testName) && list.get(i).get("execute").equalsIgnoreCase("yes"))
			{
				smalllist.add(list.get(i));
			}
		}
		
		return smalllist.toArray();
	}
}
