package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import entities.Product;

public class Program {

	public static void main(String[] args) {

		ArrayList<Product> list = new ArrayList<>();
		Locale.setDefault(Locale.US);
		Scanner scanner = new Scanner(System.in);

		//Obter o caminho do arquivo onde os produtos são inseridos
		System.out.println("Enter the file path:");
		String mainFileStr = scanner.nextLine();
		scanner.close();

		//Objeto para passar um caminho
		File mainFile = new File(mainFileStr);

		//obter o caminho pai do objeto File principal
		String folderPath = mainFile.getParent();

		//criando a subpasta out
		boolean createdOut = new File(folderPath + "\\out").mkdir();

		//criando arquivo dentro da subpasta out
		String subFolderPath = (folderPath + "\\out\\summary.csv");

		try (BufferedReader bufferReader = new BufferedReader(new FileReader(mainFileStr))){
			String line = bufferReader.readLine();
			while (line != null) {

				String [] fields = line.split(",");
				String name = fields[0];
				double price = Double.parseDouble(fields[1]);
				int quantity = Integer.parseInt(fields[2]);

				line = bufferReader.readLine();
				list.add(new Product(name, price, quantity));
			}

			try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(subFolderPath))){
				for(Product product : list) {
					bufferedWriter.write(product.toString());
					bufferedWriter.newLine();
				}
			} catch (IOException e) {
				System.out.println("Error: " + e.getMessage());
			}

		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

	}
}
