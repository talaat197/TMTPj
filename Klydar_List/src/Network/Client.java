package Network;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Talaat
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.*;
public class Client {
    Socket client;
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
    
    public Client(String target_ip,int target_port){// ip and port of the server you will connectto
        try{
        client = new Socket();
        client.connect(new InetSocketAddress(target_ip, target_port),200);
        output_data = new DataOutputStream(client.getOutputStream());
        input_data = new DataInputStream(client.getInputStream());
    }catch(Exception e){
        System.out.println("Client init-> "+e);
    
        }// end exception
    }
    //function to get data from server
    public String get_input(){
        String data = ""; 
        try{          
        data = input_data.readUTF();//read input as string
    }catch(Exception e){
        System.out.println(e);
    
        }// end exception
        return data;
    }
    //send data to the server
    public void send_data(String data){
        try{
            
            output_data.writeUTF(data);//write string to the other connection
        }
        catch(Exception e){
            System.out.println(e);
        }
    } 
    public void close(){
       try{
            input_data.close();
            output_data.close();
            client.close();
            
        }
        catch(Exception e){
            System.out.println(e);
        } 
    }
}
