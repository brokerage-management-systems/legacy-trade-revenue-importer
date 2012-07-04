/**
 * ImportMain
 * Created on Jul 29, 2009 8:41AM
 * Modified on Mar 12, 2011 12:00AM
 * @author Matthew Weppler
 * copyright 2011 InterDev Inc.
 */
package com.interdevinc.traderevenuetradedate;

import java.io.File;

import org.apache.commons.lang.ArrayUtils;

public class ImportMain {

	public static void main(String[] args) {
		//For Testing...
		//args = new String[x];
		parseArguments(args);
		String userDir = System.getProperty("user.dir");
		String configFile = userDir.concat("/appConfig.xml");
		//System.out.println(configFile);
		XMLReader xmlConf = new XMLReader(configFile);
		ApplicationSettings appSettings = xmlConf.createApplicationSettings();
		runFunctions(appSettings, args);
	}

	public static boolean parseArguments(String[] args) {
		//For Testing...
		//args[0] = "--datfile=test.txt";
		//args[0] = "--zipfile=test.txt";
		//args[0] = "--dirname=/path/to/zip/files";
		//args[0] = "--rwtrailer";
		//args[0] = "--testrun";
		//args[0] = "--writecsv";
		//args[0] = "--writedb";
		//args[0] = "--zipbup";
		boolean badArg = true;
		if (args.length > 0) {
			if (ArrayUtils.contains(args, "--rwtrailer")) {
				badArg = false;
			}
			if (ArrayUtils.contains(args, "--testrun")) {
				badArg = false;
			}
			if (ArrayUtils.contains(args, "--writecsv")) {
				badArg = false;
			}
			if (ArrayUtils.contains(args, "--writedb")) {
				badArg = false;
			}
			if (ArrayUtils.contains(args, "--zipbup")) {
				badArg = false;
			}
			if (badArg) {
				printArgumentsPassed(args); // Prints the arguments passed on the cli
				System.out.println("Please run: 'java -jar TradeRevImport.jar' without arguments to get help.");
				printHelpScreen();
				System.exit(1);
				return false;
			}
			return true;
		} else {
			printHelpScreen();
			System.exit(1);
			return false;
		}
	}

	public static void runFunctions(ApplicationSettings appSettings, String[] arguments) {

		for (int i = 0; i < arguments.length; ++i) {
			if (arguments[i].contains("--datfile=")) {
				appSettings.setDatFileName(arguments[i].substring(10, arguments[i].length()));
				break;
			}
		}

		for (int i = 0; i < arguments.length; ++i) {
			if (arguments[i].contains("--zipfile=")) {
				FormatCSV.extractTradeRevFile(appSettings, arguments[i].substring(10, arguments[i].length()));
				break;
			}
		}

		File directory = null;
		for (int i = 0; i < arguments.length; ++i) {
			if (arguments[i].contains("--dirname=")) {
				directory = new File(arguments[i].substring(10, arguments[i].length()));
				break;
			}
		}

		// Directory argument was passed.
		if (directory == null) {
			if (ArrayUtils.contains(arguments, "--testrun")) {
				System.out.println("Test Run, Goodbye...");
				System.exit(0);
			}
			if (ArrayUtils.contains(arguments, "--rwtrailer")) {
				readWriteTrailerToDB(appSettings);
			}
			if (ArrayUtils.contains(arguments, "--writecsv")) {
				createCSVFile(appSettings);
			}
			if (ArrayUtils.contains(arguments, "--writedb")) {
				writeDataToDB(appSettings);
			}
			if (ArrayUtils.contains(arguments, "--zipbup")) {
				archiveAndBackup(appSettings);
			}
		} else {
			if (directory.exists() && directory.isDirectory()) {
				appSettings.setFileDirectory(directory.getAbsolutePath()+"/");
				String[] files = directory.list();
				for (String fileX : files) {
					// not a Hidden('.') file, and is a .zip file
					if (!fileX.substring(0, 1).equals(".") && fileX.substring(fileX.length()-4).equals(".zip")) {
						if (ArrayUtils.contains(arguments, "--testrun")) {
							System.out.println("Test Run, Goodbye...");
							System.exit(0);
						}
						FormatCSV.extractTradeRevFile(appSettings, fileX);
						if (ArrayUtils.contains(arguments, "--rwtrailer")) {
							readWriteTrailerToDB(appSettings);
						}
						if (ArrayUtils.contains(arguments, "--writecsv")) {
							createCSVFile(appSettings);
						}
						if (ArrayUtils.contains(arguments, "--writedb")) {
							writeDataToDB(appSettings);
						}
						if (ArrayUtils.contains(arguments, "--zipbup")) {
							archiveAndBackup(appSettings);
						}
						File f = new File(appSettings.getFileDirectory()+"TRDREV_TD.DAT");
						f.delete();
					}
				}
			} else {
				System.out.println("Directory: " + directory.getName() + " does not exist.");
			}
		}
	}

	private static void archiveAndBackup(ApplicationSettings appSettings) {
		// Zip files, move to backup directories.
		WriteFiles wf = new WriteFiles(appSettings);
		wf.writeZipFile(); // Write Zip Files.
		wf.moveZipFile(); // Move Zip Files to backup locations.
		wf.moveDatFile(); // Delete yesterdays Dat file and move todays to that location.
	}

	private static void createCSVFile(ApplicationSettings appSettings) {
		// Convert fixed length .DAT file to .CSV file.
		FormatCSV fcsv = new FormatCSV();
		fcsv.writeCSVDataToFile(appSettings);
	}

	private static void printArgumentsPassed(String[] args) {
		System.out.println("Arguments: ");
		for (int a = 0; a < args.length; ++a) {
			System.out.println("Argument" + (a + 1) + ": " + args[a]);
		}
	}

	private static void printHelpScreen() {
		StringBuilder sb = new StringBuilder("\nPlease run with arguments:\n");
		sb.append("    --datfile:		Follow with \"='filename'\" to import. Ignores config file value.\n");
		sb.append("    --zipfile:		Follow with \"='filename'\" to extract TRDREV_TD.DAT file.\n");
		sb.append("    --dirname:		Follow with \"='/path/to/files/'\" to loop through a directory of Zipped files.\n");
		sb.append("    --rwtrailer:		Reads the DAT file header/trailer, Writes the data to the database.\n");
		sb.append("    --writecsv:		Reformats the Fixed length DAT file to a CSV file.\n");
		sb.append("    --writedb:		Writes the CSV file into the Database, then deletes the CSV file.\n");
		sb.append("    --zipbup:		Zips Original DAT file and copies it to two backup locations.\n");
		sb.append("\n*If running this command daily use: 'java -jar TradeRevImport.jar'\n");
		sb.append("    '--rwtrailer --writecsv --writedb --zipbup'\n");
		sb.append("\n*If importing old data into database use: 'java -jar TradeRevImport.jar' with\n");
		sb.append("    '--writecsv --writedb' or '--zipfile='file.zip' --writecsv --writedb'\n");
		sb.append("\n*You may want to add the trailer data if you havent already: 'java -jar TradeRevImport.jar --rwtrailer'\n");
		sb.append("\n");
		System.out.println(sb.toString());
	}

	private static void readWriteTrailerToDB(ApplicationSettings appSettings) {
		FormatCSV fcsv = new FormatCSV();
		WriteFiles wf = new WriteFiles(appSettings);
		wf.importTrailerData(fcsv.writeDataToTrailerTable(appSettings));
	}

	private static void writeDataToDB(ApplicationSettings appSettings) {
		// Import SQL into database.
		WriteFiles wf = new WriteFiles(appSettings);
		wf.importCsvToSql(); // Create SQL queries and import data to table.
	}

}
