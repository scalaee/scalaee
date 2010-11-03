
import sbt._

class ScalaEEProject(info: ProjectInfo) extends DefaultProject(info) {

  override def compileOptions = super.compileOptions ++ 
    Seq("-deprecation",
        "-Xmigration",
        "-Xcheckinit",
        "-Xstrict-warnings",
        "-Xwarninit",
        "-encoding", "utf8")
        .map(CompileOption(_))

  override def javaCompileOptions = JavaCompileOption("-Xlint:unchecked") :: super.javaCompileOptions.toList

  object Dependencies {
    val javaee_web_api = "javax" % "javaee-web-api" % "6.0"
  }

  lazy val scalaEECore = project("scalaee-core", "Scala EE Core")

  class ScalaEECore(info: ProjectInfo) extends DefaultProject(info) {
    val javaee_web_api = Dependencies.javaee_web_api
  }

}

