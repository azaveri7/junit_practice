import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.ExcludeCategory(FlakyTests.class)
@Suite.SuiteClasses({
        FlakyTests.class,
        BookDAOTest.class,
        FeatureTests.class,
        IntegrationTests.class,
        PerformanceTests.class
})

public class FlakyTestSuites {
}
