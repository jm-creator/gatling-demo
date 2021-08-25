name := "nerdwallet-challenge"

version := "0.1"

scalaVersion := "2.13.6"

enablePlugins(GatlingPlugin)

libraryDependencies += "io.gatling.highcharts" % "gatling-charts-highcharts"  %  "3.6.1"
libraryDependencies += "io.gatling" % "gatling-test-framework" % "3.6.1"
