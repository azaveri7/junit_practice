import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(FlakyTests.class)
public class RegressionTests {

    @Test
    public void testMethod(){
        System.out.println("Inside Regression Tests 1");
    }
}
