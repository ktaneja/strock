package com.stroke.data.download;

import java.io.IOException;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class DownloadDataScheduledTasks {

	/*
	 * Download Stock data every night at 10 PM
	 */
    @Scheduled(cron="0 51 22 * * *")
    public void downloadStockData() throws IOException {
        System.out.println("Downloading Stock Quotes....");
    	DownloadData.main(null);
    }
}