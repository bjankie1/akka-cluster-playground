import akka.actor.ActorSystem

object Launcher extends App {

  // Override the configuration of the port
  // when specified as program argument
  if (args.nonEmpty) System.setProperty("akka.remote.netty.tcp.port", args(0))

  val system = ActorSystem("MyCluster")

  val clusterListener = system.actorOf(ClusterListener.props)
}
