package com.alex.excelmerger.common.event;

public interface PropertiesValidationListener
{
	void propertyInvalid(String properyName,String message);
	void properiesValidatedSuccessfully();
}
