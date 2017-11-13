package br.com.rr.productdelivery.model;

import java.util.LinkedList;
import java.util.List;

public class Path implements Comparable<Path> {

	private Node startNode;
	private List<Node> nodes;
	private Integer totalDistance;

	public Path(Node startNode) {
		this.startNode = startNode;
		this.totalDistance = 0;
		this.nodes = new LinkedList<>();
	}
	
	public void addNode(Node node, Integer distance) {
		this.getNodes().add(node);
		this.totalDistance += distance;
	}
	
	public Node getStartNode() {
		return startNode;
	}
	public void setStartNode(Node startNode) {
		this.startNode = startNode;
	}
	public List<Node> getNodes() {
		return nodes;
	}
	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}
	public Integer getTotalDistance() {
		return totalDistance;
	}
	public void setTotalDistance(Integer totalDistance) {
		this.totalDistance = totalDistance;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(this.startNode.toString());
		nodes.forEach(node -> sb.append(" -> ").append(node));
		return sb.toString();
	}

	@Override
	public int compareTo(Path o) {
		return this.totalDistance.compareTo(o.totalDistance);
	}
}
