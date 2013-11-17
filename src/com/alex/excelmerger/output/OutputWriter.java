package com.alex.excelmerger.output;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class OutputWriter
{
	private PrintStream buffer;
	private ByteArrayOutputStream data;
	private String filename;
	
	public OutputWriter() throws UnsupportedEncodingException
	{
		data = new ByteArrayOutputStream();
		buffer = new PrintStream(data, true, "UTF-8");
	}
	
	public void append(Object data)
	{
		buffer.println(data);
	}
	
	public void flush() throws IOException
	{
		OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(filename), "UTF-8");
		writer.write(new String(data.toByteArray(), "UTF-8"));
		writer.close();
		buffer.close();
	}
	
	public void setFilename(String filename)
	{
		this.filename = filename;
	}
}
