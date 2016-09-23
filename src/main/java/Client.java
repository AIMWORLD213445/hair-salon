import java.util.List;
import org.sql2o.*;

public class Client{
  private String name;
  private String phone;
  private int id;
  private int stylistId;

  public Client (String name, String phone, int stylistId) {
    this.name = name;
    this.phone = phone;
    this.stylistId = stylistId;
  }

  public String getName() {
    return name;
  }

  public String getPhone() {
    return phone;
  }

  public int getStylistId() {
    return stylistId;
  }

  public int getId() {
  return id;
  }

  public static List<Client> all() {
    String sql = "SELECT * FROM clients";
    try(Connection con =DB.sql2o.open()){
      return con.createQuery(sql)
      .executeAndFetch(Client.class);
    }
  }

  public void save() {
    String sql = "INSERT INTO clients (name, phone, stylistId) VALUES (:name, :phone, :stylistId)";
    try(Connection con = DB.sql2o.open()){
      this.id = (int) con.createQuery(sql,true)
      .addParameter("name",this.name)
      .addParameter("phone",this.phone)
      .addParameter("stylistId",this.stylistId)
      .executeUpdate()
      .getKey();
    }
  }

  @Override
  public boolean equals(Object otherClient){
    if (!(otherClient instanceof Client)) {
      return false;
    } else {
      Client newClient = (Client) otherClient;
      return this.getId() == newClient.getId() &&
             this.getName().equals(newClient.getName()) &&
             this.getPhone().equals(newClient.getPhone()) &&
             this.getStylistId() == newClient.getStylistId();
    }
  }

  public static Client find(int id){
    String sql = "SELECT * FROM clients WHERE id =:id";
    try(Connection con = DB.sql2o.open()){
      return con.createQuery(sql)
      .addParameter("id",id)
      .executeAndFetchFirst(Client.class);
    }
  }

  public void update (String name, String phone, int stylistId) {
    String sql = "UPDATE clients SET name = :name, phone = :phone, stylistID = :stylistId WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
      .addParameter("name", name)
      .addParameter("phone", phone)
      .addParameter("stylistId", stylistId)
      .addParameter("id", this.id)
      .executeUpdate();
    }
  }

  public void delete() {
    String sql = "DELETE FROM clients WHERE id=:id";
    try(Connection con = DB.sql2o.open()){
      con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
    }
  }
}
