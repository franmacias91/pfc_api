import models.db.Users

import org.scalatest._
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Span, Seconds}
import slick.driver.H2Driver.api._
import slick.jdbc.meta.MTable
import slick.lifted.TableQuery

/**
  * Created by hector on 26/12/15.
  */
class PersistenceSpec extends FunSuite with BeforeAndAfter with ScalaFutures{
  implicit override val patienceConfig = PatienceConfig(timeout = Span(5, Seconds))

  val users = TableQuery[Users]

  var db: Database = _

  def createSchema() = db.run(users.schema.create).futureValue

  //def insertUser(): Long = db.run(users += (1, "Héctor", "Vizcaíno", "hektor7", "xxx@yyy.com")).futureValue

  before { db = Database.forConfig("h2mem1") }

  test("Creating the Schema works") {
    createSchema()

    val tables = db.run(MTable.getTables).futureValue

    assert(tables.size == 1)
    assert(tables.count(_.name.name.equalsIgnoreCase("users")) == 1)
  }
}
