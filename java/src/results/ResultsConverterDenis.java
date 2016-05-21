package results;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ResultsConverterDenis {

	public static String[] solvers = {"PushRelabel","PushRelabelInit","FIFOPushRelabel","FIFOPushRelabelInit","HighestLabelPushRelabel","HighestLabelPushRelabelInit"};


	public static void resultstofileDensity(String [][] results, int instance) throws IOException{
		File f = new File ("results/resultsDenis/density"+instance+".csv");
		FileWriter fw = new FileWriter(f);
		fw.write("Instances, "+solvers[0]+", "+solvers[1]+", "+solvers[2]+", "+solvers[3]+", "+solvers[4]+", "+solvers[5]+"\n");
		for(int nbrInstance=5; nbrInstance<=100; nbrInstance+=5){
			fw.write(nbrInstance+", "+results[0][(nbrInstance/5)-1]+", "+results[1][(nbrInstance/5)-1]
					+", "+results[2][(nbrInstance/5)-1]+", "+results[3][(nbrInstance/5)-1]+", "+results[4][(nbrInstance/5)-1]
					+", "+results[5][(nbrInstance/5)-1]+"\n");
		}
		fw.close();
	}
	
	public static void resultstofileSize(String [][] results, int instance) throws IOException{
		File f = new File ("results/resultsDenis/size"+instance+".csv");
		FileWriter fw = new FileWriter(f);
		fw.write("Instances, "+solvers[0]+", "+solvers[1]+", "+solvers[2]+", "+solvers[3]+", "+solvers[4]+", "+solvers[5]+"\n");
		for(int i=0; i<=8; i++){
			fw.write(((i*500)+1000)+", "+results[0][i]+", "+results[1][i]
					+", "+results[2][i]+", "+results[3][i]+", "+results[4][i]
					+", "+results[5][i]+"\n");
		}
		fw.close();
	}
	
	public static void resultstofileMatching(String [][] results, int instance) throws IOException{
		File f = new File ("results/resultsDenis/matching"+instance+".csv");
		FileWriter fw = new FileWriter(f);
		fw.write("Instances, "+solvers[0]+", "+solvers[1]+", "+solvers[2]+", "+solvers[3]+", "+solvers[4]+", "+solvers[5]+"\n");
		for(int nbrInstance=5; nbrInstance<=100; nbrInstance+=5){
			fw.write(nbrInstance+", "+results[0][(nbrInstance/5)-1]+", "+results[1][(nbrInstance/5)-1]
					+", "+results[2][(nbrInstance/5)-1]+", "+results[3][(nbrInstance/5)-1]+", "+results[4][(nbrInstance/5)-1]
					+", "+results[5][(nbrInstance/5)-1]+"\n");
		}
		fw.close();
	}

	public static void getInstancesDensity(){
		for(int i=1; i<=10; i++){

			String [] pr = new String [20];
			String [] pri = new String [20];
			String [] fpr = new String [20];
			String [] fpri = new String [20];
			String [] hlpr = new String [20];
			String [] hlpri = new String [20];

			File f = new File("results/resultsDenis/resultsDensity"+i+".txt");
			BufferedReader br;
			try {
				br = new BufferedReader(new FileReader(f));
				for(int j=0; j<20; j++){
					pr[j]=br.readLine().split(" ")[3];
				}
				for(int j=0; j<20; j++){
					pri[j]=br.readLine().split(" ")[3];
				}
				for(int j=0; j<20; j++){
					fpr[j]=br.readLine().split(" ")[3];
				}
				for(int j=0; j<20; j++){
					fpri[j]=br.readLine().split(" ")[3];
				}
				for(int j=0; j<20; j++){
					hlpr[j]=br.readLine().split(" ")[3];
				}
				for(int j=0; j<20; j++){
					hlpri[j]=br.readLine().split(" ")[3];
				}
				br.close();

				String [][] prs = {pr,pri,fpr,fpri,hlpr,hlpri};

				resultstofileDensity(prs, i);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void getInstancesSize(){
		for(int i=1; i<=10; i++){

			String [] pr = new String [9];
			String [] pri = new String [9];
			String [] fpr = new String [9];
			String [] fpri = new String [9];
			String [] hlpr = new String [9];
			String [] hlpri = new String [9];

			File f = new File("results/resultsDenis/resultsSize"+i+".txt");
			BufferedReader br;
			try {
				br = new BufferedReader(new FileReader(f));
				for(int j=0; j<9; j++){
					pr[j]=br.readLine().split(" ")[3];
				}
				for(int j=0; j<9; j++){
					pri[j]=br.readLine().split(" ")[3];
				}
				for(int j=0; j<9; j++){
					fpr[j]=br.readLine().split(" ")[3];
				}
				for(int j=0; j<9; j++){
					fpri[j]=br.readLine().split(" ")[3];
				}
				for(int j=0; j<9; j++){
					hlpr[j]=br.readLine().split(" ")[3];
				}
				for(int j=0; j<9; j++){
					hlpri[j]=br.readLine().split(" ")[3];
				}
				br.close();

				String [][] prs = {pr,pri,fpr,fpri,hlpr,hlpri};

				resultstofileSize(prs, i);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void getInstancesMatching(){
		for(int i=1; i<=5; i++){

			String [] pr = new String [20];
			String [] pri = new String [20];
			String [] fpr = new String [20];
			String [] fpri = new String [20];
			String [] hlpr = new String [20];
			String [] hlpri = new String [20];

			File f = new File("results/resultsDenis/resultsMatching"+i+".txt");
			BufferedReader br;
			try {
				br = new BufferedReader(new FileReader(f));
				for(int j=0; j<20; j++){
					pr[j]=br.readLine().split(" ")[3];
				}
				for(int j=0; j<20; j++){
					pri[j]=br.readLine().split(" ")[3];
				}
				for(int j=0; j<20; j++){
					fpr[j]=br.readLine().split(" ")[3];
				}
				for(int j=0; j<20; j++){
					fpri[j]=br.readLine().split(" ")[3];
				}
				for(int j=0; j<20; j++){
					hlpr[j]=br.readLine().split(" ")[3];
				}
				for(int j=0; j<20; j++){
					hlpri[j]=br.readLine().split(" ")[3];
				}
				br.close();

				String [][] prs = {pr,pri,fpr,fpri,hlpr,hlpri};

				resultstofileMatching(prs, i);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void getMeanInstancesDensity(){
		double [] pr = new double [20];
		double [] pri = new double [20];
		double [] fpr = new double [20];
		double [] fpri = new double [20];
		double [] hlpr = new double [20];
		double [] hlpri = new double [20];


		for(int i=1; i<=10; i++){
			File f = new File ("results/resultsDenis/density"+i+".csv");
			try {
				BufferedReader br = new BufferedReader(new FileReader(f));
				String line = br.readLine();
				// We skip the first line with algorithme's name
				line = br.readLine();
				int index=0;
				while(line!=null){
					String [] split = line.split(", ");
					pr[index]+=Double.parseDouble(split[1]);
					pri[index]+=Double.parseDouble(split[2]);
					fpr[index]+=Double.parseDouble(split[3]);
					fpri[index]+=Double.parseDouble(split[4]);
					hlpr[index]+=Double.parseDouble(split[5]);
					hlpri[index]+=Double.parseDouble(split[6]);
					line = br.readLine();
					index++;
				}
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		for(int index=0; index<20; index++){
			pr[index]/=10;
			pri[index]/=10;
			fpr[index]/=10;
			fpri[index]/=10;
			hlpr[index]/=10;
			hlpri[index]/=10;
		}
		
		File f = new File ("results/resultsDenis/densityMean.csv");
		FileWriter fw;
		try {
			fw = new FileWriter(f);
			fw.write("Instances, "+solvers[0]+", "+solvers[1]+", "+solvers[2]+", "+solvers[3]+", "+solvers[4]+", "+solvers[5]+"\n");
			for(int nbrInstance=5; nbrInstance<=100; nbrInstance+=5){
				fw.write(nbrInstance+", "+pr[(nbrInstance/5)-1]+", "+pri[(nbrInstance/5)-1]
						+", "+fpr[(nbrInstance/5)-1]+", "+fpri[(nbrInstance/5)-1]
						+", "+hlpr[(nbrInstance/5)-1]+", "+hlpri[(nbrInstance/5)-1]+"\n");
			}
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void getMeanInstancesSize(){
		double [] pr = new double [9];
		double [] pri = new double [9];
		double [] fpr = new double [9];
		double [] fpri = new double [9];
		double [] hlpr = new double [9];
		double [] hlpri = new double [9];


		for(int i=1; i<=10; i++){
			File f = new File ("results/resultsDenis/size"+i+".csv");
			try {
				BufferedReader br = new BufferedReader(new FileReader(f));
				String line = br.readLine();
				// We skip the first line with algorithme's name
				line = br.readLine();
				int index=0;
				while(line!=null){
					String [] split = line.split(", ");
					pr[index]+=Double.parseDouble(split[1]);
					pri[index]+=Double.parseDouble(split[2]);
					fpr[index]+=Double.parseDouble(split[3]);
					fpri[index]+=Double.parseDouble(split[4]);
					hlpr[index]+=Double.parseDouble(split[5]);
					hlpri[index]+=Double.parseDouble(split[6]);
					line = br.readLine();
					index++;
				}
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		for(int index=0; index<9; index++){
			pr[index]/=10.0;
			pri[index]/=10.0;
			fpr[index]/=10.0;
			fpri[index]/=10.0;
			hlpr[index]/=10.0;
			hlpri[index]/=10.0;
		}
		
		File f = new File ("results/resultsDenis/sizeMean.csv");
		FileWriter fw;
		try {
			fw = new FileWriter(f);
			fw.write("Instances, "+solvers[0]+", "+solvers[1]+", "+solvers[2]+", "+solvers[3]+", "+solvers[4]+", "+solvers[5]+"\n");
			for(int i=0; i<=8; i++){
				fw.write(((i*500)+1000)+", "+pr[i]+", "+pri[i]+", "+fpr[i]+", "+fpri[i]+", "+hlpr[i]+", "+hlpri[i]+"\n");
			}
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void getMeanInstancesMatching(){
		double [] pr = new double [20];
		double [] pri = new double [20];
		double [] fpr = new double [20];
		double [] fpri = new double [20];
		double [] hlpr = new double [20];
		double [] hlpri = new double [20];


		for(int i=1; i<=5; i++){
			File f = new File ("results/resultsDenis/matching"+i+".csv");
			try {
				BufferedReader br = new BufferedReader(new FileReader(f));
				String line = br.readLine();
				// We skip the first line with algorithme's name
				line = br.readLine();
				int index=0;
				while(line!=null){
					String [] split = line.split(", ");
					pr[index]+=Double.parseDouble(split[1]);
					pri[index]+=Double.parseDouble(split[2]);
					fpr[index]+=Double.parseDouble(split[3]);
					fpri[index]+=Double.parseDouble(split[4]);
					hlpr[index]+=Double.parseDouble(split[5]);
					hlpri[index]+=Double.parseDouble(split[6]);
					line = br.readLine();
					index++;
				}
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		for(int index=0; index<20; index++){
			pr[index]/=5;
			pri[index]/=5;
			fpr[index]/=5;
			fpri[index]/=5;
			hlpr[index]/=5;
			hlpri[index]/=5;
		}
		
		File f = new File ("results/resultsDenis/matchingMean.csv");
		FileWriter fw;
		try {
			fw = new FileWriter(f);
			fw.write("Instances, "+solvers[0]+", "+solvers[1]+", "+solvers[2]+", "+solvers[3]+", "+solvers[4]+", "+solvers[5]+"\n");
			for(int nbrInstance=5; nbrInstance<=100; nbrInstance+=5){
				fw.write(nbrInstance+", "+pr[(nbrInstance/5)-1]+", "+pri[(nbrInstance/5)-1]
						+", "+fpr[(nbrInstance/5)-1]+", "+fpri[(nbrInstance/5)-1]
						+", "+hlpr[(nbrInstance/5)-1]+", "+hlpri[(nbrInstance/5)-1]+"\n");
			}
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String [] args){
		// Avoir un plot avec chaque algo pour chaque instance
		getInstancesDensity();
		getInstancesSize();
		getInstancesMatching();
		
		// Avoir un plot avec chaque algo pour la moyenne des instances 
		getMeanInstancesDensity();
		getMeanInstancesSize();
		getMeanInstancesMatching();
	}
}
