package com.gunnampalli.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.gunnampalli.enums.ConfigProperties;
import com.gunnampalli.utils.PropertyUtils;

public class RetryFailed implements IRetryAnalyzer {

	int count = 0;
	int retry = 1;
	@Override
	public boolean retry(ITestResult result) {
		boolean value = false;
		if(PropertyUtils.getConfigMap(ConfigProperties.RETRYFAILED).equalsIgnoreCase("yes"))
		{
			value = count < retry;
			count++;
		}
		return value;
	}
}
