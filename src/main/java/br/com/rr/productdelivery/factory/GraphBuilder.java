package br.com.rr.productdelivery.factory;

import java.util.Set;

import br.com.rr.productdelivery.model.Graph;
import br.com.rr.productdelivery.model.Node;

public class GraphBuilder {
	
	private Graph graph;
	
	public GraphBuilder() {
		this.graph = new Graph();
	}
	
	public GraphBuilder addNode(String noStr) {
		Set<Node> nodes = this.graph.getNodes();
		
		String node1Name = noStr.charAt(0) + "";
		String node2Name = noStr.charAt(1) + "";
		Integer distance = new Integer(noStr.charAt(2)+"");
		
		Node n1 = nodes.stream().filter(n -> n.getName().equals(node1Name)).findFirst().orElse(null);
		Node n2 = nodes.stream().filter(n -> n.getName().equals(node2Name)).findFirst().orElse(null);
		
		if(n1 == null) n1 = new Node(node1Name);
		if(n2 == null) n2 = new Node(node2Name);
		
		this.graph.addNode(n1);
		this.graph.addNode(n2);
		
		n1.addDestination(n2, distance);
		
		return this;
	}
	
	public Graph build() {
		return this.graph;
	}

}
