//package com.brick.util;
//
//import com.brick.entity.Product;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.io.Reader;
//import java.net.HttpCookie;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.net.URLDecoder;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//import org.apache.http.util.TextUtils;
//
//public class TokopediaUtil {
//
//  private static final String DATA_TESTID = "data-testid";
//  private static final String LIST_ID = "lstCL2ProductList";
//  static final String COOKIES_HEADER = "Set-Cookie";
//  static java.net.CookieManager msCookieManager = new java.net.CookieManager();
//
//  public static void produceTop10PhonesFile() throws Exception {
//    String address = "https://www.tokopedia.com/p/handphone-tablet/handphone";
//    String response = null;
//    List<Product> listProduct = new ArrayList<Product>();
//    
//    response = getResponse(address);
//    Document doc = Jsoup.parse(response);
////    Elements list = doc.getElementsByAttributeValue(DATA_TESTID, LIST_ID);
////    System.out.println("List Text: " + list.toString());
////    Elements containers = list.select("a[data-testid=lnkProductContainer]");
//    Element mainCat = doc.select("div[data-ssr=mainCategoryL2/L3SSR]").first();
//      System.out.println("mainCat: " + mainCat.tagName());
//      
//      //TODO: somehow it didn't work although the class is there, probably because of the space
////    Element pageInfo = mainCat.select("div.css-1dq1dix e1nlzfl1").first();
//    Element pageInfo = mainCat.select("div.css-13wayc1").first();
//      System.out.println("Page Info: " + pageInfo.tagName());
//    Elements containers = doc.select("a[data-testid=lnkProductContainer]");
////    Elements containers = doc.select("div.css-bk6tzz.e1nlzfl3");        
////    System.out.println("Wrappers: " + wrappers.toString());
//    System.out.println("Containers size: " + containers.size());
//    for(Element con : containers) {
//      String detailHRef = con.attr("href");
//      String productRef = null;
//      System.out.println("Detail HREF: " + detailHRef);
//      String queryString = detailHRef.substring(detailHRef.indexOf("?") + 1);
//      System.out.println("Query String is: " + queryString);
//      String[] parameters = queryString.split("&");
//      for(String param : parameters) {
//          String[] paramKeyAndValue = param.split("=");
//          System.out.println("Param = " + paramKeyAndValue[0] + (paramKeyAndValue.length >= 2 ?
//                  " value: " + paramKeyAndValue[1] : ""));
//          if(paramKeyAndValue[0].equalsIgnoreCase("r")) {
//              productRef = paramKeyAndValue[1];
//              productRef = URLDecoder.decode(productRef, "ASCII");
//              break;
//          }
//      }
//      System.out.println("Product ref: " + productRef);
//      Document descDoc = null;
//      String name = null;
//      String price = null;
//      String imgLink = null;
//      String description = null;
//      String stars = null;
//      
////      System.out.println("HREF: " + href);
//      Element wrp = con.select("div[data-testid=divProductWrapper]").first();
////      System.out.println("Wrappers size: " + wrappers.size());
////        System.out.println("Wrapper...");
//        name = wrp.select("span.css-1bjwylw").first().text();
//        price = wrp.select("span.css-o5uqvq").first().text();
////        imgLink = wrp.select("img[crossorigin=anonymous]").first().attr("src");
//        
//        response = getResponse(productRef);
//        descDoc = Jsoup.parse(response);
////        descDoc = Jsoup.connect(detailHRef).get();
//        String style = descDoc.select("div.magnifier").first().attr("style");
//        imgLink = style.substring(style.indexOf("(") + 1, style.indexOf(")"));
//        description = descDoc.select("div[data-testid=lblPDPDescriptionProduk]").first().text();
//        stars = descDoc.select("h5[data-testid=txtRatingScore]").first().text();
//        System.out.println("Name: " + name);
//        System.out.println("Price: " + price);
//        System.out.println("Description: " + description);
//        System.out.println("Stars: " + stars);
//        System.out.println("Img link: " + imgLink);
//        break;
////        System.out.println("Img link: " + imgLink);
//    }
//
//  }
//  private static String getResponse(String address) throws Exception {
////    System.out.println("Executing getResponse");    
//    URL url = new URL(address);
//    HttpURLConnection con = (HttpURLConnection) url.openConnection();
//    con.setInstanceFollowRedirects(false);
//    con.setReadTimeout(10000);
//    
////    System.out.println("Cookie size: " + msCookieManager.getCookieStore().getCookies().size() );
//    con.setRequestProperty("accept", 
//            "text/html,application/xhtml+xml,application/xml;q=0.9,"
//                    + "image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
//    con.setRequestProperty("accept-language", "en-US,en;q=0.9");
//    con.setRequestProperty("Connection", "keep-alive");
//    con.setRequestProperty("ect", "4g");
//    con.setRequestProperty("Host", "ta.tokopedia.com");
//    con.setRequestProperty("Referer", "https://www.tokopedia.com/p/handphone-tablet/handphone");
//    con.setRequestProperty("sec-ch-ua", "\" Not A;Brand\";v=\"99\", \"Chromium\";v=\"96\", \"Microsoft Edge\";v=\"96\"");
//    con.setRequestProperty("sec-ch-ua-platform", "\"Windows\"");
//    con.setRequestProperty("sec-fetch-dest", "document");
//    con.setRequestProperty("sec-fetch-mode", "navigate");
//    con.setRequestProperty("sec-fetch-site", "none");
//    con.setRequestProperty("sec-fetch-user", "?1");
//    con.setRequestProperty("upgrade-insecure-requests", "1");
//    con.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.55 Safari/537.36 Edg/96.0.1054.41");
//    
//    if (msCookieManager.getCookieStore().getCookies().size() > 0) {
//        // While joining the Cookies, use ',' or ';' as needed. Most of the servers are using ';'
//        String cookieString = msCookieManager.getCookieStore().getCookies().stream()
//                .map(Object::toString) 
//                .collect(Collectors.joining(";"));
////        System.out.println("Cookie String: " + cookieString);
//        con.setRequestProperty("Cookie", cookieString);
//    }
//    con.setRequestMethod("GET");
////    Map<String, List<String>> requestProperties = con.getRequestProperties();
////    requestProperties.keySet().stream().forEach(
////            k -> {
////                List<String> h = requestProperties.get(k);
////                System.out.println("==============================");
////                System.out.println("Header: " + k + ", value: " + h);
////            }
////    );
//    int status = con.getResponseCode();
//    System.out.println("Status: " + status);
//    
//    Map<String, List<String>> headerFields = con.getHeaderFields();
//    List<String> cookiesHeader = headerFields.get(COOKIES_HEADER);
////    System.out.println("Cookie header size is: " + cookiesHeader.size());
////    cookiesHeader.stream().forEach(
////            c -> {
////                System.out.println("\n\nCookies header: " + c.toString());
////            }
////    );
//
//    if (cookiesHeader != null) {
//        for (String cookie : cookiesHeader) {
//            List<HttpCookie> cookies = HttpCookie.parse(cookie);
//            cookies.stream().forEach(
//                    c -> {
//                        msCookieManager.getCookieStore().add(null,c);
//                    }
//            );
////            msCookieManager.getCookieStore().add(null,HttpCookie.parse(cookie).get(0));
//        }               
//    }
//
//    Reader streamReader = null;
//
//    if (status > 299) {
//      streamReader = new InputStreamReader(con.getErrorStream());
//        System.out.println(" !!! ERROR HAPPENED !!!!");
//    } else {
//      streamReader = new InputStreamReader(con.getInputStream());
//    }
//
//    BufferedReader in = new BufferedReader(
//            new InputStreamReader(con.getInputStream()));
//    String inputLine;
//    StringBuffer content = new StringBuffer();
//    while ((inputLine = in.readLine()) != null) {
//      content.append(inputLine);
//    }
//    in.close();
//    con.disconnect();
//
////    System.out.println("Response: " + content.toString());
//
//    return content.toString();
//  }
//  
//}