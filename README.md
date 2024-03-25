# fundamentos_entrega1

## Members

* Leidy Julieth Cruz Cruz
* Sandra Milena Fernandez Rosa 
* Oscar Giobani Clavijo Gutiérrez
* Flavio Cesar Fonseca


## Sales Management App - Java Code Explanation

This Java code aims to generate data files for a sales management application, specifically product and seller files, as well as individual sales files for each seller. Below is a detailed explanation of each part of the code:

**1. `main` Method:**
* Starts the program execution.
* Prompts the user for the number of products and sellers to generate.
* Calls the `createProductsFile` and `createSalesmenInfoFile` methods to generate the corresponding files.
* Finally, prints a message indicating that the files were successfully created.

**2. `createProductsFile` Method:**
* Generates a product information file with the format: ID, ProductName, Price.
* Uses a loop to generate the specified number of products with unique IDs and random names.
* Assigns a random price to each product within a set range.

**3. `createSalesmenInfoFile` Method:**
* Generates a seller information file with the format: ID, Name, Last Name, Sales.
* Uses pre-existing name lists to generate random names for sellers.
* Assigns a random number of sales to each seller.
* Calls the `createSalesFile` method to generate individual sales files for each seller.

**4. `createSalesFile` Method:**
* Generates a sales file for a specific seller with the format: Seller ID, Product ID, Sale Value.
* Assigns a random product ID and a random sale value for each sales record.
* This method is called by `createSalesmenInfoFile` to generate the individual sales files for each seller.

**5. `loadNamesFromFile` Method:**
* Loads names from a text file and stores them in a list.
* Used by `createSalesmenInfoFile` to load seller names from external files.

**Other important aspects:**
* The `BufferedReader` and `BufferedWriter` classes are used to read and write text files, respectively.
* The `Random` class is used to generate random numbers.
* I/O (Input/Output) exceptions are handled with `try-catch` blocks to prevent errors during file reading and writing.

In summary, this program generates simulated data files for products, sellers, and sales, which can be used as input for a sales management application or any other purpose related to sales data manipulation.

**Improvement Notes:**

* Added more details to the explanation of each method.
* Clarified the purpose of each variable and method.
* Improved the overall readability of the text.

