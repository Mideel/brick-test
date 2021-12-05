package com.brick.util;

import com.brick.entity.Product;
import com.brick.exporter.CSVExporter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
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

public class TokopediaUtil {

    private static final String DATA_TESTID = "data-testid";
    private static final String LIST_ID = "lstCL2ProductList";
    private static final String HANDHONE_PAGE = "https://www.tokopedia.com/p/handphone-tablet/handphone";
    private static final String QUERY_PARAM_PAGE = "page";
    private static final int LIMIT_PRODUCT = 100;
    private static final int LIMIT_CONTAINER = Integer.MAX_VALUE;

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
            listProduct.addAll(retrieveProductsAtPage(page));
            page++;
        } while (listProduct.size() < LIMIT_PRODUCT);
        
        if(listProduct.size() > LIMIT_PRODUCT) {
            listProduct.removeIf(p -> listProduct.indexOf(p) >= LIMIT_PRODUCT);
        }

        System.out.println("Total products is: " + listProduct.size());
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

        IntStream.range(1, 3)
        .forEach(index -> {
            try {
                jsExec.executeScript("window.scrollTo(0, document.body.scrollHeight)");
                Thread.sleep(1000);
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        });
        
//        for (int i = 0; i < 3; i++) {
//            jsExec.executeScript("window.scrollTo(0, document.body.scrollHeight)");
//        }

        List<WebElement> containers = webDriver.findElements(By.cssSelector("a[data-testid=lnkProductContainer]"));
        System.out.println("Containers size: " + containers.size());
        
        int totalCon = 0;
        for (WebElement con : containers) {
            try {
                System.out.println(".............................................");
                listProduct.add(retrieveProduct(currentHandle, con));
//            break;
            } catch (Exception e) {
                System.out.println("There's an exceptio when retieving a product");
                e.printStackTrace();
            }
            totalCon++;
            if(totalCon >= LIMIT_CONTAINER)
                break;
        }
        return listProduct;
    }

    private static Product retrieveProduct(String currentHandle, WebElement con) throws Exception {
        JavascriptExecutor jsExec = (JavascriptExecutor) webDriver;
        String detailHRef = con.getAttribute("href");
        
        System.out.println("Detail HREF: " + detailHRef);

        Product p = new Product();
        setProductAttributes(p, con);
        setProductDetail(currentHandle, p, detailHRef);
        return p;
    }
    
    private static void setProductAttributes(Product p, WebElement con) {
        WebElement nameElmt = con.findElement(By.cssSelector("span.css-1bjwylw"));
        WebElement priceElmt = con.findElement(By.cssSelector("span.css-o5uqvq"));
        WebElement imgElmt = con.findElement(By.cssSelector("img[crossorigin=anonymous]"));
        WebElement sellerElmt = con.findElements(By.cssSelector("span.css-1kr22w3")).get(1);
        String name = nameElmt.getAttribute("innerText");
        String price = priceElmt.getAttribute("innerText");
        String imgLink = imgElmt.getAttribute("src");
        if(imgLink != null)
            imgLink = imgLink.replace(";", " ");
        String seller = sellerElmt.getAttribute("innerText");

        p.setName(name);
        p.setPrice(price);
        p.setImageLink(imgLink);
        p.setSeller(seller);

        System.out.println("Name: " + name);
        System.out.println("Price: " + price);
        System.out.println("Img Link: " + imgLink);
        System.out.println("Seller: " + seller);
    }
    
    private static void setProductDetail(String currentHandle, Product p, String detailHRef) throws Exception {
        JavascriptExecutor jsExec = (JavascriptExecutor) webDriver;
        String productHRef = getProductHRef(detailHRef);
        System.out.println("Product ref: " + productHRef);
        try {
        webDriver.switchTo().newWindow(WindowType.TAB);
        webDriver.navigate().to(productHRef);
        WebElement starElmt = null;
        WebElement descriptionElmt = null;
        jsExec.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        
        try {
            WebDriverWait wait = new WebDriverWait(webDriver, 6);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span[data-testid=lblPDPDetailProductRatingNumber]")));
            starElmt = webDriver.findElement(By.cssSelector("span[data-testid=lblPDPDetailProductRatingNumber]"));
        }
        catch(Exception e) {
            System.out.println("There's an exception when waiting for visibility of star element");
        }
        
        try {
            WebDriverWait wait = new WebDriverWait(webDriver, 3);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[data-testid=lblPDPDescriptionProduk]")));
            descriptionElmt = webDriver.findElement(By.cssSelector("span[data-testid=lblPDPDetailProductRatingNumber]"));
        }
        catch(Exception e) {
            System.out.println("There's an exception when waiting for visibility of desc element");
        }
        
        String description = null;
        if(descriptionElmt != null ) {
            description = webDriver.findElement(By.cssSelector("div[data-testid=lblPDPDescriptionProduk]"))
                    .getAttribute("innerText");
            if (description.length() > 200) {
                description = description.substring(0, 200);
            }

            description = description.replace("\n", ". ");

            p.setDescription(description);
        }

        String stars = null;
        if(starElmt != null) {
            stars = starElmt.getAttribute("innerText");
        }

        p.setStars(stars);
        System.out.println("Description: " + description);
        System.out.println("Stars: " + stars);
        webDriver.close();
        webDriver.switchTo().window(currentHandle);
        }
        catch(Exception e) {
            System.out.println("There's an exception when trying to get the detail");
            String s = webDriver.getWindowHandle();
            if(!s.equals(currentHandle)) {
                webDriver.close();
                webDriver.switchTo().window(currentHandle);
            }
        }
    }
    
    private static String getProductHRef(String detailHRef) throws Exception {
        String productHRef = null;
        if (detailHRef.startsWith("https://ta")) {
            String queryString = detailHRef.substring(detailHRef.indexOf("?") + 1);
            System.out.println("Query String is: " + queryString);
            String[] parameters = queryString.split("&");
            for (String param : parameters) {
                String[] paramKeyAndValue = param.split("=");
                System.out.println("Param = " + paramKeyAndValue[0] + (paramKeyAndValue.length >= 2
                        ? " value: " + paramKeyAndValue[1] : ""));
                if (paramKeyAndValue[0].equalsIgnoreCase("r")) {
                    productHRef = paramKeyAndValue[1];
                    productHRef = URLDecoder.decode(productHRef, "ASCII");
                    break;
                }
            }
        } else {
            productHRef = detailHRef;
        }
        return productHRef;
    }
}
