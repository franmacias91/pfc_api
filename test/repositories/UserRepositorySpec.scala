package repositories


import org.junit.runner.RunWith
import models.{Users,User}
import play.api.db.DB
import slick.driver.H2Driver.api._
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import org.specs2.specification.BeforeAfterEach
import play.api.test.WithApplication
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future


/**
  * Created by hector on 26/12/15.
  */
@RunWith(classOf[JUnitRunner])
class UserRepositorySpec extends Specification with BeforeAfterEach{

  private[this] val logger = org.log4s.getLogger

  def db: Database = Database.forConfig("db.default")

  val users = TableQuery[Users]

  def createSchema() = db.run(users.schema.create)
  def dropSchema() = db.run(users.schema.drop)

  def before = initializeDatabase()

  def after = cleanUpDatabase()

  def initializeDatabase(): Any = {
    println("Configuring database...")
    createSchema()
  }

  def cleanUpDatabase(): Any = {
    println("Cleaning up database...")
    dropSchema()
  }


  "An UserRepository" should {
    "add valid users" in new WithApplication{

      val newUser: Future[User] = UserRepository.create(User(None,"Héctor", "Vizcaíno", "hektor7", "asd@asd.com"))

      newUser onFailure {
        case t => logger.error(t)(s"Creating user failed: ${t.getMessage}")
      }

      newUser.value must beSome
      newUser.value.get.isSuccess must beTrue
      newUser.value.get.get.id must beSome[Long]
      newUser.value.get.get.name mustEqual "Héctor"
      newUser.value.get.get.surname mustEqual "Vizcaíno"
      newUser.value.get.get.username mustEqual "hektor7"
      newUser.value.get.get.email mustEqual "asd@asd.com"
    }

    "list all users" in new WithApplication{
      val usersList: Future[Seq[User]] = UserRepository.findAll

      usersList.value must beSome
      usersList.value.get.isSuccess must beTrue

    }
  }


}