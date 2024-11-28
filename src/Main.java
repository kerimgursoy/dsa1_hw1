import java.io.*;
import java.util.Scanner;

public class Main {
    // Method: findCity
    //--------------------------------------------------------
    // Summary: finds the City in the memory.
    // Precondition: cities are initialized and have a cityName String.
    // Postcondition: Return City if exists, or returns null.
    //--------------------------------------------------------
    private static City findCity(City[] cities, String cityName) {
        for (City city : cities) {
            if (city != null && city.name.equals(cityName)) {
                return city;
            }
        }
        return null;
    }
    public static void main(String[] args) throws IOException {
        //in order to load the text files, use args. add "cities.txt packages.txt vehicles.txt missions.txt result.txt" to program arguments in configurations.
        if (args.length < 5) {
            return;
        }

        // array for cities
        City[] cities = new City[100];
        int cityCount = 0;

        // load city names
        Scanner cityScanner = new Scanner(new File(args[0]));
        while (cityScanner.hasNextLine()) {
            String line = cityScanner.nextLine();
            cities[cityCount++] = new City(line);// increase the index after adding a city
        }
        cityScanner.close(); //close the scanner

        // load packages by splitting ID and cities
        Scanner packageScanner = new Scanner(new File(args[1]));
        while (packageScanner.hasNextLine()) {
            String line = packageScanner.nextLine();
            String[] parts = line.split(" ");
            String packageId = parts[0];
            String cityName = parts[1];
            Package packageItem = new Package(packageId, cityName);
            City city = findCity(cities, cityName);
            if (city != null) {
                city.packages.push(packageItem); // check if the city exists
            }
        }
        packageScanner.close();

        // load vehicles by splitting ID, location and importance
        Scanner vehicleScanner = new Scanner(new File(args[2]));
        while (vehicleScanner.hasNextLine()) {
            String line = vehicleScanner.nextLine();
            String[] parts = line.split(" ");
            String vehicleId = parts[0];
            String location = parts[1];
            double importance = Double.parseDouble(parts[2]);
            Vehicle vehicle = new Vehicle(vehicleId, importance, location);
            City city = findCity(cities, location);
            if (city != null) {
                city.vehicles.enqueue(vehicle); // check if the city exists
            }
        }
        vehicleScanner.close(); // close the scanner

        //load missions
        Scanner missionScan = new Scanner(new File(args[3]));
        while ( missionScan.hasNextLine()){
            String line = missionScan.nextLine();
            Mission mission = new Mission(line);
            mission.initiate(cities); // initiate the missions
        }

        //writing the output using city objects
        PrintWriter writer = new PrintWriter(new FileWriter(args[4]));
        for (int i = 0; i < cityCount; i++) {
            City city = cities[i];
            writer.println(city.name);
            writer.println("Packages:");

            //print packages
            Node<Package> packageNode = city.packages.getTopNode();
            while (packageNode != null) {
                writer.println(packageNode.data.packageId);
                packageNode = packageNode.next;
            }

            writer.println("Vehicles:");
            //print vehicles
            Node<Vehicle> vehicleNode = city.vehicles.getFrontNode();
            while (vehicleNode != null) {
                writer.println(vehicleNode.data.vehicleId);
                vehicleNode = vehicleNode.next;
            }
            writer.println("-------------");
        }
        writer.close();
    }
}