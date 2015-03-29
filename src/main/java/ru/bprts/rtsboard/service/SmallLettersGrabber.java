package ru.bprts.rtsboard.service;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.bprts.rtsboard.domain.SmallLettersIssue;

import javax.print.Doc;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

/**
 * Created by lameroot on 29.03.15.
 */
public class SmallLettersGrabber {

    private String authUrl = "http://small-letters.ru/login_check";
    private String dividendModelUrl = "http://small-letters.ru/data/dividend_model/contents";
    private String login = "lameroot@mail.ru";
    private String password = "nirvana";

    public Map<String,String> getAuthCookies() throws IOException {
        Connection.Response response = Jsoup.connect(authUrl)
                .data("_username",login)
                .data("_password",password)
                .data("_remember_me","on")
                .data("_target_path","http://small-letters.ru/login")
                .method(Connection.Method.POST)
                .execute();

        return response.cookies();
    }

    public Document getDividendModel(Map<String,String> cookies) throws IOException {
        return Jsoup.connect(dividendModelUrl).cookies(cookies).get();
    }

    public Document getDividendModel(File file) throws IOException {
        return Jsoup.parse(file, "utf-8");
    }

    public Collection<SmallLettersIssue> parseDocument(Document document) throws ParseException {
        Comparator<SmallLettersIssue> comparator = new Comparator<SmallLettersIssue>() {
            @Override
            public int compare(SmallLettersIssue o1, SmallLettersIssue o2) {
                return o1.getSpread().intValue() - o2.getSpread().intValue();
            }
        };
        /*
        Set<SmallLettersIssue> issues = new TreeSet<SmallLettersIssue>(new Comparator<SmallLettersIssue>() {
            @Override
            public int compare(SmallLettersIssue o1, SmallLettersIssue o2) {
                return o1.getSpread().intValue() - o2.getSpread().intValue();
            }
        });
        */
        List<SmallLettersIssue> issues = new ArrayList<SmallLettersIssue>();

        Elements elements = document.select(".short").select(".center");
        System.out.println("count = " + elements.size());
        for (Element element : elements) {

            SmallLettersIssue issue = new SmallLettersIssue(element);
            /*
            if ( issues.contains(issue) ) {
                System.out.println("======" + element);
                System.out.println("======" + issue);
            }
            */
            issues.add(issue);
        }
        Collections.sort(issues,comparator);
        System.out.println("count of issues of dividends = " + issues.size());
        return issues;
    }

    public static void main(String[] args) throws IOException, ParseException {
        SmallLettersGrabber grabber = new SmallLettersGrabber();
        /*
        Map<String,String> cookies = grabber.getAuthCookies();
        for (Map.Entry<String, String> entry : cookies.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
        Document document = grabber.getDividendModel(cookies);
        System.out.println(document);
        */
        Document document = grabber.getDividendModel(new File("/Users/lameroot/Downloads/small-letters/grabber/Small Letters.html"));
        Collection<SmallLettersIssue> issues = grabber.parseDocument(document);
        for (SmallLettersIssue issue : issues) {
            System.out.println(issue.getTicker() + " has spread = " + issue.getSpread() + " = " + issue.getBid() +  "/" + issue.getAsk());
        }
    }
}
