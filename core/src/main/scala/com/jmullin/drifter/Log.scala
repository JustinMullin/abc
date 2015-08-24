package com.jmullin
package drifter

import com.badlogic.gdx.Gdx

object Log {
  def info(message: String) = {
    Gdx.app.debug("abc", message)
    println(message)
  }
}
