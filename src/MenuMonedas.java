import java.util.Scanner;

public class MenuMonedas {
    Scanner escaner;
    Conversor conversor;
    int opcionesTotales;

    public MenuMonedas(Conversor conversor) {
        this.escaner = new Scanner(System.in);
        this.conversor = conversor;
        this.opcionesTotales = 7;
    }

    public void mostrarMenu(){
        boolean continuar = true;
        while(continuar){
            this.mostrarMargen();
            this.mostrarTitulo();
            this.mostrarOpciones();
            this.mostrarMargen();
            int opcion = this.elegirOpcion();
            continuar = this.opcionElegida(opcion);
        }
    }

    private int elegirOpcion(){
        System.out.println("Elegi una de las opciones: ");
        return this.escaner.nextInt();
    }

    private boolean opcionElegida(int opcion){
        if (opcion == this.opcionesTotales){
            System.out.println("Saliendo ahora ...");
            return false;
        }

        if (opcion > 0 && opcion < this.opcionesTotales ){
            System.out.println("Ingrese el valor a convertir: ");
            this.escaner.nextLine();
            double valor =  this.escaner.nextDouble();
            this.respuestas(opcion, valor);
        } else {
            System.out.println("Valor incorrecto");
        }

        return true;
    }

    private void respuestas(int opcion, double valor){
        switch (opcion){
            case 1:
                this.mostrarRespuesta(Monedas.USD, Monedas.ARS, valor);
                break;
            case 2:
                this.mostrarRespuesta(Monedas.ARS, Monedas.USD, valor);
                break;
            case 3:
                this.mostrarRespuesta(Monedas.USD, Monedas.BRL, valor);
                break;
            case 4:
                this.mostrarRespuesta(Monedas.BRL, Monedas.USD, valor);
                break;
            case 5:
                this.mostrarRespuesta(Monedas.USD, Monedas.COP, valor);
                break;
            case 6:
                this.mostrarRespuesta(Monedas.COP, Monedas.USD, valor);
                break;
        }
        System.out.println("Presione alguna tecla para continuar: ");
        this.escaner.nextLine();
        this.escaner.nextLine();
    }


    private void mostrarMargen(){
        System.out.println("*".repeat(20));
    }

    private void mostrarTitulo(){
        System.out.println("CONVERSOR DE MONEDAS");
        System.out.println(" ");
    }

    private void mostrarOpciones(){
        System.out.println("""
        1) Dolar -> Peso argentino
        2) Peso argentino -> Dolar
        3) Dolar -> Real brasilero
        4) Real brasilero -> Dolar
        5) Dolar -> Peso colombiano
        6) Peso colombiano -> Dolar
        7) Salir
        """);
    }

    private void mostrarRespuesta(Monedas monedaOriginal, Monedas monedaFinal, double valor){
        double respuesta = this.conversor.convertir(monedaOriginal, monedaFinal, valor);

        System.out.println("Conversion Realizada");
        System.out.println(valor + " (" + monedaOriginal.toString() + ") --> " + respuesta + " (" + monedaFinal.toString() + ")");
    }




}
