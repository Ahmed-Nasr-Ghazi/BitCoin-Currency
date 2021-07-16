package com.currency.cryptocurrencyindex.service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.cryptocurrency.entity.BitCoinEntity;

/**
*	Bit Coin Price Index (Daily Updated)
*		Is an app for showing the current price of Cryptocurrency
*		the user should choose between getting data from online source 
*		or from a csv file on project path
*/


@Service
public class CurrencyService {

	
	
	private String VIRUS_DATA_URI = "https://s3.amazonaws.com/rawstore.datahub.io/"
			+ "26eb33c1369b7cd90203b6fd23383a46.csv";
	
	List<BitCoinEntity> finalList = new ArrayList<>();

	/**
	 * Method For Fetcing data from Online resource
	 * @Throws IOException
	 * @Throws InterruptedException for concurrency errors
	 * 
	 */
	
	@PostConstruct
	@Scheduled(cron = "* * 1 * * *")
	public void fetchOnlineData() throws IOException, InterruptedException {

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(VIRUS_DATA_URI))
				.build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		
		
		StringReader CSVReader = new StringReader(response.body());
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader().parse(CSVReader);
		
		
		//for Preventing concurency error responses
		 List<BitCoinEntity> tempList = new ArrayList<>();
		 
		for (CSVRecord record : records) {
			
			BitCoinEntity bitCoinEntity = new BitCoinEntity();
			
			bitCoinEntity.setDate(record.get("date"));
			bitCoinEntity.setPrice(record.get("price(USD)"));
			bitCoinEntity.setGeneratedCoins(record.get(7));

			tempList.add(bitCoinEntity);		            
		}
		this.finalList = tempList;
			
	}
	
	List<BitCoinEntity> bitCoinList = new ArrayList<>();

	/**
	 * Method For Fetcing data from Online resource
	 * @Throws IOException
	 * @Throws InterruptedException for concurrency errors
	 * 
	 */
	
	@PostConstruct
	public void dataContainer() throws IOException, InterruptedException {
		
        File file = new File("market-price");
        FileReader filereader = new FileReader(file);
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader().parse(filereader);
        
        List<BitCoinEntity> newList = new ArrayList<>();
        
        for (CSVRecord record : records) {

        	BitCoinEntity bitCoinEntity = new BitCoinEntity();
        	
        	//reading data into BitCoinEntity
            String price = record.get("market-price");
            String date = record.get(0);
            date = date.substring(0, 10);

            //Setting Date and Price
            bitCoinEntity.setDate(date);
            bitCoinEntity.setPrice(price);
            
            System.out.println(bitCoinEntity);
            
            newList.add(bitCoinEntity);
        }
        this.bitCoinList = newList;       
	
	}
	/**
	 * Method For Fetcing data from Online resource
	 * @return finalList of all online BitCoinEntity list
	 * 
	 */
	public List<BitCoinEntity> getOnlineList() {
		return finalList;
	}

	/**
	 * Method For Fetcing data from File resource
	 * @return finalList of all file BitCoinEntity list
	 * 
	 */
	public List<BitCoinEntity> getFileList() {
		return bitCoinList;
	}
	
	
}
