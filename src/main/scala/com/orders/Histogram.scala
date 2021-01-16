package com.orders

import java.time.ZonedDateTime

import com.orders.Model.Order

object Histogram {
  def filterOrders(orders: List[Order], dateLowerbound: ZonedDateTime, dateUpperbound: ZonedDateTime): List[Order] = {
    orders.filter(order => order.date.isAfter(dateLowerbound) && order.date.isBefore(dateUpperbound))
  }

  def buildHistogram(orders: List[Order], monthThreshold: Int = 12, bins: Int = 3): Unit = {

    val g = orders.groupBy(order => order.date.getMonth)
    println(g)
    g
  }
}
