package Network;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Talaat
 */
public class Server {
    ServerSocket server;
    Socket client_connection;
    /*
    The class DataInputStream allows you to read lines of text and Java primitive data types in a portable way
    */
    DataInputStream input_data;
    /*
    The class DataOutputStream allows you to write Java primitive data types;
    many of its methods write a single Java primitive type to the output stream.
    The method writeBytes is a useful one.
    */
    DataOutputStream output_data;
    public Server(int port){
        try {
            server = new ServerSocket(port);
            
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    public void listen(){
        try{
            client_connection = server.accept(); //wait for client to ask for connection and store info in client_conn
            output_data = new DataOutputStream(client_connection.getOutputStream());
            input_data = new DataInputStream(client_connection.getInputStream());
        }
        catch(Exception e){
            System.out.println("listen function class Server -> "+e);
        }
    }
    //read data from clients
     public String get_input(){
        String data = ""; 
        try{      
        
        data = input_data.readUTF();//read input as string
    }catch(Exception e){
        System.out.println(e);
    
        }// end exception
        return data;
    }
    //send data to the client
     public void send_data(String data){
        try{
            
            output_data.writeUTF(data);//write string to the other connection
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public void close_server(){
       try{
//            input_data.close();
//            output_data.close();
            server.close();
//            client_connection.close();
        }
        catch(Exception e){
            System.out.println("Close ->"+e);
        } 
    }
    //func to get the current ip4v address
    public static String get_ip(){
        InetAddress address;
        try{
        address = InetAddress.getLocalHost();
        return address.getHostAddress(); // return the address
        }
        catch(Exception e){
            System.out.println("get ip ->"+e);
            return "False";
        }
        
    }
}
