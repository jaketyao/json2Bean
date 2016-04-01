package com.json2bean.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
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

public class Main extends JFrame {

	private JTextArea textArea;
	JTextField jsonText;
	JCheckBoxMenuItem boxMenuItem;
	JTextField packageText;
	JButton button;

	public Main() {
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		textArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(textArea);

		JPanel topBar = createTopBar();
		contentPane.add(topBar, BorderLayout.NORTH);

		contentPane.add(scrollPane, BorderLayout.CENTER);

		this.setContentPane(contentPane);
		this.setTitle("Json2Bean http://blog.csdn.net/qingchunweiliang");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(360, 100, 650, 700);
		this.setVisible(true);
	}

	private JPanel createTopBar() {
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new BorderLayout(0, 0));
		jsonText = new JTextField();
		jsonText.setBorder(new EmptyBorder(0, 5, 5, 0));
		JPanel checkBoxInputPanel = new JPanel();
		checkBoxInputPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		JLabel jLabel = new JLabel("包名");
		jLabel.setBorder(new EmptyBorder(0, 0, 5, 10));
		checkBoxInputPanel.add(jLabel);
		packageText = new JTextField(20);
		packageText.setBorder(new EmptyBorder(5, 5, 5, 5));
		checkBoxInputPanel.add(packageText);

		boxMenuItem = new JCheckBoxMenuItem("下划线转驼峰式");
		checkBoxInputPanel.add(boxMenuItem);

		panel.add(jsonText, BorderLayout.CENTER);

		button = new JButton("GO");

		panel.add(button, BorderLayout.EAST);
		panel.add(checkBoxInputPanel, BorderLayout.SOUTH);

		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String json = jsonText.getText().trim();
				String packageName = packageText.getText();
				// boolean isselect=boxMenuItem.isSelected();

				if (packageName.trim().length() == 0) {
					packageName = "blog.csdn.net.qingchunweiliang";
				}

				textArea.setText(JsonFormat.format(json));

				try {
					if (boxMenuItem.isSelected()) {
						new Json2Bean(json, "RootBean", new MyNameGenerator(), new MyJsonParser(),
								new CamelCaseBeanGenerator(packageName)).execute();

					} else {
						new Json2Bean(json, "RootBean", new MyNameGenerator(), new MyJsonParser(),
								new MyBeanGenerator(packageName)).execute();

					}

					// JOptionPane.showMessageDialog(Main.this, "Java
					// Bean生成完成！", "",JOptionPane.INFORMATION_MESSAGE);
					// Runtime.getRuntime().exec("explorer.exe" + "
					// "+packageName.replace(".", "/"));
					try {
						Toolkit.getDefaultToolkit().beep();
						java.awt.Desktop.getDesktop().open(new File("src/" + packageName.replace(".", "/")));
					} catch (Exception e1) {

					}
				} catch (Exception e1) {
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(Main.this, "Java Bean生成失败", "", JOptionPane.INFORMATION_MESSAGE);

					e1.printStackTrace();
				}

			}
		});

		return panel;

	}

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame.setDefaultLookAndFeelDecorated(true);
				JDialog.setDefaultLookAndFeelDecorated(true);
				try {
					/*
					 * 想要修改皮肤的话，只需要更改，下面这个函数的参数，具体改成什么样，可以打开，Referenced
					 * Libraries -> 点击substance.jar ->
					 * 找到org.jvnet.substance.skin这个包 ->
					 * 将下面的SubstanceDustCoffeeLookAndFeel 替换成
					 * 刚刚打开的包下的任意一个“Substance....LookAndFeel”即可
					 */
					UIManager.setLookAndFeel(new org.jvnet.substance.skin.SubstanceCremeLookAndFeel());
					// 例如按照上面的步骤，可以看见一个叫，"SubstanceOfficeBlue2007LookAndFeel.class"，想要替换成这个皮肤，就可以向下面这样写
					// UIManager
					// .setLookAndFeel(new
					// org.jvnet.substance.skin.SubstanceOfficeBlue2007LookAndFeel());
					// 运行一下，皮肤就可以换了
					// 想要详细了解的同学，可以去百度这个第三方包：substance.jar
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Main example = new Main();

			}
		});
	}
}
