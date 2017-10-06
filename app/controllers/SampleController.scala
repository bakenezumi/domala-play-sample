package controllers

import javax.inject._

import play.api.mvc._
import play.api.libs.json._


import domala.Required
import domala.jdbc.Config

import sample._
import PersonConverter._

@Singleton
class SampleController @Inject() (val controllerComponents: ControllerComponents)(implicit config: Config) extends BaseController {

  lazy val dao: PersonDao = PersonDao.impl

  def selectById(id: Int) = Action{ Required {
    dao.selectById(id).map( person =>
      Ok(Json.toJson(person))
    ).getOrElse(
      NotFound("not found."))
  }}

  def selectWithDeparmentById(id: Int) = Action{ Required {
    dao.selectWithDeparmentById(id).map( person =>
      Ok(Json.toJson(person))
    ).getOrElse(
      NotFound("not found."))
  }}

  def selectAll = Action{ Required {
    dao.selectAll match {
      case Nil => NotFound("not found.")
      case persons => Ok(Json.toJson(persons))
    } 
  }}

  implicit def as(request: Request[AnyContent]) = new {
    def asPerson = request.body.asJson.map(_.as[Person])
      .getOrElse(throw new RuntimeException("Request body colud not parse"))
  }  

  def insert = Action{ request => {
    Required {
      Ok(Json.toJson(dao.insert(request.asPerson)))
    }
  }}

  def update = Action{ request => {
    Required {
      Ok(Json.toJson(dao.update(request.asPerson)))
    }
  }}

  def delete(id: Int) = Action{ request => {
    Required {
      Ok(Json.toJson(dao.delete(request.asPerson)))
    }
  }}

}
