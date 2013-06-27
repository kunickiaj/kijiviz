package controllers

import scala.collection.JavaConverters._

import play.api._
import play.api.mvc._

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.HBaseConfiguration

import org.kiji.schema.KConstants
import org.kiji.schema.Kiji
import org.kiji.schema.KijiTable
import org.kiji.schema.KijiURI
import org.kiji.schema.layout.KijiTableLayout

object TableViewer extends Controller {

  private val mConf: Configuration = HBaseConfiguration.create()

  /** GET /tables */
  def index = Action {

    // TODO: Parameterize the kiji instance used.
    val kiji: Kiji = Kiji.Factory.open(
        KijiURI.newBuilder().withInstanceName(KConstants.DEFAULT_INSTANCE_NAME).build(),
        mConf)

    // List the tables available in the view.
    val tableNames: Seq[String] = kiji.getTableNames().asScala

    kiji.release()

    Ok(views.html.tableviewer_index(tableNames))
  }


  /** GET /tables/<tablename> */
  def show(tableName: String) = Action {
    // Display the layout for the table.

    val kiji: Kiji = Kiji.Factory.open(
        KijiURI.newBuilder().withInstanceName(KConstants.DEFAULT_INSTANCE_NAME).build(),
        mConf)

    val table: KijiTable = kiji.openTable(tableName)
    val layout: KijiTableLayout = table.getLayout()
    table.release()
    kiji.release()
    Ok(views.html.tableviewer_show(tableName, layout.getDesc()))
  }

  /** GET /tables/<tablename>/preview/<familyName> */
  def showFamily(tableName: String, familyName: String) = Action {
    // Display the layout for the table.

    val kiji: Kiji = Kiji.Factory.open(
        KijiURI.newBuilder().withInstanceName(KConstants.DEFAULT_INSTANCE_NAME).build(),
        mConf)

    val table: KijiTable = kiji.openTable(tableName)
    val layout: KijiTableLayout = table.getLayout()
    table.release()
    kiji.release()
    Ok(views.html.tableviewer_showFamily(tableName, familyName))
  }

}
