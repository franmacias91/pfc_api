package services

import messages.{AddCoffeeResponse, AddCoffeeRequest, GetCoffeesResponse}
import repositories.CoffeeRepositoryH2

import scala.concurrent.{Future, ExecutionContext}

trait CoffeeServices {

  def getCoffees(): Future[GetCoffeesResponse]

  def addCoffee(req: AddCoffeeRequest): Future[AddCoffeeResponse]

}

class CoffeeServicesImpl(implicit val executionContext: ExecutionContext) extends CoffeeServices {

  val coffeeRepository = new CoffeeRepositoryH2

  override def getCoffees(): Future[GetCoffeesResponse] = coffeeRepository.get map GetCoffeesResponse

  override def addCoffee(req: AddCoffeeRequest): Future[AddCoffeeResponse] = {
    coffeeRepository.create(req.name, req.supID, req.price, req.sales,req.total) map AddCoffeeResponse
  }


}