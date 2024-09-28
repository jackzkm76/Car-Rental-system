
package carrentalsystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CarRentalSystemTest {

    private CarRentalSystem rentalSystem;

    @BeforeEach
    void setUp() {
        rentalSystem = new CarRentalSystem();
        rentalSystem.addCar(new Car("C001", "Toyota", "Camry", 3500.0));
        rentalSystem.addCar(new Car("C002", "Honda", "Accord", 2500.0));
        rentalSystem.addCar(new Car("C003", "Mahindra", "Thar", 2000.0));
    }

    @Test
    void testAddCar() {
        assertEquals(3, rentalSystem.getCars().size(), "Should have 3 cars");
        rentalSystem.addCar(new Car("C004", "Nissan", "Altima", 3000.0));
        assertEquals(4, rentalSystem.getCars().size(), "Should have 4 cars after adding one");
    }

    @Test
    void testRentCar() {
        Customer customer = new Customer("CUS1", "Alice");
        Car carToRent = rentalSystem.getCars().get(0); // Renting the first car

        assertTrue(carToRent.isAvailable(), "Car should be available before renting");
        rentalSystem.rentCar(carToRent, customer, 5); // Rent for 5 days
        assertFalse(carToRent.isAvailable(), "Car should not be available after renting");
    }

    @Test
    void testReturnCar() {
        Customer customer = new Customer("CUS1", "Alice");
        Car carToRent = rentalSystem.getCars().get(0);

        rentalSystem.rentCar(carToRent, customer, 5);
        assertFalse(carToRent.isAvailable(), "Car should not be available after renting");
        rentalSystem.returnCar(carToRent);
        assertTrue(carToRent.isAvailable(), "Car should be available after returning");
    }

    @Test
    void testRentUnavailableCar() {
        Customer customer = new Customer("CUS1", "Alice");
        Car carToRent = rentalSystem.getCars().get(0);

        rentalSystem.rentCar(carToRent, customer, 5); // Rent the car first
        assertFalse(carToRent.isAvailable(), "Car should not be available after renting");

        Customer anotherCustomer = new Customer("CUS2", "Bob");
        rentalSystem.rentCar(carToRent, anotherCustomer, 3); // Try to rent it again
        assertFalse(carToRent.isAvailable(), "Car should still not be available for the second customer");
    }

    @Test
    void testReturnInvalidCar() {
        Car carToReturn = new Car("C999", "Fake", "Car", 1000.0);
        rentalSystem.returnCar(carToReturn); // Return a car that was never rented
        assertEquals(3, rentalSystem.getCars().size(), "Should still have 3 cars");
    }
}




    
    
}
