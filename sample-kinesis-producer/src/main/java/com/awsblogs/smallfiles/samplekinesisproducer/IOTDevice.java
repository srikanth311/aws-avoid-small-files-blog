package com.awsblogs.smallfiles.samplekinesisproducer;

import com.awsblogs.smallfiles.sensors.Accelerometer;
import com.awsblogs.smallfiles.sensors.GPS;
import com.awsblogs.smallfiles.sensors.Illuminance;
import com.awsblogs.smallfiles.sensors.TemperatureSensor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IOTDevice implements Serializable
{
    private String deviceId;
    private String currentDate;
    private List<Accelerometer> accelerometerSensorList;
    private List<GPS> gpsSensorList;
    private List<TemperatureSensor> tempSensorList;
    private List<Illuminance> illuminancesSensorList;

    IOTDevice(String deviceId,
              String currentDate,
              List<Accelerometer> accelerometerArrayList,
              List<GPS> gpsArrayList,
              List<TemperatureSensor> temperatureSensorArrayList,
              List<Illuminance> illuminanceArrayList
            )
    {
        this.deviceId = deviceId;
        this.currentDate = currentDate;
        this.accelerometerSensorList = accelerometerArrayList;
        this.gpsSensorList = gpsArrayList;
        this.tempSensorList = temperatureSensorArrayList;
        this.illuminancesSensorList = illuminanceArrayList;

    }

    public String getDeviceId()
    {
        return deviceId;
    }

    public String getCurrentDate()
    {
        return currentDate;
    }


}
