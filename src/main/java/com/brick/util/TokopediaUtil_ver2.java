package com.brick.util;

import com.brick.entity.Product;
import com.brick.exporter.CSVExporter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TokopediaUtil_ver2 {

  private static final String DATA_TESTID = "data-testid";
  private static final String LIST_ID = "lstCL2ProductList";
  private static final String HANDHONE_PAGE = "https://www.tokopedia.com/p/handphone-tablet/handphone";
  private static final String QUERY_PARAM_PAGE = "page";
  private static final int LIMIT_PRODUCT = 100;

  static final String COOKIES_HEADER = "Set-Cookie";
  private static WebDriver webDriver = null;
  
  static {
    initWebDriver();
  }
  
  private static void initWebDriver() {
    String driverPath = "D:/Programs/Chrome/chromedriver.exe";
    
    System.setProperty("webdriver.chrome.driver", driverPath);
    
    ChromeOptions options = new ChromeOptions();
    // setting headless mode to true.. so there isn't any ui
    options.setHeadless(true);
    options.addArguments("user-agent=Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.50 Safari/537.36");
    webDriver = new ChromeDriver(options);
    webDriver = new ChromeDriver();
  }

  public static void produceTopProductsInfoFile() throws Exception {
    String response = null;
    List<Product> listProduct = new ArrayList<Product>();

    int page = 1;
    String currentHandle = webDriver.getWindowHandle();
    do {
        String address = HANDHONE_PAGE + "?" + QUERY_PARAM_PAGE + "=" + page;
        System.out.println("============================================");
        System.out.println();
        
        webDriver.get(address);
        Thread.sleep(4000);
        String title = webDriver.getTitle();
    //    WebElement paginationElmt = webDriver.findElement(By.cssSelector(".css-1q668u-unf-pagination-items"));

        System.out.println("Title: " + title);

        JavascriptExecutor jsExec = (JavascriptExecutor) webDriver;

        for(int i=0 ; i<3; i++) {
            jsExec.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        }

        List<WebElement> containers = webDriver.findElements(By.cssSelector("a[data-testid=lnkProductContainer]"));
        System.out.println("Containers size: " + containers.size());

    //    if(jsExec != null)
    //        return;

        for(WebElement con : containers) {
          try {
            System.out.println("....................");
//            jsExec.executeScript("arguments[0].scrollIntoView();", con);
//            WebDriverWait waitCon = new WebDriverWait(webDriver, 6);      
//            waitCon.until(ExpectedConditions.visibilityOf(con));
//            Thread.sleep(5000);
//            Thread.sleep(3000);

            String detailHRef = con.getAttribute("href");
            String productRef = null;
            Product p = new Product();
            WebElement nameElmt = con.findElement(By.cssSelector("span.css-1bjwylw"));
            WebElement priceElmt = con.findElement(By.cssSelector("span.css-o5uqvq"));
            WebElement imgElmt = con.findElement(By.cssSelector("div.css-t8frx0 img"));
            WebElement sellerElmt = con.findElements(By.cssSelector("span.css-1kr22w3")).get(1);
            String name = nameElmt.getAttribute("innerText");
            String price = priceElmt.getAttribute("innerText");
            String imgLink = imgElmt.getAttribute("src");
            String seller = sellerElmt.getAttribute("innerText");
            
            p.setName(name);
            p.setPrice(price);
            p.setImageLink(imgLink);
            p.setSeller(seller);


            System.out.println("Detail HREF: " + detailHRef);
            System.out.println("Name: " + name);
            System.out.println("Price: " + price);
            System.out.println("Img Link: " + imgLink);
            System.out.println("Seller: " + seller);

            if(detailHRef.startsWith("https://ta")) {
              String queryString = detailHRef.substring(detailHRef.indexOf("?") + 1);
              System.out.println("Query String is: " + queryString);
              String[] parameters = queryString.split("&");
              for(String param : parameters) {
                  String[] paramKeyAndValue = param.split("=");
                  System.out.println("Param = " + paramKeyAndValue[0] + (paramKeyAndValue.length >= 2 ?
                          " value: " + paramKeyAndValue[1] : ""));
                  if(paramKeyAndValue[0].equalsIgnoreCase("r")) {
                      productRef = paramKeyAndValue[1];
                      productRef = URLDecoder.decode(productRef, "ASCII");
                      break;
                  }
              }
            }
            else {
                productRef = detailHRef;
            }
            System.out.println("Product ref: " + productRef);
            webDriver.switchTo().newWindow(WindowType.TAB);
            webDriver.navigate().to(productRef);
//            webDriver.get(productRef);

            WebDriverWait wait = new WebDriverWait(webDriver, 6);      
            jsExec.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span[data-testid=lblPDPDetailProductRatingNumber]")));
//            Thread.sleep(5000);

            String description = webDriver.findElement(By.cssSelector("div[data-testid=lblPDPDescriptionProduk]"))
                    .getAttribute("innerText");
            if(description.length() > 200) {
                description = description.substring(0,200);
            }
            
            description = description.replace("\n", ". ");
            
            p.setDescription(description);
            
            WebElement starElmt = webDriver.findElement(By.cssSelector("h5[data-testid=txtRatingScore]")); 
            WebElement starElmt2 = webDriver.findElement(By.cssSelector("span[data-testid=lblPDPDetailProductRatingNumber]")); 
//            starElmt2.click();
            String stars = starElmt.getAttribute("innerText");
            String stars2 = starElmt2.getAttribute("innerText");
            
            p.setStars(stars2);
            listProduct.add(p);
            System.out.println("Description: " + description);
            System.out.println("Stars: " + stars);
            System.out.println("Stars2: " + stars2);   
            webDriver.close();
            webDriver.switchTo().window(currentHandle);
//            break;
          } 
          catch(Exception e) {
              e.printStackTrace();
          }
        }
        System.out.println("Incrementing to next page");
        page++;
    } while(listProduct.size() < LIMIT_PRODUCT) ;
      System.out.println("Total products is: " + listProduct.size());
      System.out.println("Products: " + listProduct);
      
      CSVExporter.export(listProduct);
  }
  
  private static List<Product> retrieveProductsAtPage(int page) throws Exception {
        List<Product> listProduct = new ArrayList<>();
        String currentHandle = webDriver.getWindowHandle();
        String address = HANDHONE_PAGE + "?" + QUERY_PARAM_PAGE + "=" + page;
        System.out.println("============================================");
        System.out.println();
        
        webDriver.get(address);
        Thread.sleep(4000);
        String title = webDriver.getTitle();
    //    WebElement paginationElmt = webDriver.findElement(By.cssSelector(".css-1q668u-unf-pagination-items"));

        System.out.println("Title: " + title);

        JavascriptExecutor jsExec = (JavascriptExecutor) webDriver;

        for(int i=0 ; i<3; i++) {
            jsExec.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        }

        List<WebElement> containers = webDriver.findElements(By.cssSelector("a[data-testid=lnkProductContainer]"));
        System.out.println("Containers size: " + containers.size());

        for(WebElement con : containers) {
          try {
            System.out.println("....................");

            String detailHRef = con.getAttribute("href");
            String productRef = null;
            Product p = new Product();
            WebElement nameElmt = con.findElement(By.cssSelector("span.css-1bjwylw"));
            WebElement priceElmt = con.findElement(By.cssSelector("span.css-o5uqvq"));
            WebElement imgElmt = con.findElement(By.cssSelector("div.css-t8frx0 img"));
            WebElement sellerElmt = con.findElements(By.cssSelector("span.css-1kr22w3")).get(1);
            String name = nameElmt.getAttribute("innerText");
            String price = priceElmt.getAttribute("innerText");
            String imgLink = imgElmt.getAttribute("src");
            String seller = sellerElmt.getAttribute("innerText");
            
            p.setName(name);
            p.setPrice(price);
            p.setImageLink(imgLink);
            p.setSeller(seller);


            System.out.println("Detail HREF: " + detailHRef);
            System.out.println("Name: " + name);
            System.out.println("Price: " + price);
            System.out.println("Img Link: " + imgLink);
            System.out.println("Seller: " + seller);

            if(detailHRef.startsWith("https://ta")) {
              String queryString = detailHRef.substring(detailHRef.indexOf("?") + 1);
              System.out.println("Query String is: " + queryString);
              String[] parameters = queryString.split("&");
              for(String param : parameters) {
                  String[] paramKeyAndValue = param.split("=");
                  System.out.println("Param = " + paramKeyAndValue[0] + (paramKeyAndValue.length >= 2 ?
                          " value: " + paramKeyAndValue[1] : ""));
                  if(paramKeyAndValue[0].equalsIgnoreCase("r")) {
                      productRef = paramKeyAndValue[1];
                      productRef = URLDecoder.decode(productRef, "ASCII");
                      break;
                  }
              }
            }
            else {
                productRef = detailHRef;
            }
            System.out.println("Product ref: " + productRef);
            webDriver.switchTo().newWindow(WindowType.TAB);
            webDriver.navigate().to(productRef);
//            webDriver.get(productRef);

            WebDriverWait wait = new WebDriverWait(webDriver, 6);      
            jsExec.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span[data-testid=lblPDPDetailProductRatingNumber]")));
//            Thread.sleep(5000);

            String description = webDriver.findElement(By.cssSelector("div[data-testid=lblPDPDescriptionProduk]"))
                    .getAttribute("innerText");
            if(description.length() > 200) {
                description = description.substring(0,200);
            }
            
            description = description.replace("\n", ". ");
            
            p.setDescription(description);
            
            WebElement starElmt = webDriver.findElement(By.cssSelector("h5[data-testid=txtRatingScore]")); 
            WebElement starElmt2 = webDriver.findElement(By.cssSelector("span[data-testid=lblPDPDetailProductRatingNumber]")); 
//            starElmt2.click();
            String stars = starElmt.getAttribute("innerText");
            String stars2 = starElmt2.getAttribute("innerText");
            
            p.setStars(stars2);
            listProduct.add(p);
            System.out.println("Description: " + description);
            System.out.println("Stars: " + stars);
            System.out.println("Stars2: " + stars2);   
            webDriver.close();
            webDriver.switchTo().window(currentHandle);
//            break;
          } 
          catch(Exception e) {
              e.printStackTrace();
          }
        }
        return listProduct;
  }
}