import sys
import os

var = input("Are you sure you want to reset the results? ")
if(var=="yes"):
	for x in range(1,11):
		print('Tests Density for '+str(x)+' launch')
		od = os.getcwd()
		os.chdir(od+'\..\\java\\results\\resultsDenis')
		results = open('resultsDensity'+str(x)+'.txt', 'w')	
		os.chdir(od+'\..\\java\\bin')

		instances = ["05","10","15","20","25","30","35","40","45","50","55","60","65","70","75","80","85","90","95","100"]
		solvers = ["PR","PRI","FPR","FPRI","HLPR","HLPRI"]
		graphs = ["SM"]
		for solver in solvers:
			print('-----'+solver+'-----')
			for graph in graphs:
				print('---'+graph+'---')
				for instance in instances:
					print('--'+instance+'--')
					i=0
					b=0
					while i<3:
						b+=int(os.popen('java -cp . flowAlgorithm.BatchMain '+solver+' '+graph+' ..\instances\instancesUniquePrct'+str(x)+'\instanceuniqueprct'+instance+'.txt '+'true').read())
						i+=1
					b/=3
					results.write(solver+' '+graph+' '+instance+' '+str(b)+'\n')

		print('Tests for '+str(x)+' done')
		results.close()
		os.chdir(od)
		
	for x in range(1,11):
		print('Tests Size for '+str(x)+' launch')
		od = os.getcwd()
		os.chdir(od+'\..\\java\\results\\resultsDenis')
		results = open('resultsSize'+str(x)+'.txt', 'w')	
		os.chdir(od+'\..\\java\\bin')

		instances = ["05","10","15","20","25","30","35","40","45","50","55","60","65","70","75","80","85","90","95","100"]
		solvers = ["PR","PRI","FPR","FPRI","HLPR","HLPRI"]
		graphs = ["SM"]
		for solver in solvers:
			print('-----'+solver+'-----')
			for graph in graphs:
				print('---'+graph+'---')
				for instance in instances:
					print('--'+instance+'--')
					i=0
					b=0
					while i<3:
						b+=int(os.popen('java -cp . flowAlgorithm.BatchMain '+solver+' '+graph+' ..\instances\instancesSize'+str(x)+'\instancesize'+instance+'.txt '+'true').read())
						i+=1
					b/=3
					results.write(solver+' '+graph+' '+instance+' '+str(b)+'\n')

		print('Tests for '+str(x)+' done')
		results.close()
		os.chdir(od)
	
	for x in range(1,6):
		print('Tests Matching for '+str(x)+' launch')
		od = os.getcwd()
		os.chdir(od+'\..\\java\\results\\resultsDenis')
		results = open('resultsMatching'+str(x)+'.txt', 'w')	
		os.chdir(od+'\..\\java\\bin')

		instances = ["05","10","15","20","25","30","35","40","45","50","55","60","65","70","75","80","85","90","95","100"]
		solvers = ["PR","PRI","FPR","FPRI","HLPR","HLPRI"]
		graphs = ["SM"]
		for solver in solvers:
			print('-----'+solver+'-----')
			for graph in graphs:
				print('---'+graph+'---')
				for instance in instances:
					print('--'+instance+'--')
					i=0
					b=0
					while i<3:
						b+=int(os.popen('java -cp . flowAlgorithm.BatchMain '+solver+' '+graph+' ..\instances\instancesMatching'+str(x)+'\instancematching'+instance+'.txt '+'true').read())
						i+=1
					b/=3
					results.write(solver+' '+graph+' '+instance+' '+str(b)+'\n')

		print('Tests for '+str(x)+' done')
		results.close()
		os.chdir(od)