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
package com.smanne.mysqltest.server;

import java.rmi.*;
import java.rmi.registry.*;
import java.net.*;
import java.util.logging.*;
import com.smanne.mysqltest.*;

/**
 * Starts RMI Server, bind registry and initiate TestRunner threads
 */
public class TestServer extends java.rmi.server.UnicastRemoteObject implements
TestRunnerInterface {

	protected TestServer() throws RemoteException {
		super();
	}

	private static final long serialVersionUID = 2526218808798711709L;

	private int port;
	private String serverAddress;
	private Registry registry;

	private TestConfig testConfig;
	
	/**
	 * Initialise test runner threads and starts
	 */
	public void startTest(TestConfig testConfig) {

		// Logger Object
		// TODO Need to improve logging
		FileHandler handler;
		Logger logger = Logger.getLogger("log_file");
		try {
			handler = new FileHandler("mysqltest.log", true);
			logger.addHandler(handler);
		} catch (Exception e) {
			System.out.println("Unable to intilize logger");
		}
		TestRunner.logger = logger;

		// Used to calculate total time taken by tests
		TestRunner.startTime = System.currentTimeMillis();
		
		this.testConfig = testConfig;
		TestRunner.setTestConfig(testConfig);
		try {
			for (int j = 1; j <= testConfig.getConcurrentConnections(); j++) {
				Thread t = new Thread(new TestRunner(), "Thread" + j);
				TestRunner.finished++;
				t.start();
			}
		} catch (Exception e) {
			logger.severe(e.toString());
		}
		this.monitorTest();
	}
	
	/**
	 * Monitor test and print test 
	 * summary when test is completed
	 */
	private void monitorTest()
	{
		while(isTestCompleted()) {
			try {
				wait(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//TODO Mail or log test summary
		this.printTestSummary();
	}
	
	/**
	 * Check whether the test is completed 
	 * @return {@link Boolean}
	 */
	private boolean isTestCompleted()
	{
		return (TestRunner.finished == 0);
	}
	
	/**
	 * Prints test summary
	 */
	private void printTestSummary()
	{
		System.out.println("Test Summary \n=============");
		System.out.println("Total time taken to execute the test : "
				+ (System.currentTimeMillis() - TestRunner.startTime));
		System.out.println("Total threads executed : "
				+ testConfig.getConcurrentConnections());
		System.out.println("Number of queries executed : "
				+ testConfig.getQuerys().size());
	}
	
	/**
	 * Starts RMI Server
	 * 
	 * @throws RemoteException
	 */
	public void startServer() throws RemoteException {
		this.about();
		System.out.println("Starting server please wait...\n");
		try {
			// get the address of this host.
			serverAddress = (InetAddress.getLocalHost()).toString();
		} catch (Exception e) {
			throw new RemoteException("can't get inet address.");
		}
		port = 3232; // this port(registry’s port)
		if (System.getProperty("java.rmi.server.hostname") != null) {
			System.out.println(System.getProperty("java.rmi.server.hostname"));
		} else {
			System.out
			.println("Use 'java -Djava.rmi.server.hostname=xx.xx.xx.xx'"
					+ " to listen to external IP address ");
		}
		try {
			// create the registry and bind the name and object.
			registry = LocateRegistry.createRegistry(port);
			registry.rebind("mysqlTestServer", this);
			System.out.println("Server Started on " + serverAddress + ":"
					+ port + "\nReady for testing....");
		} catch (RemoteException e) {
			System.out.println(e.toString());
		}
	}

	/**
	 * Main method
	 * 
	 * @param args
	 */
	static public void main(String args[]) {
		try {
			new TestServer().startServer();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * About Server
	 */
	private void about() {
		System.out
		.println("MySQL Stress Test Server v 0.1 beta\n"
				+ "Developed by Sandeep Manne <sandeep.manne@gmail.com>\n"
				+ "Go to http://code.google.com/p/mysql-stress-test/ for latest updates\n");
	}

}