class Vehicle implements Comparable<Vehicle> {
    String vehicleId;
    double importance;
    String location;
    DLinkedList<Package> cargo;

    public Vehicle(String vehicleId, double importance, String location) {
        this.vehicleId = vehicleId;
        this.importance = importance;
        this.location = location;
        this.cargo = new DLinkedList<>();
    }
    // Method: compareTo
    //--------------------------------------------------------
    // Summary: compares two Vehicles.
    // Precondition: other is initialized.
    // Postcondition: returns if the importance is higher or not.
    //--------------------------------------------------------
    @Override
    public int compareTo(Vehicle other) {
        return Double.compare(this.importance, other.importance);
    }
}