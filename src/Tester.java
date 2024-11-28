public class Tester {
    private final StringBuilder testResults;

    // Constructor for tester
    public Tester() {
        testResults = new StringBuilder();
    }

    // Method: testCityClass
    //--------------------------------------------------------
    // Summary: Tests stack and queue functionalities.
    // Precondition: City, Package, and Vehicle classes are implemented.
    // Postcondition: Records the result of each test case.
    //--------------------------------------------------------
    public void testCityClass() {
        City city = new City("TestCity");
        Package package1 = new Package("P1", "TestCity");
        Package package2 = new Package("P2", "TestCity");
        Vehicle vehicle1 = new Vehicle("V1", 3.0, "TestCity");
        Vehicle vehicle2 = new Vehicle("V2", 5.0, "TestCity");

        // Test adding packages to the city stack directly
        city.packages.push(package1);
        city.packages.push(package2);
        if (city.packages.pop() == package2) {
            testResults.append("testCityClass - Add/Remove Package: PASS\n");
        } else {
            testResults.append("testCityClass - Add/Remove Package: FAIL\n");
        }

        // Test adding vehicles with priority directly
        city.vehicles.enqueue(vehicle1);
        city.vehicles.enqueue(vehicle2);
        if (city.vehicles.dequeue() == vehicle2) {
            testResults.append("testCityClass - Vehicle Priority Queue: PASS\n");
        } else {
            testResults.append("testCityClass - Vehicle Priority Queue: FAIL\n");
        }
    }

    // Method: testDLinkedListClass
    //--------------------------------------------------------
    // Summary: Tests the DLinkedList class by adding and removing items.
    // Precondition: DLinkedList and Node classes are implemented.
    // Postcondition: Records the result of each test case.
    //--------------------------------------------------------
    public void testDLinkedListClass() {
        DLinkedList<Package> list = new DLinkedList<>();
        Package package1 = new Package("P1", "CityA");
        Package package2 = new Package("P2", "CityA");

        // Test addFirst and addLast
        list.addFirst(package1);
        if (list.getHeadNode().data == package1) {
            testResults.append("testDLinkedListClass - Add First: PASS\n");
        } else {
            testResults.append("testDLinkedListClass - Add First: FAIL\n");
        }

        list.addLast(package2);
        if (list.getHeadNode().next.data == package2) {
            testResults.append("testDLinkedListClass - Add Last: PASS\n");
        } else {
            testResults.append("testDLinkedListClass - Add Last: FAIL\n");
        }

        // Test remove by index
        if (list.remove(0) == package1) {
            testResults.append("testDLinkedListClass - Remove by Index: PASS\n");
        } else {
            testResults.append("testDLinkedListClass - Remove by Index: FAIL\n");
        }
    }

    // Method: testMissionExecution
    //--------------------------------------------------------
    // Summary: Tests the Mission class by executing a full mission.
    // Precondition: City, Vehicle, Package, and Mission classes are implemented.
    // Postcondition: Records the result of each test case.
    //--------------------------------------------------------
    public void testMissionExecution() {
        City startCity = new City("StartCity");
        City midCity = new City("MidCity");
        City lastCity = new City("LastCity");

        Package package1 = new Package("P1", "StartCity");
        Package package2 = new Package("P2", "StartCity");
        Package package3 = new Package("P3", "MidCity");

        startCity.packages.push(package1);
        startCity.packages.push(package2);
        midCity.packages.push(package3);

        Vehicle vehicle = new Vehicle("V1", 5.0, "StartCity");
        startCity.vehicles.enqueue(vehicle);

        Mission mission = new Mission("StartCity-MidCity-LastCity-1-1-0");
        mission.initiate(new City[] { startCity, midCity, lastCity });

        // Check if the vehicle moved to the last city
        if (lastCity.vehicles.dequeue() == vehicle) {
            testResults.append("testMissionExecution - Vehicle Movement: PASS\n");
        } else {
            testResults.append("testMissionExecution - Vehicle Movement: FAIL\n");
        }

        // Check if package is unloaded at the last city
        if (lastCity.packages.pop() == package3) {
            testResults.append("testMissionExecution - Package Unloading: PASS\n");
        } else {
            testResults.append("testMissionExecution - Package Unloading: FAIL\n");
        }
    }

    // Method: testEdgeCases
    //--------------------------------------------------------
    // Summary: Tests edge cases.
    // Precondition: Methods below are implementable.
    // Postcondition: Records the result of each test case.
    //--------------------------------------------------------
    public void testEdgeCases() {
        City emptyCity = new City("EmptyCity");

        // Edge case: Dequeue vehicle from an empty city
        if (emptyCity.vehicles.dequeue() == null)
        {
            testResults.append("testEdgeCases - Empty City Vehicle Dequeue: PASS\n");
        } else {
            testResults.append("testEdgeCases - Empty City Vehicle Dequeue: FAIL\n");
        }

        // Edge case: Remove at invalid index in DLinkedList
        DLinkedList<Package> list = new DLinkedList<>();
        if (list.remove(5) == null)
        {
            testResults.append("testEdgeCases - DLinkedList Invalid Index: PASS\n");
        } else {
            testResults.append("testEdgeCases - DLinkedList Invalid Index: FAIL\n");
        }
    }

    // Method: runTests
    //--------------------------------------------------------
    // Summary: Runs all test cases and outputs results.
    // Precondition: Methods below are implementable.
    // Postcondition: All test results are printed to the console.
    //--------------------------------------------------------
    public void runTests() {
        testCityClass();
        testDLinkedListClass();
        testMissionExecution();
        testEdgeCases();

        System.out.println(testResults);
    }

    // main method for tester
    public static void main(String[] args) {
        Tester tester = new Tester();
        tester.runTests();
    }
}