package com.jmullin
package drifter.entity

import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.utils.viewport.Viewport

trait Layer extends InputProcessor {
  val viewportSize: V2
  val autoCenter: Boolean
  var viewport: Viewport = _

  var visible: Boolean = true
  var active: Boolean = true

  def update(delta: Float): Unit
  def render(): Unit
  def dispose(): Unit = {

  }
}
