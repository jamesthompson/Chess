import AssemblyKeys._ 

name := "Chess"

version := "1.0a"

scalaVersion := "2.9.2"

javaHome := Some(new File("/Library/Java/JavaVirtualMachines/1.7.0.jdk/Contents/Home"))

javacOptions ++= Seq("-source", "1.7", "-target", "1.7")

retrieveManaged := true

mainClass in (Compile, run) := Some("com.jamesrthompson.games.GamePlay")

mainClass in assembly := Some("com.jamesrthompson.games.GamePlay")

onLoadMessage := ""

assemblySettings

jarName in assembly := "Chess.jar"