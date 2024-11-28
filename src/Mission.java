class Mission {
    String startCity;
    String midCity;
    String lastCity;
    int takeStart;
    int takeMid;
    int[] dropMid;

    //constructor for the mission values
    public Mission(String string){

        String[] splitted = string.split("-");

        startCity = splitted[0];
        midCity = splitted[1];
        lastCity = splitted[2];
        takeStart = Integer.parseInt(splitted[3]);
        takeMid = Integer.parseInt(splitted[4]);

        String[] drops = splitted[5].split(",");

        dropMid = new int[drops.length];
        for(int i = 0; i<drops.length; i++){
            dropMid[i] = Integer.parseInt(drops[i]);
        }
        //sorting the numbers from largest to smallest so the remove
        //method in dlinkedlist will work properly
        for (int i = 0; i < dropMid.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < dropMid.length; j++) {
                if (dropMid[j] < dropMid[min]) {
                    min = j;
                }
            }
            int temp = dropMid[i];
            dropMid[i] = dropMid[min];
            dropMid[min] = temp;
        }

    }
    // Method: findCity
    //--------------------------------------------------------
    // Summary: finds the City in the memory.
    // Precondition: cities are initialized and have a cityName String.
    // Postcondition: Return City if exists, or returns null.
    //--------------------------------------------------------
    private City findCity(City[] cities, String name) {
        for (City city : cities) {
            if (city != null && city.name.equals(name)) {
                return city;
            }
        }
        return null;
    }

    // Method: importantVehicle
    //--------------------------------------------------------
    // Summary: Finds the important vehicle in the memory.
    // Precondition: City is initialized.
    // Postcondition: Returns Vehicle.
    //--------------------------------------------------------
    private Vehicle importantVehicle(City city){

        Node<Vehicle> current = city.vehicles.getFrontNode();
        Node<Vehicle> temp = current;

        while(current != null){
            if(current.data.importance > temp.data.importance){
                temp = current;
            }
            current = current.next;
        }

        assert temp != null;
        return temp.data;
    }

    // Method: initiate
    //--------------------------------------------------------
    // Summary: initiates the mission
    // Precondition: cities is initialized.
    // Postcondition: doesn't return anything, modifies the City objects.
    //--------------------------------------------------------
    public void initiate(City[] cities){
        City start = findCity(cities, startCity);
        City mid = findCity(cities, midCity);
        City last = findCity(cities, lastCity);

        assert start != null;
        Vehicle car = importantVehicle(start);

        //take first
        for (int i = 0; i < takeStart; i++) {
            if (!start.packages.isEmpty()) {
                assert car != null;
                car.cargo.addFirst(start.packages.pop());
            }
        }
        //take mid
        for (int i = 0; i < takeMid; i++) {
            assert mid != null;
            car.cargo.addLast(mid.packages.pop());
        }

        //drop mid
        int n = 0;
        for(int i: dropMid){
            i=i-n;
            assert mid != null;
            mid.packages.push(car.cargo.remove(i));
            n++;
        }
        for(int i = car.cargo.getSize()-1; i>=0; i--){
            assert last != null;
            last.packages.push(car.cargo.removeFirst());
        }


        start.vehicles.dequeue();
        assert last != null;
        last.vehicles.enqueue(car);
    }
}
