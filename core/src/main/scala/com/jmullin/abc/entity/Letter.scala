package com.jmullin.abc.entity

import com.badlogic.gdx.graphics.g2d.{Sprite, SpriteBatch}
import com.badlogic.gdx.physics.box2d._
import com.jmullin._
import com.jmullin.abc.Assets
import com.jmullin.drifter.Draw
import com.jmullin.drifter.entity.Entity2D

object Letter {
  def targetX(character: Char) = PhysicsWorld.chuteX(character.toLower-'a'+1.5f)
}

class Letter(var delay: Float, character: Char, world: World) extends Entity2D {
  import com.jmullin.abc.entity.Letter._

  val glyph = Assets.petMe18.getData.getGlyph(character)
  val sprite = new Sprite(Assets.petMe18.getRegion.getTexture,
    glyph.srcX, glyph.srcY, glyph.width, glyph.height)

  size.set(sprite.getWidth, sprite.getHeight)

  val bodyDef = new BodyDef
  bodyDef.`type` = BodyDef.BodyType.DynamicBody
  bodyDef.position.set(7f, 2.75f)
  bodyDef.linearDamping = 0.1f
  bodyDef.gravityScale = 0f

  val body = world.createBody(bodyDef)
  body.setActive(false)

  val shape = new PolygonShape()
  shape.setAsBox(max(sprite.getWidth, 1f)/150f, max(sprite.getHeight, 1f)/150f)

  val fixtureDef = new FixtureDef
  fixtureDef.shape = shape
  fixtureDef.density = 1f
  fixtureDef.filter.categoryBits = 2
  fixtureDef.filter.maskBits = 0

  val fixture = body.createFixture(fixtureDef)

  shape.dispose()

  var fade = 0f

  sealed trait State
  object Fixed extends State
  object Sliding extends State
  object Sorting extends State
  object Falling extends State
  object Leaving extends State

  var state: State = Fixed

  override def update(implicit delta: Float): Unit = {
    fade += (1f-fade)/10f

    if(body.getPosition.y < -6f) {
      world.destroyBody(body)
      if(character.isLetter) PhysicsWorld.liveCounts += character.toLower -> (PhysicsWorld.liveCounts(character.toLower)-1)
      remove()
    } else {
      state match {
        case Fixed =>
          delay -= delta
          if(delay <= 0) {
            delay = 0f
            body.setActive(true)
            body.setGravityScale(1f)
            updateCollisionSettings(3, 0)
            state = Sliding
          }
        case Sliding =>
          body.setLinearVelocity(min(body.getLinearVelocity.x + 0.02f, 1.8f), body.getLinearVelocity.y)
          if(body.getPosition.y <= 3f) {
            updateCollisionSettings(3, (-(character.toLower-'a'+1)).toShort)
            state = Sorting
          }
        case Sorting =>
          body.setLinearVelocity(max(body.getLinearVelocity.x - 0.25f, -2.5f), body.getLinearVelocity.y)

          if(character.isLetter && body.getPosition.x <= targetX(character)) {
            body.setTransform(targetX(character), body.getPosition.y, body.getAngle)
            body.setLinearVelocity(0, body.getLinearVelocity.y-5)
            updateCollisionSettings(0, 99)
            state = Falling
            PhysicsWorld.counts += character.toLower -> (PhysicsWorld.counts(character.toLower)+1)
            PhysicsWorld.letterGlow += character.toLower -> 1f
            PhysicsWorld.liveCounts += character.toLower -> (PhysicsWorld.liveCounts(character.toLower)+1)
          }
        case Falling =>
          body.setTransform(targetX(character), body.getPosition.y, body.getAngle)
          body.setLinearVelocity(0, body.getLinearVelocity.y)
          if(body.getPosition.y <= 2.2f) {
            state = Leaving
            updateCollisionSettings(3, 99)
          }
        case Leaving => Unit
      }
    }
  }

  def updateCollisionSettings(maskBits: Short, groupIndex: Short) {
    val filter = body.getFixtureList.get(0)
    val filterData = filter.getFilterData
    filterData.maskBits = maskBits
    filterData.groupIndex = groupIndex
    filter.setFilterData(filterData)
    body.getFixtureList.set(0, filter)
  }

  override def render(implicit batch: SpriteBatch): Unit = {
    sprite.setColor(C(0.5f+fade*0.5f, 1, 0.5f+fade*0.5f))
    sprite.setRotation(body.getAngle*180f/Math.PI.toFloat)
    Draw.sprite(sprite, toWorld(body.getPosition)-size/2f, size)
  }

  def scale = gameSize.minComponent/10f
  def toWorld(v: V2) = gameSize/2f + v*scale
}
