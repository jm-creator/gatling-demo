package com.nerdwallet.challenge.utils

import com.typesafe.config.{Config, ConfigFactory}

trait Utils {
  val conf: Config = ConfigFactory.load("load")
  val baseUrl = sys.props.getOrElse("sf.url", conf.getString("sf.url"))
  val token = sys.props.getOrElse("sf.token", conf.getString("sf.token"))

}
