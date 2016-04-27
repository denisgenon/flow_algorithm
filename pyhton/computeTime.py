import sys
import os

for x in range(1,2):
	od = os.getcwd()
	os.chdir(od+'\..\java\\results')
	results = open('resultsNOUnique'+str(x)+'.txt', 'w')	
	os.chdir(od+'\..\java\\bin')

	instances = ["05","10","15","20","25","30","35","40","45","50","55","60","65","70","75","80","85","90","95","100"]
	solvers = ["PR","FF","EK"]
	graphs = ["LL","HM","SA","TM"]
	for solver in solvers:
		for graph in graphs:
			for instance in instances:
				i=0
				b=0
				while i<10:
					b+=int(os.popen('java -cp . flowAlgorithm.BatchMain '+solver+' '+graph+' ..\instances\instancesUniquePrct'+str(x)+'\instanceuniqueprct'+instance+'.txt '+'false').read())
					i+=1
				b/=10
				print(solver+' '+graph+' '+instance+' '+str(b))
				results.write(solver+' '+graph+' '+instance+' '+str(b)+'\n')

	results.close()
	os.chdir(od)
