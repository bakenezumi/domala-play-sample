package sample

import domala._
import domala.jdbc.Result

@Dao
trait PersonDao {

  @Select("""
select * from person
where id = /* id */0
  """)
  def selectById(id: ID[Person]): Option[Person]

  @Select // this uses SQL File (src/main/resources/META-INF/sample/PersonDao.selectWithDeparmentById.sql)
  def selectWithDeparmentById(id: ID[Person]): Option[PersonDepartment]

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