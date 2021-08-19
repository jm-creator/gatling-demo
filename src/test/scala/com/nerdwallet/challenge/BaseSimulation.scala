package com.nerdwallet.challenge

import com.nerdwallet.challenge.utils.Utils
import io.gatling.core.Predef._
import io.gatling.core.structure.{PopulationBuilder, ScenarioBuilder}
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

import scala.concurrent.duration.DurationInt

class BaseSimulation extends Simulation with Utils {

  val getClassName: String = getClass.getSimpleName
  val httpProtocol: HttpProtocolBuilder = http.baseUrl(baseUrl)
  val maxTimeout = getIntValue("sf.maxTimeout")

  def run(scn: ScenarioBuilder, className: String): PopulationBuilder = scn.inject(
      rampConcurrentUsers(1).to(getUsersAmount(className)).during(getUsersBurst(className).minutes),
      constantConcurrentUsers(getUsersAmount(className)).during(getUsersBurst(className).minutes)
  ).protocols(httpProtocol)

  def getIntValue(path: String):Int = conf.getInt(path)

  def getUsersAmount(testName: String):Int = {
    getIntValue(String.format("sf.%s.users.amount", testName))
  }

  def getUsersBurst(testName: String):Int = {
    getIntValue(String.format("sf.%s.users.burst", testName))
  }
}
