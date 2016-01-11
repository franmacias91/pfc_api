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

  val users = TableQuery[Users] //FIXME: Move to User.scala??
  private def db: Database = Database.forDataSource(DB.getDataSource()) //FIXME: Misplaced¿?¿?

  def findAll: Future[Seq[User]] = {
    db.run(users.result)
  }

  def create(user: User): Future[User] = {
    db.run((users returning users.map(_.id)
      into ((user,id) => user.copy(id=Some(id)))
      ) += user)
  }
}
