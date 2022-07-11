name := "mvc-quill"

version := "0.1"

scalaVersion := "2.13.8"

val CirceVersion  = "0.14.2"
 val Http4sVersion = "0.22.0"

libraryDependencies ++= List(
  "mysql" % "mysql-connector-java" % "8.0.29",
  "io.getquill" %% "quill-jasync-mysql" % "4.1.0",

  //"org.typelevel" %% "cats-effect" % "3.3.12",
  "org.typelevel" %% "cats-effect" % "2.5.3",
  "io.circe" %% "circe-core" % CirceVersion,
  "io.circe" %% "circe-generic" % CirceVersion,
  "io.circe" %% "circe-parser" % CirceVersion,
  "org.http4s" %% "http4s-blaze-server" % Http4sVersion,
  "org.http4s" %% "http4s-dsl" % Http4sVersion,
  "org.http4s" %% "http4s-circe" % Http4sVersion,
  "org.scalatest" %% "scalatest" % "3.2.11" % "test",

  "ch.qos.logback"  %  "logback-classic" % "1.2.11"
)
