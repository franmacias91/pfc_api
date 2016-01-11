package repositories


import models.User
import org.junit.runner.RunWith
import org.specs2.mutable.{BeforeAfter, Specification}
import org.specs2.runner.JUnitRunner
import play.api.test.WithApplication

import scala.concurrent.Future


/**
  * Created by hector on 26/12/15.
  */
@RunWith(classOf[JUnitRunner])
class UserRepositorySpec extends Specification {

  "An UserRepository" should {
    "add valid users" in new WithApplication{
      val newUser: Future[User] = UserRepository.create(User(None,"Héctor", "Vizcaíno", "hektor7", "asd@asd.com"))

      newUser.value must beSome
      newUser.value.get.isSuccess must beTrue
      newUser.value.get.get.id must beSome[Long]
      newUser.value.get.get.name mustEqual "Héctor"
      newUser.value.get.get.surname mustEqual "Vizcaíno"
      newUser.value.get.get.username mustEqual "hektor7"
      newUser.value.get.get.email mustEqual "asd@asd.com"
    }
  }
}
//FIXME: Delete comments
/*class UserRepository extends FunSuite with BeforeAndAfter with ScalaFutures{
  implicit override val patienceConfig = PatienceConfig(timeout = Span(5, Seconds))

  val users = TableQuery[Users]

  var db: Database = _

  val userRepository: UserRepository

  def createSchema() = db.run(users.schema.create).futureValue

  //def insertUser(): Long = db.run(users += (1, "Héctor", "Vizcaíno", "hektor7", "xxx@yyy.com")).futureValue

  before { db = Database.forConfig("db.development") }

  test("Creating the Schema works") {
    createSchema()

    val tables = db.run(MTable.getTables).futureValue

    assert(tables.size == 1)
    assert(tables.count(_.name.name.equalsIgnoreCase("users")) == 1)
  }

  test("Create new correct user works"){
    val newUser = userRepository.create(User(None,"Héctor", "Vizcaino", "hektor7", "xxx@yyy.com"))

    assert(newUser.value.get.name.equalsIgnoreCase("Héctor"))

  }
}*/
