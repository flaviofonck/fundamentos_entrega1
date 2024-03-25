package fundamentos_programacion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GenerateInfoFiles {

	private static final String SALESMEN_FILE = "salesmen_info.txt";
	private static final String PRODUCTS_FILE = "products_info.txt";

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("CREATING THE NECESSARY FILES FOR THE APP\n");

		System.out.print("Creating Products file, press enter\n");
		scanner.nextLine();

		System.out.print("Enter the number of products: ");
		int productsCount = scanner.nextInt();
		createProductsFile(productsCount);

		System.out.print("Creating Salesmen file, press enter\n");
		scanner.nextLine();

		System.out.print("Enter the number of salesmen: ");
		int salesmanCount = scanner.nextInt();
		scanner.nextLine(); // Consume the rest of the line

		System.out.print("Enter the number of records for each salesman: ");
		long randomSalesCount = scanner.nextLong();
		scanner.nextLine(); // Consume the rest of the line

		createSalesmenInfoFile(salesmanCount, randomSalesCount, productsCount);

		scanner.close();

		System.out.println("\nFiles created successfully!"); // Indicate completion
	}

	
	private static List<String> loadNamesFromFile(String fileName) {
        List<String> names = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String name;
            while ((name = reader.readLine()) != null) {
                names.add(name);
            }
        } catch (IOException e) {
            System.err.println("Error reading names file: " + e.getMessage());
        }
        return names;
    }
	
	/**
	 * Generates a file containing product information.
	 *
	 * @param productsCount The number of products to include in the file.
	 */
	private static void createProductsFile(int productsCount) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(PRODUCTS_FILE))) {
			writer.write("ID, NombreProducto, Precio\n");

			Random random = new Random();
			for (int i = 1; i <= productsCount; i++) {
				String productId = String.format("Pdto_%05d", i);
				String productName = "Producto" + i;
				double price = 10 + (500 - 10) * random.nextDouble();

				writer.write(productId + ", " + productName + ", " + String.format("%.2f", price) + "\n");
			}
		} catch (IOException e) {
			System.err.println("Error creating products file: " + e.getMessage());
		}
	}
	/**
     * Generates a file containing salesman information and individual sales files for each salesman.
     *
     * @param salesmanCount     The number of salesmen to include in the file.
     * @param randomSalesCount  The number of random sales records for each salesman.
     * @param productsCount     The total number of products available.
     */
    private static void createSalesmenInfoFile(int salesmanCount, long randomSalesCount, int productsCount) {
        Random random = new Random();
        List<String> firstNames = loadNamesFromFile("firstNames.txt");
        List<String> lastNames = loadNamesFromFile("lastNames.txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SALESMEN_FILE))) {
            writer.write("ID, Nombre, Apellido, Ventas\n");

            for (int i = 1; i <= salesmanCount; i++) {
                int firstNameIndex = random.nextInt(firstNames.size());
                int lastNameIndex = random.nextInt(lastNames.size());
                String firstName = firstNames.get(firstNameIndex);
                String lastName = lastNames.get(lastNameIndex);
                int sales = random.nextInt(100);

                writer.write(i + ", " + firstName + ", " + lastName + ", " + sales + "\n");

                // Create a sales file for each salesman
                createSalesFile(randomSalesCount, firstName + "_" + lastName, i, productsCount);
            }
        } catch (IOException e) {
            System.err.println("Error creating salesmen file: " + e.getMessage());
        }
    }

	/**
	 * Generates a sales record file for a specific salesman.
	 *
	 * @param randomSalesCount The number of random sales records to generate.
	 * @param salesmanName     The salesman's name.
	 * @param salesmanId       The salesman's ID.
	 * @param productsCount    The total number of products available.
	 */
	private static void createSalesFile(long randomSalesCount, String salesmanName, int salesmanId, int productsCount) {
		String fileName = "salesman_" + salesmanName + "_sales.txt";

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
			writer.write("ID Vendedor, ID Producto, Valor Venta\n");
			Random random = new Random();
			for (int i = 0; i < randomSalesCount; i++) {
				String productId = String.format("Pdto_%05d", random.nextInt(productsCount) + 1);
				double saleValue = 10 + (500 - 10) * random.nextDouble();

				writer.write(salesmanId + ", " + productId + ", " + String.format("%.2f", saleValue) + "\n");
			}
		} catch (IOException e) {
			System.err.println("Error creating sales file for " + salesmanName + ": " + e.getMessage());
		}
	}
}
