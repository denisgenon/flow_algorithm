# Uncomment the following line to install ggplot2, install only one time the package!
# install.packages("ggplot2")
library('ggplot2')
results <- read.csv(file="results_linkedlist.csv", head=TRUE, sep=",")
attach(results)
ggplot() + 
  geom_line(data = results, aes(x = Instances, y = FF, color = 'FF')) +
  geom_line(data = results, aes(x = Instances, y = EK, color = 'EK'))  +
  geom_line(data = results, aes(x = Instances, y = PR, color = 'PR'))  +
  xlab('intensity') +
  ylab('time (ms)') +
  labs(color="Solver")