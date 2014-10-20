package com.github.controllers

import play.api.mvc._
import play.api.libs.iteratee.Enumerator
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import play.api.libs.json.Json

object ScalaController extends Controller {
  
  
  def about = Action.async { implicit request =>
    Future.successful(Ok(com.github.views.html.index.render("Hello")))
  }
  
}
