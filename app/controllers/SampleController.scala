package controllers

import javax.inject._
import scala.concurrent.Future

import play.api.mvc._
import play.api.libs.json._

import domala.Required
import domala.jdbc.Config

import sample._
import PersonConverter._

@Singleton
class SampleController @Inject() (val controllerComponents: ControllerComponents)(implicit config: Config, ec: JdbcExecutionContext) extends BaseController {

  lazy val dao: PersonDao = PersonDao.impl

  def selectById(id: Int) = Action.async {
    Future { Required {
      dao.selectById(id)
    }}.map(_.map( person =>
      Ok(Json.toJson(person))
    ).getOrElse(
      NotFound("not found."))
    )
  }

  def selectWithDeparmentById(id: Int) = Action.async {
    Future { Required {
      dao.selectWithDeparmentById(id)
    }}.map(_.map( person =>
      Ok(Json.toJson(person))
    ).getOrElse(
      NotFound("not found."))
    )
  }

  def selectAll = Action.async {
    Future { Required {
      dao.selectAll
    }}.map(_ match {
        case Nil => NotFound("not found.")
        case persons => Ok(Json.toJson(persons))
      }
    )
  }

  implicit def as(request: Request[AnyContent]) = new {
    def asPerson = request.body.asJson.map(_.as[Person])
      .getOrElse(throw new RuntimeException("Request body colud not parse"))
  }

  def insert = Action.async { request =>
    Future { Required {
      dao.insert(request.asPerson)
    }}.map(result => Ok(Json.toJson(result)))
  }

  def update = Action.async { request =>
    Future { Required {
      dao.update(request.asPerson)
    }}.map(result => Ok(Json.toJson(result)))
  }

  def delete(id: Int) = Action.async {
    Future { Required {
      dao.selectById(id).map(person => dao.delete(person)).getOrElse(0)
    }}.map(result => Ok(Json.toJson(result)))
  }

}
