package com.jmullin
package drifter

import com.badlogic.gdx.math.{Matrix3, Interpolation}

class ImmutableVector2(_x: Float, _y: Float) extends Vector2(_x, _y) {
  override def set(v: Vector2): Vector2  = throw new Exception("Tried to modify an immutable vector")
  override def set(x: Float, y: Float): Vector2  = throw new Exception("Tried to modify an immutable vector")
  override def setZero(): Vector2  = throw new Exception("Tried to modify an immutable vector")
  override def sub(v: Vector2): Vector2  = throw new Exception("Tried to modify an immutable vector")
  override def sub(x: Float, y: Float): Vector2  = throw new Exception("Tried to modify an immutable vector")
  override def nor(): Vector2  = throw new Exception("Tried to modify an immutable vector")
  override def add(v: Vector2): Vector2  = throw new Exception("Tried to modify an immutable vector")
  override def add(x: Float, y: Float): Vector2  = throw new Exception("Tried to modify an immutable vector")
  override def dot(v: Vector2): Float  = throw new Exception("Tried to modify an immutable vector")
  override def dot(ox: Float, oy: Float): Float  = throw new Exception("Tried to modify an immutable vector")
  override def scl(scalar: Float): Vector2  = throw new Exception("Tried to modify an immutable vector")
  override def scl(x: Float, y: Float): Vector2  = throw new Exception("Tried to modify an immutable vector")
  override def scl(v: Vector2): Vector2  = throw new Exception("Tried to modify an immutable vector")
  override def mulAdd(vec: Vector2, scalar: Float): Vector2  = throw new Exception("Tried to modify an immutable vector")
  override def mulAdd(vec: Vector2, mulVec: Vector2): Vector2  = throw new Exception("Tried to modify an immutable vector")
  override def limit(limit: Float): Vector2  = throw new Exception("Tried to modify an immutable vector")
  override def clamp(min: Float, max: Float): Vector2  = throw new Exception("Tried to modify an immutable vector")
  override def mul(mat: Matrix3): Vector2  = throw new Exception("Tried to modify an immutable vector")
  override def rotate(degrees: Float): Vector2  = throw new Exception("Tried to modify an immutable vector")
  override def rotateRad(radians: Float): Vector2  = throw new Exception("Tried to modify an immutable vector")
  override def rotate90(dir: Int): Vector2  = throw new Exception("Tried to modify an immutable vector")
  override def lerp(target: Vector2, alpha: Float): Vector2  = throw new Exception("Tried to modify an immutable vector")
  override def interpolate(target: Vector2, alpha: Float, interpolator: Interpolation): Vector2  = throw new Exception("Tried to modify an immutable vector")
  override def setAngleRad(radians: Float): Vector2  = throw new Exception("Tried to modify an immutable vector")
  override def setAngle(degrees: Float): Vector2  = throw new Exception("Tried to modify an immutable vector")
}
