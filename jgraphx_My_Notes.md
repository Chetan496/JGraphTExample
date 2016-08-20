## Notes on JGraphT and JGraphX: (My understanding of the library)

### About JGraphX:
JGraphX is a graph visualization library.


The official documentation for jGraph is at [here](https://jgraph.github.io/mxgraph/docs/manual_javavis.html#1.6.1)

JGraphX is version 6 of JGraph. Think of it as JGraph 6. So, JGraphX v1 = JGraph v6.
JGraphX is actually the Java flavor of mxGraph, it is licensed under the BSD clause.
Main package is `com.mxgraph.*` and main class is `mxGraph`

### Cell in JGraphX:
A cell can either be a vertex, an edge or a group.
You may group vertices or edges in one logical group.
Generally while interacting with the API, we interact via the cell. Eg: you may want to add a cell, remove a cell from a graph etc.

### Layouts in JGraphX: 
JGraphX supports tree, force-directed and hierarchical layouts.

### Key API methods:
`mxGraph.addVertex()`
`mxGraph.addEdge()`
`mxGraph.insertVertex()`
`mxGraph.insertEdge()`

### Core API Methods:
 1. `mxGraph.insertVertex(parent, id, value, x, y, width, height, style)` – creates and inserts a new vertex into the model, within a begin/end update call.
 2. `mxGraph.insertEdge(parent, id, value, source, target, style)` – creates and inserts a new edge into the model, within a begin/end update call.

 **parent** – the cell which is the immediate parent of the new cell in the group structure. We will address the group structure shortly, but for now use graph.getDefaultParent(); as your default parent, as used in the HelloWorld example.
 **id** – this is a global unique identifier that describes the cell, it is always a string. This is primarily for referencing the cells in the persistent output externally. If you do not wish to maintain ids yourself, pass null into this parameter and ensure that mxGraphModel.isCreateIds() returns true. This way the model will manage the ids and ensure they are unique.
 **value** – this is the user object of the cell. User object are simply that, just objects, but form the objects that allow you to associate the business logic of an application with the visual representation of JGraphX. They will be described in more detail later in this manual, however, to start with if you use a string as the user object, this will be displayed as the label on the vertex or edge.
 **x, y, width, height** – as the names suggest, these are the x and y position of the top left corner of the vertex and its width and height.
 **style** – the style description to be applied to this vertex. Styles will be described in more detail shortly, but at a simple level this parameter is a string that follows a particular format. In the string appears zero or more style names and some number of key/value pairs that override the global style or set a new style. Until we create custom styles, we will just use those currently available.

`mxCell` is the cell object for both vertices and edges.

`graph.getDefaultParent()` returns the default parent cell - which is an invisible cell with the same bounds as the graph.

### About Graph Layouts:
You can click on this [link](https://jgraph.github.io/mxgraph/docs/js-api/files/layout/mxCircleLayout-js.html) to know more about the different layouts used by jGraphX for laying out the graph vertices and edges.

### Customizing the shapes of the Vertices:
You can use custom shapes for the Vertices.
JGraphX allows using the Stencils from the Dia tool.
The Dia stencils are a pair of PNG and XML files with extension of .shape
This XML can be imported as a shape in JGraphX.
Example:

`String nodeXml = mxUtils.readFile(selectedFile.getAbsolutePath());
String name = ImportAction.addStencilShape(null, nodeXml, null);`

Then you can use it to customize the shape of a vertex. (Assuming that the name of the shape is "BPMN-Gateway"), you can use:
`style.put(mxConstants.STYLE_SHAPE, "BPMN-GATEWAY") ;`

