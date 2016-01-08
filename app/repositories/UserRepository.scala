package repositories

import models.{Users, User}
import slick.backend.DatabaseConfig
import slick.driver.JdbcProfile
import slick.lifted.TableQuery

import scala.concurrent.Future

/**
  * Created by hector on 4/01/16.
  */
trait UserRepository {
  def findAll: Future[Seq[User]]
  def create(user: User): Future[User]
}

object UserRepository extends UserRepository{

  val users = TableQuery[Users] //FIXME: Move to User.scala??
  val dbConfig = DatabaseConfig.forConfig[JdbcProfile]("db.development") //FIXME: Provisionally misplaced
  val db = dbConfig.db // all database interactions are realised through this object
  import dbConfig.driver.api._ // imports all the DSL goodies for the configured database

  override def findAll: Future[Seq[User]] = {
    try db.run(users.result)
    finally db.close
  }

  def create(user: User): Future[User] = {

    try db.run((users returning users.map(_.id)
      into ((user,id) => user.copy(id=Some(id)))
      ) += user) // By default db.run returns an Int value that depicts the number of rows affected
                 // so this change allow us to return the User entity with the new id
    finally db.close
  }
}
