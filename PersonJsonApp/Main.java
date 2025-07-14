import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Person p = new Person("Alice", 25, 2000.5, Arrays.asList("Reading", "Coding"));

        // Write to JSON file
        JsonManualExample.writeToJson(p, "person.json");

        // Read from JSON file
        Person fromJson = JsonManualExample.readFromJson("person.json");
        System.out.println("From JSON: " + fromJson);
    }
}
