package com.jmullin
package abc

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.graphics.Color
import com.jmullin.abc.entity.PhysicsWorld
import com.jmullin.drifter.{DrifterGame, DrifterScreen, Shaders}

class MainScreen(g: DrifterGame) extends DrifterScreen(g, Color.BLACK) {
  val ui = newLayer2D(gameSize, autoCenter = true)
  val world = newLayer2D(gameSize, autoCenter = true, Shaders.default)

  world.add(PhysicsWorld)

  override def keyDown(keycode: Int): Boolean = {
    keycode match {
      case Keys.ESCAPE => Gdx.app.exit()
      case _ => Unit
    }
    super.keyDown(keycode)
  }
}