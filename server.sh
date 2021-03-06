#!/bin/bash
#
# DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
#
# This file is part of MySQL Stress Test
#  
# Author: Sandeep Manne
# ver. 1.00
# Copyright: Sandeep Manne <sandeep.manne@gmail.com>
#
# MySQL Stress Test is free software: you can redistribute it
# and/or modify it under the terms of the GNU General Public License
# as published by the Free Software Foundation, either version 3 of
# the License, or (at your option) any later version.
# 
# MySQL Stress Test is distributed WITHOUT ANY WARRANTY.
# See the license for more details.
#  
# You should have received a copy of the GNU General Public License
# along with MySQL Stress Test. If not, see
# <http://www.gnu.org/licenses/>.
#
#TODO Need to verify java version, type, path
java -cp "lib/mysql-connector-java-5.1.12-bin.jar:." com.costrategix.mysqltest.server.TestServer
