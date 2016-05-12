package tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ResultsConverter {
	
	public static int inst=2;

	// TODO a réadapté à l'output du script python
	public static void resultsToFile(String [][] results, String title) throws IOException{
		File f = new File ("results/resultsBySolver/"+title+inst+".csv");
		FileWriter fw = new FileWriter(f);
		fw.write("Instances, LinkedList, HashMap, SplitArray, TreeMap, SparseMap\n");
		for(int nbrInstance=5; nbrInstance<=100; nbrInstance+=5){
			fw.write(nbrInstance+", "+results[0][(nbrInstance/5)-1]+", "+results[1][(nbrInstance/5)-1]
					+", "+results[2][(nbrInstance/5)-1]+", "+results[3][(nbrInstance/5)-1]+", "+results[4][(nbrInstance/5)-1]+"\n");
		}
		fw.close();
	}

	public static void main(String [] args){
		String [][] rek = new String [5][20];
		String [][] rff = new String [5][20];
		String [][] rpr = new String [5][20];
		File f = new File("results/resultsOriented"+inst+".txt");
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(f));

			for(int index=0; index<5; index++){
				for(int i=0; i<20; i++){
					rff[index][i]=br.readLine().split(" ")[3];
				}
			}
			for(int index=5; index<10; index++){
				for(int i=0; i<20; i++){
					rek[index-5][i]=br.readLine().split(" ")[3];
				}
			}

			/*for(int index=10; index<15; index++){
				BufferedReader br = new BufferedReader(new FileReader(files[index]));
				for(int i=0; i<10; i++){
					rpr[index-8][i]=br.readLine().split(" ")[3];
				}
				br.close();
			}*/

			br.close();


			resultsToFile(rek, "Edmonds-Karp");
			resultsToFile(rff, "Ford-Fulkerson Scaling");
			//resultsToFile(rpr, "Push-Relabel");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
