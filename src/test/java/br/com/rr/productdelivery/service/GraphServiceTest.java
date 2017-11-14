package br.com.rr.productdelivery.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.rr.productdelivery.builder.GraphBuilder;
import br.com.rr.productdelivery.model.Graph;
import br.com.rr.productdelivery.model.Node;
import br.com.rr.productdelivery.model.Path;

public class GraphServiceTest {

	private GraphService service;
	
	@Before
	public void before() {
		this.service = new GraphService();
	}
	
	@Test
	public void aToDTest() {
		Assert.assertNotNull(this.service);
		Graph graph = populatedGraph();
		Node nA = graph.getNode("A");
		Node nD = graph.getNode("D");
		List<Path> paths = this.service.getAllPaths(nA, nD);
		
		Assert.assertTrue(!paths.isEmpty());
		Assert.assertEquals(2, (int) paths.size());
		
		Assert.assertEquals(4, (int) paths.get(0).getTotalDistance()); //A -> D
		Assert.assertEquals(9 + 2 + 3, (int) paths.get(1).getTotalDistance()); //A -> C -> B -> D
	}
	
	@Test
	public void aToCTest() {
		Assert.assertNotNull(this.service);
		Graph graph = populatedGraph();
		Node nA = graph.getNode("A");
		Node nC = graph.getNode("C");
		List<Path> paths = this.service.getAllPaths(nA, nC);
		
		Assert.assertTrue(!paths.isEmpty());
		Assert.assertEquals(4, (int) paths.size());
		
		Assert.assertEquals(9, (int) paths.get(0).getTotalDistance()); //A -> C
		Assert.assertEquals(4 + 1 + 8, (int) paths.get(1).getTotalDistance()); //A -> D -> E -> C
		Assert.assertEquals(4 + 7 + 5, (int) paths.get(2).getTotalDistance()); //A -> D -> F -> C
		Assert.assertEquals(4 + 7 + 9 + 8, (int) paths.get(3).getTotalDistance()); //A -> D -> F -> E -> C
	}
	
	@Test
	public void aToATest() {
		Assert.assertNotNull(this.service);
		Graph graph = populatedGraph();
		Node nA = graph.getNode("A");
		List<Path> paths = this.service.getAllPaths(nA, nA);
		
		Assert.assertTrue(!paths.isEmpty());
		Assert.assertEquals(6, (int) paths.size());

		Assert.assertEquals(4 + 7 + 3, (int) paths.get(0).getTotalDistance()); //A -> D -> F -> A
		Assert.assertEquals(9 + 2 + 6, (int) paths.get(1).getTotalDistance()); //A -> C -> B -> A
		Assert.assertEquals(4 + 1 + 8 + 2 + 6, (int) paths.get(2).getTotalDistance()); //A -> D -> E -> C -> B -> A
		Assert.assertEquals(9 + 2 + 3 + 7 + 3, (int) paths.get(3).getTotalDistance()); //A -> C -> B -> D -> F -> A
		Assert.assertEquals(4 + 7 + 5 + 2 + 6, (int) paths.get(4).getTotalDistance()); //A -> D -> F -> C -> B -> A
		Assert.assertEquals(4 + 7 + 9 + 8 + 2 + 6, (int) paths.get(5).getTotalDistance()); //A -> D -> F -> E -> C -> B -> A
	}

	private Graph populatedGraph() {
		GraphBuilder builder = new GraphBuilder();
		builder.addNode("AD4").addNode("DE1").addNode("EC8").addNode("CB2").addNode("BA6")
		.addNode("AC9").addNode("DF7").addNode("FC5").addNode("FE9")
		.addNode("BD3").addNode("FA3");
		Graph graph = builder.build();
		return graph;
	}

}
