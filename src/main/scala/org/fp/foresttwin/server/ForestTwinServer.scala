package org.fp.foresttwin.server

import cats.effect._
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.implicits._
import org.http4s.server.Router
import org.fp.foresttwin.routes.HealthCheckService
import com.comcast.ip4s._

object ForestTwinServer extends IOApp {
  override def run(args: List[String]): IO[ExitCode] = {
    val httpApp = Router("/" -> HealthCheckService.healthRoutes).orNotFound

    EmberServerBuilder.default[IO]
      .withHost(Host.fromString("0.0.0.0").getOrElse(host"0.0.0.0")) 
      .withPort(Port.fromInt(8080).getOrElse(port"8080"))
      .withHttpApp(httpApp)
      .build
      .use(_ => IO.never)
      .as(ExitCode.Success)
  }
}
