package org.example.dao

import io.getquill.{MysqlJAsyncContext, SnakeCase}

import scala.concurrent.{ExecutionContext, Future}

class GenericDao(ctx: MysqlJAsyncContext[SnakeCase.type])(implicit ec: ExecutionContext) {

  import ctx._

  def testConnection(): Future[Boolean] = {
    val q = quote { infix"select 1".as[Int] }

    val result: Future[Int] = run(q)

    result.map(_ == 1)
  }
}