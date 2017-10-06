package sample

import play.api.inject.Module
import play.api.{Configuration, Environment}

class SampleModule extends Module {

 override def bindings(env: Environment, conf: Configuration) = Seq(
    bind[domala.jdbc.Config].to(classOf[SampleConfig])
  )

}
