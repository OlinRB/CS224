public class Main {
  public static void main(String argv[]) {
    testOne();
//  testTwo();
  }

  //-------------------------------------------------------
  // this is Figure 3.7

  public static void testOne() {
    Node n1 = new Node(1);
    Node n2 = new Node(2);
    Node n3 = new Node(3);
    Node n4 = new Node(4);
    Node n5 = new Node(5);
    Node n6 = new Node(6);
    Node n7 = new Node(7);

    Graph G = new Graph();
    G.addNode(n1);
    G.addNode(n2);
    G.addNode(n3);
    G.addNode(n4);
    G.addNode(n5);
    G.addNode(n6);
    G.addNode(n7);

    G.addEdge(n1, n4);
    G.addEdge(n1, n5);
    G.addEdge(n1, n7);
    G.addEdge(n2, n3);
    G.addEdge(n2, n5);
    G.addEdge(n2, n6);
    G.addEdge(n3, n4);
    G.addEdge(n3, n5);
    G.addEdge(n4, n5);
    G.addEdge(n5, n6);
    G.addEdge(n5, n7);
    G.addEdge(n6, n7);

    G.print();

    G.topoOrder();
  }

  //-------------------------------------------------------
  // this graph does not have a topological ordering

  public static void testTwo() {
    Node n1 = new Node(1);
    Node n2 = new Node(2);
    Node n3 = new Node(3);
    Node n4 = new Node(4);
    Node n5 = new Node(5);
    Node n6 = new Node(6);
    Node n7 = new Node(7);
    Node n8 = new Node(8);

    Graph G = new Graph();
    G.addNode(n1);
    G.addNode(n2);
    G.addNode(n3);
    G.addNode(n4);
    G.addNode(n5);
    G.addNode(n6);
    G.addNode(n7);
    G.addNode(n8);

    G.addEdge(n8, n1);
    G.addEdge(n1, n2);
    G.addEdge(n1, n7);
    G.addEdge(n2, n3);
    G.addEdge(n2, n5);
    G.addEdge(n3, n4);
    G.addEdge(n4, n5);
    G.addEdge(n5, n6);
    G.addEdge(n6, n7);
    G.addEdge(n7, n5);

    G.print();

    G.topoOrder();
  }
}
