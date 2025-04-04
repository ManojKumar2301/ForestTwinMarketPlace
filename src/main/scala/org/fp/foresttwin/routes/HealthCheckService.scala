package org.fp.foresttwin.routes

import cats.effect.IO
import org.http4s.HttpRoutes
import sttp.tapir._
import sttp.tapir.server.http4s.Http4sServerInterpreter

object HealthCheckService {
  val healthEndpoint: Endpoint[Unit, Unit, String, String, Any] = 
    endpoint.get
      .in("health")
      .out(stringBody)         
      .errorOut(stringBody)   

  
  val healthRoutes: HttpRoutes[IO] = Http4sServerInterpreter[IO]().toRoutes(
    healthEndpoint.serverLogicSuccess(_ => IO.pure("OK- Success:200"))
  )
}
