import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
     Map<String, Object> model = new HashMap<String, Object>();
     model.put("stylists", Stylist.all());
     model.put("template", "templates/index.vtl");
     return new ModelAndView(model, layout);
   }, new VelocityTemplateEngine());

   //posts genre to homepage
    post("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String stylistName = request.queryParams("stylist");
      Stylist newstylist = new Stylist(stylistName);
      newStylist.save();
      model.put("stylist", Stylist.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    //gets genre page
    get("/stylist/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist newStylist = Stylist.find(Integer.parseInt(request.params(":id")));
      model.put("stylist", newStylist);
      model.put("clients", theStylist.getClients());
      model.put("template", "templates/stylist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    //prints movies on genre page
    post("/stylist/stylistId", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist theStylist = Stylist.find(Integer.parseInt(request.queryParams("stylistId")));
      String clientName = request.queryParams("client-name");
      String phone-number = request.queryParams("phone-number");
      Client newClient = new Client(clientName,phone-number,theStylist.getId());
      newClient.save();
      String url = String.format("/stylist/%d", newClient.getStylistId());
      response.redirect(url);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    //lists all movies
    get("/clients", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("clients", Client.all());
      model.put("template", "templates/movies.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

//shows movie information
    get("/stylist/:stylistId/client/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":stylistId")));
      Client client = Client.find(Integer.parseInt(request.params(":id")));
      model.put("client", client);
      model.put("stylist", stylist);
      model.put("template", "templates/movie.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    //updates movie
    post("/stylist/:stylistId/client/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Client client = Client.find(Integer.parseInt(request.params("id")));
      String name = request.queryParams("name");
      String phone = request.queryParams("phone");
      Stylist stylist = Stylist.find(client.getStylistId());
      client.update(name, phone, client.getStylistId());
      String url = String.format("/stylist/%d/client/%d", stylist.getId(), client.getId());
      response.redirect(url);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    //deletes movie
    post("/stylist/:stylistId/client/:id/delete", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Client client = Client.find(Integer.parseInt(request.params("id")));
      Stylist stylist = Stylist.find(client.getStylistId());
      client.delete();
      model.put("clients", Client.all());
      model.put("stylist", stylist);
      model.put("template", "templates/stylist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    //updates genre
    post("/stylist/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params("id")));
      String name = request.queryParams("stylist");
      stylist.update(name);
      String phone = request.queryParams("stylist");
      stylist.update(phone);
      String url = String.format("/stylist/%d", stylist.getId());
      response.redirect(url);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    //deletes a genre
    post("/stylist/:id/delete", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params("id")));
      stylist.delete();
      model.put("stylists", Stylist.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
