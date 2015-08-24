package com.jmullin
package drifter.enrich

class RichVector2(v: V2) {
  def +=(o: V2) = v.add(o)
  def +=(n: Float) = v.add(n, n)
  def -=(o: V2) = v.sub(o)
  def -=(n: Float) = v.sub(n, n)
  def *=(o: V2) = v.scl(o)
  def *=(n: Float) = v.scl(n, n)
  def /=(o: V2) = v.scl(1f/o.x, 1f/o.y)
  def /=(n: Float) = v.scl(1f/n, 1f/n)

  def +(o: V2) = v.cpy().add(o)
  def +(n: Float) = v.cpy().add(n, n)
  def -(o: V2) = v.cpy().sub(o)
  def -(n: Float) = v.cpy().sub(n, n)
  def *(o: V2) = v.cpy().scl(o)
  def *(n: Float) = v.cpy().scl(n, n)
  def /(o: V2) = v.cpy().scl(1f/o.x, 1f/o.y)
  def /(n: Float) = v.cpy().scl(1f/n, 1f/n)

  def left = v + V2(-1, 0)
  def right = v + V2(1, 0)
  def top = v + V2(0, 1)
  def bottom = v + V2(0, -1)
  def up = top
  def down = bottom
  def orthogonal = Seq(left, right, top, bottom)

  def abs = V2(math.abs(v.x), math.abs(v.y))
  def inverse = (v * -1).fixZeroes
  def fixZeroes = V2(if(v.x == 0.0f) 0.0f else v.x, if(v.y == 0.0f) 0.0f else v.y)

  def xx = V2(v.x, v.x)
  def yy = V2(v.y, v.y)

  def center(o: V2) = v + o/2f
  def midpoint(o: V2) = V2((v.x-o.x)*0.5f, (v.y-o.y)*0.5f)

  def min(x: Float, y: Float) = V2(math.min(v.x, x), math.min(v.y, y))
  def max(x: Float, y: Float) = V2(math.max(v.x, x), math.max(v.y, y))
  def floor = V2(math.floor(v.x).toFloat, math.floor(v.y).toFloat).fixZeroes
  def ceil = V2(math.ceil(v.x).toFloat, math.ceil(v.y).toFloat).fixZeroes
  def round = V2(math.round(v.x).toFloat, math.round(v.y).toFloat).fixZeroes

  def minComponent = math.min(math.abs(v.x), math.abs(v.y))
  def maxComponent = math.max(math.abs(v.x), math.abs(v.y))

  def manhattanTo(b: V2) = {
    val difference = (b-v).abs
    difference.x + difference.y
  }
}
