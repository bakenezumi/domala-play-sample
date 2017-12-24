package sample

import domala._

@Entity
case class Person(
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(sequence = "person_id_seq")  
  id: ID[Person] = ID(-1),
  name: Name,
  age: Age,
  address: Address,
  departmentId: Option[ID[Department]],
  @Version
  version: Int = 0
)
