package com

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder.VertexInfo
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.scenes.scene2d.{Action, Actor}
import com.jmullin.drifter.ImmutableVector2
import com.jmullin.drifter.animation.Tween.Easing
import com.jmullin.drifter.animation.{Event, Timer, Tween}
import com.jmullin.drifter.enrich.{RichVector2, RichVector3}
import com.jmullin.drifter.entity.{ActorEntity, Entity2D}

import scala.math._
import scala.util.Random

package object jmullin {
  // GEOMETRY
  type Vector2 = com.badlogic.gdx.math.Vector2
  type V2 = Vector2
  def V2(xy: Float) = new V2(xy, xy)
  def V2(x: Float, y: Float) = new V2(x, y)
  def V2i(x: Float, y: Float) = new ImmutableVector2(x, y)

  type Vector3 = com.badlogic.gdx.math.Vector3
  type V3 = Vector3
  def V3(x: Float, y: Float, z: Float) = new V3(x, y, z)

  type V4 = (Vector3, Float)
  def V4(x: Float, y: Float, z: Float, u: Float) = (V3(x, y, z), u)

  type M3 = com.badlogic.gdx.math.Matrix3
  type M4 = com.badlogic.gdx.math.Matrix4

  type Rect = Rectangle
  def Rect(x: Float, y: Float, width: Float, height: Float) = new Rectangle(x, y, width, height)

  implicit def enrichVector2(v: V2) = new RichVector2(v)
  implicit def enrichVector3(v: V3) = new RichVector3(v)
  implicit def v2tov3(v: V2) = new Vector3(v.x, v.y, 0)

  val origin = new ImmutableVector2(0, 0)
  val one = new ImmutableVector2(1, 1)

  type C = Color
  def C(r: Float, g: Float, b: Float) = new Color(r, g, b, 1.0f)
  def Ca(r: Float, g: Float, b: Float, a: Float) = new Color(r, g, b, a)
  def Ci(i: Float) = new Color(i, i, i, 1.0f)

  val orthogonal = for(x <- -1 to 1; y <- -1 to 1; if abs(x)+abs(y)==1) yield V2(x, y)
  val adjacent = for(x <- -1 to 1; y <- -1 to 1; if abs(x)+abs(y)!=0) yield V2(x, y)

  def snap(v: V2, scale: Float=1f) = V2(math.floor(v.x/scale).toFloat, math.floor(v.y/scale).toFloat)

  // G3D

  def vx(v: V3, normal: V3, color: Color, uv: V2) = new VertexInfo().setPos(v).setNor(normal).setCol(color).setUV(uv)

  // ANIMATION

  def event(done: => Unit)(implicit e: Entity2D) = new Event(done)
  def delay(duration: Float)(done: => Unit)(implicit e: Entity2D) = new Timer(duration, done)
  def tween(duration: Float)(tick: Float => Unit)(implicit e: Entity2D) = new Tween(duration, tick, {})
  def tweenDone(duration: Float)(tick: Float => Unit)(done: => Unit)(implicit e: Entity2D) = new Tween(duration, tick, done)

  def moveTo(start: V2, end: V2, duration: Float, easing: Easing)(implicit e: Entity2D) = {
    tweenDone(duration) { n =>
      e.position.set(start+(end-start)*easing.interpolate(n))
    } {
      //e.position.set(end)
    }
  }

  // GDX

  def mouseX = Gdx.input.getX
  def mouseY = gameH - Gdx.input.getY-3
  def mouseV = V2(mouseX, mouseY)
  def rawMouseV = V2(Gdx.input.getX, Gdx.input.getY)
  def mouseVelocity = V2(Gdx.input.getDeltaX, Gdx.input.getDeltaY)
  def gameW = Gdx.graphics.getWidth
  def gameH = Gdx.graphics.getHeight
  def gameSize = V2(gameW, gameH)

  // SCENE2D

  def fAction(f: => Boolean) = new Action() {
    override def act(delta: Float) = { f }
  }

  implicit def fBoolToAction(f: () => Boolean): Action = fAction { f() }
  implicit def fUnitToAction(f: () => Unit): Action = fAction { f(); true }

  implicit def actorToEntity(a: Actor) = new ActorEntity(a)

  // MATH

  def cos(n: Float) = math.cos(n).toFloat
  def sin(n: Float) = math.sin(n).toFloat
  def tan(n: Float) = math.tan(n).toFloat
  def floor(n: Float) = math.floor(n).toFloat
  def ceil(n: Float) = math.ceil(n).toFloat
  def min(n: Float, m: Float) = math.min(m, n)
  def max(n: Float, m: Float) = math.max(m, n)
  def pow(n: Float, m: Float) = math.pow(m, n).toFloat

  // RANDOM
  lazy val rand = new Random(System.currentTimeMillis())

  def probability(p: Double) = rand.nextDouble() <= p
  def rInt(n: Int) = rand.nextInt(n)
  def rIntRange(n:Int, m:Int) = if(n == m) n else n+rand.nextInt(m-n)
  def rFloat(n: Float) = rand.nextFloat()*n
  def rFloatRange(n: Float, m: Float) = n+rand.nextFloat()*(m-n)
  def rElement[T](s: Iterable[T]) = rand.shuffle(s).head
  def rColor(n: Float, m: Float) = new Color(rFloatRange(n, m), rFloatRange(n, m), rFloatRange(n, m), 1.0f)

  // DEBUG

  def log(str: String) = Gdx.app.log("Vela", str)
}
