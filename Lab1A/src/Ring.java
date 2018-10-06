import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.*;
import static java.nio.charset.StandardCharsets.*;

public class Ring {
    public String manufacturer;
    public String name;
    public String metal;
    public Integer  weight;
    public Double size;
    public Integer hallmark;
    public Integer price;

    //žiedo konstruktorius
    public Ring(String manufacturer, String name, String metal, Integer weight, Double size, Integer hallmark, Integer price)
    {
        this.manufacturer = manufacturer;
        this.name = name;
        this.metal = metal;
        this.weight = weight;
        this.size = size;
        this.hallmark = hallmark;
        this.price = price;
    }
    public static Ring parse(String input)
    {
        Scanner scan = new Scanner(input);
        String Manufactorer = scan.next();
        String Name = scan.next();
        String Metal = scan.next();
        Integer Weight = scan.nextInt();
        Double Size = scan.nextDouble();
        Integer Hallmark = scan.nextInt();
        Integer Price = scan.nextInt();
        return new Ring(Manufactorer,Name,Metal,Weight,Size,Hallmark,Price);
    }
    @Override
    public String toString() {
        return String.format("| %-20s | %-10s | %1s | %3d | %8.2f | %3d | %3d |", manufacturer, name, metal, weight, size, hallmark, price);
    }
}
