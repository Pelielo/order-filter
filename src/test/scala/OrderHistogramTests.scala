import java.time.{ZoneId, ZonedDateTime}

import collection.mutable.Stack
import org.scalatest._
import flatspec._
import matchers._
import com.orders.Histogram.{buildHistogram, filterOrders}
import com.orders.Model.{Item, Order, Product}

class OrderHistogramTests extends AnyFlatSpec with should.Matchers {

  def fixture =
    new {
      val product_A: Product = Product("name_A", "category_A", 100, 10000, ZonedDateTime.parse("2020-01-01T00:00:00.000-00:00"))
      val product_B: Product = Product("name_B", "category_B", 100, 10000, ZonedDateTime.parse("2020-01-01T00:00:00.000-00:00"))
      val product_C: Product = Product("name_C", "category_C", 100, 10000, ZonedDateTime.parse("2020-01-01T00:00:00.000-00:00"))

      val item_A: Item = Item(product_A, 10000, 100, 100)
      val item_B: Item = Item(product_B, 10000, 100, 100)
      val item_C: Item = Item(product_C, 10000, 100, 100)

      val order_A: Order = Order(List(item_A), "name_A", "1111111", "address_A", 10000, ZonedDateTime.parse("2020-01-01T00:00:00.000-00:00"))
      val order_B: Order = Order(List(item_B), "name_B", "1111111", "address_B", 10000, ZonedDateTime.parse("2020-02-01T00:00:00.000-00:00"))
      val order_C: Order = Order(List(item_C), "name_C", "1111111", "address_A", 10000, ZonedDateTime.parse("2020-03-01T00:00:00.000-00:00"))
    }

  "The filterOrders function" should "filter the orders received based on order date" in {
    val f = fixture

    val orders: List[Order] = List(f.order_A, f.order_B, f.order_C)

    filterOrders(orders, ZonedDateTime.parse("2019-01-01T00:00:00.000-00:00"), ZonedDateTime.parse("2021-01-01T00:00:00.000-00:00")) should be(orders)
    filterOrders(orders, ZonedDateTime.parse("2020-01-01T00:00:00.000-00:00"), ZonedDateTime.parse("2020-03-01T00:00:00.000-00:00")) should be(List(f.order_B))
  }

  "The histogram function" should "build an histogram of orders based on product date and number of buckets" in {
    val f = fixture

    val orders: List[Order] = List(f.order_A, f.order_B, f.order_C)
    buildHistogram(orders)
  }
}