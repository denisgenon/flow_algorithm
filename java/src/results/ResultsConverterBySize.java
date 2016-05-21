package results;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ResultsConverterBySize {

	public static String[] solvers = {"Ford-Fulkerson Scaling","Edmonds-Karp","Highest Label Push-Relabel"};

	public static void resultsToFile(String [][] results, String title, int inst) throws IOException{
		
		File f = new File ("results/resultsBySize/resultsBySolver/"+title+inst+".csv");
		FileWriter fw = new FileWriter(f);
		fw.write("Instances, LinkedList, HashMap, SplitArray, TreeMap, SparseMap\n");
		for(int i=0; i<=8; i++){
			fw.write(((i*500)+1000)+", "+results[0][i]+", "+results[1][i]
					+", "+results[2][i]+", "+results[3][i]+", "+results[4][i]+"\n");
		}
		fw.close();
	}
	
	public static void getResultsBySolver(){
		for(int inst=1; inst<=10; inst++){
			String [][] rff = new String [5][9];
			String [][] rek = new String [5][9];
			String [][] rhlpr = new String [5][9];
			File f = new File("results/resultsBySize/resultsOriented"+inst+".txt");
			BufferedReader br;
			try {
				br = new BufferedReader(new FileReader(f));

				for(int index=0; index<5; index++){
					for(int i=0; i<9; i++){
						rff[index][i]=br.readLine().split(" ")[3];
					}
				}
				for(int index=5; index<10; index++){
					for(int i=0; i<9; i++){
						rek[index-5][i]=br.readLine().split(" ")[3];
					}
				}
				for(int index=20; index<25; index++){
					for(int i=0; i<9; i++){
						rhlpr[index-20][i]=br.readLine().split(" ")[3];
					}
				}

				br.close();
				
				resultsToFile(rff, solvers[0], inst);
				resultsToFile(rek, solvers[1], inst);
				resultsToFile(rhlpr, solvers[2], inst);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void getMeanResultsBySolver(){
		for(String solver : solvers){
			try {
				double [] llmean = new double [9];
				double [] hmmean = new double [9];
				double [] samean = new double [9];
				double [] tmmean = new double [9];
				double [] smmean = new double [9];

				for(int inst=1; inst<=10; inst++){
					File f = new File ("results/resultsBySize/resultsBySolver/"+solver+inst+".csv");

					BufferedReader br = new BufferedReader(new FileReader(f));
					String line = br.readLine();
					// We skip the first line with data structure names
					line = br.readLine();

					int i=0;
					while(line!=null){
						String [] split = line.split(", ");
						llmean[i]+=Double.parseDouble(split[1]);
						hmmean[i]+=Double.parseDouble(split[2]);
						samean[i]+=Double.parseDouble(split[3]);
						tmmean[i]+=Double.parseDouble(split[4]);
						smmean[i]+=Double.parseDouble(split[5]);
						line = br.readLine();
						i++;
					}

					br.close();

				}

				for(int index=0; index<llmean.length; index++){
					llmean[index]/=10;
					hmmean[index]/=10;
					samean[index]/=10;
					tmmean[index]/=10;
					smmean[index]/=10;
				}

				File f = new File ("results/resultsBySize/resultsBySolver/"+solver+"mean.csv");
				FileWriter fw = new FileWriter(f);
				fw.write("Instances, LinkedList, HashMap, SplitArray, TreeMap, SparseMap\n");
				for(int i=0; i<=8; i++){
					fw.write(((i*500)+1000)+", "+llmean[i]+", "+hmmean[i]
							+", "+samean[i]+", "+tmmean[i]+", "+smmean[i]+"\n");
				}
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void getResultsByInstance(){

		for(int inst=1; inst<=10; inst++){

			double [][] bests = new double[3][9];
			int s=0;
			for(String title : solvers){
				File f = new File ("results/resultsBySize/resultsBySolver/"+title+inst+".csv");
				try {
					BufferedReader br = new BufferedReader(new FileReader(f));
					String line = br.readLine();
					// We skip the first line with data structure names
					line = br.readLine();

					double [] ll = new double [9];
					double [] hm = new double [9];
					double [] sa = new double [9];
					double [] tm = new double [9];
					double [] sm = new double [9];
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
					double [] mins = new double[9];
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
			File f = new File ("results/resultsBySize/resultsByInstance/instance"+inst+".csv");
			FileWriter fw;
			try {
				fw = new FileWriter(f);
				fw.write("Instances, FordFulkerson, EdmondsKarp, HighestLabelPushRelabel\n");
				for(int i=0; i<=8; i++){
					fw.write(((i*500)+1000)+", "+bests[0][i]+", "+bests[1][i]
							+", "+bests[2][i]+"\n");
				}
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void getMeanResultsByInstance(){

		double [] ff = new double [9];
		double [] ek = new double [9];
		double [] hlpr = new double [9];

		for(int i=1; i<=10; i++){
			File f = new File ("results/resultsBySize/resultsByInstance/instance"+i+".csv");
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
					hlpr[index]+=Double.parseDouble(split[3]);
					line = br.readLine();
					index++;
				}
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		for(int index=0; index<9; index++){
			ff[index]/=10.0;
			ek[index]/=10.0;
			hlpr[index]/=10.0;
		}
		
		File f = new File ("results/resultsBySize/resultsByInstance/Meaninstances.csv");
		FileWriter fw;
		try {
			fw = new FileWriter(f);
			fw.write("Instances, FordFulkerson, EdmondsKarp, HighestLabelPushRelabel\n");
			for(int i=0; i<=8; i++){
				fw.write(((i*500)+1000)+", "+ff[i]+", "+ek[i]
						+", "+hlpr[i]+"\n");
			}
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static void getAllResultsBySolver(){
		double [][] ff = new double [10][9];
		double [][] ek = new double [10][9];
		double [][] hlpr = new double [10][9];

		for(int i=1; i<=10; i++){
			File f = new File ("results/resultsBySize/resultsByInstance/instance"+i+".csv");
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
					hlpr[i-1][index]+=Double.parseDouble(split[3]);
					line = br.readLine();
					index++;
				}
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		for(int i=0; i<3; i++){
			double[][] data = new double[10][9];
			if(i==0) data=ff;
			else if(i==1) data=ek;
			else if(i==2) data=hlpr;

			File f = new File ("results/resultsBySize/resultsByInstance/instances"+solvers[i]+".csv");
			FileWriter fw;
			try {
				fw = new FileWriter(f);
				fw.write("Instances, inst1, inst2, inst3, inst4, inst5, inst6, inst7, inst8, inst9, inst10\n");
				for(int i2=0; i2<=8; i2++){
					fw.write(((i2*500)+1000)+", "+data[0][i2]+", "+data[1][i2]
							+", "+data[2][i2]+", "+data[3][i2]+", "+data[4][i2]
							+", "+data[5][i2]+", "+data[6][i2]+", "+data[7][i2]
							+", "+data[8][i2]+", "+data[9][i2]+"\n");
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
		getMeanResultsByInstance();
		getAllResultsBySolver();*/
		getMeanResultsBySolver();
	}
}
