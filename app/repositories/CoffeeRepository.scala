package repositories

import models.Coffee
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global


trait CoffeeRepository {

  def get: Future[Seq[Coffee]]

  def create(
      name: String,
      supID: Int,
      price: Double,
      sales: Int,
      total: Int): Future[Coffee]

}

class CoffeeRepositoryH2 extends CoffeeRepository{

  def get: Future[Seq[Coffee]] = {
    Future.successful(Seq(
      Coffee("Name1", 1, 23.toDouble, 234, 23),
      Coffee("Name2", 2, 53.toDouble, 54, 57)
    ))
  }

  def create(
      name: String,
      supID: Int,
      price: Double,
      sales: Int,
      total: Int): Future[Coffee] = Future.successful(Coffee(name, supID, price, sales, total))


}