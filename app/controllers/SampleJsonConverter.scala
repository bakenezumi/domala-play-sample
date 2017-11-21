package controllers

import domala.jdbc.Result

import play.api.libs.json._
import play.api.libs.functional.syntax._

import sample._


object PersonConverter {

  implicit val writesName = Writes[Name] { case Name(name) => JsString(name) }
  implicit val readsName = Reads[Name] { json => json.validate[String] map (name => Name(name)) }

  implicit def writesAddress = Json.writes[Address]
  implicit def readsAddrress = Json.reads[Address]

  implicit def writesPerson = Json.writes[Person]
  implicit def readsPerson = Json.reads[Person]

  implicit def writesPersonDepartment = Json.writes[PersonDepartment]

  implicit def writesDomaResult = Json.writes[Result[Person]]

}

