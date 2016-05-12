package tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ResultsConverter {

	public static void resultsToFile(String [][] results, String title, int inst) throws IOException{
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
		for(int inst=1; inst<=10; inst++){
			String [][] rek = new String [5][20];
			String [][] rff = new String [5][20];
			String [][] rpr = new String [5][20];
			String [][] rfpr = new String [5][20];
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
				for(int index=10; index<15; index++){
					for(int i=0; i<20; i++){
						rpr[index-10][i]=br.readLine().split(" ")[3];
					}
				}
				for(int index=15; index<20; index++){
					for(int i=0; i<20; i++){
						rfpr[index-15][i]=br.readLine().split(" ")[3];
					}
				}

				br.close();


				resultsToFile(rek, "Edmonds-Karp", inst);
				resultsToFile(rff, "Ford-Fulkerson Scaling", inst);
				resultsToFile(rpr, "Push-Relabel", inst);
				resultsToFile(rfpr, "FIFO Push-Relabel", inst);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
