package com.jmullin
package abc

import com.badlogic.gdx.Gdx
import com.jmullin.drifter.DrifterGame

object Abc extends DrifterGame {
  override def create(): Unit = {
    Gdx.app.setLogLevel(3)

    Assets.load()
    Assets.finishLoading()
    Assets.populate()

    Gdx.app.debug("abc", Assets.fill.toString)

    setScreen(new MainScreen(this))
  }
}
