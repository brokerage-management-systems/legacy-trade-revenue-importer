package com.interdevinc.databaseutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.interdevinc.traderevenuetradedate.XMLReader;

/*
 * DatabaseConnect
 * Created on Sept 28, 2009 11:33 AM
 * Modified on Feb 11, 2011 12:00AM
 * @author Matthew Weppler
 * copyright 2011 InterDev Inc.
 */
public class DatabaseConnect {

    private Connection connection;

    public DatabaseConnect() {
        String userDir = System.getProperty("user.dir");
        userDir = new StringBuilder(userDir).append("/appConfig.xml").toString();
        //System.out.println(userDir);
        XMLReader xmlConf = new XMLReader(userDir);
        xmlConf.setDBSettings();
        String[] dbSettings;
        dbSettings = xmlConf.getDBSettings();
        setDBConnection(dbSettings);
    }

    public void setDBConnection(String[] dbSettings) {
        try {
            String connectString = new String("jdbc:mysql://" + dbSettings[0] + "/" + dbSettings[1]);
            connection = DriverManager.getConnection(connectString, dbSettings[2], dbSettings[3]);
        } catch (SQLException sqle) {
        	//sqle.printStackTrace();
            System.exit(1);
        }
    }

    public Connection getDBConnection() {
        return connection;
    }

    public int numOfRows(ResultSet recSet) {
        int numOfRows = 0;
        try {
            //Get num of rows
            recSet.last();
            numOfRows = recSet.getRow();
            recSet.beforeFirst();
            //System.out.println("Total Rows: " + numOfRows);
        } catch (SQLException sqle) {
        	//sqle.printStackTrace();
        }
        return numOfRows;
    }

    public int numOfColumns(ResultSet recSet) {
        int numOfColumns = 0;
        try {
            ResultSetMetaData selectRepNumMD = recSet.getMetaData();
            numOfColumns = selectRepNumMD.getColumnCount();
            //System.out.println("Total Columns: " + numOfColumns);
        } catch (SQLException sqle) {
        	//sqle.printStackTrace();
        }
        return numOfColumns;
    }

}
