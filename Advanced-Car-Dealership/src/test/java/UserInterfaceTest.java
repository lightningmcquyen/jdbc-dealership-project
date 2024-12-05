import com.pluralsight.dealership.Dealership;
import com.pluralsight.dealership.UserInterface;
import com.pluralsight.dealership.Vehicle;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserInterfaceTest {

    @Test
    void showAllVehicles_and_quit() {
        // Arrange: Initialize the dealership and UserInterface for testing
        Dealership dealership = new Dealership("Dummy Dealership", "923 Test St", "123-456-7890");
        UserInterface userInterface = new UserInterface();

        // Add vehicles to the dealership
        dealership.addVehicle(new Vehicle(1, 2014, "Lexus", "IS 250", "Car", "Black", 16000, 35000.0));
        dealership.addVehicle(new Vehicle(2, 2021, "Toyota", "Tacoma", "Truck", "White", 5000, 50000.0));

        // Act: Display vehicles
        userInterface.processGetAllVehiclesRequest();

        // Assert: Check if dealership has vehicles
        assertEquals(2, dealership.getAllVehicles().size());

        // Quitting
        userInterface.quit();
    }
}