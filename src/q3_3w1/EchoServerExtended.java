/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q3_3w1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author pagh
 */
public class EchoServerExtended implements Runnable
{

    PrintWriter out;
    BufferedReader in;
    String echo;
    Socket s;
    String outPut;
    String todo, word;
    Scanner scan;
    StringBuilder SB;
    HashMap hm = new HashMap();

    public EchoServerExtended(Socket soc)
    {
        s = soc;
    }

    String abe = "monkey";

    @Override
    public void run()
    {

        try
        {
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = new PrintWriter(s.getOutputStream(), true);

            //Random ord til opgave nummer 3 (translate kommandoen)
            hm.put("abe", "monkey");
            hm.put("hund", "dog");
            hm.put("bubber", "emperor Bubber");
            hm.put("plante", "plant");

            while (true)
            {

                echo = in.readLine();
                scan = new Scanner(echo);
                scan.useDelimiter("#");
                while (scan.hasNext())
                {
                    todo = scan.next();
                    word = scan.next();

                }

                switch (todo)
                {
                    case "UPPER":
                        out.println(word.toUpperCase());
                        break;
                    case "LOWER":
                        out.println(word.toLowerCase());
                        break;
                    case "REVERSE":
                        out.println(new StringBuilder(word).reverse().toString());
                        break;
                    case "TRANSLATE":
                        out.println(hm.get(word));
                        break;
                }

//                System.out.println(word);
//                out.println(word);
            }
        } catch (IOException e)
        {
            System.err.println("FUCK-UP IN RUN!");
        }
    }

    public static void main(String[] args) throws IOException
    {

        String ip = "localhost";
        int port = 4321;
//        if (args.length == 2)
//        {
//            ip = args[0];
//            port = Integer.parseInt(args[1]);
//        }
        ServerSocket ss = new ServerSocket();
        ss.bind(new InetSocketAddress(ip, port));

        while (true)
        {
            EchoServerExtended e = new EchoServerExtended(ss.accept());
            Thread t1 = new Thread(e);
            t1.start();
        }
    }
}
