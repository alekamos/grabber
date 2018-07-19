package it.spinore.grabber.service;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.spinore.grabber.action.HtmlActions;
import it.spinore.grabber.bean.Viewer;

@RestController
public class HtmlService {


    @RequestMapping("/grabElement")
    public ArrayList<Viewer> grabElement(@RequestParam(value="url") String url,@RequestParam(value="text") String text) throws IOException {
    	
    	
    	int i = 0;
    	ArrayList<Viewer> listaOut = new ArrayList<Viewer>();
    	
    	Document doc = Jsoup.connect(url).get();
    	Elements elements = HtmlActions.getElementByText(doc, text);
    	
  
    	
        
		for (Element elemento : elements) {
        	Viewer out = new Viewer();
        	out.setElement(elemento.text());
        	out.setHtmlTag(elemento.tagName());
        	out.setId(i++);
        	listaOut.add(out);
        	
		}

   
    	 
    	 
    	return listaOut;
    }
	
}
