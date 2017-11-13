package br.com.rr.productdelivery.model;

import java.util.HashMap;
import java.util.Map;

public class Node {

	private String name;
    private Integer distance = Integer.MAX_VALUE;
    private Map<Node, Integer> adjacentNodes;
    
    public Node(String name) {
        this.name = name;
        this.adjacentNodes = new HashMap<>();
    }
    
    public void addDestination(Node destination, int distance) {
        adjacentNodes.put(destination, distance);
    }

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getDistance() {
		return distance;
	}
	public void setDistance(Integer distance) {
		this.distance = distance;
	}
	public Map<Node, Integer> getAdjacentNodes() {
		return adjacentNodes;
	}
	public void setAdjacentNodes(Map<Node, Integer> adjacentNodes) {
		this.adjacentNodes = adjacentNodes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return name;
	}
}