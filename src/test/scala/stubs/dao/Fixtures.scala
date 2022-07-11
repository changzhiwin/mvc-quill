package org.example.stubs.dao

import org.example.dao.records._

object Fixtures {
  val catA = Category(id = 1, name = "General")

  val catB = Category(id = 2, name = "Histroy")

  val categories = List(catA, catB)
}