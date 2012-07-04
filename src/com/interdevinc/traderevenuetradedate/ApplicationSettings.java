package com.interdevinc.traderevenuetradedate;

public class ApplicationSettings {

	private String backupDirectory;
	private String backupDirectoryOther;
	private String datFileName;
	private String fileDirectory;
	private String outputFileName;
	private String previousDatFile;
	private String zipFileBaseName;
	
	public ApplicationSettings() {
		
	}

	/**
	 * @return the backupDirectory
	 */
	public String getBackupDirectory() {
		return backupDirectory;
	}

	/**
	 * @return the backupDirectoryOther
	 */
	public String getBackupDirectoryOther() {
		return backupDirectoryOther;
	}

	/**
	 * @return the datFileName
	 */
	public String getDatFileName() {
		return datFileName;
	}

	/**
	 * @return the fileDirectory
	 */
	public String getFileDirectory() {
		return fileDirectory;
	}
	
	/**
	 * @return the outputFileName
	 */
	public String getOutputFileName() {
		return outputFileName;
	}
	
	/**
	 * @return the previousDatFile
	 */
	public String getPreviousDatFile() {
		return previousDatFile;
	}

	/**
	 * @return the zipFileBaseName
	 */
	public String getZipFileBaseName() {
		return zipFileBaseName;
	}
	
	/**
	 * @param backupDirectory the backupDirectory to set
	 */
	public void setBackupDirectory(String backupDirectory) {
		this.backupDirectory = backupDirectory;
	}

	/**
	 * @param backupDirectoryOther the backupDirectoryOther to set
	 */
	public void setBackupDirectoryOther(String backupDirectoryOther) {
		this.backupDirectoryOther = backupDirectoryOther;
	}

	/**
	 * @param datFileName the datFileName to set
	 */
	public void setDatFileName(String datFileName) {
		this.datFileName = datFileName;
	}

	/**
	 * @param fileDirectory the fileDirectory to set
	 */
	public void setFileDirectory(String fileDirectory) {
		this.fileDirectory = fileDirectory;
	}
	
	/**
	 * @param outputFileName the outputFileName to set
	 */
	public void setOutputFileName(String outputFileName) {
		this.outputFileName = outputFileName;
	}
	
	/**
	 * @param previousDatFile the previousDatFile to set
	 */
	public void setPreviousDatFile(String previousDatFile) {
		this.previousDatFile = previousDatFile;
	}
    
	/**
	 * @param zipFileBaseName the zipFileBaseName to set
	 */
	public void setZipFileBaseName(String zipFileBaseName) {
		this.zipFileBaseName = zipFileBaseName;
	}
	
}
