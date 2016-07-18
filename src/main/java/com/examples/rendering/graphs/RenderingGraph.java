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

import com.mxgraph.util.mxCellRenderer;

public class RenderingGraph {

	public static void main(String[] args) {
		
		/*The vertex is a String and the Edge is an 
		 * implementation of DefaultEdge */
		UndirectedGraph<String, DefaultEdge> stringGraph = createStringGraph();
		
		/*now use JGraphX to render it.*/
		JGraphXAdapter<String, DefaultEdge> gAdapter = new 
				JGraphXAdapter<String, DefaultEdge>(stringGraph) ;
		
		
		
		BufferedImage image = mxCellRenderer.
				createBufferedImage(gAdapter, null, 2, Color.WHITE, true, null);
		
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

        String v1 = "v1";
        String v2 = "v2";
        String v3 = "v3";
        String v4 = "v4";

        // add the vertices
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);

        // add edges to create a circuit
        g.addEdge(v1, v2, new DefaultEdge()) ;
        g.addEdge(v2, v3, new DefaultEdge());
        g.addEdge(v3, v4, new DefaultEdge());
        g.addEdge(v4, v1, new DefaultEdge());
        
       
        return g;
    }

}
