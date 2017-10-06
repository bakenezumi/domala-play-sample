package sample

import play.api.inject.Module
import play.api.{Configuration, Environment}

class SampleModule extends Module {

 override def bindings(env: Environment, conf: Configuration) = Seq(
    bind[domala.jdbc.Config].to(classOf[SampleConfig]),
    bind[JdbcExecutionContext].to(classOf[JdbcExecutionContextImpl])    
  )

}

import javax.inject.Inject
import scala.concurrent.ExecutionContext
import akka.actor.ActorSystem
import play.api.libs.concurrent.CustomExecutionContext

trait JdbcExecutionContext extends ExecutionContext

class JdbcExecutionContextImpl @Inject()(system: ActorSystem)
  extends CustomExecutionContext(system, "jdbc.executor") with JdbcExecutionContext
