library('ggplot2')
solvers=c("Edmonds-Karp","FIFO Push-Relabel","Ford-Fulkerson Scaling","Push-Relabel","Highest Label Push-Relabel")

for (solver in solvers){
  f = paste(c("../java/results/resultsByMatching/resultsByInstance/instances", solver,".csv"), collapse="")
  title = paste(c(solver, " Instances"), collapse="")
  filename = paste(c("../graphiques/resultsByMatching/",title,".png"), collapse="")
  results <- read.csv(file=f, head=TRUE, sep=",")
  attach(results)
  ggplot() + 
    geom_line(data = results, aes(x = Instances, y = inst1, color = '1')) +
    geom_line(data = results, aes(x = Instances, y = inst2, color = '2')) +
    geom_line(data = results, aes(x = Instances, y = inst3, color = '3')) +
    geom_line(data = results, aes(x = Instances, y = inst4, color = '4')) +
    geom_line(data = results, aes(x = Instances, y = inst5, color = '5')) +
    xlab('Density of edges') +
    ylab('Time (ms)') +
    labs(color="Instances") +
    ggtitle(title)
  ggsave(file=filename, width=10, height=5)
}


