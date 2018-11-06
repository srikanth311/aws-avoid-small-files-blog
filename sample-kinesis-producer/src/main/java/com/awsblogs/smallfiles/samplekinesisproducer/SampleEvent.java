package com.awsblogs.smallfiles.samplekinesisproducer;

import java.util.Date;

public class SampleEvent
{
    private long phoneNumber;
    private long simid;
    private String fname;
    private String lname;
    private String prefix;
    private String title;
    private String jobArea;
    private String jobType;
    private double latitude;
    private double longitude;
    private Class  age;


}


/*
"phonenumber": {{phone.phoneNumber}},
    "eventdate":{{date.now}},
    "simid":{{random.uuid}},
    "fname":{{name.firstName}},
    "lname":{{name.lastName}},
    "prefix":{{name.prefix}},
    "suffix":{{name.suffix}},
    "title":{{name.title}},
    "jobDescriptor":{{name.jobDescriptor}},
    "jobArea":{{name.jobArea}},
    "jobType":{{name.jobType}},
    "latitude":{{address.latitude}},
    "longitude":{{address.longitude}},
    "age": {{random.number(
        {
            "min":15,
            "max":100
        }
    )}},
    "samples": [
    {
        "gpstimestamp":{{date.now}},
        "phonetype": {{lorem.word}},
        "model":{{lorem.word}},
        "carriername":{{lorem.word}},
        "carrierid":{{random.uuid}},
        "latitude":{{address.latitude}},
        "longitude":{{address.longitude}},
        "duration":{{random.number(
            {
                "min":1,
                "max":400
            }
        )}},
        "bluetooth":{random.boolean},
        "strength":{{random.number(
            {
                "min":1,
                "max":100
            }
        )}}
    }

 */