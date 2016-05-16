library('ggplot2')


f = paste(c("../java/results/resultsByInstance/Meaninstances.csv"), collapse="")
title = paste(c("Mean Instances"), collapse="")
filename = paste(c("../graphiques/",title,".png"), collapse="")
results <- read.csv(file=f, head=TRUE, sep=",")
attach(results)
ggplot() + 
  geom_line(data = results, aes(x = Instances, y = FordFulkerson, color = 'Ford-Fulkerson')) +
  geom_line(data = results, aes(x = Instances, y = EdmondsKarp, color = 'Edmonds-Karp')) +
  geom_line(data = results, aes(x = Instances, y = PushRelabel, color = 'Push-Relabel')) +
  geom_line(data = results, aes(x = Instances, y = FIFOPushRelabel, color = 'FIFO Push-Relabel')) +
  geom_line(data = results, aes(x = Instances, y = HighestLabelPushRelabel, color = 'Highest Label Push-Relabel')) +
  xlab('Density of edges') +
  ylab('Time (ms)') +
  labs(color="Algorithm") +
  ggtitle(title)
ggsave(file=filename, width=10, height=5)

