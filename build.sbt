
name := "shapeless-getresult"

scalaVersion := "2.11.7"

resolvers += "Underscore Bintray" at "https://dl.bintray.com/underscoreio/libraries"

libraryDependencies ++= slick ++ shapeless ++ h2 ++ logging

lazy val slick          = Seq("com.typesafe.slick" %% "slick"     % "3.0.1")
lazy val shapeless      = Seq("com.chuusai"        %% "shapeless" % "2.2.5")
lazy val h2             = Seq("com.h2database"      % "h2"        % "1.4.185")
lazy val logging        = Seq("ch.qos.logback"      % "logback-classic" % "1.1.2")

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-unchecked",
  "-feature",
  "-language:implicitConversions",
  "-language:postfixOps",
  "-Ywarn-dead-code",
  "-Xlint",
  "-Xfatal-warnings"
)
