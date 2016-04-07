package com.json2bean.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;


import com.json2bean.CamelCaseBeanGenerator;
import com.json2bean.Json2Bean;
import com.json2bean.MyBeanGenerator;
import com.json2bean.MyJsonParser;
import com.json2bean.MyNameGenerator;
import com.json2bean.test.JsonFormat;


public class UI extends JFrame {

	JTextField jsonInput;
	JTextField packageInput;
	 
	JTextArea jsonTextArea;

	JButton goButton;
	JCheckBox checkBox;
	
	JButton cleanJsonButton;
	JButton cleanPackageNameButton;
	public UI() {
		setLayout(new BorderLayout(0, 0));

		JPanel jsonPanel = new JPanel(new BorderLayout(5, 5));
		jsonPanel.add(new JLabel("Json:"), BorderLayout.WEST);
		jsonInput = new JTextField();
		jsonPanel.add(jsonInput, BorderLayout.CENTER);
		cleanJsonButton=new JButton("Clean");
		jsonPanel.add(cleanJsonButton, BorderLayout.EAST);
		jsonPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JPanel packagePanel = new JPanel(new BorderLayout(5, 5));
		packagePanel.add(new JLabel("PackName:"), BorderLayout.WEST);
		packageInput = new JTextField();
		packagePanel.add(packageInput, BorderLayout.CENTER);
		cleanPackageNameButton=new JButton("Clean");
		packagePanel.add(cleanPackageNameButton, BorderLayout.EAST);
		packagePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		 
		
		
		JPanel checkBoxAndBUttonPanel = new JPanel(new BorderLayout(5, 5));
		checkBox=new JCheckBox("UnderLineToCamelCase");
		checkBoxAndBUttonPanel.add(checkBox, BorderLayout.WEST);
		goButton=new JButton("GO");
		checkBoxAndBUttonPanel.add(goButton, BorderLayout.EAST);
		checkBoxAndBUttonPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		
		
		JPanel panelContainer=new JPanel(new BorderLayout());
		panelContainer.add(jsonPanel,BorderLayout.NORTH);
		panelContainer.add(packagePanel,BorderLayout.CENTER);
		 
		
		JPanel panelContainer2=new JPanel(new BorderLayout());
		panelContainer2.add(panelContainer,BorderLayout.NORTH);
		panelContainer2.add(checkBoxAndBUttonPanel,BorderLayout.SOUTH);
		
		jsonTextArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		scrollPane.setViewportView(jsonTextArea);
		
		add(panelContainer2, BorderLayout.NORTH);
		 
		add(scrollPane, BorderLayout.CENTER);
		
		addListener();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(360, 20, 650, 600);
		
		setTitle("XJson2JavaBean  http://blog.csdn.net/qingchunweiliang");
		 
	}

	private void addListener() {
		
		 
		
		goButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				
				String packageName = packageInput.getText().trim();
				 
				if (packageName.length() == 0) {
					packageName = "blog.csdn.net.qingchunweiliang";
				}
				
				String json = jsonInput.getText().trim();
				
				jsonTextArea.setText(JsonFormat.format(json));

				 
				try {
					if (checkBox.isSelected()) {
						new Json2Bean(json, "RootBean", new MyNameGenerator(), new MyJsonParser(),
								new CamelCaseBeanGenerator( packageName)).execute();

					} else {
						new Json2Bean(json, "RootBean", new MyNameGenerator(), new MyJsonParser(),
								new MyBeanGenerator( packageName)).execute();

					}
					 
					try {
						Toolkit.getDefaultToolkit().beep();
						java.awt.Desktop.getDesktop().open(new File("src/" + packageName.replace(".", "/")));
					} catch (Exception e1) {
						e1.printStackTrace();
					} 
				} catch (Exception e1) {
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Fail", JOptionPane.INFORMATION_MESSAGE);

					e1.printStackTrace();
				}

			}
		});
		cleanJsonButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jsonInput.setText("");
			}
		});
		cleanPackageNameButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				packageInput.setText("");
			}
		});
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame.setDefaultLookAndFeelDecorated(true);
				JDialog.setDefaultLookAndFeelDecorated(true);
				try {
					UIManager.setLookAndFeel(new org.jvnet.substance.skin.SubstanceCremeLookAndFeel());
				} catch (Exception e) {
					e.printStackTrace();
				}
				new UI().show();
			}
		});
	}
}
