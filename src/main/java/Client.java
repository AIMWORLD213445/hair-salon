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

  // public static List<Client> all() {
  //
  // }
}
