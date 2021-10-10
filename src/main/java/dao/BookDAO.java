package dao;

import model.Book;
import model.Topic;
import model.Year;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BookDAO {

    public static Path DEFAULT_PATH = Paths.get("src/main/resources/books.csv");
    public static String COMMA = ",";
    private List<Book> library;

    public List<Book> getSortedComputingBooksByTitle(){
        return library.stream()
                .filter(book -> book.getTopic().getType().equalsIgnoreCase("COMPUTING"))
                .sorted(Comparator.comparing(Book::getTitle))
                .collect(Collectors.toList());
    }

    public Map<Topic, List<Book>> bookByTopic(){
        return library.stream()
                .collect(Collectors.groupingBy(Book::getTopic));
    }

    public Map<Topic, Long> bookCountByTopic(){
        return library.stream()
                .collect(Collectors.groupingBy(Book::getTopic, Collectors.counting()));
    }

    public List<Book> getLibrary(){
        return library;
    }

    public void parseLibrary(Path inputFilePath){
            try {

                File inputF = new File(String.valueOf(inputFilePath));
                InputStream inputFS = new FileInputStream(inputF);
                BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));

                // skip the header of the csv
                library = br.lines().skip(1).map(mapToItem).collect(Collectors.toList());
                br.close();
            } catch (IOException e) {

            }
    }

    public List<String> getBookTitles(){
        return library.stream()
                .map(book -> book.getTitle())
                .collect(Collectors.toList());
    }

    public void parseLibrary(){
        try {

            File inputF = new File(String.valueOf(DEFAULT_PATH));
            InputStream inputFS = new FileInputStream(inputF);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));

            // skip the header of the csv
            library = br.lines().skip(1).map(mapToItem).collect(Collectors.toList());
            br.close();
        } catch (IOException e) {

        }
    }

    public void dummyMethodThrowingException() throws ArrayIndexOutOfBoundsException{
        throw new ArrayIndexOutOfBoundsException("Array Index out of bound thrown");
    }

    public void dummyMethodThrowingNullException() throws NullPointerException{
        throw new NullPointerException("Null pointer thrown");
    }

    private Function<String, Book> mapToItem = (line) -> {
        String[] p = line.split(COMMA);// a CSV has comma separated lines

        /*if (p[2] != null && p[2].trim().length() > 0 &&
                p[3] != null && p[3].trim().length() > 0 &&
                    p[4] != null && p[4].trim().length() > 0 ) {


        }*/
        List<String> authors = new ArrayList<>();
        authors.add(p[4]);

        Book item = new Book(p[0], p[1], new Topic(p[2]),
                new Year(p[3]), authors);
        System.out.println(line);
        return item;
        //more initialization goes here

    };

}
