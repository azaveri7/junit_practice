import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        BookDAOTest.class,
        FeatureTests.class,
        IntegrationTests.class,
        PerformanceTests.class
})

public class MyTestSuites {



}
