package repositories

import models.{Users,User}
import play.api.db.DB
import play.api.Play.current
import slick.driver.H2Driver.api._

import scala.concurrent.Future

/**
  * Created by hector on 4/01/16.
  */
trait UserRepository {
  def findAll: Future[Seq[User]]
  def create(user: User): Future[User]
}

object UserRepository extends UserRepository{

  private[this] val logger = org.log4s.getLogger

  val users = TableQuery[Users] //FIXME: Move to User.scala??
  def db: Database = Database.forDataSource(DB.getDataSource()) //FIXME: Misplaced¿?¿?

  def findAll: Future[Seq[User]] = {
    logger.debug("Retrieving all users")
    db.run(users.result)
  }

  def create(user: User): Future[User] = {
    logger.debug("Creating new user")
    db.run((users returning users.map(_.id)
      into ((user,id) => user.copy(id=Some(id)))
      ) += user)
  }
}
