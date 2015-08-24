resolvers += Resolver.url("scalasbt snapshots", new URL("http://repo.scala-sbt.org/scalasbt/sbt-plugin-snapshots"))(Resolver.ivyStylePatterns)

addSbtPlugin("com.hanhuy.sbt" % "android-sdk-plugin" % "1.3.23")

addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.6.0")

addSbtPlugin("org.roboscala" % "sbt-robovm" % "1.3.0-SNAPSHOT")

addSbtPlugin("com.typesafe.sbt" % "sbt-proguard" % "0.2.2")
