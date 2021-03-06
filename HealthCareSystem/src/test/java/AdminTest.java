import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;
import hospitalmanagementsystem.users.Admin;

/**
 * Tests for Admin Class
 * @author Jack Rodman
 */
public class AdminTest {
	// Users
	static Admin ad1;
	static Admin ad2;

	/**
	 * Initialise User variables to use in testing
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		// create Administrators
		ad1 = new Admin("John Doe", "+4512345678");
		ad2 = new Admin("Andy Admin", "+4912345678");
	}
	
	/**
	 * Tests the getType method in the Admin class.
	 */
	@Test
	public void getTypeTest() { 
		// ensure that getType returns "Admin"
		assertEquals("Admin", ad1.getType());
	}
	
	/**
	 * Tests the moveDepartment method of the Admin class.
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void moveDepartmentTest() {
		// An Admin cannot move departments so UnsupportedOperationException should be thrown
		ad2.moveDepartment("Emergency");
	}
}