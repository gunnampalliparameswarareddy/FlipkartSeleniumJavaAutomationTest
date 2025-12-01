package com.gunnampalli.listeners;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

import com.gunnampalli.annotations.FrameworkAnnotation;
import com.gunnampalli.enums.CategoryType;
import com.gunnampalli.enums.ConfigProperties;
import com.gunnampalli.enums.SheetNames;
import com.gunnampalli.utils.PropertyUtils;
import com.gunnampalli.utils.ReadExcelUtils;

public class MethodInterceptor implements IMethodInterceptor{

	@Override
	public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {

		List<Map<String,String>> runmanagerList = null;

		runmanagerList = ReadExcelUtils.getExcelData(SheetNames.RUNMANAGER.toString());

		//System.out.println(runmanagerList);
		String categoryType= PropertyUtils.getConfigMap(ConfigProperties.CATEGORYTYPE);
		List<IMethodInstance> result = new ArrayList<>();


		for(int i=0;i<methods.size();i++)
		{

			for(int j=0;j<runmanagerList.size();j++)
			{
				if(methods.get(i).getMethod().getMethodName().equalsIgnoreCase(runmanagerList.get(j).get("testname")) &&
						runmanagerList.get(j).get("execute").equalsIgnoreCase("yes") )
				{
					CategoryType[] categories= methods.get(i).getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameworkAnnotation.class).categories();
					if(Arrays.stream(categories).anyMatch(cat -> cat.name().equalsIgnoreCase(categoryType)))
					{
						methods.get(i).getMethod().setDescription(runmanagerList.get(j).get("testdescription"));
						methods.get(i).getMethod().setPriority((int)Double.parseDouble(runmanagerList.get(j).get("priority")));
						methods.get(i).getMethod().setInvocationCount((int)Double.parseDouble(runmanagerList.get(j).get("count")));
						result.add(methods.get(i));
						//System.out.println(methods.get(i));
					}

				}
			}
		}
		//System.out.println(result);
		return result.isEmpty()?methods:result;
	}


}
