package results;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ResultsConverter {

	public static String[] solvers = {"Ford-Fulkerson Scaling","Edmonds-Karp","Push-Relabel","FIFO Push-Relabel"};

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

	public static void getResultsBySolver(){
		for(int inst=4; inst<=4; inst++){
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


				resultsToFile(rek, solvers[0], inst);
				resultsToFile(rff, solvers[1], inst);
				resultsToFile(rpr, solvers[2], inst);
				resultsToFile(rfpr, solvers[3], inst);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void getResultsByInstance(){

		for(int inst=1; inst<=4; inst++){
			
			double [][] bests = new double[4][20];
			int s=0;
			for(String title : solvers){
				File f = new File ("results/resultsBySolver/"+title+inst+".csv");
				try {
					BufferedReader br = new BufferedReader(new FileReader(f));
					String line = br.readLine();
					// We skip the first line with data structure names
					line = br.readLine();

					double [] ll = new double [20];
					double [] hm = new double [20];
					double [] sa = new double [20];
					double [] tm = new double [20];
					double [] sm = new double [20];
					double llsum=0,hmsum=0, sasum=0, tmsum=0, smsum=0;

					int i=0;
					while(line!=null){
						String [] split = line.split(", ");
						ll[i]=Double.parseDouble(split[1]);
						hm[i]=Double.parseDouble(split[2]);
						sa[i]=Double.parseDouble(split[3]);
						tm[i]=Double.parseDouble(split[4]);
						sm[i]=Double.parseDouble(split[5]);

						llsum+=Double.parseDouble(split[1]);
						hmsum+=Double.parseDouble(split[2]);
						sasum+=Double.parseDouble(split[3]);
						tmsum+=Double.parseDouble(split[4]);
						smsum+=Double.parseDouble(split[5]);
						line = br.readLine();
						i++;
					}

					double min=Math.min(Math.min(Math.min(Math.min(llsum, hmsum), sasum), tmsum), smsum);
					double [] mins = new double[20];
					if(min==llsum) mins=ll;
					else if(min==hmsum) mins=hm;
					else if(min==sasum) mins=sa;
					else if(min==tmsum) mins=tm;
					else if(min==smsum) mins=sm;

					bests[s]=mins;
					s++;
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			File f = new File ("results/resultsByInstance/instance"+inst+".csv");
			FileWriter fw;
			try {
				fw = new FileWriter(f);
				fw.write("Instances, FordFulkerson, EdmondsKarp, PushRelabel, FIFOPushRelabel\n");
				for(int nbrInstance=5; nbrInstance<=100; nbrInstance+=5){
					fw.write(nbrInstance+", "+bests[0][(nbrInstance/5)-1]+", "+bests[1][(nbrInstance/5)-1]
							+", "+bests[2][(nbrInstance/5)-1]+", "+bests[3][(nbrInstance/5)-1]+"\n");
				}
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String [] args){
		//getResultsBySolver();
		getResultsByInstance();
	}
}
