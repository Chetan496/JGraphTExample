package com.examples.rendering.graphs;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.model.mxGraphModel;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.util.mxCellRenderer;
import com.mxgraph.view.mxGraph;

public class RenderingGraph {

	public static void main(String[] args) {
		
		/*The vertex is a String and the Edge is an 
		 * implementation of DefaultEdge */
		UndirectedGraph<String, DefaultEdge> stringGraph = createStringGraph();
		
		/*now use JGraphX to render it.*/
		
		JGraphXAdapter<String, DefaultEdge> jgxAdapter = new 
				JGraphXAdapter<String, DefaultEdge>(stringGraph) ;
		
		mxGraph graphMx = jgxAdapter.getView().getGraph() ;
		
		//mxGraph graphMx = new mxGraph() ;
		
		//Object defaultParent = graphMx.getDefaultParent() ;
		/*Insert vertices */
		//graphMx.insertVertex(defaultParent, "Start", "Start", 0.0, 0.0, 50.0, 30.0, "rounded");
		//graphMx.insertVertex(defaultParent, "Ende", "Ende", 0.0, 0.0, 50.0, 30.0, "rounded");
		
		/*Insert edge */
		//graphMx.insertEdge(defaultParent, null, "", ((mxGraphModel)graphMx.getModel()).getCell("Start"), ((mxGraphModel)graphMx.getModel()).getCell("Ende"));

		mxIGraphLayout layout = new mxHierarchicalLayout(graphMx );
		layout.execute(graphMx.getDefaultParent());

		
/*		mxGraph graphMx = new mxGraph();

		graphMx.insertVertex(graphMx.getDefaultParent(), "Start", "Start", 0.0, 0.0, 50.0, 30.0, "rounded");
		graphMx.insertVertex(graphMx.getDefaultParent(), "Ende", "Ende", 0.0, 0.0, 50.0, 30.0, "rounded");

		graphMx.insertEdge(graphMx.getDefaultParent(), null, "", ((mxGraphModel)graphMx.getModel()).getCell("Start"), ((mxGraphModel)graphMx.getModel()).getCell("Ende"));

		mxIGraphLayout layout = new mxHierarchicalLayout(graphMx);
		layout.execute(graphMx.getDefaultParent());

		BufferedImage image = mxCellRenderer.createBufferedImage(graphMx, null, 1, Color.WHITE, true, null);
		return image;*/

		
		
		
		BufferedImage image = mxCellRenderer.
				createBufferedImage(graphMx, null, 
						1, Color.WHITE, true, null);
		
		try {
			ImageIO.write(image, "PNG", new File("D:\\graph.png")) ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	
	private static UndirectedGraph<String, DefaultEdge> createStringGraph()
    {
        UndirectedGraph<String, DefaultEdge> g =
                new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);

        String v1 = "1";
        String v2 = "2";
        String v3 = "3" ;
        String v4 = "4" ;


        // add the vertices
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);


        // add edges to create a circuit
        g.addEdge(v1, v2) ;
        g.addEdge(v2, v3) ;
        g.addEdge(v3, v4) ;
        g.addEdge(v3, v1) ;
        g.addEdge(v4, v1) ;

       
        return g;
    }

}
