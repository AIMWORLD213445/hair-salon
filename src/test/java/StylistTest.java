import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;
import org.sql2o.*;

public class StylistTest{
  Stylist stylist;
  @Before
  public void setUp() {
     DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon-test", null, null);
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
    assertEquals("John", Stylist.getName());
  }

  // @Test
  // public void getId_instantiatesWithId_true(){
  //   stylist.save();
  //   assertTrue(stylist.getId()>0);
  // }
}
