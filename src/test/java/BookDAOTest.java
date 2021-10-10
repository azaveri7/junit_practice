import dao.BookDAO;
import model.Book;
import org.hamcrest.CoreMatchers;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class BookDAOTest {

    public static Path DEFAULT_PATH = Paths.get("src/test/resources/books.csv");
    private BookDAO dao;
    private List<Book> library;

    @BeforeClass
    public static void initExpensiveResource(){
        System.out.println("BookDAOTest init");

    }

    @AfterClass
    public static void destroyExpensiveResource(){
        System.out.println("BookDAOTest destroy");

    }

    @Before
    public void setUp() throws Exception{
        System.out.println("*****Start*****");
        this.dao = new BookDAO();
        this.dao.parseLibrary(DEFAULT_PATH);
        this.library = this.dao.getLibrary();
    }

    @After
    public void tearDown() throws Exception{
        this.dao = null;
        this.library = null;
        System.out.println("*****End*****");
    }

    @Test
    public void testprimitiveAssertions(){
        assertTrue(this.library.size() == 7);
    }

    @Test
    public void objectAssertionExamples(){
        assertNotNull(this.library);
    }

    @Test
    public void testMethod(){
        assertTrue(library.size() == 7);
        assertEquals(7, library.size());
        //Hamcrest matchers
        assertThat(library.size(), is(equalTo(7)));
        assertNotSame(new Object(), new Object());

        assertThat("Effective Java", containsString("Java"));
        assertThat("Effective Java", both(containsString("Java"))
                .and(containsString("Effective")));
        assertThat("Effective Java", allOf(containsString("Java")
                , containsString("Effective")));
        assertThat("Design patterns by Anand Zaveri is a good book" ,
                allOf(startsWith("Design"), containsString("Anand Zaveri"),
                endsWith("book")));
        assertThat("Design patterns by Anand Zaveri is a good book" ,
                anyOf(startsWith("Design"), containsString("Neha Zaveri"),
                        endsWith("book")));
        /*assertThat("Design patterns by Anand Zaveri is a good book" ,
                allOf(startsWith("Design"), containsString("Neha Zaveri"),
                        endsWith("book")));*/
        assertThat("Design patterns by Anand Zaveri is a good book" ,
                either(startsWith("Design")).or(containsString("Neha Zaveri")));

    }

    @Test
    public void testLibraryMethods(){
        final List<String> bookTitles = this.dao.getBookTitles();

        // Below pairs are checking the same thing
        assertThat(bookTitles, hasItem("The Jungle Safari"));
        assertTrue(bookTitles.stream()
                    .anyMatch(t -> t.equalsIgnoreCase("The Jungle Safari")));

        assertThat(bookTitles, hasItems("Lord of the Rings Part 1",
                "Design Patterns"));
        assertTrue(bookTitles.stream()
                    .anyMatch(t -> t.equalsIgnoreCase("Lord of the Rings Part 1")
                    || t.equalsIgnoreCase("Design Patterns")));


    }

    /*
    Exception tests
    3 options

    1. using expected
    2. try/catch idiom
    3. expectedexception rule idiom
     */

    // 1st option

    @Test(expected = Exception.class)
    public void exceptionTest() throws Exception {
        throw new Exception("foo");
    }

    //This will pass
    @Test(expected = Exception.class)
    public void ioExceptionPassTest() throws Exception {
        throw new IOException("foo");
    }

    // this will fail
    @Test(expected = IOException.class)
    public void ioExceptionFailTest() throws Exception {
        throw new Exception("foo");
    }

    // 2nd option

    @Test
    public void showTestCaseException(){
        try{
            this.dao.dummyMethodThrowingException();
            fail("expected an ArrayIndexOutOfBoundsException");
        } catch (ArrayIndexOutOfBoundsException e){
            assertThat(e.getMessage(),
                    CoreMatchers.containsString("Array Index"));
        }
    }

    // 3rd option
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void showTestCaseExceptionRule_Pass(){
        // set expectation
        thrown.expect(ArrayIndexOutOfBoundsException.class);
        thrown.expectMessage("Array Index");
        this.dao.dummyMethodThrowingException();
    }

    @Test
    public void showTestCaseExceptionRule_Fail(){
        // set expectation
        thrown.expect(ArrayIndexOutOfBoundsException.class);
        thrown.expectMessage("Array Index");
        this.dao.dummyMethodThrowingNullException();
    }

    /*
    Rules supported in jUnit4
        ExpectedException
        TemporaryFolder
        TestName
        Timeout
        ErrorCollector
        ClassRule
        ExternalResource
        RuleChain

        Custom rules:
         e.g.
         MyRuleOne extends ExternalResource
         MyRuleTwo extends TemporaryFolder


     */
}
