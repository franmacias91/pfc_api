package messages

import models.Coffee
import play.api.libs.json.{Json, Format}

object CoffeeImplicits {

  implicit val coffeeFormat: Format[Coffee] = Json.format[Coffee]
  implicit val getCoffeesResponseFormat: Format[GetCoffeesResponse] = Json.format[GetCoffeesResponse]
  implicit val addCoffeeRequestFormat: Format[AddCoffeeRequest] = Json.format[AddCoffeeRequest]
  implicit val addCoffeesResponseFormat: Format[AddCoffeeResponse] = Json.format[AddCoffeeResponse]

}