import java.util.HashMap;

public class RentalInfo {

  public class RentalCalculator {
    private HashMap<String, Movie> movies; // Mapa para almacenar las películas
    public RentalCalculator() {
      movies = new HashMap<>(); // Inicialización del mapa de películas

      // Agregar películas al mapa con sus respectivos códigos
      movies.put("F001", new Movie("You've Got Mail", "regular"));
      movies.put("F002", new Movie("Matrix", "regular"));
      movies.put("F003", new Movie("Cars", "childrens"));
      movies.put("F004", new Movie("Fast & Furious X", "new"));
    }

    // Método para calcular la declaración de alquiler
    public String calculateStatement(Customer customer) {
      double totalAmount = 0; // Variable para almacenar el monto total
      int frequentEnterPoints = 0; // Variable para almacenar los puntos de cliente frecuente
      StringBuilder result = new StringBuilder("Rental Record for " + customer.getName() + "\n"); // StringBuilder para construir la declaración

      for (MovieRental rental : customer.getRentals()) {
        double thisAmount = calculateAmount(rental); // Calcular el monto para cada alquiler

        frequentEnterPoints++; // Incrementar los puntos de cliente frecuente

        // Agregar puntos de cliente frecuente adicionales para un alquiler de nueva versión de dos días
        if (movies.get(rental.getMovieId()).getCode().equals("new") && rental.getDays() > 2) {
          frequentEnterPoints++;
        }

        // Agregar detalles del alquiler a la declaración
        result.append("\t")
                .append(movies.get(rental.getMovieId()).getTitle())
                .append("\t")
                .append(thisAmount)
                .append("\n");

        totalAmount += thisAmount; // Acumular el monto total
      }

      // Agregar líneas de pie de página
      result.append("Amount owed is ")
              .append(totalAmount)
              .append("\n");
      result.append("You earned ")
              .append(frequentEnterPoints)
              .append(" frequent points\n");

      return result.toString(); // Devolver la declaración como una cadena de texto
    }

    // Método para calcular el monto de alquiler para una película específica
    private double calculateAmount(MovieRental rental) {
      double thisAmount = 0;

      // Obtener la película correspondiente al ID del alquiler
      Movie movie = movies.get(rental.getMovieId());

      // Calcular el monto según el tipo de película
      if (movie.getCode().equals("regular")) {
        thisAmount = 2;
        if (rental.getDays() > 2) {
          thisAmount += (rental.getDays() - 2) * 1.5;
        }
      } else if (movie.getCode().equals("new")) {
        thisAmount = rental.getDays() * 3;
      } else if (movie.getCode().equals("childrens")) {
        thisAmount = 1.5;
        if (rental.getDays() > 3) {
          thisAmount += (rental.getDays() - 3) * 1.5;
        }
      }

      return thisAmount; // Devolver el monto de alquiler calculado
    }
  }
}

