import sbt._

/**
 * [[CompilerPlugins]] shared/used by all Scala modules.
 */
object CompilerPlugins {

  // Compiler Plugin Versions
  object V {
    val betterForComp = "0.3.1"
  }

  // Compiler Plugins
  val betterForComp   = "com.olegpy"    %% "better-monadic-for" % V.betterForComp
}
