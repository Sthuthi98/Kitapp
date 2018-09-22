package com.dandd.kitapp;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Page8 extends AppCompatActivity {
    Boolean turn;
    static WifiManager wifiManager;
    Context context;
    WifiConfiguration conf;
    public static String networkSSID="ESP";
    public static String networkPass="hainas@19";
    byte[] buf = new byte[1024];//used to sending information to esp is a form of byte

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page8);
        turn=true;
        context=this;

        // this is for thread policy the AOS doesn't allow to transfer data using wifi module so we take the permission
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }
    public void wifi_connect(View v){


        wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        if(turn){

            turnOnOffWifi(context, turn);
            turn=false;
            Toast.makeText(getApplicationContext(), "turning on...", Toast.LENGTH_SHORT).show();

            //wifi configuration .. all the code below is to explain the wifi configuration of which type the wifi is
            //if it is a WPA-PSK protocol then it would work

            conf = new WifiConfiguration();
            conf.SSID = "\"" + networkSSID + "\"";
            conf.preSharedKey = "\"" + networkPass + "\"";
            conf.status = WifiConfiguration.Status.ENABLED;
            conf.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
            conf.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
            conf.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
            conf.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
            conf.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
            conf.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
            int netid= wifiManager.addNetwork(conf);
            wifiManager.disconnect();
            wifiManager.enableNetwork(netid, true);
            wifiManager.reconnect();


        } else {
            turnOnOffWifi(context, turn);
            turn = true;
            Toast.makeText(getApplicationContext(), "turning off...", Toast.LENGTH_SHORT).show();

        }

    }
    public void up (View view){

            Client a=new Client();
            buf=null;
            buf=("1").getBytes();
            a.run();
            Toast.makeText(Page8.this, "UP", Toast.LENGTH_SHORT).show();
    }
    public void down (View view){
        Client a=new Client();
        buf=null;
        buf=("2").getBytes();
        a.run();
        Toast.makeText(Page8.this, "DOWN", Toast.LENGTH_SHORT).show();
    }
    public void right(View view){
        Client a=new Client();
        buf=null;
        buf=("3").getBytes();
        a.run();
        Toast.makeText(Page8.this, "RIGHT", Toast.LENGTH_SHORT).show();
    }
    public void left(View view){
        Client a=new Client();
        buf=null;
        buf=("4").getBytes();
        a.run();
        Toast.makeText(Page8.this, "LEFT", Toast.LENGTH_SHORT).show();

    }
    public static void turnOnOffWifi(Context context, boolean isTurnToOn) {
        wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiManager.setWifiEnabled(isTurnToOn);
    }

    //used to send data to esp module
    public class Client implements Runnable{
        private final static String SERVER_ADDRESS = "192.168.4.1";//public ip of my server
        private final static int SERVER_PORT = 8888;


        public void run(){

            InetAddress serverAddr;
            DatagramPacket packet;
            DatagramSocket socket;


            try {
                serverAddr = InetAddress.getByName(SERVER_ADDRESS);
                socket = new DatagramSocket(); //DataGram socket is created
                packet = new DatagramPacket(buf, buf.length, serverAddr, SERVER_PORT);//Data is loaded with information where to send on address and port number
                socket.send(packet);//Data is send in the form of packets
                socket.close();//Needs to close the socket before other operation... its a good programming
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
