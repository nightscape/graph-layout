package io.github.nightscape.graphlayout

trait GraphLayouter {
  def layout[T](nodes: Seq[T], edges: Seq[(T, T)]): PartialFunction[T, NodeLocation]
}
case class NodeLocation(posX: Double, posY: Double)

