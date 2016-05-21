library('ggplot2')
types=c("density","size","matching")

for(type in types){
  if(type=="matching"){
    instances=c(1,2,3,4,5,"mean")
  }
  else {
    instances=c(1,2,3,4,5,6,7,8,9,10,"mean")
  }
  for (instance in instances){
    f = paste(c("../java/results/resultsDenis/", type, instance,".csv"), collapse="")
    title = paste(c(type, " Instance ", instance), collapse="")
    filename = paste(c("../graphiques/resultsDenis/",title,".png"), collapse="")
    results <- read.csv(file=f, head=TRUE, sep=",")
    attach(results)
    ggplot() + 
      geom_line(data = results, aes(x = Instances, y = PushRelabel, color = 'Generic Push-Relabel')) +
      geom_line(data = results, aes(x = Instances, y = PushRelabelInit, color = 'Generic Push-Relabel with init')) +
      geom_line(data = results, aes(x = Instances, y = FIFOPushRelabel, color = 'FIFO Push-Relabel')) +
      geom_line(data = results, aes(x = Instances, y = FIFOPushRelabelInit, color = 'FIFO Push-Relabel with init')) +
      geom_line(data = results, aes(x = Instances, y = HighestLabelPushRelabel, color = 'Highest Label Push-Relabel')) +
      geom_line(data = results, aes(x = Instances, y = HighestLabelPushRelabelInit, color = 'Highest Label Push-Relabel with ini')) +
      xlab('Density of edges') +
      ylab('Relative time') +
      labs(color="Algorithm") +
      ggtitle(title)
    ggsave(file=filename, width=10, height=5)
  }
}