package app.controllers;

import app.models.*;
import app.tools.Json;

import static spark.Spark.*;
import spark.*;

public class PostitsController {

/*
$.ajax({
  type: "POST",
  url: "postits",
  data: JSON.stringify({text : "Hello", star : true}),
  dataType: "json",
  success: function(data){
    console.log(data)
  }
});
 */
  public String insert(Request request, Response response) {
    response.type("application/json");
    response.status(200);
    Postit postit = new Postit();
    postit.fromJsonString(request.body());
    postit.insert();
    return Json.stringify(postit.readable());
  }
/*
  $.ajax({
    type: "PUT",
    url: "postits/52fb1a2730046bb2be3f2e1a",
    data: JSON.stringify({_id: "52fb1a2730046bb2be3f2e1a", text : "===COUCOU===", star : true}),
    dataType: "json",
    success: function(data){
      console.log(data)
    }
  });
*/
  public String update(Request request, Response response) {
    response.type("application/json");
    response.status(200);
    String id = request.params(":id"); //tester si id existe ou pas
    Postit postit = new Postit();
    postit.fromJsonString(request.body());
    postit.update();
    return Json.stringify(postit.readable());
  }

/*
  $.get("postits/52fb1dfd300432e650df4a48",function(data){ console.log(data)})
 */
  public String getOne(Request request, Response response) {
    response.type("application/json");
    response.status(200);
    String id = request.params(":id");
    Postit postit = new Postit();
    postit.fetch(id);
    return Json.stringify(postit.readable());
  }

/*
  $.get("postits",function(data){ console.log(data)})
 */
  public String getAll(Request request, Response response) {
    response.type("application/json");
    response.status(200);
    return Json.stringify(Postit.fetchAllReadable());
  }

/*
  $.ajax({
    type: "DELETE",
    url: "postits/52fbe8b13004d9284b1d48e6",
    dataType: "json",
    success: function(data){
      console.log(data)
    }
  });
*/
  public String delete(Request request, Response response) {
    response.type("application/json");
    response.status(200);
    String id = request.params(":id");
    Postit postit = new Postit();
    postit.remove(id);
    return Json.stringify(postit.readable());
  }

}