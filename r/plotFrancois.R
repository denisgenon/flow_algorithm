library('ggplot2')

f = "../java/results/resultsFrancois/path.csv"
title = "PATH instance"
filename = "../java/results/resultsFrancois/path.png"
results <- read.csv(file=f, head=TRUE, sep=",")
attach(results)
ggplot() + 
  geom_line(data = results, aes(x = Instance, y = PushRelabel, color = 'Generic Push-Relabel')) +
  geom_line(data = results, aes(x = Instance, y = EdmondsKarp, color = 'Edmonds-Karp')) +
  ylab('Relative time') +
  labs(color="Algorithm") +
  ggsave(file=filename, width=10, height=5)

f = "../java/results/resultsFrancois/dag.csv"
title = "DAG instance"
filename = "../java/results/resultsFrancois/dag.png"
results <- read.csv(file=f, head=TRUE, sep=",")
attach(results)
ggplot() + 
  geom_line(data = results, aes(x = Instance, y = PushRelabel, color = 'Generic Push-Relabel')) +
  geom_line(data = results, aes(x = Instance, y = EdmondsKarp, color = 'Edmonds-Karp')) +
  ylab('Relative time') +
  labs(color="Algorithm") +
  ggsave(file=filename, width=10, height=5)
