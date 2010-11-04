import sbt._

class Plugins(info: ProjectInfo) extends PluginDefinition(info) {

  // Module configurations

  // Dependencies
  val scalaEESbtPlugin = "org.scalaee" % "scalaee-sbt-plugin" % "0.1-SNAPSHOT"
}
