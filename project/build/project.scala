import org.scalaee.sbtjeeplugin.WebProfileJEEProject
import sbt._

class ScalaEEProject(info: ProjectInfo) extends ParentProject(info) with UnpublishedProject {

  // ===================================================================================================================
  // Repositories
  // ===================================================================================================================

  val javaNetRepo = "java.net repo" at "http://download.java.net/maven/2"

  // ===================================================================================================================
  // Dependencies for subprojects: Intentionally defs!
  // ===================================================================================================================

  // Dependencies (compile)
  def scalaz = "com.googlecode.scalaz" %% "scalaz-core" % "5.0" withSources
  def slf4s = "com.weiglewilczek.slf4s" %% "slf4s" % "1.0.2" withSources

  // Dependencies (provided)
  def javaeeWebApi = "javax" % "javaee-web-api" % "6.0" % "provided"

  // Dependencies (test)
  def glassfishELImpl = "org.glassfish.web" % "el-impl" % "2.2" % "test" // Needed because of crippled javaee-(web-)api!
  def mockito = "org.mockito" % "mockito-all" % "1.8.4" % "test"
  def specs = "org.scala-tools.testing" %% "specs" % "1.6.5" % "test" withSources

  // Dependencies (varying scopes)
  def slf4jLog4j(scope: String) = "org.slf4j" % "slf4j-log4j12" % "1.6.1" % scope

  // ===================================================================================================================
  // Publishing
  // ===================================================================================================================

  override def managedStyle = ManagedStyle.Maven
//   override def deliverAction = super.deliverAction dependsOn(publishLocal) // Fix for issue 99!
  Credentials(Path.userHome / ".ivy2" / ".credentials", log)
//  lazy val publishTo = "Scala Tools Nexus" at "http://nexus.scala-tools.org/content/repositories/releases/"
//  lazy val publishTo = "Scala Tools Nexus" at "http://nexus.scala-tools.org/content/repositories/snapshots/"
  val publishTo = Resolver.file("Local Test Repository", Path fileProperty "java.io.tmpdir" asFile)

  // ===================================================================================================================
  // scalaee-core subproject
  // ===================================================================================================================

  val coreProject = project("core", "scalaee-core", new CoreProject(_))

  class CoreProject(info: ProjectInfo) extends DefaultProject(info) {

    override def libraryDependencies =
      Set(scalaz, slf4s, javaeeWebApi, specs, mockito, glassfishELImpl, slf4jLog4j("test"))
    override def defaultExcludes = super.defaultExcludes || "*-sources.jar"

    override def packageSrcJar = defaultJarPath("-sources.jar")
    lazy val sourceArtifact = sources(artifactID) // lazy is important here!
    override def packageToPublishActions = super.packageToPublishActions ++ Seq(packageSrc)

    override def testClasspath = super.testClasspath --- providedClasspath // Needed because of crippled javaee-(web-)api!
  }

  // ===================================================================================================================
  // scalaee-core-it subproject
  // ===================================================================================================================

  val coreITProject = project("core-it", "scalaee-core-it", new CoreITProject(_), coreProject)

  class CoreITProject(info: ProjectInfo) extends DefaultWebProject(info) with UnpublishedProject with WebProfileJEEProject {

    override def libraryDependencies = Set(slf4jLog4j("compile"), javaeeWebApi)

    override protected def glassfishAsadmin = "%s/tools/glassfish/glassfishv3/glassfish/bin/asadmin" format Path.userHome
  }
}

trait UnpublishedProject extends BasicManagedProject {
   override def publishLocalAction = task { None }
   override def deliverLocalAction = task { None }
   override def publishAction = task { None }
   override def deliverAction = task { None }
   override def artifacts = Set.empty
}
