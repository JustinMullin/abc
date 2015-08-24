package com.jmullin
package drifter

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.glutils.ShaderProgram

class ShaderSet(fragmentShaderName: String, vertexShaderName: String) {
  val vert = Gdx.files.internal(f"shader/$vertexShaderName.vert")
  val frag = Gdx.files.internal(f"shader/$fragmentShaderName.frag")

  var loadTime = 0l

  var program: ShaderProgram = _

  compile()

  def compile(): Unit = {
    program = new ShaderProgram(vert, frag)
    loadTime = System.currentTimeMillis()

    if(!program.isCompiled) {
      log(program.getLog)
    }
  }

  def refresh(): Unit = {
    if(vert.lastModified() > loadTime) {
      compile()
      log(f"Reloaded shader $fragmentShaderName / $vertexShaderName.")
    }
  }

  def init(): Unit = {}
  def tick(): Unit = {}
}
