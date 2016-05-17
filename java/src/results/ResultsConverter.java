package results;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ResultsConverter {

	public static String[] solvers = {"Ford-Fulkerson Scaling","Edmonds-Karp","Push-Relabel","FIFO Push-Relabel","Highest Label Push-Relabel"};

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
		for(int inst=1; inst<=10; inst++){
			String [][] rff = new String [5][20];
			String [][] rek = new String [5][20];
			String [][] rpr = new String [5][20];
			String [][] rfpr = new String [5][20];
			String [][] rhlpr = new String [5][20];
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
				for(int index=20; index<25; index++){
					for(int i=0; i<20; i++){
						rhlpr[index-20][i]=br.readLine().split(" ")[3];
					}
				}

				br.close();

				resultsToFile(rff, solvers[0], inst);
				resultsToFile(rek, solvers[1], inst);
				resultsToFile(rpr, solvers[2], inst);
				resultsToFile(rfpr, solvers[3], inst);
				resultsToFile(rhlpr, solvers[4], inst);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void getResultsByInstance(){

		for(int inst=1; inst<=10; inst++){

			double [][] bests = new double[5][20];
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
				fw.write("Instances, FordFulkerson, EdmondsKarp, PushRelabel, FIFOPushRelabel, HighestLabelPushRelabel\n");
				for(int nbrInstance=5; nbrInstance<=100; nbrInstance+=5){
					fw.write(nbrInstance+", "+bests[0][(nbrInstance/5)-1]+", "+bests[1][(nbrInstance/5)-1]
							+", "+bests[2][(nbrInstance/5)-1]+", "+bests[3][(nbrInstance/5)-1]+", "+bests[4][(nbrInstance/5)-1]+"\n");
				}
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void getMeanResultsByInstance(){

		double [] ff = new double [20];
		double [] ek = new double [20];
		double [] pr = new double [20];
		double [] fpr = new double [20];
		double [] hlpr = new double [20];

		for(int i=1; i<=10; i++){
			File f = new File ("results/resultsByInstance/instance"+i+".csv");
			try {
				BufferedReader br = new BufferedReader(new FileReader(f));
				String line = br.readLine();
				// We skip the first line with data structure names
				line = br.readLine();
				int index=0;
				while(line!=null){
					String [] split = line.split(", ");
					ff[index]+=Double.parseDouble(split[1]);
					ek[index]+=Double.parseDouble(split[2]);
					pr[index]+=Double.parseDouble(split[3]);
					fpr[index]+=Double.parseDouble(split[4]);
					hlpr[index]+=Double.parseDouble(split[5]);
					line = br.readLine();
					index++;
				}
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		for(int index=0; index<20; index++){
			ff[index]/=10;
			ek[index]/=10;
			pr[index]/=10;
			fpr[index]/=10;
			hlpr[index]/=10;
		}
		
		File f = new File ("results/resultsByInstance/Meaninstances.csv");
		FileWriter fw;
		try {
			fw = new FileWriter(f);
			fw.write("Instances, FordFulkerson, EdmondsKarp, PushRelabel, FIFOPushRelabel, HighestLabelPushRelabel\n");
			for(int nbrInstance=5; nbrInstance<=100; nbrInstance+=5){
				fw.write(nbrInstance+", "+ff[(nbrInstance/5)-1]+", "+ek[(nbrInstance/5)-1]
						+", "+pr[(nbrInstance/5)-1]+", "+fpr[(nbrInstance/5)-1]+", "+hlpr[(nbrInstance/5)-1]+"\n");
			}
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static void getAllResultsBySolver(){
		double [][] ff = new double [10][20];
		double [][] ek = new double [10][20];
		double [][] pr = new double [10][20];
		double [][] fpr = new double [10][20];
		double [][] hlpr = new double [10][20];

		for(int i=1; i<=10; i++){
			File f = new File ("results/resultsByInstance/instance"+i+".csv");
			try {
				BufferedReader br = new BufferedReader(new FileReader(f));
				String line = br.readLine();
				// We skip the first line with data structure names
				line = br.readLine();
				int index=0;
				while(line!=null){
					String [] split = line.split(", ");
					ff[i-1][index]+=Double.parseDouble(split[1]);
					ek[i-1][index]+=Double.parseDouble(split[2]);
					pr[i-1][index]+=Double.parseDouble(split[3]);
					fpr[i-1][index]+=Double.parseDouble(split[4]);
					hlpr[i-1][index]+=Double.parseDouble(split[5]);
					line = br.readLine();
					index++;
				}
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		for(int i=0; i<5; i++){
			double[][] data = new double[10][20];
			if(i==0) data=ff;
			else if(i==1) data=ek;
			else if(i==2) data=pr;
			else if(i==3) data=fpr;
			else if(i==4) data=hlpr;

			File f = new File ("results/resultsByInstance/instances"+solvers[i]+".csv");
			FileWriter fw;
			try {
				fw = new FileWriter(f);
				fw.write("Instances, inst1, inst2, inst3, inst4, inst5, inst6, inst7, inst8, inst9, inst10\n");
				for(int nbrInstance=5; nbrInstance<=100; nbrInstance+=5){
					fw.write(nbrInstance+", "+data[0][(nbrInstance/5)-1]+", "+data[1][(nbrInstance/5)-1]
							+", "+data[2][(nbrInstance/5)-1]+", "+data[3][(nbrInstance/5)-1]+", "+data[4][(nbrInstance/5)-1]
							+", "+data[5][(nbrInstance/5)-1]+", "+data[6][(nbrInstance/5)-1]+", "+data[7][(nbrInstance/5)-1]
							+", "+data[8][(nbrInstance/5)-1]+", "+data[9][(nbrInstance/5)-1]+"\n");
				}
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	public static void main(String [] args){
		/*getResultsBySolver();
		getResultsByInstance();
		getMeanResultsByInstance();*/
		getAllResultsBySolver();
	}
}
