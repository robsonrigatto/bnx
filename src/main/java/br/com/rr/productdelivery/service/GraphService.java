package br.com.rr.productdelivery.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


import java.util.Set;

import br.com.rr.productdelivery.comparator.SmallerCostComparator;
import br.com.rr.productdelivery.model.Node;
import br.com.rr.productdelivery.model.Path;

public class GraphService {
	
	public List<Path> getAllPaths(Node startNode, Node endNode) {
		List<Path> allPaths;
		
		if(startNode.equals(endNode)) {
			allPaths = getCirclePaths(startNode, endNode);
		} else {
			Set<Node> blackList = new HashSet<>();
			blackList.add(startNode);
			allPaths = this.getAllPaths(startNode, endNode, blackList);
		}
		
		Collections.sort(allPaths, new SmallerCostComparator());
		return allPaths;
	}

	private List<Path> getAllPaths(Node startNode, Node endNode, Set<Node> blackList) {
		List<Path> paths = new ArrayList<>();
		if(startNode.equals(endNode)) {
			paths.add(new Path(endNode));
			return paths;
		}
		
		blackList.add(startNode);
		Map<Node, Integer> adjacentNodes = startNode.getAdjacentNodes();
		
		Iterator<Entry<Node, Integer>> iterator = adjacentNodes.entrySet().iterator();
		while(iterator.hasNext()) {
			Entry<Node, Integer> entry = iterator.next();
			Node node = entry.getKey();
			Integer distance = entry.getValue();
			
			if(blackList.contains(node)) continue;
			
			List<Path> subpaths = this.getAllPaths(node, endNode, new HashSet<>(blackList));
			
			for(Path sp : subpaths) {
				Path path = new Path(startNode); path.addNode(node, distance);
				
				for(Node spNode : sp.getNodes()) {
					path.addNode(spNode, 0);
				}
				path.setTotalDistance(distance + sp.getTotalDistance());
				paths.add(path);
			}
		}
		
		return paths;
	}
	
	private List<Path> getCirclePaths(Node startNode, Node endNode) {
		List<Path> paths = new ArrayList<>();
		Map<Node, Integer> adjacentNodes = startNode.getAdjacentNodes();
		
		for(Entry<Node, Integer> an : adjacentNodes.entrySet()) {
			List<Path> subpaths = getAllPaths(an.getKey(), endNode);
			
			for(Path sp : subpaths) {
				Path newSp = new Path(startNode);
				List<Node> spNodes = sp.getNodes();
				spNodes.add(0, sp.getStartNode());
				
				newSp.setTotalDistance(sp.getTotalDistance() + an.getValue());
				newSp.setNodes(spNodes);
				
				paths.add(newSp);
				
			}
		}
		
		return paths;
	}
}
