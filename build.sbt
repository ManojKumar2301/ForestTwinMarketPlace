import sbtassembly.AssemblyPlugin
import sbtassembly.AssemblyKeys
import sbtassembly.MergeStrategy

lazy val tapirVersion = "1.11.21"
lazy val http4sVersion = "0.23.30"
lazy val catsEffectVersion = "3.6.0"
val sttpClientVersion = "3.10.3"
val circeVersion = "0.14.6"

lazy val root = project.in(file("."))
  .settings(
    scalaVersion := "3.4.2",
    libraryDependencies ++= Seq(
      "com.softwaremill.sttp.tapir" %% "tapir-core" % tapirVersion,
      "com.softwaremill.sttp.tapir" %% "tapir-http4s-server" % tapirVersion,
      "com.softwaremill.sttp.tapir" %% "tapir-json-circe" % tapirVersion,
      "com.softwaremill.sttp.tapir" %% "tapir-sttp-client" % tapirVersion,

      "org.typelevel" %% "cats-effect" % catsEffectVersion,
      "org.http4s" %% "http4s-ember-server" % http4sVersion,
      "org.http4s" %% "http4s-ember-client" % http4sVersion,
      "org.http4s" %% "http4s-circe" % http4sVersion,

      "io.circe" %% "circe-generic" % "0.14.12",
      "io.circe" %% "circe-parser" % "0.14.12",

      "com.softwaremill.sttp.client3" %% "core" % sttpClientVersion,
      "com.softwaremill.sttp.client3" %% "circe" % sttpClientVersion,

      "org.scalatest"     %% "scalatest"                % "3.2.19" % "test",
      "org.typelevel"     %% "munit-cats-effect-3"      % "1.0.7"  % "test",

      "org.typelevel" %% "log4cats-core" % "2.7.0",
      "org.typelevel" %% "log4cats-slf4j" % "2.7.0",
      "org.log4s" %% "log4s" % "1.10.0",
      "ch.qos.logback" % "logback-classic" % "1.5.18",
      "org.slf4j" % "slf4j-api" % "2.0.17",
    ),
    assembly / mainClass := Some("org.fp.foresttwin.server.ForestTwinServer"),
    assembly / assemblyJarName := "foresttwin-assembly.jar",
    assembly / assemblyMergeStrategy := {
      case PathList("META-INF", _*) => MergeStrategy.discard
      case x => MergeStrategy.first
    }
  )
  .enablePlugins(AssemblyPlugin)