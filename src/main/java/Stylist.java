import java.util.List;
import org.sql2o.*;

public class Stylist{
  private String name;
  private String phone;
  private int id;

  public Stylist (String name, String phone) {
    this.name = name;
    this.phone = phone;
  }

  public String getName() {
    return name;
  }

  public String getPhone() {
    return phone;
  }

  public int getId() {
  return id;
  }

  public static List<Stylist> all() {
    String sql = "SELECT * FROM stylists";
    try(Connection con =DB.sql2o.open()){
      return con.createQuery(sql)
      .executeAndFetch(Stylist.class);
    }
  }

  public void save() {
    String sql = "INSERT INTO stylists (name, phone) VALUES (:name, :phone)";
    try(Connection con = DB.sql2o.open()){
      this.id = (int) con.createQuery(sql,true)
      .addParameter("name",this.name)
      .addParameter("phone",this.phone)
      .executeUpdate()
      .getKey();
    }
  }

  @Override
  public boolean equals(Object otherStylist){
    if (!(otherStylist instanceof Stylist)) {
      return false;
    } else {
      Stylist newStylist = (Stylist) otherStylist;
      return this.getName().equals(newStylist.getName()) &&
             this.getPhone().equals(newStylist.getPhone()) &&
             this.getId() == newStylist.getId();
    }
  }

  public static Stylist find(int id){
    String sql = "SELECT * FROM stylists WHERE id =:id";
    try(Connection con = DB.sql2o.open()){
      return con.createQuery(sql)
      .addParameter("id",id)
      .executeAndFetchFirst(Stylist.class);
    }
  }

  public void update (String name, String phone) {
    String sql = "UPDATE stylists SET name = :name, phone = :phone  WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
      .addParameter("name", name)
      .addParameter("phone", phone)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  public void delete() {
    String sql = "DELETE FROM stylists WHERE id=:id";
    try(Connection con = DB.sql2o.open()){
      con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  public List<Client> getClients(){
   String sql = "SELECT * FROM clients WHERE stylistId =:id";
   try(Connection con = DB.sql2o.open()){
     return con.createQuery(sql)
     .addParameter("id",this.id)
     .executeAndFetch(Client.class);
   }
 }
}
