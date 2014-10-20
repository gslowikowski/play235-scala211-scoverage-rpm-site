import sbt._
import sbt.Keys._
import com.typesafe.sbt.packager.Keys._
import com.typesafe.sbt.SbtNativePackager._
import com.typesafe.sbt.packager.archetypes._
import java.text.SimpleDateFormat
import java.util.Date

object ApplicationBuild extends Build {
  import scala.sys.process._

  val gitRevision: String = {
    val gr = "git rev-parse HEAD".!!
    println("Git revision:" + gr)
    gr.trim()
  }

  //  val main = routesImport += "extensions.Binders._"

  val main = Project("module", file(".")).enablePlugins(play.PlayScala).settings(
    //resolvers := Seq("Local Maven Repository" at "file://" + Path.userHome.absolutePath + "/.m2/repository"),
    version := Common.version,
    libraryDependencies ++= Pom.dependencies(baseDirectory.value),
    //If version is SNAPSHOT append git revision +  current date YYYYMMDD.hhmmss 
    version in Rpm := {
      if (Common.version.trim.endsWith("SNAPSHOT"))
        Common.version.replaceAll("-", ".") + "." + new SimpleDateFormat("YYYYMMdd.HHmmss").format(new Date()) + "." + gitRevision
      else
        Common.version
    })
  //  override def rootProject = Some(root)

}
