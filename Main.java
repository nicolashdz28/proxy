package Proxy;

public class Main {
    public static void main(String[] args) {
        System.out.println("Application started.");

        Image image = new ProxyImage("photo.jpg");
        System.out.println("Proxy created, but RealImage not loaded yet.");

        image.display();

        System.out.println("Second call:");
        image.display();
    }
}