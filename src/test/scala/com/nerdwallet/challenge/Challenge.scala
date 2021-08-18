package com.nerdwallet.challenge

import com.nerdwallet.challenge.models.User
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps


case class Challenge() extends BaseSimulation {

  val className: String = getClassName

  val scn: ScenarioBuilder = scenario(className)
    .exec(addCookie(Cookie("access_token", "1")))
    .repeat(5) {
      exec(User.postUser)
      exec(User.getUser)
    }

  setUp(run(scn, className)).maxDuration(maxTimeout minutes)
}
