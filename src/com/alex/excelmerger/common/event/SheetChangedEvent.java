package com.alex.excelmerger.common.event;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

public class SheetChangedEvent
{
	private List<SheetChangedListener>listeners;
	
	private enum MethodName{
		SheetChagned,
		ColumnsMissing,
		LastSheet,
		FirstSheet;
	}
	
	
	
	private class Runner implements Runnable
	{
	
		private String sheetName;
		private Integer ordinal;
		private Integer total;
		private List<String>errorColumns;
		
		
		
		private Runner(Integer total)
		{
			super();
			this.methodName = MethodName.FirstSheet;
			this.total = total;
		}
		
		private Runner(String sheetName, List<String> errorColumns)
		{
			super();
			this.sheetName = sheetName;
			this.errorColumns = errorColumns;
			this.methodName = MethodName.ColumnsMissing;
		}
		
		private Runner(String sheetName, Integer ordinal)
		{
			super();
			this.sheetName = sheetName;
			this.ordinal = ordinal;
			this.methodName = MethodName.SheetChagned;
		}

		private Runner()
		{
			super();
			this.methodName = MethodName.LastSheet;
		}

		private MethodName methodName;
		@Override
		public void run()
		{
			for (SheetChangedListener listener : listeners)
			{
				switch (methodName)
				{
				case ColumnsMissing:listener.sheetColumnsMissing(sheetName, errorColumns);break;
				case FirstSheet:listener.sheetBeforeFirst(total);break;
				case LastSheet:listener.sheetLastMergered();break;
				case SheetChagned:listener.sheetChagned(sheetName, ordinal);break;
				default:
					break;
				}
			}
			
		}
	}
	
	private SheetChangedEvent()
	{
		listeners = new ArrayList<>();
	}
	
	private static SheetChangedEvent event;
	
	public static SheetChangedEvent getEvent()
	{
		if (event == null)
		{
			event = new SheetChangedEvent();
		}
		return event;
	}
	
	public void addListener(SheetChangedListener listener)
	{
		listeners.add(listener);
	}
	
	public void fireBeforeFirst(Integer total)
	{
		SwingUtilities.invokeLater(new Runner(total));
	}
	public void fireSheetLastMerged()
	{
		SwingUtilities.invokeLater(new Runner());
	}
	
	public void fireSheetChagned(String name,Integer ordinal)
	{
		SwingUtilities.invokeLater(new Runner(name,ordinal));
	}
	
	public void	fireSheetColumnMissing(String name,List<String>columns)
	{
		SwingUtilities.invokeLater(new Runner(name,columns));
	}
}
