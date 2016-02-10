package tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ResultsConverter {
	
	public static void resultsToFile(String [][] results, String title) throws IOException{
		File f = new File ("resultsBySolver/"+title+".txt");
		FileWriter fw = new FileWriter(f);
		fw.write("Instances, LinkedList, HashMap, SplitArray, TreeMap\n");
		for(int nbrInstance=5; nbrInstance<=50; nbrInstance+=5){
			fw.write(nbrInstance+", "+results[0][(nbrInstance/5)-1]+", "+results[1][(nbrInstance/5)-1]
					+", "+results[2][(nbrInstance/5)-1]+", "+results[3][(nbrInstance/5)-1]+"\n");
		}
		fw.close();
	}

	public static void main(String [] args){
		File directoryToScan = new File("results"); 
		try {
			String [][] rek = new String [4][10];
			String [][] rff = new String [4][10];
			String [][] rpr = new String [4][10];
			File[] files = directoryToScan.listFiles();
			for(int index=0; index<4; index++){
				BufferedReader br = new BufferedReader(new FileReader(files[index]));
				br.readLine(); // On skip la première ligne
				for(int i=0; i<10; i++){
					rek[index][i]=br.readLine().split(" ")[3];
				}
				br.close();
			}
			
			for(int index=4; index<8; index++){
				BufferedReader br = new BufferedReader(new FileReader(files[index]));
				br.readLine(); // On skip la première ligne
				for(int i=0; i<10; i++){
					rff[index-4][i]=br.readLine().split(" ")[3];
				}
				br.close();
			}
			
			for(int index=8; index<12; index++){
				BufferedReader br = new BufferedReader(new FileReader(files[index]));
				br.readLine(); // On skip la première ligne
				for(int i=0; i<10; i++){
					rpr[index-8][i]=br.readLine().split(" ")[3];
				}
				br.close();
			}
			
			resultsToFile(rek, "EdmondsKarp");
			resultsToFile(rff, "Ford-Fulkerson Scaling");
			resultsToFile(rpr, "Push-Relabel");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
