import sbt._

object Dependencies {
  val zioVersion = "1.0.0-RC18-2"
  val zioTest = "dev.zio" %% "zio-test" % zioVersion
  val zioTestSbt = "dev.zio" %% "zio-test-sbt" % zioVersion
  val zioTestMagnolia = "dev.zio" %% "zio-test-magnolia" % zioVersion

  val eclipseDraw2D = "org.eclipse" % "draw2d" % "3.2.100-v20070529"
  val eclipseZestCore = "org.eclipse.zest" % "org.eclipse.zest.core" % "3.4.0.v20140429-1120"
  val eclipseZestLayouts = "org.eclipse.zest" % "org.eclipse.zest.layouts" % "1.1.100.201408150207"
  val eclipseGef = "org.eclipse" % "gef" % "3.10.1"
  val eclipseGefLayout = "at.bestsolution.efxclipse.eclipse" % "org.eclipse.gef.layout" % "5.0.0.201901221055"
}
