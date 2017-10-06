package sample

import domala._

@Entity
case class PersonDepartment(
  id: Option[Int],
  name: Name,
  departmentId: Option[Int],
  departmentName: Name
)
