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
package com.smanne.mysqltest;

import java.io.Serializable;
import java.util.ArrayList;

public class TestConfig implements Serializable {

	private static final long serialVersionUID = 8706464204693928464L;
	
	// MySQL Connection settings
	private String connectionURL = "jdbc:mysql://localhost:3306/";
	private String username = "root";
	private String password = "";

	// Test settings
	private int concurrentConnections = 1;
	private ArrayList<String> querys = new ArrayList<String>();

	public String getConnectionURL() {
		return this.connectionURL;
	}

	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}

	public int getConcurrentConnections() {
		return this.concurrentConnections;
	}

	public ArrayList<String> getQuerys() {
		return this.querys;
	}

	public void setConnectionURL(String connectionURL) {
		this.connectionURL = connectionURL;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setConcurrentConnections(int concurrentConnections) {
		this.concurrentConnections = concurrentConnections;
	}

	public void setQuerys(ArrayList<String> querys) {
		this.querys = querys;
	}
}
