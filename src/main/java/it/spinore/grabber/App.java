package it.spinore.grabber;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;



import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import it.spinore.grabber.action.HtmlActions;
import it.spinore.grabber.service.HtmlService;

/**
 * Hello world!
 *
 */
public class App 
{
	
	/*
	public static void main( String[] args ) throws IOException
	{

		int i = 0;
		File input = new File("E:\\\\AlessandroCostanza\\\\workspace_1C\\\\Utiltest\\\\Osservatorio meteorologico SMI di Moncalieri - Dati meteo e webcam.html");
		Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");

		Elements el_1 = HtmlActions.getElementByText(doc, "Dati di oggi");
		Elements el_2 = HtmlActions.getElementById(el_1, 17);
		for (Element element2 : el_2) {
			System.out.println(i+++" "+element2.text());

		}

		Elements el_3 = HtmlActions.getElementById(el_2, 69);
		i=0;
		for (Element element2 : el_3) {
			System.out.println(i+++" "+element2.text());

		}

	}
	*/
	
	

	
}

