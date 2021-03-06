import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "kijiviz"
  val appVersion      = "1.0-SNAPSHOT"


  val hadoopVer = "2.0.0-mr1-cdh4.3.0"
  val hbaseVer = "0.94.6-cdh4.3.0"

  val appDependencies = Seq(
    // Add your project dependencies here,
    //jdbc,
    //anorm,
    "org.kiji.schema" % "kiji-schema" % "1.2.1",
    "org.kiji.platforms" % "kiji-cdh4-platform" % "1.2.0"

//    "org.apache.hadoop" % "hadoop-client"  % hadoopVer,
//    "org.apache.hadoop" % "hadoop-core"  % hadoopVer,
//    "org.apache.hbase" % "hbase" % hbaseVer
  )


  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here

    // Add Kiji repository to resolvers
    resolvers += "Kiji Repository" at "https://repo.wibidata.com/artifactory/kiji"
  )

}
