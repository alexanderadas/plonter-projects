package com.alex.excelmerger.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.UIManager;

import com.alex.excelmerger.common.event.PropertiesValidationEvent;
import com.alex.excelmerger.common.event.PropertiesValidationListener;
import com.alex.excelmerger.common.event.SheetChangedEvent;
import com.alex.excelmerger.common.event.SheetChangedListener;
import com.alex.excelmerger.main.Merger;

public class MainFrame extends JFrame implements SheetChangedListener,PropertiesValidationListener
{
	private static final long serialVersionUID = -9101155267500559418L;
	private JLabel name;
	private JLabel current;
	private static final String CURRENT_FORMAT = "%d/%d";
	private Integer total;
	private JTextArea area;
	public MainFrame()
	{
		setIconImage(new ImageIcon(getClass().getClassLoader().getResource("logo.jpg")).getImage());
		setTitle("CDLOG Merger");
		SpringLayout layout = new SpringLayout();
		JPanel panel = new  JPanel(layout);
		panel.add(name = new JLabel() );
		panel.add(current = new JLabel());
		
		panel.setPreferredSize(new Dimension(230, 30));
		
		//name
		layout.putConstraint(SpringLayout.WEST, name, 10, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, name, 5, SpringLayout.NORTH, panel);
		//current
		layout.putConstraint(SpringLayout.EAST, current, -10, SpringLayout.EAST, panel);
		layout.putConstraint(SpringLayout.NORTH, current, 0, SpringLayout.NORTH, name);
		
		area = new JTextArea();
		area.setFont(area.getFont().deriveFont(14.0f));
		
		JPanel contentPanel = new JPanel(new BorderLayout());

		JScrollPane pane = new JScrollPane(area);
		contentPanel.add(pane);
		contentPanel.add(panel,BorderLayout.NORTH);
		
		setContentPane(contentPanel);

		setSize(230, 120);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args)
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			MainFrame mainFrame = new MainFrame();
			SheetChangedEvent.getEvent().addListener(mainFrame);
			PropertiesValidationEvent.getEvent().addListener(mainFrame);
			mainFrame.setLocationRelativeTo(null);
			mainFrame.setVisible(true);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void sheetBeforeFirst(Integer total)
	{
		this.total = total;
		
	}

	@Override
	public void sheetChagned(String sheetName, Integer ordinal)
	{
		name.setText(sheetName);
		current.setText(String.format(CURRENT_FORMAT, ordinal + 1,total));
		
	}

	@Override
	public void sheetColumnsMissing(String name, List<String> columnNames)
	{
		area.append("Sheet [");
		area.append(name);
		area.append("] following columns not found in excel file:\n");
		for (String string : columnNames)
		{
			area.append(string);
			area.append("\n");
		}
	}

	@Override
	public void setVisible(boolean b)
	{
		super.setVisible(b);
		if (b)
		{
			new Thread(new Runnable() {
				public void run()
				{
					try
					{
						Merger.main(null);
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			}).start();
		}
	}
	
	@Override
	public void sheetLastMergered()
	{
		System.exit(0);
		
	}

	@Override
	public void propertyInvalid(String properyName, String message)
	{
		area.append(properyName + " - " + message + "\n");
	}
	@Override
	public void properiesValidatedSuccessfully()
	{
		area.append("Properties valid\n");		
	}
}
