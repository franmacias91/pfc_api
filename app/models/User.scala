package models

import models.db.Users
import play.api.db.DB
import slick.lifted.TableQuery
import slick.driver.H2Driver.api._


/**
  * Domain model: An user with a some basic attributes.
  *
  * @param id unique identifier
  * @param name user's name
  * @param surname user's surname
  * @param username user's login name
  * @param email user's email
  */
case class User(id: Option[Long] = None, name: String, surname: String, username: String, email: String)

/**
  * Data access functions
  */
/*object User{

  // Base Slick database query to use for data access.
  val users = TableQuery[Users]

}*/
