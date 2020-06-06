package io.github.nightscape.graphlayout.eclipsezest

import io.github.nightscape.graphlayout.GraphLayouter
import io.github.nightscape.graphlayout.NodeLocation
import org.eclipse.zest.layouts.exampleStructures.{SimpleNode, SimpleRelationship}
import org.eclipse.zest.layouts.{LayoutEntity, LayoutRelationship, LayoutStyles}

class GraphLayouterEclipseZest extends GraphLayouter {

  override def layout[T](nodes: Seq[T], edges: Seq[(T, T)]): PartialFunction[T,NodeLocation] = {
    val simpleNodes = nodes.map(new SimpleNode(_))
    val labels = simpleNodes.map { n =>
      val labelNode = new SimpleNode(n)
      (labelNode, new SimpleRelationship(labelNode, n, false))
    }
    def findLabel(n: T): SimpleNode =
      labels.find(_._2.getDestinationInLayout.asInstanceOf[SimpleNode].getRealObject == n).get._1

    def findNode(p: T): LayoutEntity = simpleNodes.find(_.getRealObject == p).get

    def layoutRelationshipTransformer(edge: (T, T)): LayoutRelationship = {
      val source = findNode(edge._1)
      val target = findNode(edge._2)
      new SimpleRelationship(source, target, false)
    }

    val layout = new DirectedGraphLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING)
    val entitiesToLayout = (simpleNodes ++ labels.map(_._1)).toArray
    val edgeRelationShips = edges.map(layoutRelationshipTransformer).toArray
    val relationships = edgeRelationShips ++ labels.map(_._2)
    layout.applyLayout(entitiesToLayout.map(_.asInstanceOf[LayoutEntity]), relationships, 0, 0, 10000, 10000, false, false)

    simpleNodes.map { simpleNode =>
      val node = simpleNode.getRealObject.asInstanceOf[T]
      node -> NodeLocation(simpleNode.getX, simpleNode.getY)
    }.toMap
  }
}

