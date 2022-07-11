package org.example.api

import cats.effect.IO
import org.http4s.HttpRoutes
import org.http4s.dsl._
import org.http4s.circe._

import org.example.entities._
import org.example.service.QuizService

class QuizApi(quizService: QuizService) extends Http4sDsl[IO] {

  private implicit val quizEncoder = jsonEncoderOf[IO, QuizEntity]

  private object CategoryParam extends QueryParamDecoderMatcher[Long]("category_id")

  private implicit val givenAnswersDecoder = jsonOf[IO, List[GivenAnswerEntity]]
  private implicit val quizScoreEncoder = jsonEncoderOf[IO, ScoreEntity]

  val routes = HttpRoutes.of[IO] {
    case GET            -> Root :? CategoryParam(categoryId) =>
      quizService.generate(categoryId).flatMap {
        case Some(quiz)  => Ok(quiz)
        case None        => NotFound(s"Category $categoryId does not exits")
      }
    case request @ POST -> Root  =>
      val response = for {
        answers <- request.as[List[GivenAnswerEntity]]
        score   <- quizService.score(answers)
      } yield score
      Ok(response)
  }
}