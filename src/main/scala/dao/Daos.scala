package org.example.dao

import io.getquill.{MysqlJAsyncContext, SnakeCase}
import scala.concurrent.ExecutionContext

class Dao(ctx: MysqlJAsyncContext[SnakeCase.type])(implicit ec: ExecutionContext) {
  val category = new CategoryDao(ctx)

  val generic = new GenericDao(ctx)

  val questionAnswer = new QuestionAnswerDao(ctx)
}