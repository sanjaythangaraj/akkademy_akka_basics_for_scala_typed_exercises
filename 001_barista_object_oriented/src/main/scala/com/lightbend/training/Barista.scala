package com.lightbend.training

import scala.collection._
import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.{AbstractBehavior, ActorContext, Behaviors}

object Barista {

  final case class OrderCoffee(whom: String, coffee: Coffee)

  def apply(): Behavior[OrderCoffee] =
    Behaviors.setup(context => new BaristaBehavior(context))

  def printOrders(orders: Set[(String, Coffee)]): String = {
    val formattedOrders = orders
      .map(order => s"${order._1}->${order._2}")
      .reduce((acc, s) => acc + "," + s)
    s"[$formattedOrders]"
  }

  class BaristaBehavior(context: ActorContext[OrderCoffee])
      extends AbstractBehavior[OrderCoffee](context) {

    private val orders: mutable.Map[String, Coffee] = mutable.Map.empty

    override def onMessage(msg: OrderCoffee): Behavior[OrderCoffee] = msg match {
      case OrderCoffee(whom, coffee) =>
        orders.put(whom, coffee)
        context.log.info("Orders:" + printOrders(orders.toSet))
        this
    }
  }
}
