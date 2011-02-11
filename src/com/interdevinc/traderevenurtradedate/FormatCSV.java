/*
 * DatToCSV
 * Created on Jul 29, 2009, 8:39 AM
 * @author Matthew Weppler
 * copyright 2009 InterDev Inc.
 *
 */
package com.interdevinc.traderevenurtradedate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FormatCSV {

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

    //TODO: Filename should not be hardcoded...
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
