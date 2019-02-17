package exp1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * @author Rahul S Sharma
 */
public class BFS {

    private HashMap<String, ArrayList<String>> tree;
    String startNode;
    ArrayList<String> goalNode;

    public BFS(HashMap<String, ArrayList<String>> tree, String startNode, ArrayList<String> goalNode) {
        this.tree = tree;
        this.startNode = startNode;
        this.goalNode = goalNode;
    }
    
    private boolean compute(){
        
        if(this.startNode.equals(goalNode)){
            System.out.println("Goal Node Found!");
            System.out.println(startNode);
        }
        
        Queue<String> queue = new LinkedList<>();
        ArrayList<String> explored = new ArrayList<>();
        queue.add(this.startNode);
                
        while(!queue.isEmpty()){
            String current = queue.remove();
            if(this.goalNode.contains(current)) {
                explored.add(current);
                System.out.print(explored);
                return true;
            }
            else{
                if(tree.get(current).isEmpty()){
                    explored.add(current);
                    continue;
                }
                else
                    for(int i=0; i< tree.get(current).size(); i++)
                        queue.add(tree.get(current).get(i));        
            }
            explored.add(current);
        }
        
        return false;        
    }
    
    public static void main(String[] args) {
        ArrayList<String> a,b,c,d,e,f,g,h,i,j;
        a = new ArrayList<>();
        b = new ArrayList<>();
        c = new ArrayList<>();
        d = new ArrayList<>();
        e = new ArrayList<>();
        f = new ArrayList<>();
        g = new ArrayList<>();
        h = new ArrayList<>();
        i = new ArrayList<>();
        j = new ArrayList<>();
        a.add("B");a.add("C");a.add("D");
        b.add("E");b.add("F");b.add("G");
        c.add("H");
        f.add("I");
        g.add("J");
        
        HashMap<String, ArrayList<String>> graph = new HashMap<String, ArrayList<String>>();
        graph.put("A", a);      //Start Node
        graph.put("B", b);
        graph.put("C", c);
        graph.put("D", d);       //Leaf
        graph.put("E", e);       //Leaf
        graph.put("F", f);
        graph.put("G", g);       //Goal State
        graph.put("H", h);       //Leaf
        graph.put("I", i);       //Leaf & Goal State
        graph.put("J", j);       //Leaf
        
        //System.out.println(graph);
        
        Set set = graph.entrySet();
        Iterator it = set.iterator();

        System.out.println("Adjacency List for the graph: ");
        while (it.hasNext()) {
            Map.Entry m = (Map.Entry) it.next();
            System.out.println(m.getKey() + ":" + m.getValue());
        }
        
        System.out.println();
        
        ArrayList<String> goal = new ArrayList<>();
        goal.add("G");
        goal.add("I");
        
        BFS bfs = new BFS(graph,"A",goal);
        
        if(bfs.compute())
            System.out.println(" Is the Path Found from State to Goal state");
        else
            System.out.println("Path Not Found!");
        
    }
}