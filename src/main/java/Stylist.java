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

  public String getName(){
    return name;
  }

  public String getPhone(){
    return phone;
  }

  public int getId() {
  return id;
  }
}
