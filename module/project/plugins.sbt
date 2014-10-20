// Comment to get more information during initialization
logLevel := Level.Info

resolvers := Seq("Local Maven Repository2" at "file://" + Path.userHome.absolutePath + "/.m2/repository")

// The Typesafe repository 
resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += Classpaths.sbtPluginReleases

// Use the Play sbt plugin for Play projects
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.3.5")

//Use the latest version of sbt-native-packager plugin
addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "0.7.5")


//addSbtPlugin("org.scoverage" % "sbt-scoverage" % "0.99.7.1")
addSbtPlugin("com.sksamuel.scoverage" % "sbt-scoverage" % "0.95.9")