package com.jmullin
package drifter

import com.badlogic.gdx.Game

abstract class DrifterGame extends Game {
  var showFps = false

  def clearHover(): Unit = {
    //getScreen.asInstanceOf[DrifterScreen].clearHover()
  }
}
