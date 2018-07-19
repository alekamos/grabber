package it.spinore.grabber.action;

import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlActions {


	public static Elements getElementByText(Document doc,String text){

		Elements elem = doc.getElementsContainingText(text);

		return elem;

	}

	public static Elements getElementByTextAndId(Document doc,String text,int id){

		Elements elem = doc.getElementsContainingText(text);
		Elements out = null;
		int i= 0;
		for (Element element : elem) {
			if (i++ == id)
				out = element.getAllElements();


		}

		return out;

	}

	public static Elements getElementById(Elements elem, int id){

		Elements out = null;
		int i= 0;
		for (Element element : elem) {
			if (i++ == id)
				out = element.getAllElements();


		}

		return out;

	}


}
