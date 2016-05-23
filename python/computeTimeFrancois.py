import sys
import os

var = input("Are you sure you want to reset the results? ")
if(var=="yes"):

	'''print('Tests for DAG launch')
	od = os.getcwd()
	os.chdir(od+'\..\\java\\results\\resultsFrancois')
	results = open('resultsDAG.txt', 'w')	
	os.chdir(od+'\..\\java\\bin')
	solvers = ["PR","EK"]
	graphs = ["SA"]
	
	for solver in solvers:
		print('-----'+solver+'-----')
		for graph in graphs:
			print('---'+graph+'---')
			for instance in range(100,500,100):
				print('--'+str(instance)+'--')
				i=0
				b=0
				while i<3:
					b+=int(os.popen('java -cp . flowAlgorithm.BatchMain '+solver+' '+graph+' ..\instances\DAG\dag'+str(instance)+'.txt '+'true').read())
					i+=1
				b/=3
				results.write(solver+' '+graph+' '+str(instance)+' '+str(b)+'\n')
		print('Tests for DAG done')
		
	results.close()
	os.chdir(od)'''

	
	print('Tests for PATH launch')
	od = os.getcwd()
	os.chdir(od+'\..\\java\\results\\resultsFrancois')
	results = open('resultsPATHfull.txt', 'w')	
	os.chdir(od+'\..\\java\\bin')
	solvers = ["PR","EK"]
	graphs = ["SA"]
	
	for solver in solvers:
		print('-----'+solver+'-----')
		for graph in graphs:
			print('---'+graph+'---')
			for instance in range(1000,51000,1000):
				print('--'+str(instance)+'--')
				i=0
				b=0
				while i<3:
					b+=int(os.popen('java -cp . flowAlgorithm.BatchMain '+solver+' '+graph+' ..\instances\PATH\path'+str(instance)+'.txt '+'true').read())
					i+=1
				b/=3
				results.write(solver+' '+graph+' '+str(instance)+' '+str(b)+'\n')
		print('Tests for PATH done')
		
	results.close()
	os.chdir(od)