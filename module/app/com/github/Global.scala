package com.github

import scala.collection.JavaConversions.mapAsScalaMap
import scala.concurrent.Future
import org.apache.commons.lang3.StringUtils
import org.fusesource.jansi.Ansi.Color.GREEN
import org.fusesource.jansi.Ansi.Color.MAGENTA
import org.fusesource.jansi.Ansi.Color.RED
import org.fusesource.jansi.Ansi.Color.YELLOW
import org.fusesource.jansi.Ansi.ansi
import org.fusesource.jansi.AnsiConsole
import play.api.Application
import play.api.GlobalSettings
import play.api.Logger
import play.api.Play
import play.api.libs.json.Json
import play.api.mvc.RequestHeader
import play.api.mvc.Results.BadRequest
import play.api.mvc.Results.InternalServerError
import play.api.mvc.Results.NotFound
import play.api.mvc.WithFilters
import com.github.controllers.request.LoggingFilter


//
// $COVERAGE-OFF$
/**
 * NOTE: this class has to be in the default package
 * @see http://www.playframework.com/documentation/2.2.x/ScalaGlobal
 */
object Global extends WithFilters(LoggingFilter) with GlobalSettings {

  override def onHandlerNotFound(request: RequestHeader) = {
    Future.successful(NotFound("Path not found " + request.path))
  }

  override def onBadRequest(request: RequestHeader, error: String) = {
    Future.successful(BadRequest("Bad Request: " + error))
  }

  override def onError(request: RequestHeader, ex: Throwable) = {
    Logger.error("Error",ex)
    val json = Json.toJson(Map("error" -> ex.getCause().getMessage()))
    Future.successful(InternalServerError(json))
  }

  override def onStart(app: Application) {
    import scala.collection.JavaConversions._
    AnsiConsole.systemInstall()
    Logger.info("Starting...")
    //Display a banner only when booting as regular startup and not during JUnit testing on commandline or eclipse
    val startCommand = System.getProperty("sun.java.command")
    if (!(startCommand.contains("junit") || startCommand.contains("sbt.ForkMain"))) {
      //Print Banner in ANSI colors
      println(ansi.fg(MAGENTA).a("YEAH").reset())

      println(ansi.fg(YELLOW).a("Module JAR library details ..."))
      val resources = this.getClass().getClassLoader()
      val manifests = resources.getResources("META-INF/MANIFEST.MF")
      while (manifests.hasMoreElements) {
        val nextManifest = manifests.nextElement
        if (nextManifest.getFile.contains("module")) {
          val manifest = new java.util.jar.Manifest(nextManifest.openStream)
          val fattribs = manifest.getMainAttributes()
          fattribs.foreach { kv => // implicit java to scala conversion
            println(ansi.fg(RED).a(kv._1).fg(GREEN).a(" " + kv._2))
          }
        }
      }
      //println(ansi.reset)
    }
    // All done...
  }

  override def onStop(app: Application) {
    Logger.info("Shutdown")
  }


}
// $COVERAGE-ON$
