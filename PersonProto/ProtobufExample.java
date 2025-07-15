package PersonProto;

import PersonProto.PersonProto;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class ProtobufExample {
    public static void main(String[] args) {
        try {
            // 1. Create a Person object
            PersonProto.Person person = PersonProto.Person.newBuilder()
                    .setName("Alice")
                    .setAge(30)
                    .build();

            // 2. Serialize to file
            FileOutputStream output = new FileOutputStream("person_data.bin");
            person.writeTo(output);
            output.close();
            System.out.println("Data written to person_data.bin");

            // 3. Deserialize from file
            FileInputStream input = new FileInputStream("person_data.bin");
            PersonProto.Person deserialized = PersonProto.Person.parseFrom(input);
            input.close();

            // 4. Print deserialized data
            System.out.println("Deserialized Person:");
            System.out.println("Name: " + deserialized.getName());
            System.out.println("Age: " + deserialized.getAge());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}