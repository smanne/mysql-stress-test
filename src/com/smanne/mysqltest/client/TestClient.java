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

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import com.smanne.mysqltest.TestConfig;
import com.smanne.mysqltest.TestRunnerInterface;

/**
 * Test Client is used to connect to test server and invoke the TestRunner in
 * server, It connects each server in different threads
 * 
 * @author Sandeep Manne <sandeep.manne@gmail.com>
 */
public class TestClient extends Thread {

	private static int serverPort = 3232;

	private String server;
	private TestConfig testConfig;

	public TestClient(String server, TestConfig testConfig) {
		this.server = server;
		this.testConfig = testConfig;
	}

	@Override
	public void run() {
		System.out.println("Starting Test");
		TestRunnerInterface rmiServer;
		Registry registry;
		try {
			// get the “registry”
			registry = LocateRegistry.getRegistry(this.server, serverPort);
			System.out.println(registry.toString());
			// look up the remote object
			rmiServer = (TestRunnerInterface) (registry
					.lookup("mysqlTestServer"));
			// call the remote method
			System.out.println(rmiServer.toString());
			rmiServer.startTest(this.testConfig);
			System.out.println("Invoked Test");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
}
