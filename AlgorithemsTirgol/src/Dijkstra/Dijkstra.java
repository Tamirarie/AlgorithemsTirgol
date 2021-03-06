package Dijkstra;

import java.util.PriorityQueue;

public class Dijkstra {
	
	Vertex graph[];
	int start;
	static final int infinity = Integer.MAX_VALUE;
	
	public Dijkstra(Vertex[] ve, int start) {
		graph = ve;
		this.start = start;
		PriorityQueue<Vertex> q = new PriorityQueue<>();
		graph[start].distance = 0;
		q.add(graph[start]);
		
		while(!q.isEmpty()){
			int u = q.poll().id;
			graph[u].isVisited = true;
			for (Edge ed : graph[u].ed) {
				int v = ed.vetrex;
				if(!graph[v].isVisited){
					if(graph[v].distance > graph[u].distance + ed.weight){
						q.remove(graph[v]);
						graph[v].distance = graph[u].distance + ed.weight;
						graph[v].parent = u;
						q.add(graph[v]);
					}
				}
			}
		}
	}
	public double getDist(int vertex){
		return graph[vertex].distance;
	}
	
	public String getPath(int dest){
		if(dest>graph.length) return null;
		String ans = "" + dest;
		while(graph[dest].parent!=-1){
			ans = graph[dest].parent +"->" +ans;
			dest = graph[dest].parent;
		}
		if(dest==start) return ans;
		return null;
	}
	
	public static void main(String[] args) {
		Vertex ve [] = new Vertex[5];
		Edge ed[] = new Edge[5];
		
		ed[0] = new Edge(0, 5);
		ed[1] = new Edge(1, 6);
		ed[2] = new Edge(2, 7);
		ed[3] = new Edge(3, 8);
		ed[4] = new Edge(4, 9);
		
		ve[0] = new Vertex(0, new Edge[]{ed[1],ed[2]});
		ve[1] = new Vertex(1, new Edge[]{ed[0],ed[2]});
		ve[2] = new Vertex(2, new Edge[]{ed[1],ed[3]});
		ve[3] = new Vertex(3, new Edge[]{ed[2],ed[4]});
		ve[4] = new Vertex(4, new Edge[]{ed[3],ed[0]});
		Dijkstra s = new Dijkstra(ve, 0);
		System.out.println(s.getPath(4));
		System.out.println(s.getDist(4));
		
		Vertex v0 = new Vertex(0, null); 
		Vertex v1 = new Vertex(1, null); 
		Vertex v2 = new Vertex(2, null);
		Vertex v3 = new Vertex(3, null);
		Vertex v4 = new Vertex(4, null);
		Vertex v5 = new Vertex(5, null);
		v0.ed = new Edge[]{new Edge(1,7), new Edge(2,9), new Edge(5,14)};
		v1.ed = new Edge[]{new Edge(0,7), new Edge(2,10), new Edge(3,115)};
		v2.ed = new Edge[]{new Edge(0,9), new Edge(1, 10), new Edge(3,11), new Edge(5,2)};
		v3.ed = new Edge[]{new Edge(1,15), new Edge(2,11), new Edge(4, 6)};
		v4.ed = new Edge[]{new Edge(3,6), new Edge(5,9)};
		v5.ed = new Edge[]{new Edge(4,9), new Edge(2,2), new Edge(0,14)};
		Vertex[] vs = {v0,v1,v2,v3,v4,v5};
		Dijkstra s2 = new Dijkstra(vs, 0);
		System.out.println(s2.getPath(4));
		System.out.println(s2.getDist(4));
	}
}
