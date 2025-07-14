package PersonXmlApp;

import java.io.*;
import java.util.*;

public class XmlManualExample {

    // ✅ Write a Person object to an XML file
    public static void writeToXml(Person person, String filename) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("<person>\n");
        sb.append("  <name>").append(person.name).append("</name>\n");
        sb.append("  <age>").append(person.age).append("</age>\n");
        sb.append("  <balanceAmount>").append(person.balanceAmount).append("</balanceAmount>\n");

        sb.append("  <interests>\n");
        for (String interest : person.interests) {
            sb.append("    <interest>").append(interest).append("</interest>\n");
        }
        sb.append("  </interests>\n");

        sb.append("</person>");

        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(sb.toString());
        }
    }

    // ✅ Read XML and convert it back to a Person object (pure manual parsing)
    public static Person readFromXml(String filename) throws IOException {
        StringBuilder xml = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                xml.append(line.trim());
            }
        }

        String data = xml.toString();

        // Extract values manually using tag names
        String name = getValue(data, "name");
        int age = Integer.parseInt(getValue(data, "age"));
        double balance = Double.parseDouble(getValue(data, "balanceAmount"));

        List<String> interests = new ArrayList<>();
        int index = 0;
        while ((index = data.indexOf("<interest>", index)) != -1) {
            int start = index + "<interest>".length();
            int end = data.indexOf("</interest>", start);
            String interest = data.substring(start, end);
            interests.add(interest);
            index = end + "</interest>".length();
        }

        return new Person(name, age, balance, interests);
    }

    // ✅ Helper to get a value between opening and closing XML tags
    private static String getValue(String xml, String tag) {
        String open = "<" + tag + ">";
        String close = "</" + tag + ">";
        int start = xml.indexOf(open) + open.length();
        int end = xml.indexOf(close);
        return xml.substring(start, end);
    }
}
