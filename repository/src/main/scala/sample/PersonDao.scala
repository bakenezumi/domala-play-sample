package sample

import domala._
import domala.jdbc.Result

@Dao
trait PersonDao {

  @Select("""
select * from person
where id = /* id */0
  """)
  def selectById(id:Int): Option[Person]

  @Select("""
select
    p.id,
    p.name,
    d.id as department_id,
    d.name as department_name
from
    person p
    inner join
    department d
    on (p.department_id = d.id)
where
    p.id = /*id*/0
  """)
  def selectWithDeparmentById(id: Int): Option[PersonDepartment]

  @Select("""
select * from person
  """)
  def selectAll: Seq[Person]

  @Insert
  def insert(person: Person): Result[Person]

  @Update
  def update(person: Person): Result[Person]

  @Delete
  def delete(person: Person): Int
}   