package com.jmullin
package abc

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.tools.texturepacker.TexturePacker

object Pack {
  def main(args: Array[String]) {
    val resourcePath: String = "image"
    val settings = new TexturePacker.Settings()
    settings.maxWidth = 1024
    settings.maxHeight = 1024
    settings.filterMin = Texture.TextureFilter.Nearest
    settings.filterMag = Texture.TextureFilter.Nearest
    settings.paddingX = 1
    settings.paddingY = 1
    TexturePacker.process(settings, resourcePath, "atlas", "abc")
  }
}