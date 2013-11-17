package com.alex.excelmerger.common.event;

import java.util.List;

public interface SheetChangedListener
{
	void sheetBeforeFirst(Integer total);
	void sheetChagned(String sheetName,Integer ordinal);
	void sheetColumnsMissing(String name,List<String>columnNames);
	void sheetLastMergered();
}
