package com.orders

import java.time.ZonedDateTime

import com.orders.Model.{Item, Order, Product, ProductOrder}

object SampleData {
  val product_A: Product = Product("name_A", "category_A", 100, 10000, ZonedDateTime.parse("2020-01-01T00:00:00.000-00:00"))
  val product_B: Product = Product("name_B", "category_B", 100, 10000, ZonedDateTime.parse("2020-03-02T00:00:00.000-00:00"))
  val product_C: Product = Product("name_C", "category_C", 100, 10000, ZonedDateTime.parse("2020-09-02T00:00:00.000-00:00"))
  val product_D: Product = Product("name_D", "category_D", 100, 10000, ZonedDateTime.parse("2020-12-03T00:00:00.000-00:00"))

  val item_A: Item = Item(product_A, 10000, 100, 100)
  val item_B: Item = Item(product_B, 10000, 100, 100)
  val item_C: Item = Item(product_C, 10000, 100, 100)
  val item_D: Item = Item(product_D, 10000, 100, 100)

  val order_A: Order = Order(List(item_A), "name_A", "1111111", "address_A", 10000, ZonedDateTime.parse("2020-01-01T00:00:00.000-00:00"))
  val order_B: Order = Order(List(item_B, item_C), "name_B", "1111111", "address_B", 10000, ZonedDateTime.parse("2020-02-01T00:00:00.000-00:00"))
  val order_C: Order = Order(List(item_A, item_C), "name_C", "1111111", "address_A", 10000, ZonedDateTime.parse("2020-03-01T00:00:00.000-00:00"))
  val order_D: Order = Order(List(item_A, item_B, item_C, item_D), "name_D", "1111111", "address_A", 10000, ZonedDateTime.parse("2020-04-01T00:00:00.000-00:00"))

  val orders = List(order_A, order_B, order_C, order_D)
}
