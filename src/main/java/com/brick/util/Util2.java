//package com.brick.util;
//
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
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//import org.apache.http.util.TextUtils;
//
//public class Util2 {
//
//  private static final String DATA_TESTID = "data-testid";
//  private static final String LIST_ID = "lstCL2ProductList";
//  static final String COOKIES_HEADER = "Set-Cookie";
//  static java.net.CookieManager msCookieManager = new java.net.CookieManager();
//
//  public void produceFile(String address) throws Exception {
//    String response = null;
//    response = getResponse(address);
//      System.out.println("Executed 1");
//    response = getResponse(address);
//      System.out.println("Executed 2");
////    Document doc = Jsoup.parse(response);
//////    Document doc = Jsoup.connect(address).get();
////    Elements list = doc.getElementsByAttributeValue(DATA_TESTID, LIST_ID);
//////    System.out.println("List Text: " + list.toString());
////    Elements containers = list.select("a[data-testid=lnkProductContainer]");
//////    System.out.println("Wrappers: " + wrappers.toString());
////    System.out.println("Containers size: " + containers.size());
////    for(Element con : containers) {
////      String detailHRef = con.attr("href");
////      System.out.println("Detail HREF: " + detailHRef);
////      Document descDoc = null;
////      String name = null;
////      String price = null;
////      String imgLink = null;
////      String description = null;
////      String stars = null;
////      
//////      System.out.println("HREF: " + href);
////      Element wrp = con.select("div[data-testid=divProductWrapper]").first();
//////      System.out.println("Wrappers size: " + wrappers.size());
//////        System.out.println("Wrapper...");
//////        name = wrp.select("span.css-1bjwylw").first().text();
//////        price = wrp.select("span.css-o5uqvq").first().text();
//////        imgLink = wrp.select("img[crossorigin=anonymous]").first().attr("src");
////        
////        response = getResponse(detailHRef);
////        descDoc = Jsoup.parse(response);
//////        descDoc = Jsoup.connect(detailHRef).get();
////        String style = descDoc.select("div.magnifier").first().attr("style");
////        description = descDoc.select("div[data-testid=lblPDPDescriptionProduk]").first().text();
////        stars = descDoc.select("h5[data-testid=txtRatingScore]").first().text();
////        System.out.println("Name: " + name);
////        System.out.println("Price: " + price);
////        System.out.println("Description: " + description);
////        System.out.println("Stars: " + stars);
////        System.out.println("Img link: " + style);
////        break;
//////        System.out.println("Img link: " + imgLink);
////    }
//
//  }
//  public String getResponse(String address) throws Exception {
//      System.out.println("Executing getResponse");
//    //TODO: remove this
////    address = "https://www.wikipedia.org/";
//    
//    URL url = new URL(address);
//    HttpURLConnection con = (HttpURLConnection) url.openConnection();
//    con.setInstanceFollowRedirects(false);
//    
//    System.out.println("Cookie size: " + msCookieManager.getCookieStore().getCookies().size() );
//    con.setRequestProperty("accept", 
//            "text/html,application/xhtml+xml,application/xml;q=0.9,"
//                    + "image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
//    
////    con.setRequestProperty(":authority", "www.tokopedia.com");
////    con.setRequestProperty(":method", "GET");
////    con.setRequestProperty(":path", "/p/handphone-tablet/handphone");
////    con.setRequestProperty(":scheme", "https");
//
////    con.setRequestProperty("accept-encoding", "gzip, deflate, br");
//    con.setRequestProperty("accept-language", "en-US,en;q=0.9");
//    con.setRequestProperty("ect", "4g");
//    con.setRequestProperty("sec-ch-ua", "\" Not A;Brand\";v=\"99\", \"Chromium\";v=\"96\", \"Microsoft Edge\";v=\"96\"");
//    con.setRequestProperty("sec-ch-ua-platform", "\"Windows\"");
//    con.setRequestProperty("sec-fetch-dest", "document");
//    con.setRequestProperty("sec-fetch-mode", "navigate");
//    con.setRequestProperty("sec-fetch-site", "none");
//    con.setRequestProperty("sec-fetch-user", "?1");
//    con.setRequestProperty("upgrade-insecure-requests", "1");
//    con.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.55 Safari/537.36 Edg/96.0.1054.41");
//    
////    if (msCookieManager.getCookieStore().getCookies().size() > 0) {
////        // While joining the Cookies, use ',' or ';' as needed. Most of the servers are using ';'
////        String cookieString = msCookieManager.getCookieStore().getCookies().stream()
////                .map(Object::toString) 
////                .collect(Collectors.joining(";"));
////        
////        //TODO: remove this
////        cookieString = "bm_sz=6D5E669196207784334D1E4CCE1B434A~YAAQ7l9idspaVC19AQAANEaHgA1WeCpC4DjAbI0TfvhiQ5/WHkQlkeY4oiQVmjldkKtx7x7/Hvpphhnr/eeOi2oMjIfdOGjZxlr1Lu65l2dLFYgR8rbeoLXkAkmBff/YEn4+83R7LZkC5juD/P7JeILSDdtmkxtpwvPtg9EdvJ3R38n8uwRYXyjqGP18MZabdStmn1XyjbQZVpJWLKlDG2wORFYBWdC/14v+ETcLn3fOlei6FCO72099ah+L0ggrhBp9pMG/cNMT5Trgbp7bsEB5tRzyIjYMtqmpA7T098BFi7oBopo=~3622454~4535106; _UUID_NONLOGIN_=69415a9573e96a26286f216ef2e95353; _UUID_NONLOGIN_.sig=u7zhzp4stMvPAED3HUV4f-0-qHA; _SID_Tokopedia_=Im5K_yGsh85Ur7whslvr4M5I6IrTd2qJbFGrsS3mk1MdG7cN-JDAydT3MK5iYlP5hqtBn0ZTqyZb-W4BN-DpCqyNM_VRxljmEBUrYXH-mfcnVy-Zeiyay1ZIhD6-hCiE; DID=4e0caab6faabcd297ee1fa01770c0939a226d06c90ee183dd2690edb0bd830eb5e418b6a59efc42774434259dc24d84e; DID_JS=NGUwY2FhYjZmYWFiY2QyOTdlZTFmYTAxNzcwYzA5MzlhMjI2ZDA2YzkwZWUxODNkZDI2OTBlZGIwYmQ4MzBlYjVlNDE4YjZhNTllZmM0Mjc3NDQzNDI1OWRjMjRkODRl47DEQpj8HBSa+/TImW+5JCeuQeRkm5NMpJWZG3hSuFU=; ak_bmsc=AF3F7173B8018345581FCF85127D11CA~000000000000000000000000000000~YAAQ9l9idnqU2299AQAA9cGTgA3/uEdraDO//XMAF2Q+09BBP7yaeN2PGA9zGF8ti3Thzhq2S3WmeOte9dfFlGXFAga1oksO7WZqQ4JyHLqFflYESlPDY3qwOEsH4f1rsJgrdI9nfqJMufgvZzXo6l+t0xbiIJZF3Lo2SaY9LTO0TvK0/iSGcVQ6ehV8UmjM6/FyhpVvY/gFSlpHLNxuPf+bqH1UYJlTjGuM6rxbZ6KZfvso/EwJOToN9lnnnhECmLFuoUO5fUG3B0YOYncjgobKnonUr4gsRVz1uD69p3T1K1CwcLLZFjtOp81QcXun6a3vzexawEorDV8C4VwbWNUkDcQbU63F8N2JrtSDQAZxCn8pFR/qfCBM0sCcuL0nKMyAJWDDQiMlotrstEFLv63g8ICOaGHCETTrcf4mhN/1FG7AELidGfusQFT6YD/zYSiNPrM4RYtMjv99r47gckSaN5eHbpK7NCIMyw8gFX4G8xnWS4Nj11VlKtWXPQ==; _gcl_au=1.1.1457013539.1638539712; _gid=GA1.2.1640313463.1638539712; _UUID_CAS_=f0a8eafe-c6e5-4df5-a125-98c48ef9c3e7; _CAS_=%7B%22dId%22%3A2274%2C%22aId%22%3A0%2C%22lbl%22%3A%22Jakarta%20Pusat%22%2C%22cId%22%3A176%2C%22long%22%3A%22%22%2C%22lat%22%3A%22%22%2C%22pCo%22%3A%22%22%2C%22wId%22%3A12210375%2C%22sId%22%3A11530573%7D; _CASE_=742d6b466b2d353d3d383b232d6e466b2d353f232d636d632d352d456e646e7d7b6e2f5f7a7c6e7b2d232d6c466b2d353e3839232d636061682d352d2d232d636e7b2d352d2d232d7f4c602d352d2d232d78466b2d353e3d3d3e3f3c383a232d7c466b2d353e3e3a3c3f3a383c72; __asc=0b0fc4dd17d8093e4e54e588458; __auc=0b0fc4dd17d8093e4e54e588458; _fbp=fb.1.1638539716778.1035390410; _jx=a145c910-5443-11ec-a0d7-37b40964ad36; _jxs=1638540998-a145c910-5443-11ec-a0d7-37b40964ad36; bm_sv=CE0EB0901FF1CA615DCCD0892264A510~SbAwifTU5V6HAM3Zp8GO8IEhPrOogOdrOgr29muPQkJS7h2V4RDTyyGhEQJtONRq8kLAMM4CBj5mHLTS2/0vW0Tfp0sfzJT1fu56Wgn20XLenLIQjAkymoP8jaR6BIkqRl+xlFwzJ+mSyyAc3YFpeT/RhmhkBoBmS4DjCytefBQ=; _ga_70947XW48P=GS1.1.1638539711.1.1.1638541014.42; _ga=GA1.2.1268648023.1638539712; cto_bundle=8Rua6V9VbVVNMTUlMkJpaklGOCUyQlY1enNTOVJsZGRuTzElMkZ3blFkMVZJa2ZqaXJSM2Z2Tnd4bHRRMnpxVXlTTU5hWGU0SjBjektTT1RHVTFDaUU2UXBCN3k3eXZEeERqOW9TZ3R5ZktvdHQ5THVUT3JqdWpxOGRpbVNGdVNMNjhYMGQ1QmE5SDNyWWQ0YmJJMjlLT09ZSmRCeWxwb3clM0QlM0Q; _abck=885D7D810424D31F95DC593C42E8F232~-1~YAAQ7l9idkXuVC19AQAA+b+ngAaPES8afRPrcQcx8dl27dlHBek+58rIsoe3pcAG44Tg+cMl1y3cY6g1GS4kjuaOYd1Pqc6HTtJNPSv5l8AdYCUyH6QFRUwl/KYvgB7nRUfk3Ev2WOjHg6Wn9bIKY9c+svlFa36riRF2eESYUtfqSwAKr6oSS8PS/p72s9n8rusOgd0aD0ADrZ+7N1GKLwRZlRlhARZCUJ4/h5pPz5b5MC58p0lpVQhzo09eWUpoMuu5+O0Ibg7rzQjx2028TYNRLo0DWTnY9koeWdXh9Iwcfvr9tqo7O0joDC9h3YcmuH3H1QGVjH9/fDEjzp8z/Hf+LPSPPA+WL7Bcwdb2qOV+ZkkzTKpCHrrIBlrLuC8yUkwFSLs+VODrgdSuCM2EdHdjfGya6nZaOG0Jrw==~-1~-1~-1";
////        System.out.println("Cookie String: " + cookieString);
////        con.setRequestProperty("Cookie", cookieString);
////    }
//
//    //TODO: remove this
////    String cookieString = "bm_sz=6D5E669196207784334D1E4CCE1B434A~YAAQ7l9idspaVC19AQAANEaHgA1WeCpC4DjAbI0TfvhiQ5/WHkQlkeY4oiQVmjldkKtx7x7/Hvpphhnr/eeOi2oMjIfdOGjZxlr1Lu65l2dLFYgR8rbeoLXkAkmBff/YEn4+83R7LZkC5juD/P7JeILSDdtmkxtpwvPtg9EdvJ3R38n8uwRYXyjqGP18MZabdStmn1XyjbQZVpJWLKlDG2wORFYBWdC/14v+ETcLn3fOlei6FCO72099ah+L0ggrhBp9pMG/cNMT5Trgbp7bsEB5tRzyIjYMtqmpA7T098BFi7oBopo=~3622454~4535106; _UUID_NONLOGIN_=69415a9573e96a26286f216ef2e95353; _UUID_NONLOGIN_.sig=u7zhzp4stMvPAED3HUV4f-0-qHA; _SID_Tokopedia_=Im5K_yGsh85Ur7whslvr4M5I6IrTd2qJbFGrsS3mk1MdG7cN-JDAydT3MK5iYlP5hqtBn0ZTqyZb-W4BN-DpCqyNM_VRxljmEBUrYXH-mfcnVy-Zeiyay1ZIhD6-hCiE; DID=4e0caab6faabcd297ee1fa01770c0939a226d06c90ee183dd2690edb0bd830eb5e418b6a59efc42774434259dc24d84e; DID_JS=NGUwY2FhYjZmYWFiY2QyOTdlZTFmYTAxNzcwYzA5MzlhMjI2ZDA2YzkwZWUxODNkZDI2OTBlZGIwYmQ4MzBlYjVlNDE4YjZhNTllZmM0Mjc3NDQzNDI1OWRjMjRkODRl47DEQpj8HBSa+/TImW+5JCeuQeRkm5NMpJWZG3hSuFU=; ak_bmsc=AF3F7173B8018345581FCF85127D11CA~000000000000000000000000000000~YAAQ9l9idnqU2299AQAA9cGTgA3/uEdraDO//XMAF2Q+09BBP7yaeN2PGA9zGF8ti3Thzhq2S3WmeOte9dfFlGXFAga1oksO7WZqQ4JyHLqFflYESlPDY3qwOEsH4f1rsJgrdI9nfqJMufgvZzXo6l+t0xbiIJZF3Lo2SaY9LTO0TvK0/iSGcVQ6ehV8UmjM6/FyhpVvY/gFSlpHLNxuPf+bqH1UYJlTjGuM6rxbZ6KZfvso/EwJOToN9lnnnhECmLFuoUO5fUG3B0YOYncjgobKnonUr4gsRVz1uD69p3T1K1CwcLLZFjtOp81QcXun6a3vzexawEorDV8C4VwbWNUkDcQbU63F8N2JrtSDQAZxCn8pFR/qfCBM0sCcuL0nKMyAJWDDQiMlotrstEFLv63g8ICOaGHCETTrcf4mhN/1FG7AELidGfusQFT6YD/zYSiNPrM4RYtMjv99r47gckSaN5eHbpK7NCIMyw8gFX4G8xnWS4Nj11VlKtWXPQ==; _gcl_au=1.1.1457013539.1638539712; _gid=GA1.2.1640313463.1638539712; _UUID_CAS_=f0a8eafe-c6e5-4df5-a125-98c48ef9c3e7; _CAS_=%7B%22dId%22%3A2274%2C%22aId%22%3A0%2C%22lbl%22%3A%22Jakarta%20Pusat%22%2C%22cId%22%3A176%2C%22long%22%3A%22%22%2C%22lat%22%3A%22%22%2C%22pCo%22%3A%22%22%2C%22wId%22%3A12210375%2C%22sId%22%3A11530573%7D; _CASE_=742d6b466b2d353d3d383b232d6e466b2d353f232d636d632d352d456e646e7d7b6e2f5f7a7c6e7b2d232d6c466b2d353e3839232d636061682d352d2d232d636e7b2d352d2d232d7f4c602d352d2d232d78466b2d353e3d3d3e3f3c383a232d7c466b2d353e3e3a3c3f3a383c72; __asc=0b0fc4dd17d8093e4e54e588458; __auc=0b0fc4dd17d8093e4e54e588458; _fbp=fb.1.1638539716778.1035390410; _jx=a145c910-5443-11ec-a0d7-37b40964ad36; _jxs=1638540998-a145c910-5443-11ec-a0d7-37b40964ad36; bm_sv=CE0EB0901FF1CA615DCCD0892264A510~SbAwifTU5V6HAM3Zp8GO8IEhPrOogOdrOgr29muPQkJS7h2V4RDTyyGhEQJtONRq8kLAMM4CBj5mHLTS2/0vW0Tfp0sfzJT1fu56Wgn20XLenLIQjAkymoP8jaR6BIkqRl+xlFwzJ+mSyyAc3YFpeT/RhmhkBoBmS4DjCytefBQ=; _ga_70947XW48P=GS1.1.1638539711.1.1.1638541014.42; _ga=GA1.2.1268648023.1638539712; cto_bundle=8Rua6V9VbVVNMTUlMkJpaklGOCUyQlY1enNTOVJsZGRuTzElMkZ3blFkMVZJa2ZqaXJSM2Z2Tnd4bHRRMnpxVXlTTU5hWGU0SjBjektTT1RHVTFDaUU2UXBCN3k3eXZEeERqOW9TZ3R5ZktvdHQ5THVUT3JqdWpxOGRpbVNGdVNMNjhYMGQ1QmE5SDNyWWQ0YmJJMjlLT09ZSmRCeWxwb3clM0QlM0Q; _abck=885D7D810424D31F95DC593C42E8F232~-1~YAAQ7l9idkXuVC19AQAA+b+ngAaPES8afRPrcQcx8dl27dlHBek+58rIsoe3pcAG44Tg+cMl1y3cY6g1GS4kjuaOYd1Pqc6HTtJNPSv5l8AdYCUyH6QFRUwl/KYvgB7nRUfk3Ev2WOjHg6Wn9bIKY9c+svlFa36riRF2eESYUtfqSwAKr6oSS8PS/p72s9n8rusOgd0aD0ADrZ+7N1GKLwRZlRlhARZCUJ4/h5pPz5b5MC58p0lpVQhzo09eWUpoMuu5+O0Ibg7rzQjx2028TYNRLo0DWTnY9koeWdXh9Iwcfvr9tqo7O0joDC9h3YcmuH3H1QGVjH9/fDEjzp8z/Hf+LPSPPA+WL7Bcwdb2qOV+ZkkzTKpCHrrIBlrLuC8yUkwFSLs+VODrgdSuCM2EdHdjfGya6nZaOG0Jrw==~-1~-1~-1";
////    con.setRequestProperty("Cookie", cookieString);
//    
//    con.setRequestMethod("GET");
//    int status = con.getResponseCode();
//    System.out.println("Status: " + status);
//    
//    Map<String, List<String>> headerFields = con.getHeaderFields();
//    List<String> cookiesHeader = headerFields.get(COOKIES_HEADER);
//      System.out.println("Cookie header size is: " + cookiesHeader.size());
//      cookiesHeader.stream().forEach(
//              c -> {
//                  System.out.println("\n\nCookies header: " + c.toString());
//              }
//      );
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
//    System.out.println("Response: " + content.toString());
//
//    return content.toString();
//  }
//  
//}