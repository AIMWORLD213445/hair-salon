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

  public ont getStylistId() {
    return name;
  }

  public int getId() {
  return id;
  }
}
