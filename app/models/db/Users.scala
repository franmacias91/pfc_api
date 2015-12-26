package models.db

import models.User
import slick.driver.H2Driver.api._

/**
  * Created by hector on 26/12/15.
  */
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
