# Advanced Car Dealership Application

This project is an advanced car dealership management application built with Java. It utilizes a DAO (Data Access Object) pattern to interact with a MySQL database for managing vehicles, dealerships, sales contracts, and lease contracts. The application follows best practices for modularity and database integration, with clear separation of concerns.

---

## **Project Features**

- **Vehicle Management**:
    - Search for vehicles by price, make/model, year, color, mileage, or type.
    - Add and remove vehicles from the database.

- **Dealership Management**:
    - Manage dealerships and their inventories.
    - Link vehicles to dealerships using MySQL relationships.

- **Sales and Lease Contracts**:
    - Record and manage sales and lease contracts.
    - Store contracts in respective `sales_contracts` and `lease_contracts` tables.

- **DAO Pattern**:
    - The DAO (Data Access Object) pattern is implemented to abstract database operations, providing a clean interface for CRUD (Create, Read, Update, Delete) operations.

---

## **Database Setup**

### **Schema**
The application interacts with the following MySQL tables:
1. **`vehicles`**: Stores vehicle information.
2. **`dealerships`**: Stores dealership information.
3. **`inventory`**: Links vehicles to dealerships.
4. **`sales_contracts`**: Stores sales contract details.
5. **`lease_contracts`**: Stores lease contract details.

Refer to the provided SQL file for creating and populating the database.

---

## **Key Classes**

### **DAO Interfaces**

#### **VehicleDAO**
Defines the interface for vehicle-related database operations:
```java
public interface VehicleDAO {
    List<Vehicle> findVehicleByPriceRange(double minPrice, double maxPrice);
    List<Vehicle> findVehicleByMakeModel(String make, String model);
    List<Vehicle> findVehicleByYear(int minYear, int maxYear);
    List<Vehicle> findVehicleByColor(String color);
    List<Vehicle> findVehicleByMileage(int minMileage, int maxMileage);
    List<Vehicle> findVehicleByType(String vehicleType);
    void addVehicle(Vehicle vehicle);
    boolean removeVehicle(int vin);
    List<Vehicle> findAllVehicles();
    Vehicle findVehicleByVin(int vin);
}