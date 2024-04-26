package principal;

import api.ApiResponse;
import servicios.ApiService;

import java.util.Map;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);

        System.out.println("****************************");
        System.out.println("Bienvenido/a al Conversor de Moneda\n");

        ApiResponse apiResponse = ApiService.obtenerApiResponse();
        if (apiResponse != null) {
            boolean salir = false;
            while (!salir) {
                mostrarMenu();
                int opcion = lectura.nextInt();

                switch (opcion) {
                    case 1:
                        realizarConversion("USD", "ARS", apiResponse);
                        break;
                    case 2:
                        realizarConversion("ARS", "USD", apiResponse);
                        break;
                    case 3:
                        realizarConversion("USD", "BRL", apiResponse);
                        break;
                    case 4:
                        realizarConversion("BRL", "USD", apiResponse);
                        break;
                    case 5:
                        realizarConversion("USD", "COP", apiResponse);
                        break;
                    case 6:
                        realizarConversion("COP", "USD", apiResponse);
                        break;
                    case 7:
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción no válida. Inténtelo de nuevo.");
                        break;
                }
            }
        } else {
            System.out.println("No se pudo obtener la respuesta de la API.");
        }
    }

    private static void mostrarMenu() {
        System.out.println("1) Dolar =>> Peso argentino");
        System.out.println("2) Peso argentino =>> Dolar");
        System.out.println("3) Dolar =>> Real brasileño");
        System.out.println("4) Real brasileño =>> Dolar");
        System.out.println("5) Dolar =>> Peso Colombiano");
        System.out.println("6) Peso colombiano =>> Dolar");
        System.out.println("7) Salir");
        System.out.println("*********************************");
        System.out.print("Por favor, elige una opción: ");
    }

    private static void realizarConversion(String monedaOrigen, String monedaDestino, ApiResponse apiResponse) {
        if (apiResponse != null) {
            Map<String, Double> conversionRates = apiResponse.getConversionRates();

            if (conversionRates != null && conversionRates.containsKey(monedaOrigen) && conversionRates.containsKey(monedaDestino)) {

                double tasaDeCambioOrigen = conversionRates.get(monedaOrigen);
                double tasaDeCambioDestino = conversionRates.get(monedaDestino);

                System.out.print("Ingrese el valor que desea convertir (" + monedaOrigen + " a " + monedaDestino + "): ");
                double valorAConvertir = new Scanner(System.in).nextDouble();

                double resultado = (valorAConvertir / tasaDeCambioOrigen) * tasaDeCambioDestino;
                System.out.println("El valor de " + valorAConvertir + " " + monedaOrigen + " equivale a " + String.format("%.2f", resultado) + " " + monedaDestino);
                System.out.println("**********************************");
            } else {
                System.out.println("La moneda de origen o destino ingresada no está disponible.");
            }
        } else {
            System.out.println("No se pudo obtener la respuesta de la API.");
        }
    }
}





