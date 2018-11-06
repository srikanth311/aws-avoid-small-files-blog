package com.awsblogs.smallfiles.datagenerator;

import java.util.ArrayList;
import java.util.UUID;

// https://howtoprogramwithjava.com/java-multithreading/
/*
private String deviceId;
    Date currentDate;
    ArrayList<Accelerometer> accelerometerSensor;
    ArrayList<GPS> gpsSensor;
    ArrayList<TemperatureSensor> tempSensor;
    ArrayList<Illuminance> illuminancesSensor;

 */
public class GenerateDataWorker implements Runnable
{
    //DataFactory df;
    public boolean running = false;

    GenerateDataWorker()
    {
        Thread t = new Thread(this);
        t.start();
    }
    @Override
    public void run()
    {
        this.running = true;
        System.out.print("Need to generate data and insert into Blocked Queue. The Id is : " + Thread.currentThread().getId() );
    }

}
