package org.example.stubs.dao

import scala.concurrent.{ExecutionContext, Future}

import org.example.dao.CategoryDao
import org.example.dao.records.Category

class FakeCategoryDao(implicit ec: ExecutionContext) extends CategoryDao(ctx = null) {
  private var fakeCategories = Fixtures.categories

  private def safelyModify(f: List[Category] => List[Category]): Unit = {
    synchronized { fakeCategories = f(fakeCategories) }
  }

  override def save(category: Category): Future[Long] = {
    safelyModify(_ :+ category)
    Future(category.id)
  }

  override def all(): Future[List[Category]] = Future(fakeCategories)

  override def findById(id: Long): Future[Option[Category]] = {
    Future(fakeCategories.find(_.id == id))
  }

  override def deleteById(id: Long): Future[Boolean] = {
    val isPresent = fakeCategories.exists(_.id == id)
    safelyModify(_.filterNot(_.id == id))
    Future(isPresent)
  }
}