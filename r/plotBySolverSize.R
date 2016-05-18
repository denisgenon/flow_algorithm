# Uncomment the following line to install ggplot2, install only one time the package!
#install.packages("ggplot2")
library('ggplot2')
instances=c(1,2,3,4,5,6,7,8,9,10)
solvers=c("Edmonds-Karp","FIFO Push-Relabel","Ford-Fulkerson Scaling","Push-Relabel","Highest Label Push-Relabel")

for (instance in instances){
  for (solver in solvers){
    f = paste(c("../java/results/resultsBySize/resultsBySolver/", solver, instance,".csv"), collapse="")
    title = paste(c(solver, " Instance ", instance), collapse="")
    filename = paste(c("../graphiques/resultsBySize/",title,".png"), collapse="")
    results <- read.csv(file=f, head=TRUE, sep=",")
    attach(results)
    ggplot() + 
      geom_line(data = results, aes(x = Instances, y = LinkedList, color = 'Linked List')) +
      geom_line(data = results, aes(x = Instances, y = HashMap, color = 'HashMap')) +
      geom_line(data = results, aes(x = Instances, y = SplitArray, color = 'Split Array')) +
      geom_line(data = results, aes(x = Instances, y = TreeMap, color = 'TreeMap')) +
      geom_line(data = results, aes(x = Instances, y = SparseMap, color = 'SparseMap')) +
      xlab('Number of vertices') +
      ylab('Time (ms)') +
      labs(color="Data structure") +
      ggtitle(title)
    ggsave(file=filename, width=10, height=5)
  }
}

