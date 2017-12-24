package controllers

import domala.jdbc.Result

import play.api.libs.json._
import play.api.libs.functional.syntax._

import sample._

object PersonConverter {

  implicit def idFormat[E]: Format[ID[E]] = 
    implicitly[Format[Long]].inmap(v => ID[E](v), { case ID(v) => v })

  implicit val nameFormat: Format[Name] =
    implicitly[Format[String]].inmap(v => Name(v), { case Name(v) => v })

  implicit val ageFormat: Format[Age] =
    implicitly[Format[Int]].inmap(v => Age(v), _ match { case Age(v) => v })

  implicit val addressFormat = Json.format[Address]

  implicit val departmentFormat = Json.using[Json.WithDefaultValues].format[Department]

  implicit val personFormat = Json.using[Json.WithDefaultValues].format[Person]

  implicit def writesPersonDepartment = Json.writes[PersonDepartment]

  implicit def writesDomaResult = Json.writes[Result[Person]]

}
