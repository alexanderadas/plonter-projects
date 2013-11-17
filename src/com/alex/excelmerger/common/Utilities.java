package com.alex.excelmerger.common;

public class Utilities
{
	public static boolean validateStrings(String...strings)
	{
		for (String string : strings)
		{
			if (string == null || string.isEmpty())
			{
				return false;
			}
		}
		return true;
	}
}
