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
select * from person
where id = /* id */0
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
  def delete(person: Person): Result[Person]
}   