library('ggplot2')
solvers=c("Edmonds-Karp","Ford-Fulkerson Scaling","Highest Label Push-Relabel")

for (solver in solvers){
  f = paste(c("../java/results/resultsBySize/resultsByInstance/instances", solver,".csv"), collapse="")
  title = paste(c(solver, " Instances"), collapse="")
  filename = paste(c("../graphiques/resultsBySize/",title,".png"), collapse="")
  results <- read.csv(file=f, head=TRUE, sep=",")
  attach(results)
  ggplot() + 
    geom_line(data = results, aes(x = Instances, y = inst1, color = '1')) +
    geom_line(data = results, aes(x = Instances, y = inst2, color = '2')) +
    geom_line(data = results, aes(x = Instances, y = inst3, color = '3')) +
    geom_line(data = results, aes(x = Instances, y = inst4, color = '4')) +
    geom_line(data = results, aes(x = Instances, y = inst5, color = '5')) +
    geom_line(data = results, aes(x = Instances, y = inst6, color = '6')) +
    geom_line(data = results, aes(x = Instances, y = inst7, color = '7')) +
    geom_line(data = results, aes(x = Instances, y = inst8, color = '8')) +
    geom_line(data = results, aes(x = Instances, y = inst9, color = '9')) +
    geom_line(data = results, aes(x = Instances, y = inst10, color = '10')) +
    xlab('Number of vertices') +
    ylab('Time (ms)') +
    labs(color="Instances") +
  ggsave(file=filename, width=10, height=5)
}


