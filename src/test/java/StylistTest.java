import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;
import org.sql2o.*;

public class StylistTest{
  Stylist stylist;
  @Before
  public void setUp() {
     DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", null, null);
     stylist = new Stylist ("John", "555-555-5555" );
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
  public void stylist_instantiatesCorrectly_true() {
    assertTrue(stylist instanceof Stylist);
  }

  @Test
  public void getName_instantiatesWithName_String(){
    assertEquals("John", stylist.getName());
  }

  @Test
  public void getId_instantiatesWithId_true(){
    stylist.save();
    assertTrue(stylist.getId()>0);
  }

  @Test
  public void equals_isTrueIfFieldsAretheSame_true(){
    Stylist secondStylist = new Stylist("John","555-555-5555");
    assertTrue(stylist.equals(secondStylist));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    stylist.save();
    assertTrue(Stylist.all().get(0).equals(stylist));
  }

  @Test
  public void all_returnsAllInstancesOfStylist_true() {
    stylist.save();
    Stylist secondStylist = new Stylist("Jane","444-444-4444");
    secondStylist.save();
    assertEquals(true, Stylist.all().contains(stylist));
    assertEquals(true, Stylist.all().contains(secondStylist));
  }
  @Test
  public void find_returnsStylistWithSameId_secondStylist() {
    stylist.save();
    Stylist secondStylist = new Stylist("Jane","444-444-4444");
    secondStylist.save();
    assertEquals(secondStylist, Stylist.find(secondStylist.getId()));
  }

  @Test
   public void delete_DeletesAStylist_Null(){
     stylist.save();
     int stylistId = stylist.getId();
     stylist.delete();
     assertEquals(null, Stylist.find(stylistId));
   }

   @Test
   public void update_UpdatesTheStylist_Void(){
     stylist.save();
     stylist.update("John", "333-333-3333");
     assertEquals("333-333-3333", Stylist.find(stylist.getId()).getPhone());
   }

   @Test
   public void getClients_RetrieveAllClientsFromStylist_List(){
     stylist.save();
     Client newClient = new Client("Brian", "555-555-5555", stylist.getId());
     newClient.save();
     Client secondClient = new Client("Susan","222-222-2222" ,stylist.getId());
     secondClient.save();
     assertTrue(stylist.getClients().equals(Client.all()));

   }
}
