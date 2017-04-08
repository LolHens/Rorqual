name := "Rorqual"

lazy val settings = Seq(
  version := "0.0.0",

  //scalaOrganization := "org.typelevel",
  scalaVersion := "2.12.1",

  resolvers := Seq("Artifactory" at "http://lolhens.no-ip.org/artifactory/libs-release/"),

  libraryDependencies ++= Seq(
    "org.scala-lang" % "scala-reflect" % "2.12.1",
    "org.scala-lang.modules" %% "scala-java8-compat" % "0.8.0",
    "org.slf4j" % "slf4j-api" % "1.7.24",
    "ch.qos.logback" % "logback-classic" % "1.2.1",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.5.0",
    "org.typelevel" %% "cats" % "0.9.0",
    "com.chuusai" %% "shapeless" % "2.3.2",
    "com.github.mpilquist" %% "simulacrum" % "0.10.0",
    "io.monix" %% "monix" % "2.2.3",
    "io.monix" %% "monix-cats" % "2.2.3",
    "org.atnos" %% "eff" % "3.1.0",
    "com.typesafe.akka" %% "akka-actor" % "2.4.17",
    "com.typesafe.akka" %% "akka-remote" % "2.4.17",
    "com.typesafe.akka" %% "akka-stream" % "2.4.17",
    "io.spray" %% "spray-json" % "1.3.3",
    "com.github.fommil" %% "spray-json-shapeless" % "1.3.0",
    "org.scodec" %% "scodec-bits" % "1.1.4",
    "org.jcodec" % "jcodec-javase" % "0.2.0",
    "org.jcodec" % "jcodec-samples" % "0.2.0",
    "io.swave" %% "swave-core" % "0.7.0",
    "io.swave" %% "swave-akka-compat" % "0.7.0",
    "io.swave" %% "swave-scodec-compat" % "0.7.0",
    "com.github.julien-truffaut" %% "monocle-core" % "1.4.0",
    "com.github.julien-truffaut" %% "monocle-macro" % "1.4.0",
    "com.github.melrief" %% "pureconfig" % "0.6.0",
    "eu.timepit" %% "refined" % "0.7.0",
    "eu.timepit" %% "refined-pureconfig" % "0.7.0"
  ),

  addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full),
  addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.3"),

  mainClass in Compile := None,

  dependencyUpdatesExclusions := moduleFilter(organization = "org.scala-lang"),

  scalacOptions ++= Seq("-Xmax-classfile-name", "254")
)

lazy val root = project.in(file("."))
  .settings(settings: _*)
  .aggregate(core)

lazy val core = project.in(file("core"))
  .enablePlugins(
    JavaAppPackaging,
    UniversalPlugin)
  .settings(settings: _*)

lazy val iscsi = project.in(file("iscsi"))
  .enablePlugins(
    JavaAppPackaging,
    UniversalPlugin)
  .settings(settings: _*)
  .settings(libraryDependencies ++= Seq(
  ))
  .dependsOn(core)

lazy val raw = project.in(file("raw"))
  .enablePlugins(
    JavaAppPackaging,
    UniversalPlugin)
  .settings(settings: _*)
  .dependsOn(core)

lazy val fuse = project.in(file("fuse"))
  .enablePlugins(
    JavaAppPackaging,
    UniversalPlugin)
  .settings(settings: _*)
  .dependsOn(core)
