package com.brick.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Util {

  private static final String DATA_TESTID = "data-testid";
  private static final String LIST_ID = "lstCL2ProductList";

  public void produceFile(String html) throws Exception {
    String response = getResponse(html);
    Document doc = Jsoup.parse(response);
    Elements list = doc.getElementsByAttributeValue(DATA_TESTID, LIST_ID);
//    System.out.println("List Text: " + list.toString());
    Elements containers = list.select("a[data-testid=lnkProductContainer]");
//    System.out.println("Wrappers: " + wrappers.toString());
    System.out.println("Containers size: " + containers.size());
    for(Element con : containers) {
      String href = con.attr("href");
//      System.out.println("HREF: " + href);
      Elements wrappers = con.select("div[data-testid=divProductWrapper]");
      System.out.println("Wrappers size: " + wrappers.size());
      for(Element wrp : wrappers) {
//        System.out.println("Wrapper...");
        String imgLink = wrp.select("img[crossorigin=anonymous]").first().attr("src");
        System.out.println("Img link: " + imgLink);
      }
    }

  }
  public String getResponse(String html) throws Exception {

    URL url = new URL(html);
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestMethod("GET");
    int status = con.getResponseCode();
    System.out.println("Status: " + status);

    Reader streamReader = null;

    if (status > 299) {
      streamReader = new InputStreamReader(con.getErrorStream());
    } else {
      streamReader = new InputStreamReader(con.getInputStream());
    }

    BufferedReader in = new BufferedReader(
            new InputStreamReader(con.getInputStream()));
    String inputLine;
    StringBuffer content = new StringBuffer();
    while ((inputLine = in.readLine()) != null) {
      content.append(inputLine);
    }
    in.close();

    System.out.println("Response: " + content.toString());

    return content.toString();
  }
  
}