import sbt._
import Keys._

object Common {
  def localPomDir = new File(".")
  def parentPomDir = new File("..")
  
  def appName = Pom.name(localPomDir)  
  def version = Pom.version(localPomDir)  
  def scalaVersion =  Pom.scalaVersion(parentPomDir)
  def playVersion = Pom.playVersion(parentPomDir)
  def organization  = "com.github"
}