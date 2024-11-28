class City {
    String name;
    Stack<Package> packages;
    Queue<Vehicle> vehicles;

    // constructor for city
    public City(String name) {
        this.name = name;
        this.packages = new Stack<>();
        this.vehicles = new Queue<>();
    }
}