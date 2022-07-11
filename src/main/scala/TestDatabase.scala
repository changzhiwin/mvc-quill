package org.example

import io.getquill.{MysqlJAsyncContext, SnakeCase}
import org.example.dao.records._
import org.example.dao.CategoryDao

object TestDatabase {

  lazy val ctx = new MysqlJAsyncContext(SnakeCase, "ctx")

  def saveCategory(name: String): Any = {
    import scala.concurrent.ExecutionContext.Implicits.global
    val cate = new CategoryDao(ctx)

    cate.save(Category(0L, name))
  }

  def getCategory(): Any = {
    import scala.concurrent.ExecutionContext.Implicits.global
    val cate = new CategoryDao(ctx)

    cate.all()
  }

}
