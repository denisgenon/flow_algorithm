library('ggplot2')


f = paste(c("../java/results/resultsBySize/resultsByInstance/Meaninstances.csv"), collapse="")
title = paste(c("Mean Instances"), collapse="")
filename = paste(c("../graphiques/resultsBySize/",title,".png"), collapse="")
results <- read.csv(file=f, head=TRUE, sep=",")
attach(results)
ggplot() + 
  geom_line(data = results, aes(x = Instances, y = FordFulkerson, color = 'Ford-Fulkerson with scaling')) +
  geom_line(data = results, aes(x = Instances, y = EdmondsKarp, color = 'Edmonds-Karp')) +
  geom_line(data = results, aes(x = Instances, y = HighestLabelPushRelabel, color = 'Highest Label Push-Relabel')) +
  xlab('Number of vertices') +
  ylab('Time (ms)') +
  labs(color="Algorithm") +
ggsave(file=filename, width=10, height=5)

