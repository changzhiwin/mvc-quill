package org.example.api

import cats.effect.IO
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import org.example.service.GenericService

class GenericApi(genericService: GenericService) extends Http4sDsl[IO] {
  val routes = HttpRoutes.of[IO] {
    case GET  -> Root / "ping"          => Ok("pong")
    case GET  -> Root / "healthCheck"   => Ok(genericService.healthCheck)
  }
}