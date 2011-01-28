
import sbt._
import org.scalaee.sbtjeeplugin.WebProfileJEEProject
import collection.immutable.Set // only to avoid a wrong warning by IDEA


class ScalaEEProject(info: ProjectInfo) extends ParentProject(info) with UnpublishedProject {

  // ===================================================================================================================
  // Repositories
  // ===================================================================================================================

  val javaNetRepo = "java.net repo" at "http://download.java.net/maven/2"

  val jbossCommunityRepo = "JBoss Community Repo" at "http://repository.jboss.org/nexus/content/groups/public-jboss/"

  // ===================================================================================================================
  // Dependencies for subprojects: Intentionally defs!
  // ===================================================================================================================

  // Dependencies (compile)
  def scalaz = "com.googlecode.scalaz" %% "scalaz-core" % "5.0" withSources
  def slf4s = "com.weiglewilczek.slf4s" %% "slf4s" % "1.0.2" withSources
  def seamSolder = "org.jboss.seam.solder" % "seam-solder" % "3.0.0.Beta2" withSources
  def logManager = "org.jboss.logmanager" % "jboss-logmanager" % "1.2.0.CR4" withSources

  // Dependencies (provided)
  def javaeeWebApi = "javax" % "javaee-web-api" % "6.0" % "provided"

  // Dependencies (test)
  def glassfishELImpl = "org.glassfish.web" % "el-impl" % "2.2" % "test" // Needed because of crippled javaee-(web-)api!
  def mockito = "org.mockito" % "mockito-all" % "1.8.5" % "test"
  def specs = "org.scala-tools.testing" %% "specs" % "1.6.6" % "test" withSources

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
      Set(/*scalaz, */
        slf4s, javaeeWebApi, seamSolder, logManager, specs, mockito, glassfishELImpl, slf4jLog4j("compile"))
    override def defaultExcludes = super.defaultExcludes || "*-sources.jar"

    override def packageSrcJar = defaultJarPath("-sources.jar")
    lazy val sourceArtifact = sources(artifactID) // lazy is important here!
    override def packageToPublishActions = super.packageToPublishActions ++ Seq(packageSrc)

    override def testClasspath = super.testClasspath --- providedClasspath // Needed because of crippled javaee-(web-)api!
  }

  // ===================================================================================================================
  // scalaee-core-it subproject
  // ===================================================================================================================

  val dummyAppProject = project("dummyapp", "scalaee-dummyapp", new DummyAppProject(_), coreProject)

  class DummyAppProject(info: ProjectInfo) extends DefaultWebProject(info) with UnpublishedProject with WebProfileJEEProject {

    override def libraryDependencies = Set(
      //slf4jLog4j("compile"),
      javaeeWebApi)

  }
}

trait UnpublishedProject extends BasicManagedProject {
   override def publishLocalAction = task { None }
   override def deliverLocalAction = task { None }
   override def publishAction = task { None }
   override def deliverAction = task { None }
   override def artifacts = Set.empty
}
