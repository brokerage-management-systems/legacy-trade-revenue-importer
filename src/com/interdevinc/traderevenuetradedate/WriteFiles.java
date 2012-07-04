/**
 * WriteFiles
 * Created on Sept 25, 2009, 5:18 PM
 * Modified on Mar 12, 2011 12:00AM
 * @author Matthew Weppler
 * copyright 2011 InterDev Inc.
 */

package com.interdevinc.traderevenuetradedate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.interdevinc.databaseutils.DatabaseConnect;

public class WriteFiles {

	private DatabaseConnect dbc;
	private String initDirectory;
	private String datFileName;
	private String csvFileName;
	private String zipBaseFileName;
	private String firstBupDir;
	private String secondBupDir;
	private String yesterdaysDat;
	private String todayIs;

	public WriteFiles(ApplicationSettings as) {
		setInitDirectory(as.getFileDirectory());
		setDatFileName(as.getDatFileName());
		setCsvFileName(as.getOutputFileName());
		setZipBaseName(as.getZipFileBaseName());
		setFirstBupDir(as.getBackupDirectory());
		setSecondBupDir(as.getBackupDirectoryOther());
		setYesterdayDatName(as.getPreviousDatFile());
		setTodayDateStr();
	}

	public void setInitDirectory(String initDir) {
		initDirectory = initDir;
	}

	public String getInitDirectory() {
		return this.initDirectory;
	}

	public void setDatFileName(String datFName) {
		datFileName = datFName;
	}

	public String getDatFileName() {
		return this.datFileName;
	}

	public void setCsvFileName(String csvFName) {
		csvFileName = csvFName;
	}

	public String getCsvFileName() {
		return this.csvFileName;
	}

	public void setZipBaseName(String zipFName) {
		zipBaseFileName = zipFName;
	}

	public String getZipBaseName() {
		return this.zipBaseFileName;
	}

	public void setFirstBupDir(String bupDir) {
		firstBupDir = bupDir;
	}

	public String getFirstBupDir() {
		return this.firstBupDir;
	}

	public void setSecondBupDir(String bupDir) {
		secondBupDir = bupDir;
	}

	public String getSecondBupDir() {
		return this.secondBupDir;
	}

	public void setYesterdayDatName(String yDayDat) {
		yesterdaysDat = yDayDat;
	}

	public String getYesterdayDatName() {
		return this.yesterdaysDat;
	}

	public void setTodayDateStr() {
		Calendar cal = Calendar.getInstance();
		//Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		cal.add(Calendar.DATE, -1);
		todayIs = new String(sdf.format(cal.getTime()));
	}

	public String getTodayDateStr() {
		return this.todayIs;
	}

	public void writeZipFile() {

		// These are the files to include in the ZIP file
		String[] filenames = new String[]{datFileName};

		// Create a buffer for reading the files
		byte[] buf = new byte[1024];

		try {
			// Create the ZIP file
			String outFilename = zipBaseFileName;
			outFilename = new StringBuilder(outFilename).append(todayIs).append(".zip").toString();
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(outFilename));

			// Compress the files
			for (int i = 0; i < filenames.length; i++) {
				FileInputStream in = new FileInputStream(filenames[i]);

				// Add ZIP entry to output stream.
				out.putNextEntry(new ZipEntry(filenames[i]));

				// Transfer bytes from the file to the ZIP file
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}

				// Complete the entry
				out.closeEntry();
				in.close();
			}

			// Complete the ZIP file
			out.close();
		} catch (IOException ioe) {
			//ioe.printStackTrace();
		}
	}

	public void moveZipFile() {
		try {
			String zipFilename = new StringBuilder("/").append(zipBaseFileName).toString();
			zipFilename = new StringBuilder(zipFilename).append(todayIs).append(".zip").toString();
			File inFile = new File(initDirectory + zipFilename);
			InputStream inStream = new FileInputStream(inFile);

			// Zip Files to Move
			//if one of the dirs are null we have a problem... needs fixing!!!
			File firstOutFile = new File(firstBupDir + zipFilename);
			OutputStream firstOutStream = new FileOutputStream(firstOutFile);
			File secondOutFile = new File(secondBupDir + zipFilename);
			OutputStream secondOutStream = new FileOutputStream(secondOutFile);

			//Transer bytes from in to out
			byte[] buf = new byte[1024];
			int len;
			while ((len = inStream.read(buf)) > 0) {
				firstOutStream.write(buf, 0, len);
				secondOutStream.write(buf, 0, len);
			}
			inStream.close();
			firstOutStream.close();
			secondOutStream.close();
			inFile.delete();

		} catch (FileNotFoundException fnfe) {
			//fnfe.printStackTrace();
		} catch (IOException ioe) {
			//ioe.printStackTrace();
		}

	}

	public void moveDatFile() {
		try {
			//Delete previous days Dat File
			File ydayDatFile = new File(yesterdaysDat + "/" + datFileName);
			ydayDatFile.delete();

			//Move todays Dat File
			File datInFile = new File(initDirectory + "/" + datFileName);
			InputStream datInStream = new FileInputStream(datInFile);
			File datOutFile = new File(yesterdaysDat + "/" + datFileName);
			OutputStream datOutStream = new FileOutputStream(datOutFile);
			byte[] buf = new byte[1024];
			int len;
			while ((len = datInStream.read(buf)) > 0) {
				datOutStream.write(buf, 0, len);
			}
			datInStream.close();
			datOutStream.close();
			datInFile.delete();

		} catch (FileNotFoundException fnfe) {
			//fnfe.printStackTrace();
		} catch (IOException ioe) {
			//ioe.printStackTrace();
		}
	}

	
	public void importCsvToSql() {
		dbc = new DatabaseConnect();
		try {
			File csvInFile = new File(initDirectory + "/" + csvFileName);

			//Upload CSV File
			String importString = new String("LOAD DATA LOCAL INFILE '" + csvFileName + "' INTO TABLE TRDREV_TD FIELDS TERMINATED BY ','");
			Statement stmt = dbc.getDBConnection().createStatement();
			stmt.executeUpdate(importString);

			//Delete CSV File
			csvInFile.delete();

		} catch (SQLException sqle) {
			//sqle.printStackTrace();
		}
	}

	/**
	 * METHOD: IMPORT TRAILER DATA
	 * @param trailerData
	 * 
	 * index 1: runDate
	 * index 2: principal
	 * index 3: logicalRecordsHT
	 * index 4: logicalRecords
	 * index 5: commission
	 * index 6: concession
	 * index 7: clearingCharge
	 * 
	 */
	public void importTrailerData(ArrayList<String> trailerData) {
		dbc = new DatabaseConnect();
		try {            
			PreparedStatement pstmt = dbc.getDBConnection().prepareStatement("INSERT INTO TRDREV_TD_TRAILER VALUES (?,?,?,?,?,?,?)");
			pstmt.setString(1, trailerData.get(0));
			pstmt.setInt(2, Integer.parseInt(trailerData.get(1)));
			pstmt.setInt(3, Integer.parseInt(trailerData.get(2)));
			pstmt.setInt(4, Integer.parseInt(trailerData.get(3)));
			pstmt.setInt(5, Integer.parseInt(trailerData.get(4)));
			pstmt.setInt(6, Integer.parseInt(trailerData.get(5)));
			pstmt.setInt(7, Integer.parseInt(trailerData.get(6)));
			pstmt.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

}
