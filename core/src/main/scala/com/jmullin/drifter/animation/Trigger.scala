package com.jmullin
package drifter.animation

import com.jmullin.drifter.entity.{Entity, Hook}

abstract class Trigger(f: => Unit) extends Hook {
  var next: Option[Trigger] = None

  def go()(implicit e: Entity) = {
    e.add(this)
    update(0, e)
    this
  }

  def execute()(implicit e: Entity) = {
    f
    next.foreach(_.go())
    this
  }

  def last: Trigger = next match {
    case Some(n) => n.last
    case None => this
  }

  def then(t: Trigger)(implicit e: Entity) = {
    last.next = Some(t)
    this
  }

  def label(s: String*): Trigger = {
    tags ++= s
    next.foreach(_.label(s:_*))
    this
  }
}