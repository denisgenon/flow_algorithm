library('ggplot2')

f = paste(c("../java/results/resultsVictor/PRsSA.csv"), collapse="")
title = paste(c(type, " Instance ", instance), collapse="")
filename = paste(c("../graphiques/resultsVictor/PRsSA.png"), collapse="")
results <- read.csv(file=f, head=TRUE, sep=",")
attach(results)
ggplot() + 
  geom_line(data = results, aes(x = Instances, y = PushRelabel, color = 'Generic Push-Relabel')) +
  geom_line(data = results, aes(x = Instances, y = PushRelabelInit, color = 'Generic Push-Relabel with init')) +
  geom_line(data = results, aes(x = Instances, y = FIFOPushRelabel, color = 'FIFO Push-Relabel')) +
  geom_line(data = results, aes(x = Instances, y = FIFOPushRelabelInit, color = 'FIFO Push-Relabel with init')) +
  geom_line(data = results, aes(x = Instances, y = HighestLabelPushRelabel, color = 'Highest Label Push-Relabel')) +
  geom_line(data = results, aes(x = Instances, y = HighestLabelPushRelabelInit, color = 'Highest Label Push-Relabel with init')) +
  xlab('Density of edges') +
  ylab('Relative time') +
  labs(color="Algorithm") +
  ggsave(file=filename, width=10, height=5)

