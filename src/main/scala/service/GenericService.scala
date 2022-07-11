package org.example.service

import cats.effect.{ContextShift, IO}
import org.example.dao.GenericDao

class GenericService(dao: GenericDao)(implicit cs: ContextShift[IO]) {
  def healthCheck: IO[String] =
    checkDbConnectivity().map { success =>
      s"Database Connectivity: ${if (success) "OK" else "Failure"}"
    }
  
  private def checkDbConnectivity(): IO[Boolean] =
    IO.fromFuture(IO(dao.testConnection()))
      .handleErrorWith(_ => IO(false))
}