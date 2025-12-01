package com.gunnampalli.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.gunnampalli.constants.FrameworkConstants;
import com.gunnampalli.exceptions.FileNotFoundExceptions;
import com.gunnampalli.exceptions.IOFileExceptions;

public class ReadExcelUtils {
	private ReadExcelUtils()
	{
		
	}
	
	public static List<Map<String,String>> getExcelData(String sheetName)
	{
		List<Map<String,String>> list = new ArrayList<>();
		System.out.println(FrameworkConstants.getExcelfilepath());
		try(FileInputStream fis = new FileInputStream(FrameworkConstants.getExcelfilepath());
				XSSFWorkbook workbook = new XSSFWorkbook(fis))
		{
			XSSFSheet sheet = workbook.getSheet(sheetName);
			
			int rowCount = sheet.getPhysicalNumberOfRows();
			int columnCount = sheet.getRow(0).getLastCellNum();
			
			Map<String,String> map = null;
			for(int i=1;i<rowCount;i++)
			{
				map = new HashMap<>();
				for(int j=0;j<columnCount;j++)
				{
					String key = sheet.getRow(0).getCell(j).getStringCellValue();
					String value = sheet.getRow(i).getCell(j).getStringCellValue();
					map.put(key, value);
				}
				list.add(map);
			}
			workbook.close();
		}
		catch(FileNotFoundException e)
		{
			throw new FileNotFoundExceptions("Excel File not available in the mentioned path");
		} catch (IOException e1) {
			throw new IOFileExceptions(e1.toString());
		}
		System.out.println("ReadExcelData");
		return list;
	}
}
