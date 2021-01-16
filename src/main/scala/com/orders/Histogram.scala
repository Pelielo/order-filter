package com.orders

import java.time.{Period, ZonedDateTime}

import com.orders.Model.{Order, ProductOrder}

object Histogram {
  def filterOrders(orders: List[Order], dateLowerbound: ZonedDateTime, dateUpperbound: ZonedDateTime): List[Order] = {
    orders.filter(order => order.date.isAfter(dateLowerbound) && order.date.isBefore(dateUpperbound))
  }

  def monthsDiff(firstDate: ZonedDateTime, secondDate: ZonedDateTime): Int = {
    Period.between(firstDate.toLocalDate, secondDate.toLocalDate).getMonths.abs
  }

  def explodeOrders(orders: List[Order]): List[ProductOrder] = {
    orders.flatMap(order => order.items.map(item => ProductOrder(item.product.creationDate, order.date)))
  }

  def groupOrders(orders: List[ProductOrder], monthBins: List[(Int, Int)], monthThreshold: Int): Map[String, List[ProductOrder]] = {
    def grouper(order: ProductOrder, monthBins: List[(Int, Int)], monthThreshold: Int): String = {
      val now = ZonedDateTime.now
      for (bin <- monthBins) {
        val monthsOld = monthsDiff(order.productCreationDate, now)
        if (monthsOld >= bin._1 && monthsOld <= bin._2) return s"${bin._1}-${bin._2}"
      }
      s">${monthThreshold}"
    }

    orders.groupBy { order =>
      grouper(order, monthBins, monthThreshold)
    }
  }

  def orderStats(
                  orders: List[Order],
                  dateLowerbound: ZonedDateTime,
                  dateUpperbound: ZonedDateTime,
                  binIntervals: List[(Int, Int)],
                  thresholdValue: Int): Map[String, Int] = {
    val filtered = filterOrders(orders, dateLowerbound, dateUpperbound)
    val exploded = explodeOrders(filtered)
    val grouped = groupOrders(exploded, binIntervals, thresholdValue)

    grouped.map{ case (bin, order) => (bin, order.length)}
  }
}
