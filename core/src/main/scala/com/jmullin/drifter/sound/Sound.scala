package com.jmullin
package drifter.sound

import com.badlogic.gdx.audio.{Music, Sound}

object Sound {
  var sfxOn = true
  var musicOn = true

  def play(s: Sound, v: Float=1f) = {
    if(sfxOn) s.play(v)
    s
  }

  def play(m: Music) = {
    m.setLooping(true)
    m.setVolume(1)
    if(musicOn) m.play()
    m
  }
}
