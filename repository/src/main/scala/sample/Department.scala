package sample

import domala._

@Entity
case class Department(
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(sequence = "department_id_seq")    
  id: ID[Department] = ID(-1),
  name: Name,
  @Version
  version: Int = 0
)
