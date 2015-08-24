package com.jmullin
package abc

import com.badlogic.gdx.backends.lwjgl.{LwjglApplication, LwjglApplicationConfiguration}

object Main extends App {
  val cfg = new LwjglApplicationConfiguration
  cfg.title = "abc"
  cfg.width = 1024
  cfg.height = 768
  //cfg.vSyncEnabled = false
  //cfg.foregroundFPS = 0
  //cfg.backgroundFPS = 0
  cfg.fullscreen = false
  cfg.resizable = false
  cfg.forceExit = true
  new LwjglApplication(Abc, cfg)
}
