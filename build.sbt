import Dependencies._

ThisBuild / scalaVersion     := "2.13.2"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "io.github.nightscape"
ThisBuild / organizationName := "nightscape"

lazy val core = (project in file("core"))
  .settings(
    name := "graph-layout-core",
  )

lazy val zest = (project in file("eclipse-zest"))
  .dependsOn(core)
  .settings(
    name := "graph-layout-eclipse-zest",
    resolvers ++= Seq(
      "WSO2" at "http://dist.wso2.org/maven2/",
    ),
    libraryDependencies ++= Seq(
      eclipseDraw2D,
      eclipseZestCore,
      eclipseZestLayouts,
    ),
  )

lazy val gef = (project in file("eclipse-gef"))
  .dependsOn(core)
  .settings(
    name := "graph-layout-eclipse-gef",
    resolvers ++= Seq(
      "Averbis" at "https://maven.averbis.com/m2/",
      "BestSolution" at "https://maven.bestsolution.at/efxclipse-releases/",
    ),
    libraryDependencies ++= Seq(
      eclipseGef,
      eclipseGefLayout,
    ),
  )
// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
