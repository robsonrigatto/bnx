package br.com.rr.productdelivery.model;

import java.util.HashSet;
import java.util.Set;

public class Graph {
	
	private Set<Node> nodes = new HashSet<>();
    
    public void addNode(Node nodeA) {
        nodes.add(nodeA);
    }
    
    public Node getNode(String nodeName) {
    	return this.getNodes().stream().filter(n -> n.getName().equals(nodeName)).findFirst().orElse(null);
    }

	public Set<Node> getNodes() {
		return nodes;
	}
	public void setNodes(Set<Node> nodes) {
		this.nodes = nodes;
	}
}