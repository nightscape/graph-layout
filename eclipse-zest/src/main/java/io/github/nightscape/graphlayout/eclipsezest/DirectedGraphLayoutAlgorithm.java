/*******************************************************************************
 * Copyright 2005, CHISEL Group, University of Victoria, Victoria, BC, Canada.
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: The Chisel Group, University of Victoria
 *******************************************************************************/
package io.github.nightscape.graphlayout.eclipsezest;

import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.graph.DirectedGraph;
import org.eclipse.draw2d.graph.DirectedGraphLayout;
import org.eclipse.draw2d.graph.Edge;
import org.eclipse.draw2d.graph.Node;
import org.eclipse.zest.layouts.algorithms.AbstractLayoutAlgorithm;
import org.eclipse.zest.layouts.dataStructures.InternalNode;
import org.eclipse.zest.layouts.dataStructures.InternalRelationship;

public class DirectedGraphLayoutAlgorithm extends AbstractLayoutAlgorithm {

    public DirectedGraphLayoutAlgorithm(int styles) {
        super(styles);
    }

    public static int SWT_HORIZONTAL = 256;

    protected void applyLayoutInternal(InternalNode[] entitiesToLayout, InternalRelationship[] relationshipsToConsider, double boundsX, double boundsY, double boundsWidth, double boundsHeight) {
        HashMap mapping = new HashMap(entitiesToLayout.length);
        DirectedGraph graph = new DirectedGraph();
        int padding = 50;
        graph.setDefaultPadding(new Insets(padding, padding, padding, padding));
        for (int i = 0; i < entitiesToLayout.length; i++) {
            InternalNode internalNode = entitiesToLayout[i];
            Node node = new Node(internalNode);
            node.setSize(new Dimension(10, 10));
            mapping.put(internalNode, node);
            graph.nodes.add(node);
        }
        for (int i = 0; i < relationshipsToConsider.length; i++) {
            InternalRelationship relationship = relationshipsToConsider[i];
            Node source = (Node) mapping.get(relationship.getSource());
            Node dest = (Node) mapping.get(relationship.getDestination());
            Edge edge = new Edge(relationship, source, dest);
            graph.edges.add(edge);
        }
        DirectedGraphLayout directedGraphLayout = new DirectedGraphLayout();
        directedGraphLayout.visit(graph);

        for (Iterator iterator = graph.nodes.iterator(); iterator.hasNext(); ) {
            Node node = (Node) iterator.next();
            InternalNode internalNode = (InternalNode) node.data;
            // For horizontal layout transpose the x and y coordinates
            if ((layout_styles & SWT_HORIZONTAL) == SWT_HORIZONTAL) {
                internalNode.setInternalLocation(node.y, node.x);
            } else {
                internalNode.setInternalLocation(node.x, node.y);
            }
        }
        updateLayoutLocations(entitiesToLayout);
    }

    protected int getCurrentLayoutStep() {
        // TODO Auto-generated method stub
        return 0;
    }

    protected int getTotalNumberOfLayoutSteps() {
        // TODO Auto-generated method stub
        return 0;
    }

    protected boolean isValidConfiguration(boolean asynchronous, boolean continuous) {
        // TODO Auto-generated method stub
        return true;
    }

    protected void postLayoutAlgorithm(InternalNode[] entitiesToLayout, InternalRelationship[] relationshipsToConsider) {
        // TODO Auto-generated method stub

    }

    protected void preLayoutAlgorithm(InternalNode[] entitiesToLayout, InternalRelationship[] relationshipsToConsider, double x, double y, double width, double height) {
        // TODO Auto-generated method stub

    }

    public void setLayoutArea(double x, double y, double width, double height) {
        // TODO Auto-generated method stub

    }

}