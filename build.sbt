name := "akka-cluster"

version := "1.0"

scalaVersion := "2.10.3"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.2.3",
  "com.typesafe.akka" %% "akka-cluster" % "2.2.3",
  "com.typesafe.akka" %% "akka-slf4j" % "2.2.3",
  "com.typesafe.akka" %% "akka-testkit" % "2.2.3" % "test")
