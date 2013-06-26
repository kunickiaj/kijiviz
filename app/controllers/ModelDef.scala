package controllers

import play.api._
import play.api.mvc._

object ModelDef extends Controller {

  def index = Action {
    Ok(views.html.modeldef())
  }

}
