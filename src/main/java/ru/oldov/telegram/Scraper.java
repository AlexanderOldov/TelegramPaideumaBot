package ru.oldov.telegram;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.io.IOException;

public class Scraper {
    private String videoTitle = "";
    private String linkAssemble = "https://paideuma.tv";
    public void Scraper(){
        WebClient webClient = new WebClient();
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);
        try {
            HtmlPage page = webClient.getPage("https://paideuma.tv/catalog");
            int startOfLink = page.asXml().indexOf("/video/");
            while(true){
                if(page.asXml().indexOf(startOfLink-2) == 'e'){
                    linkAssemble+="/en";
                }
                linkAssemble += page.asXml().charAt(startOfLink);
                startOfLink++;
                if (page.asXml().charAt(startOfLink) == '>'){
                    break;
                }
            }
            videoTitle = linkAssemble;
            linkAssemble = "https://paideuma.tv";
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getVideoTitle() {
        Scraper();
        return videoTitle;
    }
}
