import akka.actor.{Props, Actor, ActorLogging}
import akka.cluster.Cluster
import akka.cluster.ClusterEvent._
import akka.cluster.ClusterEvent.CurrentClusterState
import akka.cluster.ClusterEvent.MemberUp
import akka.cluster.ClusterEvent.UnreachableMember


object ClusterListener {
  def props: Props = Props(classOf[ClusterListener])
}

class ClusterListener extends Actor with ActorLogging {

  val cluster = Cluster(context.system)

  override def preStart() = {
    cluster.subscribe(self, classOf[ClusterDomainEvent])
  }

  override def receive: Actor.Receive = {
    case state: CurrentClusterState ⇒
      log.info("Current members: {}", state.members.mkString(", "))
    case MemberUp(member) ⇒
      log.info("Member is Up: {}", member.address)
    case UnreachableMember(member) ⇒
      log.info("Member detected as unreachable: {}", member)
    case MemberRemoved(member, previousStatus) ⇒
      log.info("Member is Removed: {} after {}",
        member.address, previousStatus)
    case _: ClusterDomainEvent ⇒ // ignore
  }

}
