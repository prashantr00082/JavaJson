import java.io.*;
import java.util.*;

public class JsonManualExample {

    public static void writeToJson(Person person, String filename) throws IOException {
        StringBuilder json = new StringBuilder();

        json.append("{\n");
        json.append("  \"name\": \"").append(person.name).append("\",\n");
        json.append("  \"age\": ").append(person.age).append(",\n");
        json.append("  \"balanceAmount\": ").append(person.balanceAmount).append(",\n");

        json.append("  \"interests\": [");
        for (int i = 0; i < person.interests.size(); i++) {
            json.append("\"").append(person.interests.get(i)).append("\"");
            if (i < person.interests.size() - 1) json.append(", ");
        }
        json.append("]\n");
        json.append("}");

        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(json.toString());
        }
    }

    public static Person readFromJson(String filename) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line.trim());
            }
        }

        String json = content.toString();
        String name = getValue(json, "name");
        int age = Integer.parseInt(getValue(json, "age"));
        double balanceAmount = Double.parseDouble(getValue(json, "balanceAmount"));

        String interestsArray = json.split("\"interests\":")[1].trim();
        interestsArray = interestsArray.substring(interestsArray.indexOf("[") + 1, interestsArray.indexOf("]"));
        String[] interestsSplit = interestsArray.split(",");

        List<String> interests = new ArrayList<>();
        for (String item : interestsSplit) {
            String cleaned = item.replace("\"", "").trim();
            if (!cleaned.isEmpty()) interests.add(cleaned);
        }

        return new Person(name, age, balanceAmount, interests);
    }

    private static String getValue(String json, String key) {
        String[] parts = json.split("\"" + key + "\"\\s*:\\s*");
        if (parts.length < 2) return null;

        String valuePart = parts[1];
        int commaIndex = valuePart.indexOf(",");
        int endIndex = commaIndex != -1 ? commaIndex : valuePart.indexOf("}");

        String rawValue = valuePart.substring(0, endIndex).replace("\"", "").trim();
        return rawValue;
    }
}
