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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.*;

import com.smanne.mysqltest.*;

/**
 * Starts running queries in new thread
 */
public class TestRunner implements Runnable {

	private static TestConfig testConfig = new TestConfig();
	
	public static int finished = 0;
	public static long startTime;
	public static long endTime;
	public static Logger logger;
	

	public static void setTestConfig(TestConfig testConfig) {
		TestRunner.testConfig = testConfig;
	}
	
	public void run() {

		try {
			//TODO need to take class name from testConfig
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection(testConfig
					.getConnectionURL(), testConfig.getUsername(), testConfig
					.getPassword());
			Statement stat = conn.createStatement();

			for (String query : testConfig.getQuerys()) {
				ResultSet result = stat.executeQuery(query);
				result.last();
			}
			TestRunner.finished--;
			if (TestRunner.finished == 0) {
				TestRunner.endTime = System.currentTimeMillis();
			}
			conn.close();
		} catch (Exception e) {
			logger.severe(e.toString());
		}
	}
}
