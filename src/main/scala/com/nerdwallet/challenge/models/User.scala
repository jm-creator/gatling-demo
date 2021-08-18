package com.nerdwallet.challenge.models

import com.nerdwallet.challenge.utils.Utils
import io.gatling.core.Predef._    // required for Gatling core structure DSL
import io.gatling.http.Predef._    // required for Gatling HTTP DSL
import scala.concurrent.duration._ // used for specifying duration unit, eg "5 second"


object User extends Utils {

  val postUser =
    exec(http("Post User")
      .post(baseUrl + "/v1/user")
      .body(ElFileBody("body/user.json"))
      .header("security_token", "1")
      .check(jsonPath("$.success").is("true"))
      .check(status.is(201)))


  val getUser =
//    exec(addCookie(Cookie("access_token", token)))
      exec(http("Get user")
      .get("v1/user/1")
      .header("security_token", token)
      .check(status.is(200))

    )

}
