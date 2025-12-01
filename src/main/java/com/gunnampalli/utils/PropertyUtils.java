package com.gunnampalli.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

import com.gunnampalli.constants.FrameworkConstants;
import com.gunnampalli.enums.ConfigProperties;
import com.gunnampalli.exceptions.FrameworkExceptions;

public class PropertyUtils {

	private static Properties properties = new Properties();
	private static final Map<String,String> configMap = new HashMap<>();

	static {
		FileInputStream fis;
		try {
			fis = new FileInputStream(FrameworkConstants.getConfigfilepath());
			properties.load(fis);
			
			for(Map.Entry<Object, Object> entry: properties.entrySet())
			{
				configMap.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getConfigMap(ConfigProperties key)
	{
		if(Objects.isNull(key) || Objects.isNull(configMap.get(key.name().toLowerCase())))
		{
			throw new FrameworkExceptions("Key "+key+" Value not found, Retry once again");
		}
		return configMap.get(key.name().toLowerCase());
	}

}
