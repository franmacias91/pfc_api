package controllers

import messages.AddCoffeeRequest
import play.api.libs.json.Json
import play.api.mvc._
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._
import services.CoffeeServicesImpl
import messages.CoffeeImplicits._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class Coffee extends Controller {

  val coffeeServices = new CoffeeServicesImpl()


  def get = Action.async { implicit request =>
    coffeeServices.getCoffees().map { response =>
      Ok(Json.toJson(response))
    }.recover { case err =>
      InternalServerError(err.getMessage)
    }
  }


  def add = Action.async(BodyParsers.parse.json) { implicit request =>
    request.body.validate[AddCoffeeRequest].fold(
      errors => {
        Future.successful(BadRequest(Json.obj("status" -> "KO", "message" -> JsError.toJson(errors))))
      },
      req => {
        coffeeServices.addCoffee(req).map(res => Ok(Json.toJson(res)))
      })
  }


}
