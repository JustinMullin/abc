package com.jmullin
package abc

import com.badlogic.gdx.graphics.Pixmap.Format
import com.badlogic.gdx.graphics.g2d.{BitmapFont, Sprite, TextureAtlas}
import com.badlogic.gdx.graphics.{Color, Pixmap, Texture}
import com.jmullin.drifter.assets.DrifterAssets

object Assets extends DrifterAssets {
  var abc: TextureAtlas = _

  var petMe10: BitmapFont = _
  var petMe12: BitmapFont = _
  var petMe18: BitmapFont = _
  var petMe24: BitmapFont = _
  var petMe32: BitmapFont = _

  val pixmap = new Pixmap(1, 1, Format.RGBA8888)
  pixmap.setColor(Color.WHITE)
  pixmap.fill()
  lazy val fill: Sprite = new Sprite(new Texture(pixmap))
}