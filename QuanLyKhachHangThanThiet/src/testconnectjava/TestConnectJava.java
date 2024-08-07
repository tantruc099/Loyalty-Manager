/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package testconnectjava;

import Models.TestModel;
import com.mongodb.*;
import com.mongodb.client.*;
import org.bson.Document;

/**
 *
 * @author AnhNhi
 */
public class TestConnectJava {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        // TODO code application logic here
      var test = new TestModel();
      test.GetData();
    }
    
}
