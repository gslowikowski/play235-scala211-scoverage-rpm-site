import Keys._
import sbt._
import sbt.Keys._
import com.typesafe.sbt.packager.Keys._
import com.typesafe.sbt.SbtNativePackager._
import com.typesafe.sbt.packager.archetypes._ 

scalaVersion := Common.scalaVersion

scalacOptions += "-feature"

resolvers += "Local Maven Repository" at "file://" + Path.userHome.absolutePath + "/.m2/repository"

target in (Compile, TwirlKeys.compileTemplates) := target.value / "src_managed" / "main"

autoScalaLibrary := true

crossPaths := false //Disable scala version in file path

packageArchetype.java_server  ++ Seq(
    // GENERAL LINUX PACKAGING 
    maintainer := "Patrick Sauts",
    packageSummary := "Platform App on Play framework",
    packageDescription := "Platform Server",
    defaultLinuxInstallLocation := "/opt",
    defaultLinuxLogsLocation := "/var/log"
) ++ Seq(
   // RPM SPECIFIC
    name in Rpm := Common.appName,
    rpmRelease := "1",
    rpmVendor := "redbookconnect",
    rpmUrl := {
    			val artifactory = "http://somewhere/"
  				if (Common.version.trim.contains("SNAPSHOT"))
    				Some(artifactory + "libs-snapshots-local/com/github/blah/")
  				else
    				Some(artifactory + "libs-releases-local/com/github/blah/")
				},
    rpmLicense := Some("Commercial"),
    serverLoading in Rpm := ServerLoader.SystemV
)

mappings in Universal ++= Seq(
  file("conf/application.conf") -> "conf/application.conf",
  file("conf/application-logger.xml") -> "conf/application-logger.xml",
  file("conf/prod-logger.xml") -> "conf/prod-logger.xml"
)

// -- Code Coverage

//instrumentSettings

publishArtifact in Test := false

parallelExecution in Test := true

ScoverageKeys.excludedPackages in ScoverageCompile := "<empty>;views.html.*;"
