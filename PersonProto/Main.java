import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        // Create a Person object using builder
        PersonOuterClass.Person person = PersonOuterClass.Person.newBuilder()
                .setName("Alice")
                .setAge(25)
                .setBalanceAmount(2500.75)
                .addAllInterests(Arrays.asList("Reading", "Gaming"))
                .build();

        // Serialize to Protobuf file
        try (FileOutputStream out = new FileOutputStream("person.bin")) {
            person.writeTo(out);
        }

        // Deserialize from Protobuf file
        PersonOuterClass.Person parsedPerson;
        try (FileInputStream in = new FileInputStream("person.bin")) {
            parsedPerson = PersonOuterClass.Person.parseFrom(in);
        }

        // Print to confirm
        System.out.println("Name: " + parsedPerson.getName());
        System.out.println("Age: " + parsedPerson.getAge());
        System.out.println("Balance: " + parsedPerson.getBalanceAmount());
        System.out.println("Interests: " + parsedPerson.getInterestsList());
    }
}
