package br.com.rr.productdelivery;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import br.com.rr.productdelivery.factory.GraphBuilder;
import br.com.rr.productdelivery.model.Graph;
import br.com.rr.productdelivery.model.Node;
import br.com.rr.productdelivery.model.Path;
import br.com.rr.productdelivery.service.GraphService;

public class Main {
	
	private static final GraphService _graphService;
	
	static {
		_graphService = new GraphService();
	}

	public static void main(String[] args) {
		GraphBuilder builder = new GraphBuilder();
		builder.addNode("AD4").addNode("DE1").addNode("EC8").addNode("CB2").addNode("BA6")
		.addNode("AC9").addNode("DF7").addNode("FC5").addNode("FE9")
		.addNode("BD3").addNode("FA3");
		Graph graph = builder.build();
		
		System.out.println("OUTPUT #1: " + getCostOutput(graph, "A-D-E"));
		System.out.println("OUTPUT #2: " + getCostOutput(graph, "A-F-E"));
		System.out.println("OUTPUT #3: " + getCostOutput(graph, "E-C-B"));
		System.out.println("OUTPUT #4: " + getCostOutput(graph, "B-D-F-E"));
		System.out.println("OUTPUT #5: " + getCostOutput(graph, "F-C"));
		System.out.println("OUTPUT #6: " + numberOfEdgesArriving(graph, "C"));
		System.out.println("OUTPUT #7: " + numberOfRoutesLessThanStops(graph, "B", "A", 5));
		//TODO 8 (implementar)
		System.out.println("OUTPUT #8: " + numberOfCircleRoutesByStops(graph, "A", 3));
		System.out.println("OUTPUT #9: " + costOfShortestRoute(graph, "A", "E"));
		System.out.println("OUTPUT #10: " + costOfShortestRoute(graph, "C", "E"));
		//TODO 11
		//TODO 12
		System.out.println("OUTPUT #11: " + numberOfDifferentRoutesLessThan(graph, "A", "B", 40));
		System.out.println("OUTPUT #12: " + numberOfDifferentRoutesLessThan(graph, "E", "D", 60));
		
	}
	
	private static String getCostOutput(Graph graph, String route) {
		String[] nodesStr = route.split("-");
		
		String startNode = nodesStr[0];
		String endNode = nodesStr[nodesStr.length - 1];
		
		Node start = graph.getNode(startNode);
		Node end = graph.getNode(endNode);
		
		List<Path> paths = _graphService.getAllPaths(start, end);
		paths = paths.stream().filter(p -> p.getNodes().size() == nodesStr.length - 1).collect(Collectors.toList());
		
		for(Path p : paths) {
			Boolean matches = true;
			
			for(int i = 0; i < p.getNodes().size(); i++) {
				if(!p.getNodes().get(i).getName().equals(nodesStr[i+1])) {
					matches = false;
					break;
				}
			}
			
			if(matches) {
				return p.getTotalDistance().toString();
			}
		}
		
		return "NO SUCH ROUTE";
	}
	
	private static Long numberOfEdgesArriving(Graph graph, String node) {
		return graph.getNodes().stream().filter(n -> 
			n.getAdjacentNodes().keySet().stream().filter
				(an -> an.getName().equals(node)).findFirst().orElse(null) != null
		).count();
	}

	private static Long numberOfRoutesLessThanStops(Graph graph, String startNode, String endNode, Integer stops) {
		Node start = graph.getNode(startNode);
		Node end = graph.getNode(endNode);
		
		List<Path> paths = _graphService.getAllPaths(start, end);
		return paths.stream().filter(p -> p.getNodes().size() <= (stops - 1)).count();
	}
	
	private static Long numberOfCircleRoutesByStops(Graph graph, String nodeString, Integer stops) {
		Node node = graph.getNode(nodeString);
		
		List<Path> paths = _graphService.getAllPaths(node, node);
		return paths.stream().filter(p -> p.getNodes().size() == stops - 1).count();
	}
	
	private static Integer costOfShortestRoute(Graph graph, String startNode, String endNode) {
		Node start = graph.getNode(startNode);
		Node end = graph.getNode(endNode);

		List<Path> paths = _graphService.getAllPaths(start, end);
		
		paths.sort(new Comparator<Path>() {

			@Override
			public int compare(Path o1, Path o2) {
				return Integer.valueOf(o1.getNodes().size()).compareTo(o2.getNodes().size());
			}
		});
		
		return paths.get(0).getTotalDistance();
	}
	
	private static Long numberOfDifferentRoutesLessThan(Graph graph, String startNode, String endNode, Integer maxValue) {
		Node start = graph.getNode(startNode);
		Node end = graph.getNode(endNode);

		List<Path> paths = _graphService.getAllPaths(start, end);
		
		return paths.stream().filter(p -> p.getTotalDistance() < maxValue).count();		
	}
}
