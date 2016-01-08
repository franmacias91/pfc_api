package models

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

class Users(tag: Tag) extends Table[User](tag, "USERS"){

  //Database columns definitions
  def id = column[Long]("ID", O.PrimaryKey, O.AutoInc)
  def name = column[String]("NAME")
  def surname = column[String]("SURNAME")
  def username = column[String]("USERNAME")
  def email = column[String]("EMAIL")

  //Constraints to enforce uniques values
  def usernameIndex = index("USERNAME_INDEX", username, unique = true)
  def emailIndex = index("EMAIL_INDEX", email, unique = true)

  // Every table needs a * projection with the same type as the table's type parameter
  // The * projection (e.g. select * ...) auto-transforms the tupled
  // column values to / from a User
  def * = (id.?, name, surname, username, email) <> (User.tupled, User.unapply)
}
