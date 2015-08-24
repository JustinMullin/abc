package com.jmullin
package drifter.entity

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.utils.Drawable
import com.jmullin.drifter.Draw
import com.jmullin.abc.Assets

case class FillDrawable(c: Color) extends Drawable {
  override def draw(batch: Batch, x: Float, y: Float, width: Float, height: Float): Unit = {
    Assets.fill.setColor(c)
    Draw.sprite(Assets.fill, V2(x, y), V2(width, height))(batch)
  }

  override def setTopHeight(topHeight: Float): Unit = ???
  override def setLeftWidth(leftWidth: Float) {}
  override def getMinHeight = 0
  override def getRightWidth = 0
  override def getMinWidth = 0
  override def setMinWidth(minWidth: Float) {}
  override def setMinHeight(minHeight: Float) {}
  override def setRightWidth(rightWidth: Float) {}
  override def setBottomHeight(bottomHeight: Float) {}
  override def getTopHeight = 0
  override def getLeftWidth = 0
  override def getBottomHeight = 0
}
