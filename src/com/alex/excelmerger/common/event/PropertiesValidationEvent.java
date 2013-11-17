package com.alex.excelmerger.common.event;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

public class PropertiesValidationEvent
{
	private List<PropertiesValidationListener>listeners;
	
	private class Runner implements Runnable
	{
	
		private String propertyName;
		private String message;

		private Runner(String propertyName, String message)
		{
			super();
			this.propertyName = propertyName;
			this.message = message;
		}

		private Runner()
		{
			
		}
		
		@Override
		public void run()
		{
			for (PropertiesValidationListener listener : listeners)
			{
				if (propertyName != null && message != null)
				{
					listener.propertyInvalid(propertyName, message);
				}
				else{
					listener.properiesValidatedSuccessfully();
				}
			}
			
		}
	}
	
	private PropertiesValidationEvent()
	{
		listeners = new ArrayList<>();
	}
	
	private static PropertiesValidationEvent event;
	
	public static PropertiesValidationEvent getEvent()
	{
		if (event == null)
		{
			event = new PropertiesValidationEvent();
		}
		return event;
	}
	
	public void addListener(PropertiesValidationListener listener)
	{
		listeners.add(listener);
	}
	
	public void firePropertyInvalid(String name,String message)
	{
		SwingUtilities.invokeLater(new Runner(name,message));
	}
	public void firePropertiesValidatedSuccessfully()
	{
		SwingUtilities.invokeLater(new Runner());
	}
}
