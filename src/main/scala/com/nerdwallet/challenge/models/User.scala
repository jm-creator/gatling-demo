package com.nerdwallet.challenge.models

import com.nerdwallet.challenge.utils.Utils
import io.gatling.core.Predef._    // required for Gatling core structure DSL
import io.gatling.http.Predef._    // required for Gatling HTTP DSL



object User extends Utils {

  val postUser =
    exec(http("Post User")
      .post("/v1/user")
      .body(ElFileBody("body/user.json"))
      .header("security_token", token)
      .check(status.is(200))
      .check(jsonPath("$.success").is("true")))




  val getUser =
      exec(addCookie(Cookie("access_token", token)))
      exec(http("Get user")
      .get("v1/user/1")
      .header("security_token", token)
      .check(status.is(200))
      .check(jsonPath("$.data.user.id").is("1"))
      .check(jsonPath("$.data.user.first_name").is("John"))
      .check(jsonPath("$.data.user.last_name").is("Doe"))

    )

}
