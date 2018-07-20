package it.spinore.grabber;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import it.spinore.grabber.action.HtmlActions;
import it.spinore.grabber.action.Validation;
import it.spinore.grabber.bean.Instruction;
import it.spinore.grabber.bean.Viewer;

public class TestMain {

	public static void main(String[] args) throws Exception {


		int i = 0;
		ArrayList<Viewer> listaOut = new ArrayList<Viewer>();
		Elements elements = null;

		//Connesione tramite proxy
		Document doc = Jsoup.connect("http://www.nimbus.it/moncalieri/moncalieri.asp")
				.proxy("app001fipv5000p.corp.infogroup.it", 8080)
				.get();

		elements = doc.getAllElements();
		
		String[] istruzioniInput = {"Dati di oggi","htmlTag:div"};

		ArrayList<Instruction> istruzioni = Validation.getIstruzioni(istruzioniInput);
		

		for (Instruction instruction : istruzioni) {
			if(instruction.getElementNumber()!=0)
				elements= HtmlActions.getElementById(elements, instruction.getElementNumber());
			else if (instruction.getHtmlTag()!=null)
				elements = HtmlActions.getElementByHtmlTag(elements,instruction.getHtmlTag());
			else if (instruction.getTriggerString()!=null)
				elements = HtmlActions.getElementByText(elements, instruction.getTriggerString());
		}





		if(elements!=null) {
			for (Element elemento : elements) {
				Viewer out = new Viewer();
				out.setElement(elemento.text());
				out.setHtmlTag(elemento.tagName());
				out.setIndex(i++);
				listaOut.add(out);

			}
		}


	




		for (Element elemento : elements) {

			System.out.println((elemento.text())+" "+elemento.tagName()+" "+i++);


		}
	}
}


