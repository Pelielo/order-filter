package com.orders

import java.time.ZonedDateTime

object Model {
  case class Product (
                       name: String,
                       category: String,
                       weight: Int,
                       price: Long,
                       creationDate: ZonedDateTime
                     )

  case class Item (
                    product: Product,
                    cost: Long,
                    shippingFee: Long,
                    taxAmount: Long,
                  )

  case class Order (
                     items: List[Item],
                     customerName: String,
                     contactInfo: String,
                     shippingAddress: String,
                     grandTotal: Long,
                     date: ZonedDateTime,
                   )

  case class ProductOrder (
                          productCreationDate: ZonedDateTime,
                          orderDate: ZonedDateTime
                          )
}
