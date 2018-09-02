package it.spinore.grabber.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.spinore.grabber.action.HtmlActions;
import it.spinore.grabber.action.Validation;
import it.spinore.grabber.bean.Instruction;
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
			@RequestParam(value="instruction", required=false) String[] istruzioniInput,
			@RequestParam(value="encoding", required=false) String encoding
			) throws Exception {


		int i = 0;
		ArrayList<Viewer> listaOut = new ArrayList<Viewer>();
		Elements elements = null;
		Document doc;
		URL urlOff = new URL(url);

		//Connesione tramite proxy
		//		Document doc = Jsoup.connect(url)
		//				.proxy("proxyAddress", 8080)
		//				.get();

		//		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxyAddress", 8080)); // or whatever your proxy is

		if(encoding!=null) {
			HttpURLConnection uc = (HttpURLConnection) urlOff.openConnection();
			uc.connect();

			String line = null;
			StringBuffer tmp = new StringBuffer();
			BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(),encoding));
			while ((line = in.readLine()) != null) {
				tmp.append(line);
			}
			
			doc = Jsoup.parse(String.valueOf(tmp));
		}else 
			doc = Jsoup.connect(url).get();
		
		
		
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
