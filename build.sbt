lazy val metaMacroSettings: Seq[Def.Setting[_]] = Seq(
  addCompilerPlugin("org.scalameta" % "paradise" % "3.0.0-M10" cross CrossVersion.full),
  scalacOptions += "-Xplugin-require:macroparadise",
  scalacOptions in (Compile, console) ~= (_ filterNot (_ contains "paradise")) // macroparadise plugin doesn't work in repl yet.
)

lazy val root = (project in file(".")).enablePlugins(PlayScala).settings(
  inThisBuild(List(
    scalaVersion := "2.12.4",
    version := "0.1.0"
  )),
  name := "domala-play-sample",
  libraryDependencies ++= Seq(
    guice,
    ws,
    jdbc,
    evolutions,
    "com.h2database" % "h2" % "1.4.196",
    "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test
  )
) dependsOn repository aggregate repository

lazy val repository = (project in file("repository")).settings(
  inThisBuild(List(
    scalaVersion := "2.12.4",
    version := "0.1.0"
  )),
  metaMacroSettings,
  libraryDependencies ++= Seq(
    "com.github.domala" %% "domala" % "0.1.0-beta.9",
    "com.github.domala" %% "domala-paradise" % "0.1.0-beta.9" % Provided,
    "org.scalameta" %% "scalameta" % "1.8.0" % Provided
  ),
  publishArtifact in (Compile, packageDoc) := false,
  publishArtifact in packageDoc := false,
  sources in (Compile, doc) := Seq.empty  
)

// for windows https://docs.oracle.com/javase/tutorial/deployment/jar/downman.html
lazy val shortyJarName = "shorty-classpath.jar"
scriptClasspath := {
  val manifest = new java.util.jar.Manifest()
  manifest.getMainAttributes().putValue("Class-Path", scriptClasspath.value.mkString(" "))
  val shortyJar = (target in Universal).value / "lib" / shortyJarName
  IO.jar(Seq.empty, shortyJar, manifest)
  Seq(shortyJar.getName)
}
mappings in Universal += (((target in Universal).value / "lib" / shortyJarName) -> s"lib/$shortyJarName")

// https://github.com/playframework/playframework/issues/7832
evictionWarningOptions in update := EvictionWarningOptions.default
  .withWarnTransitiveEvictions(false)
  .withWarnDirectEvictions(false)
