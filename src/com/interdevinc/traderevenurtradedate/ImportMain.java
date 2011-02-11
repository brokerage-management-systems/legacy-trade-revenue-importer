package com.interdevinc.traderevenurtradedate;

/*
 * ImportMain
 * Created on Jul 29, 2009 8:41AM
 * MOdified on Feb 11, 2011 12:00AM
 * @author Matthew Weppler
 * copyright 2011 InterDev Inc.
 */
public class ImportMain {

	public static void main(String[] args) {

		//        // For Testing...
		//        args = new String[1];
		//        args[0] = "-writecsv";
		//        args[0] = "-zipbup";
		//        args[0] = "-writedb";

		String userDir = System.getProperty("user.dir");
		userDir = new StringBuilder(userDir).append("/appConfig.xml").toString();
		//System.out.println(userDir);
		XMLReader xmlConf = new XMLReader(userDir);
		xmlConf.setAppSettings();
		String[] appSettings;
		appSettings = xmlConf.getAppSettings();

		if (args.length > 0) {
			//printArgumentsPassed(args); // Prints the arguments passed on the cli
			boolean badArg = true;
			for (int a = 0; a < args.length; ++a) {
				if ("-writecsv".equals(args[a])) {
					writecsv(appSettings);
					badArg = false;
				}

				if ("-zipbup".equals(args[a])) {
					zipAndBackup(appSettings);
					badArg = false;
				}

				if ("-writedb".equals(args[a])) {
					writeToDB(appSettings);
					badArg = false;
				}
				if (badArg == true) {
					System.out.println("Please run: 'java -jar DatToCSV.jar' to get a list of arguments.");
				}
			}
		} else {
			System.out.println("\nPlease run with arguments:");
			System.out.println("    -writecsv - Reformats the Fixed length DAT file to a CSV file.");
			System.out.println("    -zipbup   - Zips Original DAT file and copies it to two backup locations.");
			System.out.println("    -writedb  - Writes the CSV file into the Database, then deletes the CSV file.");
			System.out.println("\n*If running this command daily use: 'java -jar DatToCSV.jar -writecsv -zipbup - writedb'");
			System.out.println("\n*If importing old data into database use: 'java -jar DatToCSV.jar -writecsv - writedb'");
			System.out.println("");
		}
	}

	public static void printArgumentsPassed(String[] args) {
		System.out.println("Arguments: ");
		for (int a = 0; a < args.length; ++a) {
			System.out.println("Arg" + (a + 1) + " - " + args[a]);
		}
	}

	public static void writecsv(String[] appSettings) {
		// Convert .DAT file to .CSV file.
		FormatCSV fcsv = new FormatCSV();
		fcsv.readDATtoMem(appSettings[1]);
		fcsv.writeCSVtoFile();
	}

	public static void zipAndBackup (String[] appSettings) {
		// Zip files, move to backup directories.
		WriteFiles wf = new WriteFiles(appSettings);
		wf.writeZipFile(); // Write Zip Files.
		wf.moveZipFile(); // Move Zip Files to backup locations.
		wf.moveDatFile(); // Delete yesterdays Dat file and move todays to that location.
	}

	public static void writeToDB(String[] appSettings) {
		// Import SQL into database.
		WriteFiles wf = new WriteFiles(appSettings);
		wf.importCsvToSql(); // Create SQL queries and import data to table.
	}

}
