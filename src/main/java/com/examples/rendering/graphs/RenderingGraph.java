package com.examples.rendering.graphs;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import org.jgrapht.DirectedGraph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.graph.SimpleGraph;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.util.mxCellRenderer;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxStylesheet;

public class RenderingGraph {
	private static final String OUTPUT_FILE_PATH = "/home/MAC/Study/";

	public static void main(String[] args) {

		/*
		 * The vertex is a String and the Edge is an implementation of
		 * DefaultEdge
		 */
		// UndirectedGraph<String, DefaultEdge> stringGraph =
		// createStringGraph();
		DirectedGraph<String, DefaultEdge> stringGraph = createDirectedStringGraph();

		/* now use JGraphX to render it. */

		JGraphXAdapter<String, DefaultEdge> jgxAdapter = new JGraphXAdapter<String, DefaultEdge>(
				stringGraph);

		// mxGraph graphMx = jgxAdapter.getView().getGraph();

		mxGraph graphMx = createMxGraphUsingSamePositions();

		// mxGraph graphMx = new mxGraph() ;

		// Object defaultParent = graphMx.getDefaultParent() ;
		/* Insert vertices */
		// graphMx.insertVertex(defaultParent, "Start", "Start", 0.0, 0.0, 50.0,
		// 30.0, "rounded");
		// graphMx.insertVertex(defaultParent, "Ende", "Ende", 0.0, 0.0, 50.0,
		// 30.0, "rounded");

		/* Insert edge */
		// graphMx.insertEdge(defaultParent, null, "",
		// ((mxGraphModel)graphMx.getModel()).getCell("Start"),
		// ((mxGraphModel)graphMx.getModel()).getCell("Ende"));

		/*
		 * For your problem of visualizing all objects in Appian application,
		 * the CompactTreeLayout is suitable
		 */
		/*
		 * an explanation of compactTreeLayout is here:
		 * http://hci.stanford.edu/courses
		 * /cs448b/f09/lectures/CS448B-20091021-GraphsAndTrees.pdf
		 */
		/*
		 * vertical layout - the graph vertices will be structured like a Tree.
		 * All node will be layed out in vertical direction
		 */
		/*
		 * mxCompactTreeLayout customLayout = new mxCompactTreeLayout(graphMx,
		 * false);
		 */
		// circleLayout seems to be quite a good fit for your use-case
		mxCircleLayout customLayout = new mxCircleLayout(graphMx);

		// distance between each node
		// customLayout.setNodeDistance(80);

		// distnace between each level of tree
		// customLayout.setLevelDistance(10);

		mxIGraphLayout layout = customLayout;

		layout.execute(graphMx.getDefaultParent());

		/*
		 * mxGraph graphMx = new mxGraph();
		 * 
		 * graphMx.insertVertex(graphMx.getDefaultParent(), "Start", "Start",
		 * 0.0, 0.0, 50.0, 30.0, "rounded");
		 * graphMx.insertVertex(graphMx.getDefaultParent(), "Ende", "Ende", 0.0,
		 * 0.0, 50.0, 30.0, "rounded");
		 * 
		 * graphMx.insertEdge(graphMx.getDefaultParent(), null, "",
		 * ((mxGraphModel)graphMx.getModel()).getCell("Start"),
		 * ((mxGraphModel)graphMx.getModel()).getCell("Ende"));
		 * 
		 * mxIGraphLayout layout = new mxHierarchicalLayout(graphMx);
		 * layout.execute(graphMx.getDefaultParent());
		 * 
		 * BufferedImage image = mxCellRenderer.createBufferedImage(graphMx,
		 * null, 1, Color.WHITE, true, null); return image;
		 */

		BufferedImage image = mxCellRenderer.createBufferedImage(graphMx, null,
				1, Color.WHITE, true, null);

		try {
			ImageIO.write(image, "PNG",
					new File(OUTPUT_FILE_PATH + "graph.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static UndirectedGraph<String, DefaultEdge> createStringGraph() {
		UndirectedGraph<String, DefaultEdge> g = new SimpleGraph<String, DefaultEdge>(
				DefaultEdge.class);

		String v1 = "1";
		String v2 = "2";
		String v3 = "3";
		String v4 = "4";

		// add the vertices
		g.addVertex(v1);
		g.addVertex(v2);
		g.addVertex(v3);
		g.addVertex(v4);

		// add edges to create a circuit
		g.addEdge(v1, v2);
		g.addEdge(v2, v3);
		g.addEdge(v3, v4);
		g.addEdge(v4, v1);

		return g;
	}

	/* creates a directed graph */
	private static DirectedGraph<String, DefaultEdge> createDirectedStringGraph() {
		DirectedGraph<String, DefaultEdge> dirGraph = new SimpleDirectedGraph<String, DefaultEdge>(
				DefaultEdge.class);

		final String v1 = "v1";
		final String v2 = "v2";
		final String v3 = "v3";
		final String v4 = "v4";

		dirGraph.addVertex(v1);
		dirGraph.addVertex(v2);
		dirGraph.addVertex(v3);
		dirGraph.addVertex(v4);

		DefaultEdge edge = dirGraph.addEdge(v1, v2);

		dirGraph.addEdge(v2, v3);
		dirGraph.addEdge(v3, v4);

		return dirGraph;

	}

	/* This created a mxGraph and returns it */
	/**
	 * This method demonstrates using the JGraphX API to insert vertices and
	 * edges
	 * 
	 * @return
	 */
	public static mxGraph createMxGraph() {
		mxGraph graph = new mxGraph();

		/* we created a Stylesheet */
		mxStylesheet stylesheet = graph.getStylesheet();
		Hashtable<String, Object> style = new Hashtable<String, Object>();
		style.put(mxConstants.STYLE_FONTCOLOR, "#774400");
		stylesheet.putCellStyle("ROUNDED", style);

		graph.setStylesheet(stylesheet); // set the Stylesheet

		Object parent = graph.getDefaultParent();

		graph.getModel().beginUpdate();
		try {

			Object v1 = graph.insertVertex(parent, null, "Hello", 20, 20, 80,
					30, "ROUNDED");
			Object v2 = graph.insertVertex(parent, null, "World!", 200, 150,
					80, 30, "ROUNDED");
			Object v3 = graph.insertVertex(parent, null, "Alone", 200, 350, 80,
					30, "ROUNDED");
			Object e1 = graph.insertEdge(parent, null, "", v1, v2);
			Object e2 = graph.insertEdge(parent, null, "", v1, v3);
		} finally {
			// Updates the display
			graph.getModel().endUpdate();
		}

		return graph;
	}

	/* This method is not specifying the geometry anywhere.. */
	public static mxGraph createMxGraphUsingSamePositions() {
		mxGraph graph = new mxGraph();

		/* we created a Stylesheet */
		mxStylesheet stylesheet = graph.getStylesheet();
		Hashtable<String, Object> style = new Hashtable<String, Object>();
		style.put(mxConstants.STYLE_FONTCOLOR, "#774400");
		style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_DOUBLE_ELLIPSE);
		stylesheet.putCellStyle("ROUNDED", style);

		graph.setStylesheet(stylesheet); // set the Stylesheet

		Object parent = graph.getDefaultParent();

		/*
		 * note that we are specifying the same x and y co-ordinates for every
		 * vertex. And we are specifying different height and width.. so if you
		 * are using a layout algorithm, you need not care about writing the
		 * logic for x and y co-ordinates for each vertex.
		 */
		graph.getModel().beginUpdate();
		try {

			Object v1 = graph.insertVertex(parent, null, "Hello", 20, 20, 80,
					30, "ROUNDED");
			Object v2 = graph.insertVertex(parent, null, "World!", 20, 20, 120,
					30, "ROUNDED");
			Object v3 = graph.insertVertex(parent, null, "Alone", 20, 20, 120,
					30, "ROUNDED");
			Object e1 = graph.insertEdge(parent, null, "", v1, v2);
			Object e2 = graph.insertEdge(parent, null, "", v2, v3);
			Object e3 = graph.insertEdge(parent, null, "", v1, v3);

		} finally {
			// Updates the display
			graph.getModel().endUpdate();
		}

		return graph;
	}

}
