package flowAlgorithm;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

import interfaces.Graph;
import models.HashMapGraph;
import models.LinkedListGraph;
import models.SparseMapGraph;
import models.SplitArrayGraph;
import models.TreeMapGraph;
import solver.FIFOPushRelabel;
import solver.FIFOPushRelabelInit;
import solver.FordFulkersonScaling;
import solver.HighestLabelPushRelabel;
import solver.HighestLabelPushRelabelInit;
import solver.PushRelabel;
import solver.PushRelabelInit;
import solver.Solver;

public class Main {
	public static void main(String[] args) {

		if (args.length == 0) {
			System.out.println("no instance file");
		}
		else {
			try {
				if (args[0].equals("all")) {
					int i = 1;
					int[][] column1 = new int[10][20];
					int[][] column2 = new int[10][20];
					int[][] column3 = new int[10][20];
					int[][] column4 = new int[10][20];
					int[][] column5 = new int[10][20];
					int[][] column6 = new int[10][20];
					while (i <= 10) {
						File f = new File("results/counts/density_correct/inst_" + i + "_FIFOPR.csv");
						try (BufferedReader br = new BufferedReader(new FileReader(f))) {
							String line;
							int j = 0;
							while ((line = br.readLine()) != null) {
								if (j != 0) {
									System.out.println(i + " " + j);
									String[] tmp = line.split(",");
									column1[i-1][j-1] =  Integer.parseInt(tmp[3]);
								}
								j++;
							}
						}
						f = new File("results/counts/density_correct/inst_" + i + "_FIFOPRI.csv");
						try (BufferedReader br = new BufferedReader(new FileReader(f))) {
							String line;
							int j = 0;
							while ((line = br.readLine()) != null) {
								if (j != 0) {
									System.out.println(i + " " + j);
									String[] tmp = line.split(",");
									column2[i-1][j-1] =  Integer.parseInt(tmp[3]);
								}
								j++;
							}
						}
						f = new File("results/counts/density_correct/inst_" + i + "_PR.csv");
						try (BufferedReader br = new BufferedReader(new FileReader(f))) {
							String line;
							int j = 0;
							while ((line = br.readLine()) != null) {
								if (j != 0) {
									System.out.println(i + " " + j);
									String[] tmp = line.split(",");
									column3[i-1][j-1] =  Integer.parseInt(tmp[3]);
								}
								j++;
							}
						}
						f = new File("results/counts/density_correct/inst_" + i + "_PRI.csv");
						try (BufferedReader br = new BufferedReader(new FileReader(f))) {
							String line;
							int j = 0;
							while ((line = br.readLine()) != null) {
								if (j != 0) {
									System.out.println(i + " " + j);
									String[] tmp = line.split(",");
									column4[i-1][j-1] =  Integer.parseInt(tmp[3]);
								}
								j++;
							}
						}
						f = new File("results/counts/density_correct/inst_" + i + "_HLPR.csv");
						try (BufferedReader br = new BufferedReader(new FileReader(f))) {
							String line;
							int j = 0;
							while ((line = br.readLine()) != null) {
								if (j != 0) {
									System.out.println(i + " " + j);
									String[] tmp = line.split(",");
									column5[i-1][j-1] =  Integer.parseInt(tmp[3]);
								}
								j++;
							}
						}
						f = new File("results/counts/density_correct/inst_" + i + "_HLPRI.csv");
						try (BufferedReader br = new BufferedReader(new FileReader(f))) {
							String line;
							int j = 0;
							while ((line = br.readLine()) != null) {
								if (j != 0) {
									System.out.println(i + " " + j);
									String[] tmp = line.split(",");
									column6[i-1][j-1] =  Integer.parseInt(tmp[3]);
								}
								j++;
							}
						}
						i++;
					}
					double[] means1 = new double[20];
					double[] means2 = new double[20];
					double[] means3 = new double[20];
					double[] means4 = new double[20];
					double[] means5 = new double[20];
					double[] means6 = new double[20];
					i = 0;
					int j = 0;

					while (j < 20) {
						i=0;
						double tmp1 = 0.0;
						double tmp2 = 0.0;
						double tmp3 = 0.0;
						double tmp4 = 0.0;
						double tmp5 = 0.0;
						double tmp6 = 0.0;
						while (i < 10) {
							tmp1 += column1[i][j];
							tmp2 += column2[i][j];
							tmp3 += column3[i][j];
							tmp4 += column4[i][j];
							tmp5 += column5[i][j];
							tmp6 += column6[i][j];
							i++;
						}
						means1[j] = tmp1/10;
						means2[j] = tmp2/10;
						means3[j] = tmp3/10;
						means4[j] = tmp4/10;
						means5[j] = tmp5/10;
						means6[j] = tmp6/10;

						j++;
					}

					PrintWriter writer = new PrintWriter("results/counts/density_correct/mean_relabel.csv", "UTF-8");
					writer.println("Instances, FIFOPushRelabel, FIFOPushRelabelInit, PushRelabel, PushRelabelInit, HLPushRelabel, HLPushRelabelInit");
					int density = 5;
					int k = 0;
					while (density <= 100) {
						writer.println(density + "," + means1[k] + "," + means2[k] + "," + means3[k] + "," + means4[k] + "," + means5[k] + "," + means6[k] + "\n");
						density += 5;
						k++;
					}
					writer.close();
				}
				/*File directoryToScan = new File("results/counts/density");
					for(File f : directoryToScan.listFiles()){
						PrintWriter writer = new PrintWriter("results/counts/density_correct/"+f.getName(), "UTF-8");
						try (BufferedReader br = new BufferedReader(new FileReader(f))) {
						    String line;
						    int density = 0;
						    while ((line = br.readLine()) != null) {
						       if (density == 0) {
						    	   writer.println("Instances," + line);
						       }
						       else {
						    	   writer.println(density + "," + line);
						    	   br.readLine();
						       }

						       density = density + 5;
						    }
						}
						writer.close();
					}*/
				/*
					int i = 1;
					while (i <= 10) {
					File directoryToScan = new File("instances/instances/instancesUniquePrct" + i);
					PrintWriter writer = new PrintWriter("results/counts/density/inst_"+i+"_PR.csv", "UTF-8");
					writer.println("Saturing pushes, Non-saturing pushes, Relabels");
					PrintWriter writer1 = new PrintWriter("results/counts/density/inst_"+i+"_PRI.csv", "UTF-8");
					writer1.println("Saturing pushes, Non-saturing pushes, Relabels");
					PrintWriter writer2 = new PrintWriter("results/counts/density/inst_"+i+"_FIFOPR.csv", "UTF-8");
					writer2.println("Saturing pushes, Non-saturing pushes, Relabels");
					PrintWriter writer3 = new PrintWriter("results/counts/density/inst_"+i+"_FIFOPRI.csv", "UTF-8");
					writer3.println("Saturing pushes, Non-saturing pushes, Relabels");
					PrintWriter writer4 = new PrintWriter("results/counts/density/inst_"+i+"_HLPR.csv", "UTF-8");
					writer4.println("Saturing pushes, Non-saturing pushes, Relabels");
					PrintWriter writer5 = new PrintWriter("results/counts/density/inst_"+i+"_HLPRI.csv", "UTF-8");
					writer5.println("Saturing pushes, Non-saturing pushes, Relabels");
					int j = 1;
					for(File f : directoryToScan.listFiles()){
						Graph g = new SparseMapGraph(f.getPath(), true);
						Solver s = new PushRelabel(g);
						writer.println(s.getCount());
						System.out.print(".");

						Graph g1 = new SparseMapGraph(f.getPath(), true);
						Solver s1 = new PushRelabelInit(g1);
						writer1.println(s1.getCount());
						System.out.print(".");

						Graph g2 = new SparseMapGraph(f.getPath(), true);
						Solver s2 = new FIFOPushRelabel(g2);
						writer2.println(s2.getCount());
						System.out.print(".");

						Graph g3 = new SparseMapGraph(f.getPath(), true);
						Solver s3 = new FIFOPushRelabelInit(g3);
						writer3.println(s3.getCount());
						System.out.print(".");

						Graph g4 = new SparseMapGraph(f.getPath(), true);
						Solver s4 = new HighestLabelPushRelabel(g4);
						writer4.println(s4.getCount());
						System.out.print(".");

						Graph g5 = new SparseMapGraph(f.getPath(), true);
						Solver s5 = new HighestLabelPushRelabelInit(g5);
						writer5.println(s5.getCount());
						System.out.print(".");
						System.out.print(j + " .");
						j++;
					}
					writer.close();
					writer1.close();
					writer2.close();
					writer3.close();
					writer4.close();
					writer5.close();
					System.out.println(" " + i + " DONE!");
					i++;
					}
				 */
				else {
					/*
					System.out.println("Generic Init");
					Graph g = new SparseMapGraph(args[0], true);
					Solver s = new PushRelabelInit(g);
					s.getResults();

					System.out.println("Generic -");
					Graph g1 = new SparseMapGraph(args[0], true);
					Solver s1 = new PushRelabel(g1);
					s1.getResults();

					System.out.println("FIFO Init");
					Graph g2 = new SparseMapGraph(args[0], true);
					Solver s2 = new FIFOPushRelabelInit(g2);
					s2.getResults();

					System.out.println("FIFO -");
					Graph g3 = new SparseMapGraph(args[0], true);
					Solver s3 = new FIFOPushRelabel(g3);
					s3.getResults();

					System.out.println("HighL Init");
					Graph g4 = new SparseMapGraph(args[0], true);
					Solver s4 = new HighestLabelPushRelabelInit(g4);
					s4.getResults();

					System.out.println("HighL -");
					Graph g5 = new SparseMapGraph(args[0], true);
					Solver s5 = new HighestLabelPushRelabel(g5);
					s5.getResults();

					System.out.println("GENERIC");
					System.out.println("HM");
					Graph g = new HashMapGraph(args[0], true);
					Solver s = new PushRelabel(g);
					s.getResults();
					System.out.println("SM");
					Graph g1 = new SparseMapGraph(args[0], true);
					Solver s1 = new PushRelabel(g1);
					s1.getResults();
					System.out.println("LL");
					Graph g2 = new LinkedListGraph(args[0], true);
					Solver s2 = new PushRelabel(g2);
					s2.getResults();
					System.out.println("SA");
					Graph g3 = new SplitArrayGraph(args[0], true);
					Solver s3 = new PushRelabel(g3);
					s3.getResults();
					System.out.println("TM");
					Graph g4 = new TreeMapGraph(args[0], true);
					Solver s4 = new PushRelabel(g4);
					s4.getResults();

					System.out.println("FIFO");
					System.out.println("HM");
					Graph gg = new HashMapGraph(args[0], true);
					Solver ss = new FIFOPushRelabel(gg);
					ss.getResults();
					System.out.println("SM");
					Graph gg1 = new SparseMapGraph(args[0], true);
					Solver ss1 = new FIFOPushRelabel(gg1);
					ss1.getResults();
					System.out.println("LL");
					Graph gg2 = new LinkedListGraph(args[0], true);
					Solver ss2 = new FIFOPushRelabel(gg2);
					ss2.getResults();
					System.out.println("SA");
					Graph gg3 = new SplitArrayGraph(args[0], true);
					Solver ss3 = new FIFOPushRelabel(gg3);
					ss3.getResults();
					System.out.println("TM");
					Graph gg4 = new TreeMapGraph(args[0], true);
					Solver ss4 = new FIFOPushRelabel(gg4);
					ss4.getResults();

					System.out.println("HIGHEST LABEL");
					System.out.println("HM");
					Graph ggg = new HashMapGraph(args[0], true);
					Solver sss = new HighestLabelPushRelabel(ggg);
					sss.getResults();
					System.out.println("SM");
					Graph ggg1 = new SparseMapGraph(args[0], true);
					Solver sss1 = new HighestLabelPushRelabel(ggg1);
					sss1.getResults();
					System.out.println("LL");
					Graph ggg2 = new LinkedListGraph(args[0], true);
					Solver sss2 = new HighestLabelPushRelabel(ggg2);
					sss2.getResults();
					System.out.println("SA");
					Graph ggg3 = new SplitArrayGraph(args[0], true);
					Solver sss3 = new HighestLabelPushRelabel(ggg3);
					sss3.getResults();
					System.out.println("TM");
					Graph ggg4 = new TreeMapGraph(args[0], true);
					Solver sss4 = new HighestLabelPushRelabel(ggg4);
					sss4.getResults();*/

					/*
					String instance = "instances/instancesUniquePrct1/instanceuniqueprct100.txt";
					try {
					    Thread.sleep(10000);                 //1000 milliseconds is one second.
					} catch(InterruptedException ex) {
					    Thread.currentThread().interrupt();
					}

					Graph g = new HashMapGraph(instance, true);
					Solver s = new FordFulkersonScaling(g);
					s.getResults(); */

				}

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}


