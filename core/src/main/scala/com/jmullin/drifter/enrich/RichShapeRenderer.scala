package com.jmullin
package drifter.enrich

import com.badlogic.gdx.graphics.glutils.ShapeRenderer

class RichShapeRenderer extends ShapeRenderer {
  def circle(v: V2, radius: Float): Unit = circle(v.x, v.y, radius)
  def circle(v: V2, radius: Float, n: Int): Unit = circle(v.x, v.y, radius, n)
}
