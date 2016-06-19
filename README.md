# Maximum Flow Problem

Greetings!
This is the code we (Victor Velghe & Denis Genon) provided for our master thesis at [UCL](https://www.uclouvain.be). 

The goal of this implementation if to provide a flexible and extensible environnement to solve the maximum flow problem. Feel free to contribute!

---

### How to use ?

You can import this project in your favorite java IDE, there is no dependances! An example of use (LinkedList representation with FordFulkersonScaling solver) can be found below:

```java
Graph g = new LinkedListGraph("path", true);
Solver s = new FordFulkersonScaling(g);
s.getResults();
```

Here is the different graph representations already implemented:

1. HashMapGraph
2. LinkedListGraph
3. SparseMaGraph
4. SplitArrayGraph
5. TreeMapGraph

Here is the principal solvers already implemented:

1. EdmondsKarp
2. FordFulkerson
3. FordFulkersonScaling
4. PushRelabel
5. FIFOPushRelabel
6. HighestLabelPushRelabel

---

### How to contribute ?

To add a graph representation, create a new class in *models* which implements the *Graph* interface.
To add a solver, create a new class in *solver* which implements the *Solver* interface.