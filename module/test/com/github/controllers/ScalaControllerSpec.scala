package com.github.controllers

import org.junit.runner.RunWith
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import org.specs2.mutable.Specification
import play.api.test.FakeRequest
import play.api.test.Helpers._
import play.api.test.WithApplication
import play.api.mvc.Cookie
import org.specs2.runner.JUnitRunner
import play.test.FakeApplication

/**
 * @author marcoellwanger
 * @author djaychaudhary
 * @author patricksauts
 */
@RunWith(classOf[JUnitRunner])
class ScalaControllerSpec extends Specification {

  "Blah blah Specifications".title

  "Test" should {

    "expect OK" in {
      val request = FakeRequest(GET, "/about")
      val result = Await.result(ScalaController.about(request), Duration(20, "seconds"))
      result.header.status must equalTo(OK)
    }

  }
}
