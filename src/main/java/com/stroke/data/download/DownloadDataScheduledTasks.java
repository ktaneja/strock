package com.stroke.data.download;

import java.io.IOException;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class DownloadDataScheduledTasks {


    @Scheduled(cron="0 03 22 * * *")
    public void reportCurrentTime() throws IOException {
        System.out.println("Executing....");
    	DownloadData.main(null);
    }
}