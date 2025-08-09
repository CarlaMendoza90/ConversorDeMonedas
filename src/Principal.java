public class Principal {
    public static void main(String[] args){
        Conversor conversor = new Conversor();
        MenuMonedas menu = new MenuMonedas(conversor);
        menu.mostrarMenu();
    }
}
