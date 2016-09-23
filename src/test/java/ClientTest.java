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
    assertEquals("555-555-5555", client.getPhone());
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

  @Test
  public void all_returnsAllClientInstances_true() {
    client.save();
    Client secondClient = new Client("Tim", "111-111-1111", 1);
    secondClient.save();
    assertEquals(true, Client.all().get(0).equals(client));
    assertEquals(true, Client.all().get(1).equals(secondClient));
  }

  @Test
  public void equals_returnsTrueIfCharacteristicsAreTheSame() {
    Client secondClient = new Client("Brian", "555-555-5555", 1);
    assertTrue(client.equals(secondClient));
}

  @Test
  public void save_returnsTrueIfFieldsAreSame_true() {
    client.save();
    assertEquals(true, Client.all().get(0).equals(client));
  }

  @Test
  public void save_assignsIdToObject() {
    client.save();
    Client savedClient = Client.all().get(0);
    assertEquals(client.getId(), savedClient.getId());
  }

  @Test
  public void save_savesStylistIdIntoDB_true() {
    Stylist stylist = new Stylist("John", "555-555-5555");
    stylist.save();
    client = new Client("Brian", "999-999-9999", stylist.getId());
    client.save();
    Client savedClient = Client.find(client.getId());
    assertEquals(savedClient.getStylistId(), stylist.getId());
  }

  @Test
  public void find_returnsClientWithSameId_secondEntry() {
    client.save();
    Client secondClient = new Client("Brian", "999-999-9999", 1);
    secondClient.save();
    assertEquals(Client.find(secondClient.getId()), secondClient);
  }

  @Test
  public void delete_deletesClient_true() {
    Client client = new Client("Brian", "999-999-9999", 1);
    client.save();
    int clientId = client.getId();
    client.delete();
    assertEquals(null, Client.find(clientId));
  }

  @Test
  public void update_UpdatesTheClient_Void(){
    client.save();
    client.update("Claire","111-111-1111", 2);
    assertEquals("Claire", Client.find(client.getId()).getName());
    assertEquals("111-111-1111", Client.find(client.getId()).getPhone());
    assertEquals(2 ,Client.find(client.getId()).getStylistId());
  }


}
