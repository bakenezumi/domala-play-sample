package controllers

import javax.inject._
import scala.concurrent.Future

import play.api.mvc._
import play.api.libs.json._

import domala.Required
import domala.jdbc.Config
import domala.jdbc.DaoProvider

import sample._
import PersonConverter._

@Singleton
class SampleController @Inject()
(val controllerComponents: ControllerComponents)
(implicit config: Config, ec: JdbcExecutionContext) extends BaseController {

  lazy val dao: PersonDao = DaoProvider.get[PersonDao]

  def selectById(id: Long) = Action.async {
    Future { Required {
      dao.selectById(ID(id))
    }}.map {
      case Some(person) => Ok(Json.toJson(person))
      case None => NotFound("not found.")
    }
  }

  def selectWithDeparmentById(id: Long) = Action.async {
    Future { Required {
      dao.selectWithDeparmentById(ID(id))
    }} map {
      case Some(person) => Ok(Json.toJson(person))
      case None => NotFound("not found.")
    }
  }

  def selectAll = Action.async {
    Future { Required {
      dao.selectAll
    }} map {
      case Nil => NotFound("not found.")
      case persons => Ok(Json.toJson(persons))
    }
  }

  implicit def toPerson(request: Request[AnyContent]) = request.body.asJson.map(_.as[Person])
      .getOrElse(throw new RuntimeException("Request body colud not parse"))

  def insert = Action.async { request =>
    Future { Required {
      dao.insert(toPerson(request))
    }} map { result => 
      Ok(Json.toJson(result))
    }
  }

  def update(id: Long) = Action.async { request =>
    Future { Required {
      dao.selectById(ID(id)) map { selected =>
        val entity = toPerson(request).copy(
          id = selected.id,
          version = selected.version)
        dao.update(entity)
      } 
    }} map { 
      case Some(result) => Ok(Json.toJson(result))
      case None => NotFound("not found.")
    }
  }

  def delete(id: Long) = Action.async {
    Future { Required {
      dao.selectById(ID(id)).map(dao.delete)
    }} map {
      case Some(result) => Ok(Json.toJson(result))
      case None => NotFound("not found.")
    }
  }

}
