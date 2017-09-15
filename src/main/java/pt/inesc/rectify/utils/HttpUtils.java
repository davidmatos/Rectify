/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.inesc.rectify.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import pt.inesc.rectify.RectifyLogger;

/**
 *
 * @author davidmatos
 */
public class HttpUtils {
    
    
    
   public static void performHttpRequest(String uri) {


        try {
            URL url = new URL(uri);
            URLConnection yc = url.openConnection();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            yc.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                //System.out.println(inputLine);
            }
            in.close();
        } catch (Exception e) {
            RectifyLogger.severe("Couldn't perform HTTP request due to: " + e.getMessage());
        }
    }
}
