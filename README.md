ACME Super Deluxe Letter Counter 6.0
====================================

This fully automated letter counting machine comes with a full set of features, and is
ready out of the box to start counting your letters today.  Just feed your text in the
top hopper line by line (no more than one sentence at a time or the mechanism may jam),
and the ACME Super Deluxe Letter Counter 6.0 will collect your letters and deliver them
into its trademarked Count-O-Matic system.  Excess letters will be dropped neatly through
the bottom chute for easy cleanup.  It's that easy!

GIF of the sorter in action: [http://i.imgur.com/mf333PL.gifv](http://i.imgur.com/mf333PL.gifv)

To Run
------
'sbt run', or download and execute the JAR here: [ABC.jar](https://s3.amazonaws.com/JustinMullinMisc/ABC.jar)

Technical Notes
---------------
The ACME Super Deluxe Letter Counter 6.0 was written in Scala and uses the excellent
[LibGDX game development framework](https://libgdx.badlogicgames.com/) in conjunction
with [Box2D](http://box2d.org) for physics.

This is a prime example of
hurry-quick-get-something-anything-at-all-out-the-door coding here folks.  Please don't
look too carefully at the code.  It's gross, and may burn your eyes (in which case I
recommend rinsing with water and calling your physician if the stinging worsens or lasts for more
than 12 hours).

...okay fine, if you want to see the meat of the source, it's in [PhysicsWorld.scala](PhysicsWorld.scala) and [Letter.scala](Letter.scala).  Everything else is framework n' stuff I reuse for Ludum Dare and other game jams and little side projects.  =)
