package sample

import javax.inject._
import domala.jdbc.Config
import org.seasar.doma.jdbc.Naming
import org.seasar.doma.jdbc.dialect.H2Dialect
import org.seasar.doma.jdbc.tx.LocalTransactionDataSource
import play.api.db.Database

@Singleton
class SampleConfig @Inject() (db: Database) extends Config(
  dataSource =  new LocalTransactionDataSource(db.dataSource),
  dialect = new H2Dialect,
  naming = Naming.SNAKE_LOWER_CASE
)
