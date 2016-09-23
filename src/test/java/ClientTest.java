import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;
import org.sql2o.*;

public class ClientTest{
  Client client;
  @Before
  public void setUp() {
     DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", null, null);
     client = new Client ("Brian", "555-555-5555", 1);
  }

  @After
  public void tearDown() {
   try(Connection con = DB.sql2o.open()) {
     String deleteClientsQuery = "DELETE FROM clients *;";
     String deleteStylistsQuery = "DELETE FROM stylists *;";
     con.createQuery(deleteClientsQuery).executeUpdate();
     con.createQuery(deleteStylistsQuery).executeUpdate();
    }
  }

  @Test
  public void client_instantiatesCorrectly_true() {
    assertTrue(client instanceof Client);
  }
  @Test
  public void getName_instantiatesWithName_String() {
    assertEquals("Brian", client.getName());
  }
  @Test
  public void getPhone_instantiatesWithPhoneNumber_String() {
    assertEquals("555-555-5555" client.getPhone());
  }

  @Test
  public void getStylistId_instantiatesWithStylistId_int() {
    assertEquals(1, client.getStylistId());
  }

  @Test
  public void getId_clientsInstantiateWithId_1() {
    client.save();
    assertTrue(client.getId()> 0);
  }
}
