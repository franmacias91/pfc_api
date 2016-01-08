package repositories


import models.User
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import play.api.test.WithApplication

import scala.concurrent.Future


/**
  * Created by hector on 26/12/15.
  */
@RunWith(classOf[JUnitRunner])
class UserRepositorySpec extends Specification {
  "An UserRepository" should {
    "add users" in new WithApplication{
      val newUser: Future[User] = UserRepository.create(User(None,"Hector", "Vizcaino", "hektor7", "asd@asd.com"))



    }
  }
}
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
