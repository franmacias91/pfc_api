package messages

import models.Coffee

case class GetCoffeesResponse(coffees: Seq[Coffee])

case class AddCoffeeRequest(name: String, supID: Int, price: Double, sales: Int, total: Int)

case class AddCoffeeResponse(coffee: Coffee)
