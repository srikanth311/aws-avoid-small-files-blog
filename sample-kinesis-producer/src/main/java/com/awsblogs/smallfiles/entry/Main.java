package com.awsblogs.smallfiles.entry;

import com.awsblogs.smallfiles.samplekinesisproducer.IOTDevice;
import com.awsblogs.smallfiles.samplekinesisproducer.IOTDeviceConsumerFromBlockingQueueToKinesisStreams;
import com.awsblogs.smallfiles.samplekinesisproducer.IOTDeviceProducerToBlockingQueue;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;


// This will throw an error - as the messeage size will be 1054382 bytes(more than 1 MB) when we used 2300 as parameter.
// java -cp sample-kinesis-producer-1.0-SNAPSHOT-jar-with-dependencies.jar com.awsblogs.smallfiles.entry.Main 100 2300
// java -Xms1024m -Xmx3072m -XX:+UseG1GC -cp sample-kinesis-producer-1.0-SNAPSHOT-jar-with-dependencies.jar com.awsblogs.smallfiles.entry.Main 10000 2000
/*
com.amazonaws.services.kinesis.model.AmazonKinesisException: 1 validation error detected: Value 'java.nio.HeapByteBuffer[pos=0 lim=1054425 cap=1054425]' at 'records.1.member.data' failed to satisfy constraint: Member must have length less than or equal to 1048576 (Service: AmazonKinesis; Status Code: 400; Error Code: ValidationException; Request ID: db484bdc-81b1-892a-8f66-501e0e2ce361)
	at com.amazonaws.http.AmazonHttpClient$RequestExecutor.handleErrorResponse(AmazonHttpClient.java:1640)
	at com.amazonaws.http.AmazonHttpClient$RequestExecutor.executeOneRequest(AmazonHttpClient.java:1304)
	at com.amazonaws.http.AmazonHttpClient$RequestExecutor.executeHelper(AmazonHttpClient.java:1058)
	at com.amazonaws.http.AmazonHttpClient$RequestExecutor.doExecute(AmazonHttpClient.java:743)
	at com.amazonaws.http.AmazonHttpClient$RequestExecutor.executeWithTimer(AmazonHttpClient.java:717)
	at com.amazonaws.http.AmazonHttpClient$RequestExecutor.execute(AmazonHttpClient.java:699)
	at com.amazonaws.http.AmazonHttpClient$RequestExecutor.access$500(AmazonHttpClient.java:667)
	at com.amazonaws.http.AmazonHttpClient$RequestExecutionBuilderImpl.execute(AmazonHttpClient.java:649)
	at com.amazonaws.http.AmazonHttpClient.execute(AmazonHttpClient.java:513)
	at com.amazonaws.services.kinesis.AmazonKinesisClient.doInvoke(AmazonKinesisClient.java:2388)
	at com.amazonaws.services.kinesis.AmazonKinesisClient.invoke(AmazonKinesisClient.java:2364)
	at com.amazonaws.services.kinesis.AmazonKinesisClient.executePutRecords(AmazonKinesisClient.java:1859)
	at com.amazonaws.services.kinesis.AmazonKinesisClient.putRecords(AmazonKinesisClient.java:1834)
	at com.awsblogs.smallfiles.samplekinesisproducer.IOTDeviceConsumerFromBlockingQueueToKinesisStreams.pushToKinesis(IOTDeviceConsumerFromBlockingQueueToKinesisStreams.java:77)
	at com.awsblogs.smallfiles.samplekinesisproducer.IOTDeviceConsumerFromBlockingQueueToKinesisStreams.run(IOTDeviceConsumerFromBlockingQueueToKinesisStreams.java:51)
	at java.lang.Thread.run(Thread.java:748)

 */

public class Main {
    public static void main(String [] args)
    {
        if(args.length !=2)
        {
            System.out.println("Needs two arguments...");
            System.out.println("1st Argument : Number of Messages to send to Kinesis");
            System.out.println("2nd Argument : Number of Threads");
        }
        else {
            int numberOfSamplesInEachMessage = 2200;
            int numberOfThreads = Integer.parseInt(args[1]);
            int numberOfMessages = Integer.parseInt(args[0]);
            BlockingQueue<IOTDevice> inputQueue = new LinkedBlockingDeque();

            Thread[] consumerThread = new Thread[numberOfThreads];
            Thread producerThread = new Thread(new IOTDeviceProducerToBlockingQueue(inputQueue, numberOfMessages, numberOfSamplesInEachMessage));
            System.out.println("Starting producer and consumer.....");
            producerThread.start();

            for (int i = 0; i < numberOfThreads; i++) {
                consumerThread[i] = new Thread(new IOTDeviceConsumerFromBlockingQueueToKinesisStreams(inputQueue));
                consumerThread[i].start();
            }
        }
    }
}
