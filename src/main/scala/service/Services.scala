package org.example.service

import cats.effect.{ContextShift, IO}
import org.example.dao.Dao

class Services(dao: Dao)(implicit cs: ContextShift[IO]) {
  val generic = new GenericService(dao.generic)

  val category = new CategoryService(dao.category)

  val quiz = new QuizService(dao.questionAnswer, category)
}