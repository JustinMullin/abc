package com.jmullin
package drifter.animation

import com.badlogic.gdx.graphics.g2d.Sprite

case class Animation(sprites: Vector[Sprite]) {
  def frame(n: Float) = sprites(math.min(sprites.length-1, n*sprites.length).toInt)
}
