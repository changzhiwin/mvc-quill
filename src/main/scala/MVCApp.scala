package org.example

import cats.effect.{ExitCode, IO, IOApp}
import org.http4s.implicits._
import org.http4s.server.Router
import org.http4s.blaze.server.BlazeServerBuilder
import scala.concurrent.ExecutionContext
import io.getquill.{MysqlJAsyncContext, SnakeCase}

import org.example.service.Services
import org.example.dao.Dao
import org.example.api.Api

object MVCApp extends IOApp {

  lazy private val ctx = new MysqlJAsyncContext(SnakeCase, "ctx")
  private val dao = new Dao(ctx)(ExecutionContext.global)
  private val services = new Services(dao)
  private val api = new Api(services)
  
  private val httpApp = Router(
    "/"          ->  api.generic.routes,
    "categories" ->  api.category.routes,
    "quiz"       ->  api.quiz.routes
  ).orNotFound

  override def run(args: List[String]): IO[ExitCode] =
    stream(args).compile.drain.as(ExitCode.Success)

  private def stream(args: List[String]) =
    BlazeServerBuilder[IO](ExecutionContext.global)
    .bindHttp(8000, "0.0.0.0")
    .withHttpApp(httpApp)
    .serve
}