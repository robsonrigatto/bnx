package br.com.rr.productdelivery.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


import java.util.Set;

import br.com.rr.productdelivery.model.Node;
import br.com.rr.productdelivery.model.Path;

public class GraphService {
	
	public List<Path> getAllPaths(Node startNode, Node endNode) {
		Set<Node> blackList = new HashSet<>();
		blackList.add(startNode);
		List<Path> allPaths = this.getAllPaths(startNode, endNode, blackList);
		Collections.sort(allPaths);
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
		
		//paths = paths.stream().filter(p -> p.getNodes().contains(endNode)).collect(Collectors.toList());
		return paths;
	}

}
