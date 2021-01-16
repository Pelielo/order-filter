package com.orders

import java.time.ZonedDateTime

import com.orders.Histogram.orderStats

object Main extends App {
  val dateArgs = args(0).split(",").toList

  val firstDate = ZonedDateTime.parse(dateArgs.head)
  val secondDate = ZonedDateTime.parse(dateArgs.last)

  try {
    val binArgs = args(1).split(",").toList
    val bins = binArgs.init.map(bin => (bin(0).toString.toInt, bin(2).toString.toInt))
    val threshold = binArgs.last.toInt

    println(s"Running orderStats filtering between ${firstDate} and ${secondDate} with bins ${bins} and threshold ${threshold}")
    val result = orderStats(SampleData.orders, firstDate, secondDate, bins, threshold)
    result.map{case (bin, count) => println(s"Products with ${bin} months have been ordered ${count} times")}
  }
  catch {
    case e: IndexOutOfBoundsException =>
      val bins = List((1,3),(4,6),(7,12))
      val threshold = 12

      println(s"Running orderStats filtering between ${firstDate} and ${secondDate} with bins ${bins} and threshold ${threshold}")
      val result = orderStats(SampleData.orders, firstDate, secondDate, bins, threshold)
      result.map{case (bin, count) => println(s"Products with ${bin} months have been ordered ${count} times")}
  }
}