package inclassOct4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RecurisiveFileReader {
	

	public static void main(String [] args) throws FileNotFoundException{
		File dir = new File("/Users/danaebartel/Documents/workspace/"); //
		traverseDir(dir);

		}
	
	
	public static void traverseDir(File dir) throws FileNotFoundException{
		//traverses through the whole workspace
		int[] numberLines;
		if(dir.isDirectory()){
			
			File [] contents = dir.listFiles();
			numberLines = new int[contents.length];


			String dirName = dir.getName();
			if(dirName.endsWith("src")){
				System.out.println("Project: " + dir.getAbsolutePath());			
				}
			
			
			//System.out.println(dir.getName().split("/"));
			for(int i = 0; i < contents.length; i++){
				traverseDir(contents[i]);				
			}
		}
		else{
			//this part of the code is executed when dir is a file
			String fileName = dir.getName();
			if(fileName.endsWith("java")) {
				System.out.println("Class: " + dir.getAbsolutePath() + "Total Lines " + processFile(dir) + " Total lines"
						+ " without comments " + processFile(dir));
				int test = processFile(dir); 

				System.out.println("SDKFJSKF "+ test);
			}
		}
	}
	
	public static int processFile(File input) throws FileNotFoundException{
		Scanner fScan = new Scanner(input);
		int lineCounter = 0;
		int validLineCounter = 0; 
		boolean found = true; 
				
		while(fScan.hasNextLine()){
			String line = fScan.nextLine();
			lineCounter++;
			if(line.trim().startsWith("//")){
				validLineCounter--;
			}
			else if(line.contains("/*") == false && found == false){
				validLineCounter++;
			}
			else{
				found = true;
			}
			if(found == true && line.contains("*/")){
				found = false;
			}
		}
		if(validLineCounter == 0){
			return (lineCounter + lineCounter);
		}
			
		else{
			return (lineCounter + validLineCounter); 
		}
	}



}

