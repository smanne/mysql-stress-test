/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * This file is part of MySQL Stress Test
 *  
 * Author: Sandeep Manne
 * ver. 1.00
 * Copyright: Sandeep Manne <sandeep.manne@gmail.com>
 *
 * MySQL Stress Test is free software: you can redistribute it
 * and/or modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation, either version 3 of
 * the License, or (at your option) any later version.
 * 
 * MySQL Stress Test is distributed WITHOUT ANY WARRANTY.
 * See the license for more details.
 *  
 * You should have received a copy of the GNU General Public License
 * along with MySQL Stress Test. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.smanne.mysqltest.client;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import com.smanne.mysqltest.TestConfig;

//VS4E -- DO NOT REMOVE THIS LINE!
/**
 * Test Client is used to connect to test server and invoke the TestRunner in
 * server
 * 
 * @author Sandeep Manne <sandeep.manne@gmail.com>
 */
public class TestClientView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel queryLabel;
	private JList queryList;
	private JScrollPane queryScrollPane;
	private JButton removeQueryButton;
	private JButton addQueryButton;
	private JLabel threadsLabel;
	private JTextField threadsTextField;
	private JButton runButton;
	private JSeparator jSeparator0;
	private JTextField connectionURLTextField;
	private JLabel connectionURLLabel;
	private JLabel usernameLabel;
	private JTextField usernameTextField;
	private JTextField passwordTextField;
	private JLabel passwordLabel;
	private JLabel remoteServersLabel;
	private JList remoteServerList;
	private JScrollPane serverScrollPane;
	private JButton removeServerButton;
	private JButton addServerButton;
	private JSeparator jSeparator1;
	private JButton jButton0;

	private CustomListModel queryCollection = new CustomListModel();
	private CustomListModel serverCollection = new CustomListModel();

	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";

	public TestClientView() {
		initComponents();
	}
	
	/**
	 * UI Intialization and Adding children
	 */
	
	
	private void initComponents() {
		setResizable(false);
		setLayout(new GroupLayout());
		add(getQueryLabel(), new Constraints(new Leading(10, 10, 10),
				new Leading(11, 10, 10)));
		add(getAddQueryButton(), new Constraints(new Leading(199, 10, 10),
				new Leading(88, 6, 6)));
		add(getThreadsLabel(), new Constraints(new Leading(12, 10, 10),
				new Leading(137, 6, 6)));
		add(getThreadsTextField(), new Constraints(
				new Leading(105, 30, 10, 10), new Leading(132, 6, 6)));
		add(getJSeparator0(), new Constraints(new Leading(12, 298, 6, 6),
				new Leading(121, 10, 6, 6)));
		add(getConnectionURLLabel(), new Constraints(
				new Leading(12, 102, 6, 6), new Leading(167, 10, 10)));
		add(getUsernameLabel(), new Constraints(new Leading(39, 10, 10),
				new Leading(204, 6, 6)));
		add(getPasswordLabel(), new Constraints(new Leading(44, 6, 6),
				new Leading(238, 6, 6)));
		add(getQueryScrollPane(), new Constraints(new Leading(65, 251, 10, 10),
				new Leading(10, 76, 10, 10)));
		add(getRemoveQueryButton(), new Constraints(new Leading(249, 6, 6),
				new Leading(88, 6, 6)));
		add(getConnectionURLTextField(), new Constraints(new Leading(116, 194,
				6, 6), new Leading(163, 6, 6)));
		add(getUsernameTextField(), new Constraints(
				new Leading(116, 194, 6, 6), new Leading(198, 10, 10)));
		add(getPasswordTextField(), new Constraints(
				new Leading(116, 194, 6, 6), new Leading(231, 6, 6)));
		add(getRemoteServersLabel(), new Constraints(new Leading(8, 6, 6),
				new Leading(267, 10, 10)));
		add(getServerScrollPane(), new Constraints(
				new Leading(116, 194, 10, 10), new Leading(262, 64, 6, 6)));
		add(getRemoveServerButton(), new Constraints(new Leading(233, 6, 6),
				new Leading(330, 6, 6)));
		add(getAddServerButton(), new Constraints(new Leading(180, 10, 10),
				new Leading(330, 6, 6)));
		add(getJSeparator1(), new Constraints(new Leading(15, 293, 6, 6),
				new Leading(363, 10, 6, 6)));
		add(getRunButton(), new Constraints(new Leading(265, 10, 10),
				new Leading(369, 6, 6)));
		add(getJButton0(), new Constraints(new Leading(197, 10, 10),
				new Leading(369, 6, 6)));
		setSize(323, 401);
	}

	private JButton getJButton0() {
		if (jButton0 == null) {
			jButton0 = new JButton();
			jButton0.setText("About");
			jButton0.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent event) {
					jButton0MouseMouseClicked(event);
				}
			});
		}
		return jButton0;
	}

	private JSeparator getJSeparator1() {
		if (jSeparator1 == null) {
			jSeparator1 = new JSeparator();
		}
		return jSeparator1;
	}

	private JButton getAddServerButton() {
		if (addServerButton == null) {
			addServerButton = new JButton();
			addServerButton.setText("Add");
			addServerButton.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent event) {
					addServerButtonMouseMouseClicked(event);
				}
			});
		}
		return addServerButton;
	}

	private JButton getRemoveServerButton() {
		if (removeServerButton == null) {
			removeServerButton = new JButton();
			removeServerButton.setText("Remove");
			removeServerButton.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent event) {
					removeServerButtonMouseMouseClicked(event);
				}
			});
		}
		return removeServerButton;
	}

	private JScrollPane getServerScrollPane() {
		if (serverScrollPane == null) {
			serverScrollPane = new JScrollPane();
			serverScrollPane.setViewportView(getRemoteServerList());
		}
		return serverScrollPane;
	}

	private JList getRemoteServerList() {
		if (remoteServerList == null) {
			remoteServerList = new JList();
			remoteServerList.setModel(serverCollection);
		}
		return remoteServerList;
	}

	private JLabel getRemoteServersLabel() {
		if (remoteServersLabel == null) {
			remoteServersLabel = new JLabel();
			remoteServersLabel.setText("Remote Servers");
		}
		return remoteServersLabel;
	}

	private JLabel getPasswordLabel() {
		if (passwordLabel == null) {
			passwordLabel = new JLabel();
			passwordLabel.setText("Password");
		}
		return passwordLabel;
	}

	private JTextField getPasswordTextField() {
		if (passwordTextField == null) {
			passwordTextField = new JTextField();
			passwordTextField.setText("123456");
		}
		return passwordTextField;
	}

	private JTextField getUsernameTextField() {
		if (usernameTextField == null) {
			usernameTextField = new JTextField();
			usernameTextField.setText("root");
		}
		return usernameTextField;
	}

	private JLabel getUsernameLabel() {
		if (usernameLabel == null) {
			usernameLabel = new JLabel();
			usernameLabel.setText("User name");
		}
		return usernameLabel;
	}

	private JLabel getConnectionURLLabel() {
		if (connectionURLLabel == null) {
			connectionURLLabel = new JLabel();
			connectionURLLabel.setText("Connection URL");
		}
		return connectionURLLabel;
	}

	private JTextField getConnectionURLTextField() {
		if (connectionURLTextField == null) {
			connectionURLTextField = new JTextField();
			connectionURLTextField.setText("jdbc:mysql://localhost:3306/cacti");
		}
		return connectionURLTextField;
	}

	private JSeparator getJSeparator0() {
		if (jSeparator0 == null) {
			jSeparator0 = new JSeparator();
		}
		return jSeparator0;
	}

	private JButton getRunButton() {
		if (runButton == null) {
			runButton = new JButton();
			runButton.setFont(new Font("Arial", Font.PLAIN, 11));
			runButton.setText("Run");
			runButton.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent event) {
					runButtonMouseMouseClicked(event);
				}
			});
		}
		return runButton;
	}

	private JTextField getThreadsTextField() {
		if (threadsTextField == null) {
			threadsTextField = new JTextField();
			threadsTextField.setFont(new Font("Arial", Font.PLAIN, 11));
			threadsTextField.setText("10");
			threadsTextField.setToolTipText("Number of concurrent queries ");
		}
		return threadsTextField;
	}

	private JLabel getThreadsLabel() {
		if (threadsLabel == null) {
			threadsLabel = new JLabel();
			threadsLabel.setFont(new Font("Arial", Font.PLAIN, 11));
			threadsLabel.setText("Number of threads");
		}
		return threadsLabel;
	}

	private JLabel getQueryLabel() {
		if (queryLabel == null) {
			queryLabel = new JLabel();
			queryLabel.setFont(new Font("Arial", Font.PLAIN, 11));
			queryLabel.setText("Query List");
		}
		return queryLabel;

	}

	private JButton getAddQueryButton() {
		if (addQueryButton == null) {
			addQueryButton = new JButton();
			addQueryButton.setFont(new Font("Arial", Font.PLAIN, 11));
			addQueryButton.setText("Add");
			addQueryButton.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent evt) {
					addQueryButtonMouseClicked(evt);
				}
			});

		}
		return addQueryButton;
	}

	private JButton getRemoveQueryButton() {
		if (removeQueryButton == null) {
			removeQueryButton = new JButton();
			removeQueryButton.setFont(new Font("Arial", Font.PLAIN, 11));
			removeQueryButton.setText("Remove");
			removeQueryButton.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent event) {
					removeQueryButtonMouseMouseClicked(event);
				}
			});
		}
		return removeQueryButton;
	}

	private JScrollPane getQueryScrollPane() {
		if (queryScrollPane == null) {
			queryScrollPane = new JScrollPane();
			queryScrollPane.setViewportView(getJList0());
		}
		return queryScrollPane;
	}

	private JList getJList0() {
		if (queryList == null) {
			queryList = new JList();
			queryList.setFont(new Font("Arial", Font.PLAIN, 11));
			queryList.setModel(queryCollection);
		}
		return queryList;
	}

	private static void installLnF() {
		try {
			String lnfClassname = PREFERRED_LOOK_AND_FEEL;
			if (lnfClassname == null)
				lnfClassname = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(lnfClassname);
		} catch (Exception e) {
			System.err.println("Cannot install " + PREFERRED_LOOK_AND_FEEL
					+ " on this platform:" + e.getMessage());
		}
	}

	/**
	 * Main entry of the class. Note: This class is only created so that you can
	 * easily preview the result at runtime. It is not expected to be managed by
	 * the designer. You can modify it as you like.
	 */
	public static void main(String[] args) {
		installLnF();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				TestClientView frame = new TestClientView();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setTitle("MySQL Stress Test");
				frame.getContentPane().setPreferredSize(frame.getSize());
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

	/**
	 * Action listeners
	 */

	public void addQueryButtonMouseClicked(java.awt.event.MouseEvent evt) {
		String newQuery = JOptionPane.showInputDialog(null, "Add Query",
				"Add new query", JOptionPane.QUESTION_MESSAGE);
		if (!newQuery.equals("")) {
			queryCollection.addElement(newQuery);
		}

	}

	private void removeQueryButtonMouseMouseClicked(MouseEvent event) {
		queryCollection.remove(queryList.getSelectedIndex());
	}

	private void addServerButtonMouseMouseClicked(MouseEvent event) {
		String newQuery = JOptionPane.showInputDialog(null, "Add Server",
				"Add new server", JOptionPane.QUESTION_MESSAGE);
		if (!newQuery.equals("")) {
			serverCollection.addElement(newQuery);
		}
	}

	private void removeServerButtonMouseMouseClicked(MouseEvent event) {
		serverCollection.remove(remoteServerList.getSelectedIndex());
	}

	private void runButtonMouseMouseClicked(MouseEvent event) {
		TestConfig testConfig = new TestConfig();
		testConfig.setConcurrentConnections(new Integer(threadsTextField
				.getText()));
		testConfig.setConnectionURL(connectionURLTextField.getText());
		testConfig.setPassword(passwordTextField.getText());
		testConfig.setQuerys(queryCollection.toArrayList());
		testConfig.setUsername(usernameTextField.getText());
		ArrayList<String> servers = serverCollection.toArrayList();
		for (String server : servers) {
			Thread t = new Thread(new TestClient(server, testConfig), server);
			t.start();
		}
	}

	private void jButton0MouseMouseClicked(MouseEvent event) {
		JOptionPane
				.showMessageDialog(null,
						"Developed by Sandeep Manne\ncontact me @ sandeep.manne@gmail.com");
	}
}
