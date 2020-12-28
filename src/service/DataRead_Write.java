package service;

import enums.Banks;
import model.Customer;

import javax.print.attribute.standard.MediaSize;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public abstract class DataRead_Write {
    private static final Map<Banks, Path> FILES = new EnumMap<>(Banks.class);
    private static final String PATH = "src\\com\\company\\bank\\";

    public Path initialise(Banks name, String path) {
        if (!FILES.containsKey(name)) {
            FILES.put(name, Paths.get(path));
        }
        return FILES.get(name);
    }

    public static ArrayList<Customer> read(Banks name) throws IOException {
        ArrayList<Customer> customers = new ArrayList<>();
        Object obj;
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(FILES.get(name).toString()))) {
            while ((obj = input.readObject()) != null) {
                customers.add((Customer) obj);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public static void write(Banks name, Customer data) {
        FILES.putIfAbsent(name, Paths.get(PATH + name + ".txt"));
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(FILES.get(name).toString()))) {
            output.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void write(Banks name, List<Customer> customers) {
        FILES.putIfAbsent(name, Paths.get(PATH + name + ".txt"));
        for (Customer customer : customers) {
            write(name, customer);
        }
    }

    public static void update(Banks name, Customer data) {
        try {
            List<Customer> customers = read(name);
            for (int i = 0; i < customers.size(); i++) {
                if (customers.get(i).equals(data)) {
                    customers.set(i, data);
                    break;
                }
            }

            Files.deleteIfExists(FILES.get(name));
            write(name, customers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
