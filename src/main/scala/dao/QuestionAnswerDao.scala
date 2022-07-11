package org.example.dao

import io.getquill.{MysqlJAsyncContext, SnakeCase}
import scala.concurrent.{ExecutionContext, Future}

import org.example.dao.records.{Answer, Question}

class QuestionAnswerDao(ctx: MysqlJAsyncContext[SnakeCase.type])(implicit ec: ExecutionContext) {
  import ctx._
  
  private val questions = quote { query[Question] }
  private val answers = quote { query[Answer] }

  def save(newQuestion: Question, newAnswers: Seq[Answer]) : Future[(Long, Seq[Long])] = {
    val saveQuestion = quote {
      questions.insertValue(lift(newQuestion)).returningGenerated(_.id)
    }

    val saveAnswers = { questionId: Long =>
      val newAnswersWithQuestionId = newAnswers.map(_.copy(questionId = questionId))

      quote {
        liftQuery(newAnswersWithQuestionId).foreach {  a =>
          answers.insertValue(a).returningGenerated(_.id)
        }
      }
    }

    transaction { implicit ec =>
      for {
        questionId <- run(saveQuestion)
        answerIds   <- run(saveAnswers(questionId))
      } yield questionId -> answerIds
    }
  }

  def pickByCategoryId(categoryId: Long, n: Int): Future[Map[Question, Seq[Answer]]] = {
    val result: Future[Seq[(Question, Answer)]] = run {
      for {
        question <- questions.filter(_.categoryId == lift(categoryId))
                             .sortBy(_ => infix"RAND()")
                             .take(lift(n))
        answer   <- answers.filter(_.questionId == question.id)
      } yield (question, answer)
    }

    result.map { questionsAndAnswers =>
      val questions: Seq[Question] = questionsAndAnswers.map { case (q, _) => q }.distinct

      val answersByQuestionId: Map[Long, Seq[Answer]] =
        questionsAndAnswers.map {
          case (_, a) => a
        }.groupBy(_.questionId)

      questions.map { question =>
        question -> answersByQuestionId.getOrElse(question.id, List.empty)
      }.toMap
    }
  }

  def deleteById(id: Long): Future[Boolean] = {
    val q = quote {
      questions.filter(_.id == lift(id)).delete
    }
    run(q).map(_ > 0)
  }

  def getCorrectQuestionAnswers(questionIds: Seq[Long]): Future[Seq[(Long, Long)]] = {
    val q = quote {
      for {
        question      <- questions.filter(q => liftQuery(questionIds).contains(q.id))
        correctAnswer <- answers.filter(a => a.questionId == question.id && a.isCorrect)
      } yield question.id -> correctAnswer.id
    }
    run(q)
  }
}