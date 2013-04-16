package com.china.btc;

import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Watcher {

	public static double getBtcChina() {
 

		double btc = -1;
		try {
 
			System.setProperty(
					"http.agent",
					"Mozilla/5.0 (Windows NT 5.1) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.84 Safari/535.11 SE 2.X MetaSr 1.0");
			Document doc;
	
				doc = Jsoup.parse(new URL("https://btcchina.com/"),
						1000 * 20);

			String html = doc.html();
			html = html.substring(html.indexOf("Last BTC Price"),
					html.indexOf("Last BTC Price") + 70);
			html = html
					.substring(html.indexOf(";") + 1, html.lastIndexOf("</"));
			System.out.println(html);
			double v = Double.parseDouble(html);
			
			btc = v;
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return btc;
	}

	
public static double getMtgox() {


		double mtg = -1;
		try {

			System.setProperty(
					"http.agent",
					"Mozilla/5.0 (Windows NT 5.1) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.84 Safari/535.11 SE 2.X MetaSr 1.0");
			Document doc;
	
				doc = Jsoup.parse(new URL("https://mtgox.com/"),
						1000 * 20);

			String html = doc.html();
			html = html.substring(html.indexOf("Last price:<span>"),
					html.indexOf("Last price:<span>") + 30);
			html = html
					.substring(html.indexOf("$") + 1, html.lastIndexOf("</"));
			System.out.println(html);
			
			double v = Double.parseDouble(html);
 
			mtg = v;
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mtg;
	}

	
}
