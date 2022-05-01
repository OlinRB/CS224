// CS224 Spring 2022
// jdh 4/9/22

import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class Graph {
  ArrayList<Node> nodes;

  //==============================================================

  public Graph() {
    this.nodes = new ArrayList<Node>();
  }

  //==============================================================

  public void addNode(Node n) {
    this.nodes.add(n);
  }

  //==============================================================

  public void addEdge(Node n1, Node n2, int capacity) {
    this.addEdge(n1, n2, capacity, 0);
  }

  //==============================================================

  public void addEdge(Node n1, Node n2, int capacity, int flow) {
    Edge e1 = new Edge(n1, n2, capacity, flow);
    assert(flow <= capacity);
    int idx1 = this.nodes.indexOf(n1);
    if (idx1 >= 0) {
      this.nodes.get(idx1).add(e1);
    } else {
      System.out.println("node " + n1.name + " not found in graph");
    }
  }

  //==============================================================

  private void addResidualEdge(Node n1, Node n2, int capacity, boolean backward) {
    Edge e1 = new Edge(n1, n2, capacity, backward);
    int idx1 = this.nodes.indexOf(n1);
    if (idx1 >= 0) {
      this.nodes.get(idx1).addResidualEdge(e1);
    } else {
      System.out.println("node " + n1.name + " not found in graph");
    }
  }

  //==============================================================

  public void print() {
    for (Node n: this.nodes) {
      System.out.print("Node " + n.name + ":");
      for (Edge edge: n.adjlist) {
        System.out.print(" " + edge.n2.name + " (c=" + edge.capacity);
        System.out.print(", f=" + edge.flow + ")");
      }
      System.out.println();
    }
  }

  //==============================================================

  private void printResidual() {
    for (Node n: this.nodes) {
      System.out.print("Node " + n.name + ":");
      for (Edge edge: n.adjlistResid) {
        System.out.print(" " + edge.n2.name + " (c=" + edge.capacity);
        if (edge.backward)
          System.out.print(" <=");
        System.out.print(")");
      }
      System.out.println();
    }
  }

  //=========================================================

  private ArrayList<Edge> findPathInResid(Node s, Node t) {
    int i, k, idx;
    boolean done, found;
    Node n1, n2;

    ArrayList<Edge> path = new ArrayList<Edge>();

    Stack<Node> stack = new Stack<Node>();
    boolean explored[] = new boolean[1 + this.nodes.size()];
    int parent[] = new int[1 + this.nodes.size()];

    for (i = 0; i <= this.nodes.size(); ++i)
      explored[i] = false;

    done = false;
    stack.push(s);
    while (!done && !stack.empty()) {
      n1 = stack.pop();
      if (!explored[n1.name]) {
        explored[n1.name] = true;
//P     System.out.println("explore: " + n1.name);
        if (parent[n1.name] != 0)
          System.out.println("tree: " + n1.name + " -> " + parent[n1.name]);
//P     System.out.println("set explored [" + n1.name + "] to true");
        for (Edge edge : n1.adjlistResid) {
          n2 = edge.n2;
          if (!explored[n2.name]) {
//P         System.out.println("add edge from " + n1.name + " to " + n2.name);
//P         System.out.println("discover: " + n2.name);
            stack.push(n2);
            parent[n2.name] = n1.name;
            if (n2.name == t.name)
              done = true;
//P       } else {
//P         System.out.println("have already explored " + n2.name);
          }
        }
      }
    }

    System.out.println("here's the backward path from " + t.name);
    done = false;
    idx = t.name;
    while (!done) {
      if (parent[idx] == 0)
        done = true;
      else {
        System.out.println(parent[idx] + " to " + idx);
        // find the edge from parent[idx] to idx
        found = false;
        k = 0;
        while (!found && k < nodes.size()) {
          if (nodes.get(k).name == parent[idx])
            found = true;
          else
            k = k + 1;
        }
        n1 = nodes.get(k);
        found = false;
        for (Edge e : n1.adjlistResid) {
          if (e.n2.name == idx) {
//P         System.out.println("found edge from " + parent[idx] + " to " + idx + " " + e);
            path.add(e);
            found = true;
          }
        }
        idx = parent[idx];
      }
    }

    System.out.println();
    return path;
  }

  //==============================================================

  public boolean checkFlow(Node s, Node t) {
    // check that flow out of s == flow into t
    // check conservation condition at each internal node -> For each node v other than s and t,
    // Make sure e1.flow == e2.flow
    for (Node node: nodes) {
        int inFlow = 0;
        int outFlow = 0;
        int sourceFlow = 0;
        int drainFlow = 0;

        // Add flows to in/out if node != s or t
        // Add flows to s and t
        // Check cap vs flow

        for (Edge edge: node.adjlist) {
            if (edge.n1 == node && (node != s || node != t))
                outFlow += edge.flow;
            if (edge.n2 == node && (node != s || node != t))
                inFlow += edge.flow;
            if (edge.n1 == s)
                sourceFlow += edge.flow;
            if (edge.n2 == t)
                drainFlow += edge.flow;
            if (edge.flow > edge.capacity)
                return false;
        }

//        System.out.print("Flow into: ");
//        System.out.print(inFlow);
//        System.out.print(" Flow out: ");
//        System.out.println(outFlow);
        // If flows dont match return false
        if (inFlow != outFlow)
          return false;
        if (sourceFlow != drainFlow)
          return false;
        // If flow exceed capacity return false
    }


    return true;
  } // checkFlow()

  //=========================================================

  private void constructResidualGraph() {
    // Clear adj resid
    for (Node node : nodes) {
      node.adjlistResid.clear();
    }
    // Add forward edges
    for (Node node : nodes) {
      for (Edge edge : node.adjlist) {
        if (edge.flow < edge.capacity) {
          // Add leftover units of cap
          Edge newEdge = new Edge(edge.n1, edge.n2, edge.capacity - edge.flow, false);
          node.addResidualEdge((new Edge(edge.n1, edge.n2, edge.capacity - edge.flow, false)));
          System.out.println(newEdge + "; create forward edge");
        }
        if (edge.flow > 0) {
          Edge newEdge = new Edge(edge.n2, edge.n1, edge.flow, true);
          node.addResidualEdge((new Edge(edge.n2, edge.n1, edge.flow, true)));
          System.out.println(newEdge + "; create backward edge");
        }
      }
    }
    printResidual();
  }// constructResidualGraph()

  //=========================================================

  private void constructResidualGraph(int delta) {
    // grad students should implement this for scaling F-F
  } // constructResidualGraph()

  //=========================================================

  private int findBottleneck(ArrayList<Edge> path) {
    
    int bottleNeckValue = Integer.MAX_VALUE;

    for (Edge edge: path) {
        if (!edge.backward)
          bottleNeckValue = Math.min(bottleNeckValue, edge.capacity);
    } 
    System.out.println("Bottlekneck Value is: " + bottleNeckValue);
    return bottleNeckValue;
  } // findBottleneck()

  //=========================================================

  private void augment(ArrayList<Edge> path) {
    /*
    augment(f,P)
    let b = bottleneck(P,f)
    for e = (u,v) in P
      if e = (u,v) is forward
        increase f(e) in G by b
      else (u,v) is backward let e = (v,u)
        decrease f(e) in G by b
      endif
    endfor
    return f
    */
    // Find bn
    int b = findBottleneck(path);
    Edge targetEdge = null;
    // Find e = (u, v)
    for (Edge edge1: path) {
      for (Edge edge2: edge1.n1.adjlist) {
        if (edge2.n2 == edge1.n2) {
          targetEdge = edge2;
        }
      }
      // Add if backwards
      if (!edge1.backward) {
        targetEdge.flow += b;
      }
      // Else subtract
      else {
        targetEdge.flow -= b;
      } 
    } 
  }

  //=========================================================

  public int maxFlow(Node s, Node t) {
    // s == source, t == sink

    // Set all e = 0
    for (Node node : nodes) {
      for (Edge edge: node.adjlist) {
        edge.flow = 0;
      }
    }
    // Construct resid
    constructResidualGraph();
    // Find P -> s-t path in resid
    ArrayList<Edge> P = findPathInResid(s,t);

    // While path exists from s-t in resid graph
    int i = 0;
    while (P.size() > 0) {
      ++i;
      // Augment
//      System.out.println("\n\nEdges before augment:");
//      for (Edge edge: P) {
//        System.out.println(edge);
//        System.out.println(edge.flow);
//      }
      augment(P);
      //System.out.println("\nEdges after augment:");
//      for (Edge edge: P) {
//        System.out.println(edge);
//        System.out.println(edge.flow);
//      }
//      System.out.println("Graph after augmenting");
      System.out.println("Here is the residual graph");
      print();
      System.out.println("----------------------------------");
      System.out.println("Building residual graph: ");
      constructResidualGraph();
      P = findPathInResid(s,t);
    }
    // Init flow
    int flow = 0;
    // Find flow
    for (Edge edge: s.adjlist) {
      flow += edge.flow;
    }
    System.out.println("max flow is " + flow);
    //System.out.println("\n\ninside loop\n\n");

    return flow;
  }
}