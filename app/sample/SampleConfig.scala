package sample

import javax.inject._
import domala.jdbc.LocalTransactionConfig
import org.seasar.doma.jdbc.Naming
import org.seasar.doma.jdbc.dialect.Dialect
import org.seasar.doma.jdbc.tx.LocalTransactionDataSource
import play.api.db.Database

@Singleton
class SampleConfig @Inject() (db: Database, dialect: Dialect) extends LocalTransactionConfig(
  dataSource = db.dataSource,
  dialect = dialect,
  naming = Naming.SNAKE_LOWER_CASE
)
