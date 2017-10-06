lazy val metaMacroSettings: Seq[Def.Setting[_]] = Seq(
  resolvers += Resolver.sonatypeRepo("releases"),
  resolvers += Resolver.bintrayRepo("scalameta", "maven"),
  addCompilerPlugin("org.scalameta" % "paradise" % "3.0.0-M10" cross CrossVersion.full),
  scalacOptions += "-Xplugin-require:macroparadise",
  scalacOptions in (Compile, console) := Seq()
)

lazy val root = (project in file(".")).enablePlugins(PlayScala).settings(
  inThisBuild(List(
    scalaVersion := "2.12.3",
    version := "0.1.0"
  )),
  name := "domala-play-sample",
  metaMacroSettings,
  libraryDependencies ++= Seq(
    guice,
    ws,
    jdbc,
    evolutions,
    "com.h2database" % "h2" % "1.4.193",
    "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.0" % Test
  ) 
) dependsOn repository aggregate repository

lazy val repository = (project in file("repository")).settings(
  inThisBuild(List(
    scalaVersion := "2.12.3",
    version := "0.1.0"
  )),
  metaMacroSettings,
  libraryDependencies ++= Seq(
    "com.github.domala" %% "domala" % "0.1.0-beta.2"
  ) 
)