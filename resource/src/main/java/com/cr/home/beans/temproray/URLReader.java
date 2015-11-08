package com.cr.home.beans.temproray;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class URLReader {
	
   /* public String getHtml() {
    	char[] input=new char[100];
int i=1;
		URL urlLink;
		try {
			urlLink = new URL("https://hpqproxy-itg.external.hp.com/idp/startSSO.ping?PartnerSpId=107142:OSS:OT&TargetResource=https%3A%2F%2Foss-tst.corp.hp.com%2Fos%2Fcommon%2Flib%2FhpOSSWidgetInt.js");
        
			BufferedReader in;
			in = new BufferedReader(new InputStreamReader(urlLink.openStream()));
	
    	int k=0;
        String inputLine;

			while ((inputLine = in.readLine()) != null){
				String str=inputLine.replaceAll("\\s+","");
				if(str.equals("<tdclass=\"credential-choice\">")){
					if(i==1){
						i=2;
					}else{
					System.out.println(str);
					inputLine= in.readLine();
					String actualStrng=inputLine.replaceAll("\\s+","");
					char [] strg={};
					strg=actualStrng.toCharArray();
				
					for(int j=7; j<=actualStrng.length();j++){
						if(strg[j]!='"'){
							input[k]=strg[j];
							k++;
						}else if(strg[j]=='"'){
							if(i==2){
								i=3;
								continue;
							}else{
							break;
							}
						}
					}
					}
				}
			}
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String url=getScript(input);
        return url;
    }

	private String getScript(char[] input) {
		String scriptUrl="";
		for (Character c : input){
			if(c!='\u0000'){
				scriptUrl += c.toString();
			}else{
				break;
			}
		}
		
		String url="https://hpqproxy-itg.external.hp.com"+scriptUrl;
		System.out.println(url);
		try {
			Document doc = Jsoup.connect(url).userAgent("Chrome").data("name", "jsoup").get();
			System.out.println(doc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		/*try{
			InputStream is;
			URL urlLink = new URL(url);
			is=urlLink.openStream();
			in = new BufferedReader(new InputStreamReader(is));
				String inputLine;
		        while ((inputLine = in.readLine()) != null){
		        	System.out.println(inputLine);
		        }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
	/*	return url;
	}*/
	public String getHtml() {
		String linkHrefs=null;
		try {
			Connection.Response res = Jsoup.connect("https://hpqproxy-itg.external.hp.com/idp/startSSO.ping?PartnerSpId=107142:OSS:OT&TargetResource=https%3A%2F%2Foss-tst.corp.hp.com%2Fos%2Fcommon%2Flib%2FhpOSSWidgetInt.js").method(Method.POST).execute();
			Document doc=res.parse();
			String sessionId = res.cookie("JSESSIONID"); 
			/*System.out.println(doc);*/
			/*Elements content = doc.getElementsByClass("credential-choice");*/
			Element content = doc.getElementsByClass("credential-choice").last();
			Element link=content.select("a").first();
			  /*String linkHref = link.attr("href");*/
			   linkHrefs = link.attr("abs:href");
			  res = Jsoup.connect(linkHrefs).method(Method.POST).execute();
			 /* doc = Jsoup.connect("https://oss-tst.corp.hp.com/os/common/lib/hpOSSWidgetInt.js").get();
			  doc = Jsoup.connect(linkHrefs).userAgent("Chrome").get();
			  doc=Jsoup.connect("https://login-itg.ext.hpe.com/idp/SSO.saml2").get();
			 doc= Jsoup.connect("https://login-itg.ext.hpe.com/idp/G0qMp/resumeSAML20/idp/SSO.ping").get();
			  doc=Jsoup.connect("https://hpqproxy-itg.external.hp.com/sp/ACS.saml2").get();
			  Jsoup.connect("https://onehp-itg-idp.external.hp.com/sp/ACS.saml2").get();*/
			 
			  System.out.println(doc);
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return linkHrefs;
		
	}
	
	/*private static void checkForspace(String inputLine, BufferedReader in) {
		String str=inputLine.replaceAll("\\s+","");
		String set="<tdclass=\"credential-choice\">";
		if(str.equals(set)){
		System.out.println(str);
		}
	}*/
}