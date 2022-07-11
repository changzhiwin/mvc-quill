package org.example.api

import org.example.service.Services

class Api(services: Services) {
  val category = new CategoryApi(services.category)

  val generic = new GenericApi(services.generic)

  val quiz = new QuizApi(services.quiz)
}