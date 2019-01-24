package ejercicio2servidor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.ServerSocket;

/**
 * Esta aplicación Cliente-Servidor se encarga de sumar una serie de numeros
 * enviados por el cliente y mostrar el total.
 *
 * @author Arturo
 */
public class Ejercicio2Servidor{

    /**
     * El servidor se encarga de recibir el mensaje con los numeros, separarlos
     * y sumarlos.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args){
        try{
            System.out.println("Creando socket servidor");

            ServerSocket serverSocket=new ServerSocket();

            System.out.println("Realizando el bind");

            InetSocketAddress addr=new InetSocketAddress("localhost", 6666);
            serverSocket.bind(addr);

            System.out.println("Aceptando conexiones");

            Socket newSocket=serverSocket.accept();

            System.out.println("Conexion recibida");

            InputStream is=newSocket.getInputStream();
            OutputStream os=newSocket.getOutputStream();

            byte[] mensaje=new byte[25];
            is.read(mensaje);
            System.out.println("Mensaje recibido: "+new String(mensaje));
            //Se guarda el mensaje en un String
            String mens=new String(mensaje);
            //Separamos los numeros y los guardamos en una lista
            String[] numeros=mens.split(",");
            //inicializamos la variable de la suma
            int total=0;
            //realizamos el bucle que extrae los numeros de la lista los convierte en int y los va sumando
            for(int i=0; i<numeros.length-1; i++){

                int var=Integer.parseInt(numeros[i]);

                total=total+var;
            }
            System.out.println("Total = "+total);

            System.out.println("Cerrando el nuevo socket");

            newSocket.close();

            System.out.println("Cerrando el socket servidor");

            serverSocket.close();

            System.out.println("Terminado");

        }catch(IOException e){
        }
    }
}
