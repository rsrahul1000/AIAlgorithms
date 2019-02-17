package exp1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

/**
 * @author Rahul S Sharma
 */
public class BFSgeneric {

    private HashMap<Character, ArrayList<Character>> tree;
    char startNode;
    ArrayList<Character> goalNode;

    public BFSgeneric(HashMap<Character, ArrayList<Character>> tree, char startNode, ArrayList<Character> goalNode) {
        this.tree = tree;
        this.startNode = startNode;
        this.goalNode = goalNode;
    }
    
    private boolean compute(){
        
        if(goalNode.contains(this.startNode)){
            System.out.println("Goal Node Found!");
            System.out.println(startNode);
            return true;
        }
        
        Queue<Character> queue = new LinkedList<>();
        ArrayList<Character> explored = new ArrayList<>();
        queue.add(this.startNode);
                
        while(!queue.isEmpty()){
            char current = queue.remove();
            if(this.goalNode.contains(current)) {
                explored.add(current);
                System.out.print("Path: "+explored);
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
        Scanner s = new Scanner(System.in);
        char parent,child;
        
        System.out.print("Enter number of total nodes: ");
        int totalNodes = s.nextInt();
        System.out.println();
        
        HashMap<Character,ArrayList<Character>> graph = new HashMap<>(totalNodes);
        ArrayList<Character> list[] = new ArrayList[totalNodes];
        
        //Initialize the children of each node to empty
        for(int i=0; i<totalNodes; i++){
            list[i] = new ArrayList<Character>();
        }
        
        //Make the tree
        for(int i=0; i<totalNodes; i++){
            System.out.print("Enter " + (i+1) +" parent node: ");
            parent = Character.toUpperCase(s.next().charAt(0));
            System.out.print("Enter number of child nodes of "+parent+": ");
            int childNodesNumber = s.nextInt();
            
            //If the parent node is the child node
            if(childNodesNumber==0)
                graph.put(parent, list[i]);    
            
            //to enter the child nodes of the parent
            for(int j=0; j< childNodesNumber; j++){
                System.out.print("Enter "+(j+1)+" child node of "+parent+": ");
                child = Character.toUpperCase(s.next().charAt(0));
                list[i].add(child);
                graph.put(parent,list[i]);
            }
        }
        
        //Goal Nodes
        System.out.print("\nEnter the number of goal nodes: ");
        int goal = s.nextInt();
        ArrayList<Character> goalNodes = new ArrayList<>(goal);
        for(int i=0; i<goal; i++){
            System.out.print("Enter "+(i+1)+" goal node: ");
            goalNodes.add(Character.toUpperCase(s.next().charAt(0)));
        }
        
        System.out.println("\nState Space: ");
        System.out.println(graph);
        
        System.out.println("Goal State: ");
        System.out.println(goalNodes);
        
        System.out.println();

        BFSgeneric bfs = new BFSgeneric(graph,'A',goalNodes);
        
        if(bfs.compute())
            System.out.println("\n Is the Path Found from Start State to Goal state using BFS");
        else
            System.out.println("Path Not Found!");
        
    }
}