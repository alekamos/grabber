package it.spinore.grabber.service;

import java.io.IOException;
import java.util.ArrayList;

import javax.validation.Valid;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.spinore.grabber.action.HtmlActions;
import it.spinore.grabber.action.Validation;
import it.spinore.grabber.bean.Instruction;
import it.spinore.grabber.bean.SimpleBean;
import it.spinore.grabber.bean.Viewer;

@RestController
public class HtmlService {


	@RequestMapping("/grabElement")
	public ArrayList<Viewer> grabElement(@RequestParam(value="url") String url,
			@RequestParam(value="text", required=false) String text,
			@RequestParam(value="index", defaultValue="0") int index,
			@RequestParam(value="htmlTag",required=false) String htmlTag
			) throws IOException {


		int i = 0;
		ArrayList<Viewer> listaOut = new ArrayList<Viewer>();
		Elements elements = null;

		//Connesione tramite proxy
//		Document doc = Jsoup.connect(url)
//				.proxy("proxyAddress", 8080)
//				.get();

		//Connesione semplice
		Document doc = Jsoup.connect(url).get();
				
				


		if(index!=0 && text!=null)
			elements= HtmlActions.getElementByTextAndId(doc, text, index);
		else if (htmlTag!=null && text!=null)
			elements = HtmlActions.getElementByTextAndHtmlTag(doc, text, htmlTag);
		else if (index==0 && htmlTag==null && text!=null)
			elements = HtmlActions.getElementByText(doc, text);
		else
			elements = doc.getAllElements();




		if(elements!=null) {
			for (Element elemento : elements) {
				Viewer out = new Viewer();
				out.setElement(elemento.text());
				out.setHtmlTag(elemento.tagName());
				out.setIndex(i++);
				listaOut.add(out);

			}
		}

		return listaOut;
	}


	@RequestMapping("/grabHtmlData")
	public ArrayList<Viewer> grabHtmlData(@RequestParam(value="url") String url,
			@RequestParam(value="instruction", required=false) String[] istruzioniInput
			) throws Exception {


		int i = 0;
		ArrayList<Viewer> listaOut = new ArrayList<Viewer>();
		Elements elements = null;

		//Connesione tramite proxy
//		Document doc = Jsoup.connect(url)
//				.proxy("proxyAddress", 8080)
//				.get();
                
                Document doc = Jsoup.connect(url).get();

		elements = doc.getAllElements();

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

		return listaOut;
	}



}
