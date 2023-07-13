import java.util.Arrays;

public class Main {
  public static void main(String[] args) {
    String expected = "Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\nAmount owed is 5.5\nYou earned 2 frequent points\n";

    // Crear una instancia de RentalInfo
    RentalInfo rentalInfo = new RentalInfo();

    // Crear una instancia de Customer con los alquileres correspondientes
    Customer customer = new Customer("C. U. Stomer", Arrays.asList(
            new MovieRental("F001", 3),
            new MovieRental("F002", 1)
    ));

    // Crear una instancia de RentalCalculator dentro de RentalInfo
    RentalInfo.RentalCalculator rentalCalculator = rentalInfo.new RentalCalculator();

    // Calcular la declaración de alquiler utilizando RentalCalculator
    String result = rentalCalculator.calculateStatement(customer);

    // Comparar el resultado con el valor esperado
    if (!result.equals(expected)) {
      throw new AssertionError("Expected: " + System.lineSeparator() + String.format(expected) + System.lineSeparator() + System.lineSeparator() + "Got: " + System.lineSeparator() + result);
    }

    System.out.println("Success");
  }
}
