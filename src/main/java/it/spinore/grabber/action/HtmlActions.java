package it.spinore.grabber.action;

import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import it.spinore.grabber.bean.Instruction;

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

	public static Elements getElementByTextAndHtmlTag(Document doc,String text,String htmlTag){
		Elements out = new Elements();
		Elements elem = doc.getElementsContainingText(text);



		for (Element element : elem) {
			if (htmlTag.equals(element.tagName()))
				out.add(element);


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

	public static Elements getElementByHtmlTag(Elements elements, String htmlTag) {

		Elements out = new Elements();


		for (Element element : elements) {
			if (htmlTag.equals(element.tagName()))
				out.add(element);


		}

		return out;
	}

	public static Elements getElementByText(Elements elements, String triggerString) {

		Elements out = new Elements();

		for (Element element : elements) {
			if(element.text().contains(triggerString))
				out.add(element);

		}
		return out;
	}


}
