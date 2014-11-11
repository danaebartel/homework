package inclassOct4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RecurisiveFileReader {
	int lineCounter = 0;
	int validLineCounter = 0;

	public static void main(String[] args) throws FileNotFoundException {
		File dir = new File("/Users/danaebartel/Documents/workspace/"); //
		traverseDir(dir);

	}

	public static void traverseDir(File dir) throws FileNotFoundException {
		// traverses through the whole workspace
		int totalDir = 0;
		if (dir.isDirectory()) {

			File[] contents = dir.listFiles();
			

			String dirName = dir.getName();
			if (dirName.endsWith("src")) {
				System.out.println("Project: " + dir.getAbsolutePath());
				
				// totalFiles++;
				// System.out.println("TOTAL FILES: " + totalFiles);
			}

			for (int i = 0; i < contents.length; i++) {
				traverseDir(contents[i]);	
//				if(contents[i].getName().endsWith(dirName)){
//					totalDir++;
//					System.out.println("Number of directories " + totalDir);
//
//					}

				//System.out.println("Number of files" + totalDir);
			}
		} else {
			// this part of the code is executed when dir is a file
			String fileName = dir.getName();
			if (fileName.endsWith("java")) {
				System.out.println("Class: " + dir.getAbsolutePath()
						+ "Total Lines " + processFile(dir) + " Total lines"
						+ " without comments " + processFile(dir));
				int test = processFile(dir);
				System.out.println("SDKFJSKF " + test);
				//System.out.println("DIRECTORY " + getDirCount(dir));
				

			}
		
		}
		//System.out.println("FILES " + getFilesCount(dir));
		 
	}


	public static int processFile(File input) throws FileNotFoundException {
		Scanner fScan = new Scanner(input);
		int lineCounter = 0, validLineCounter = 0;
		boolean found = true;

		while (fScan.hasNextLine()) {
			String line = fScan.nextLine();
			lineCounter++;
			if (line.trim().startsWith("//")) {
				validLineCounter--;
			} else if (line.contains("/*") == false && found == false) {
				validLineCounter++;
			} else {
				found = true;
			}
			if (found == true && line.contains("*/")) {
				found = false;
			}
		}
		if (validLineCounter == 0) {
			return (lineCounter);
		}

		else {
			return (validLineCounter);
		}
	}
	
	public static int getAverageLines(File input){
		File[] files = input.listFiles();
		int sum = 0;
		for(int i=0; i < files.length ; i++)
            sum += files[i];
	}
	
	public static int getDirCount(File input){
		int count = 0;
		File[] files = input.listFiles();
		
		for (int i = 0; i < files.length; i++) {
		    if (files[i].isDirectory()) {
		            count++;
		        }
		}
		System.out.println("No of dir " + count);
		return count;
	}

	public static int getFilesCount(File file) {

        File[] files = file.listFiles();
		int count = 0;
		
		if (files != null)
			for (int i = 0; i < files.length; i++) {
				count++;
				File file1 = files[i];
				if(files[i].getName().endsWith("java")){
					getFilesCount(file1);
				}
				//System.out.println("COUNT" + count);
			}

		//System.out.println("COUNT" + count);
		return count;

	}

}

