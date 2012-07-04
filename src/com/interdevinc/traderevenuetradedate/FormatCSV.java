/*
 * TODO Delete old variables and methods.
 * TODO Remove 'new' from filename of write
 * TODO Move Write methods to Write class.
 */

/**
 * FormatCSV
 * Created on Jul 29, 2009 8:39AM
 * Modified on Mar 12, 2011 12:00AM
 * @author Matthew Weppler
 * copyright 2011 InterDev Inc.
 */
package com.interdevinc.traderevenuetradedate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class FormatCSV {

	private ArrayList<String> reportByLine;
	private ArrayList<String> combined13Rows;

	/**
	 * METHOD: EXTRACT TRADE REV FILE
	 * @param as
	 * @param zipFileName
	 */
	public static void extractTradeRevFile(ApplicationSettings as, String zipFileName) {
		try {
			ZipFile zf = new ZipFile(as.getFileDirectory()+zipFileName);
			Enumeration entries = zf.entries();
			while (entries.hasMoreElements()) {
				ZipEntry ze = (ZipEntry) entries.nextElement();
				if (ze.getName().equals(as.getDatFileName())) {
					BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(as.getFileDirectory()+as.getDatFileName())));
					BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(zf.getInputStream(ze)));
					String line;
					while ((line = bufferedReader.readLine()) != null) {
						bufferedWriter.write(line + "\r\n");
					}
					bufferedReader.close();
					bufferedWriter.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * METHOD: WRITE CSV DATA TO FILE
	 * @param as
	 */
	public void writeCSVDataToFile(ApplicationSettings as) {
		createArrayListOfReport(as.getFileDirectory()+as.getDatFileName());
		createTrailerData();
		createArrayListCombine13Rows();
		FixedToCSV fcsv = new FixedToCSV();
		fcsv.setFieldLengths();
		fcsv.insertCommasInto(combined13Rows);
		writeCSVtoFileNew(as.getOutputFileName());
	}

	/**
	 * METHOD: WRITE DATA TO TRAILER TABLE
	 * @param as
	 */
	public ArrayList<String> writeDataToTrailerTable(ApplicationSettings as) {
		createArrayListOfReport(as.getFileDirectory()+as.getDatFileName());
		return createTrailerData();
	}

	/**
	 * METHOD: CREATE ARRAY LIST OF REPORT
	 * Creates an arraylist of strings containing line by line data from the report file.
	 * Each line is 101 characters.
	 * Each record is actually 13 lines or 1313 characters.
	 * @param datFileName
	 */
	private void createArrayListOfReport(String datFileName) {
		reportByLine = new ArrayList<String>();
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(datFileName)));
			String tempLine;
			while ((tempLine = bufferedReader.readLine()) != null) {
				reportByLine.add(tempLine.replace(",", " ").replaceAll("\r\n", ""));
			}
			bufferedReader.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * 	METHOD: CREATE ARRAY LIST COMBINE 13 ROWS
	 *  Creates an arraylist of strings containing entire 1313 characters per string.
	 */
	private void createArrayListCombine13Rows() {
		combined13Rows = new ArrayList<String>();
		int lucky = 13, counter = 0;
		String tempString = new String("");
		for (String concatString : reportByLine) {
			tempString = tempString.concat(concatString);
			counter++;
			if (counter == lucky) {
				combined13Rows.add(tempString);
				counter = 0;
				tempString = new String("");
			}
		}
	}

	/**
	 * METHOD: CREATE TRAILER DATA
	 * 
	 * Creates an arraylist of strings containing the date from the header row, 
	 * and the rest of the data from the trailer row. 
	 * Deletes the header and trailer rows once we have the needed data.
	 * 
	 * index 0: runDate
	 * index 1: principal
	 * index 2: logicalRecordsHT
	 * index 3: logicalRecords
	 * index 4: commission
	 * index 5: concession
	 * index 6: clearingCharge
	 * 
	 */
	private ArrayList<String> createTrailerData() {
		ArrayList<String> dataForTrailerDB = new ArrayList<String>();
		dataForTrailerDB.add("20".concat(reportByLine.get(0).substring(45, 47)).concat(reportByLine.get(0).substring(41, 43)).concat(reportByLine.get(0).substring(43, 45)));
		dataForTrailerDB.add(reportByLine.get(reportByLine.size() - 1).substring(2, 16));
		dataForTrailerDB.add(reportByLine.get(reportByLine.size() - 1).substring(21, 36));
		dataForTrailerDB.add(reportByLine.get(reportByLine.size() - 1).substring(42, 56));
		dataForTrailerDB.add(reportByLine.get(reportByLine.size() - 1).substring(58, 67));
		dataForTrailerDB.add(reportByLine.get(reportByLine.size() - 1).substring(68, 78));
		dataForTrailerDB.add(reportByLine.get(reportByLine.size() - 1).substring(79, 89));

//		for (String dataElement : dataForTrailerDB) {
//			System.out.println(dataElement);
//		}

		// Remove Header and Trailer from reportByLine.
		reportByLine.remove(0);
		reportByLine.remove(reportByLine.size() - 1);

		return dataForTrailerDB;
	}

	/**
	 * METHOD: WRITE CSV TO FILE
	 */
	private void writeCSVtoFileNew(String csvFileName) {
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(csvFileName)));
			for (String string : combined13Rows) {
				bufferedWriter.write(string + "\r\n");
			}
			bufferedWriter.close();
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}

	//OLD DATA AS OF 20110312 
	private String inFileName;
	private File trdFile;
	private BufferedReader datFile;
	private String outFileName;
	private File outFile;
	private BufferedWriter outStream;
	private int numOfLines;
	private String[] line;
	private String[] lineWOBreak;
	private int numLineWOBreak;
	private String[] lineCSV;

	public void setInFileName(String fileName) {
		inFileName = fileName;
	}

	public String getInFileName() {
		return this.inFileName;
	}

	public void setInFileReader() {
		try {
			trdFile = new File(inFileName);
			datFile = new BufferedReader(new FileReader(trdFile));
		} catch (IOException ioe) {
			//ioe.printStackTrace();
		}
	}

	public BufferedReader getInFileReader() {
		return this.datFile;
	}

	public void setOutFileName() {
		outFileName = new String("TRDREV_TD.csv");
	}

	public String getOutFileName() {
		return this.outFileName;
	}

	public void setOutFileWriter() {
		outFile = new File(outFileName);
	}

	public File getOutFileWriter() {
		return this.outFile;
	}

	public void setNumOfLines() {
		numOfLines = 0;
		try {
			while (datFile.readLine() != null) {
				++numOfLines;
			}
			datFile.close();
		} catch (IOException ioe) {
			//ioe.printStackTrace();
		}
	}

	public int getNumOfLines() {
		return this.numOfLines;
	}

	public void setLineAsString() {
		try {
			line = new String[numOfLines];
			for (int a = 0; a < numOfLines; ++a) {
				//line[a] = datFile.readLine();
				line[a] = checkStrForCommas(datFile.readLine());
				//System.out.println("Row: " + (a + 1) + " " + line[a]);
			}
			datFile.close();
		} catch (IOException ioe) {
			//ioe.printStackTrace();
		}
	}

	public String[] getLineAsString() {
		return this.line;
	}

	public void setLineWOBreak() {
		int first = 0;
		int last = numOfLines;

		/*
		 * numLineWOBreak = Number of total lines which is numOfLines + 1 since it starts at 0
		 * subtract for header and trailer in the file, and divide by divideLinesNum(13)
		 */
		int divideLinesNum = 13;
		int headTrailLines = 2;
		numLineWOBreak = (((numOfLines + 1) - headTrailLines) / divideLinesNum);
		lineWOBreak = new String[numLineWOBreak];
		int lineCounter = 0;
		int lineWOBreakCounter = 0;
		StringBuffer sb = new StringBuffer();
		for (int a = 0; a < numOfLines; ++a) {
			if (a == first || a == last) {
				//Do nothing with first or last line of data...
			} else {
				if (lineCounter != 12) {
					//remove linebreak from String...
					sb.append(line[a].replaceAll("\r\n", ""));
					++lineCounter;
				} else {
					//dont remove linebreak from String...
					sb.append(line[a]);
					lineWOBreak[lineWOBreakCounter] = sb.toString();
					//System.out.println(this.lineWOBreak[lineWOBreakCounter]);
					lineCounter = 0;
					++lineWOBreakCounter;
					sb = new StringBuffer();
				}
			}
		}
		if (lineWOBreakCounter == numLineWOBreak) {
			//System.out.println("good - lines: " + lineWOBreakCounter);
		} else {
			System.out.println("bad - lines: " + lineWOBreakCounter + " should be: " + numLineWOBreak);
		}
	}

	public String[] getLineWOBreak() {
		return this.lineWOBreak;
	}

	public void setLineCSV() {
		/*
		 * Create array of strings of comma seperated values.
		 * Write strings to file TRDREV_TD.csv.
		 */
		FixedToCSV fcsv = new FixedToCSV();
		fcsv.setFieldLengths();
		fcsv.setFixToCSV(lineWOBreak, numLineWOBreak);
		lineCSV = fcsv.getFixToCSV();
	}

	public String[] getLineCSV() {
		return this.lineCSV;
	}

	public void readDATtoMem(String tfn) {
		setInFileName(tfn);
		setInFileReader();
		setNumOfLines();
		setInFileName(tfn);
		setInFileReader();
		setLineAsString();
		setLineWOBreak();
		setLineCSV();
	}

	public String checkStrForCommas(String str) {
		String tmpStr = str.replace(",", " ");
		return tmpStr;
	}

	public void writeCSVtoFile() {
		try {
			setOutFileName();
			setOutFileWriter();
			outStream = new BufferedWriter(new FileWriter(outFile));
			for (int a = 0; a < numLineWOBreak; ++a) {
				outStream.write(lineCSV[a] + "\r\n");
			}
			outStream.close();
		}
		catch(IOException ioe) {
			//ioe.printStackTrace();
		}
	}

}
