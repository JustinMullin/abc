package com.jmullin
package abc.entity

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.{GlyphLayout, SpriteBatch}
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.physics.box2d._
import com.jmullin.abc.Assets
import com.jmullin.abc.model.Text
import com.jmullin.drifter.Draw
import com.jmullin.drifter.entity.{Entity2D, EntityContainer2D}

import scala.collection.JavaConversions._

object PhysicsWorld extends Entity2D {
  val characterDelay = 0.05f
  val lineDelay = 6.5f

  depth = 10

  val world = new World(V2(0, -3f), true)

  var lines = Text.lines

  var counts = ('a' to 'z').map(_ -> 0).toMap
  var liveCounts = ('a' to 'z').map(_ -> 0).toMap

  var letterGlow = ('a' to 'z').map(_ -> 0f).toMap

  var releaseValves = Set[Body]()
  var releaseTime = 3f

  var flash = 0f

  var walls = List[(V2, V2, Body)]()

  wall(1, V2(10f, 2.5f), V2(7f, 2.5f), 0)
  wall(1, V2(-7f, 3.75f), V2(4.8f, 3.75f), 0)
  wall(1, V2(6.8f, 7f), V2(6.8f, 2.5f), 0)
  wall(1, V2(-6.8f, -3f), V2(-1f, -4.8f), 0)
  wall(1, V2(6.8f, -3f), V2(1f, -4.8f), 0)

  for(i <- 1 to 27) {
    wall(1, V2(chuteX(i), -2.5f), V2(chuteX(i), 2.5f), 0)
    wall(-i, V2(chuteX(i), 2.5f), V2(chuteX(i + 1), 2.5f), 0)
    releaseValves += wall(1, V2(chuteX(i), -2.5f), V2(chuteX(i + 1), -2.5f), 0)
  }

  def chuteX(i: Float) = -7f + i * (14f / 28f)

  def wall(i: Int, a: V2, b: V2, friction: Float) = {
    val bodyDef = new BodyDef
    bodyDef.`type` = BodyDef.BodyType.StaticBody

    val shape = new EdgeShape()
    shape.set(a, b)

    val body = world.createBody(bodyDef)

    val fixtureDef = new FixtureDef
    fixtureDef.shape = shape
    fixtureDef.density = 1f
    fixtureDef.friction = friction
    fixtureDef.filter.categoryBits = 1
    fixtureDef.filter.groupIndex = i.toShort

    body.createFixture(fixtureDef)

    walls ::= (a, b, body)

    shape.dispose()

    body
  }

  override def create(container: EntityContainer2D): Unit = {
    nextLine()
  }

  def nextLine(): Unit = {
    if(lines.size > 0 && lines.head.nonEmpty) {
      val (line :: remaining) = lines
      lines = remaining

      val totalDelay = (line.size+1)*characterDelay

      val layout = new GlyphLayout(Assets.petMe18, line, Color.WHITE, 11f*scale, 0, true)

      var runDelay = 0f

      var charI = 0

      for((run, y) <- layout.runs.zipWithIndex) {
        var x = -6.3f

        for(((glyph), i) <- run.glyphs.zipWithIndex) {
          val character = line(charI)

          x += glyph.xadvance/scale

          val letterX = x

          val thisDelay = runDelay

          delay(thisDelay+0.01f) {
            val letter = new Letter(totalDelay-thisDelay, character, PhysicsWorld.world)
            letter.body.setTransform(V2(glyph.xoffset, glyph.yoffset)/scale + V2(letterX, 4.8f-(y*Assets.petMe18.getLineHeight*1.1f)/scale) + (V2(glyph.width, glyph.height)/scale)/2f, 0)
            parent.get.add(letter)
          } go()

          runDelay += characterDelay
          charI += 1
        }

        charI += 1
      }

      delay(totalDelay) {
        flash = 1f
      } go()

      delay(totalDelay+lineDelay) {
        nextLine()
      } go()
    }
  }

  override def update(implicit delta: Float): Unit = {
    super.update(delta)

    flash *= 0.9f

    for(c <- 'a' to 'z') {
      letterGlow += c -> letterGlow(c)*0.9f

      if(liveCounts(c) >= 40) {
        releaseValves.foreach(_.setActive(false))
        releaseTime = 1f
      }
    }

    if(releaseTime > 0f) {
      releaseTime -= delta
      if(releaseTime <= 0f) {
        releaseValves.foreach(_.setActive(true))
      }
    }

    world.step(delta, 6, 2)
  }

  override def render(implicit batch: SpriteBatch): Unit = {
    implicit val _layer = layer.get

    Draw.rect(toWorld(V2(-7f, 3.75f)), toWorld(V2(14f, 3.25f)), C(0.15f+flash*0.7f, 0.15f+flash*0.7f, 0.25f+flash*0.75f))
    
    for(c <- 'a' to 'z') {
      val i = c-'a'
      Assets.petMe24.setColor(C(1-letterGlow(c), 1, 1-letterGlow(c)))
      Draw.string(c.toString, toWorld(V2(chuteX(i+1.5f), 3.1f)), Assets.petMe24, V2(0, 0))
      Draw.string(counts(c).toString, toWorld(V2(chuteX(i+1.5f), 3.4f)), Assets.petMe10, V2(0, 0))
    }

    Draw.shapes(ShapeRenderer.ShapeType.Line) { r =>
      r.setColor(Color.WHITE)

      for(wall <- walls; if wall._3.isActive) {
        r.line(toWorld(wall._1), toWorld(wall._2))
      }
    }
  }

  def scale = gameSize.minComponent/10f
  def toWorld(v: V2) = gameSize/2f + v*scale
}
