package com.yearup.dealership;

public class UserInterface {

    public static String filename = "inventory.csv";
    public Dealership currentDealership;
    private Vehicle v;

    public UserInterface(){
        currentDealership = DealershipFileManager.getFromCSV(filename);
    }


    public void display(){

        String options = """
                Please select from the following choices:
                1 - Find vehicles within a price range
                2 - Find vehicles by make / model
                3 - Find vehicles by year range
                4 - Find vehicles by color
                5 - Find vehicles by mileage range
                6 - Find vehicles by type (car, truck, SUV,)
                7 - List ALL vehicles
                8 - Add a vehicle
                9 - Remove a vehicle
                10 - Sell/Lease a Vehicle
                99 - Quit

                >>>\s""";

        int selection;

        // User Interface Loop
        do {
            System.out.println("Welcome to " + currentDealership.getName() + "!");
            selection = Console.PromptForInt(options);
            switch (selection) {
                case 1 -> processGetByPriceRequest();
                case 2 -> processGetByMakeModelRequest();
                case 3 -> processGetByYearRequest();
                case 4 -> processGetByColorRequest();
                case 5 -> processGetByMileageRequest();
                case 6 -> processGetByVehicleTypeRequest();
                case 7 -> processGetAllVehiclesRequest();
                case 8 -> processAddVehicleRequest();
                case 9 -> processRemoveVehicleRequest();
                case 10 -> processSellLeaseRequest();
                case 99 -> System.exit(0);
                default -> System.out.println("Invalid selection. Please try again.");
            }
        } while (selection != 99);



    }

    private void processSellLeaseRequest() {
    }


    private void processAddVehicleRequest() {
        //get lots of values from the user...
        int vin = Console.PromptForInt("Enter Vin: ");
        int year = Console.PromptForInt("Enter year: ");
        String make = Console.PromptForString("Enter make: ");
        String model = Console.PromptForString("Enter model: ");
        String vehicleType = Console.PromptForString("Enter vehicle type: ");
        String color = Console.PromptForString("Enter color:  ");
        int odometer = Console.PromptForInt("Enter odometer: ");
        double price = Console.PromptForDouble("Enter price: ");

        Vehicle v = new Vehicle(vin,year, make, model, vehicleType, color, odometer, price);

        currentDealership.addVehicleToInventory(v);
        DealershipFileManager.saveToCSV(currentDealership, filename);

    }
    private void processRemoveVehicleRequest() {
        int vin = Console.PromptForInt("Enter vin number of the vehicle you want to remove: ");
        currentDealership.removeVehicleFromInventory(vin);

    }
        private void processGetByVehicleTypeRequest () {
            String type = Console.PromptForString("Enter what type of vehicle you are looking for: ");
            for (Vehicle v : currentDealership.getVehiclesByType(type)) {
                displayVehicle(v);
            }

        }
    private void processGetByMileageRequest() {
        int min = Console.PromptForInt("Enter Min Miles: ");
        int max = Console.PromptForInt("Enter Max Miles: ");
        for(Vehicle v : currentDealership.getVehicleByOdometer(min, max)){
        displayVehicle(v);
        }
    }

    private void processGetByColorRequest() {
        String color = Console.PromptForString("Enter Color: ");
        for(Vehicle v : currentDealership.getVehiclesByColor(color)) {
            displayVehicle(v);
        }
    }

    private void processGetByYearRequest() {
        int min = Console.PromptForInt("Enter Start Year: ");
        int max = Console.PromptForInt("Enter End Year: ");
        for(Vehicle v : currentDealership.getVehicleByYear(min, max)){
            displayVehicle(v);
        }
    }


    private void processGetByMakeModelRequest() {
        String make =Console.PromptForString("Enter Make: ");
        String model = Console.PromptForString("Enter Model: ");
        for(Vehicle v : currentDealership.getVehicleByMakeModel(make, model)){
            displayVehicle(v);
        }
    }

    private void processGetByPriceRequest() {
        double min = Console.PromptForDouble("Enter min: ");
        double max = Console.PromptForDouble("Enter max: ");
        for(Vehicle v : currentDealership.getVehiclesByPrice(min, max)){
            displayVehicle(v);
        }
    }




    public void processGetAllVehiclesRequest(){
        for(Vehicle v : currentDealership.getInventory()){
            displayVehicle(v);
        }
    }

    public void displayVehicle(Vehicle v){
        System.out.println(v);
    }


}