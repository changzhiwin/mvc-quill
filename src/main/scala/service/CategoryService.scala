package org.example.service

import cats.effect.{ContextShift, IO}

import org.example.dao.CategoryDao
import org.example.dao.records.Category
import org.example.entities.CategoryEntity

class CategoryService(dao: CategoryDao)(implicit cs: ContextShift[IO]) {

  def get(id: Long): IO[Option[CategoryEntity]] =
    IO.fromFuture(IO(dao.findById(id))).map { maybeRecord =>
      maybeRecord.map(CategoryEntity.fromRecord)
    }

  def all(): IO[List[CategoryEntity]] =
    IO.fromFuture(IO(dao.all())).map { records =>
      records.toList.map(CategoryEntity.fromRecord)
    }
}