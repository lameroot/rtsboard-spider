package ru.bprts.rtsboard.domain;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lameroot on 29.03.15.
 */
public class SmallLettersIssue {

    private String name;
    private String ticker;
    private Double bid;
    private Double ask;
    private Double dividend12;
    private Double dividend13;
    private Double dividend14;
    private Double dividendYield12;
    private Double dividendYield13;
    private Double dividendYield14;
    private Double pe12;
    private Double pe13;
    private Double pe14;
    private Date close13;
    private Date close14;

    public SmallLettersIssue(Element element) throws ParseException {
        Elements elements = element.select("td");
        Element elemNameAndTicker = elements.get(0);
        Element elemName = elemNameAndTicker.select("a").first();
        Element elemTicker = elemNameAndTicker.select("b").first();

        Element elemBid = elements.get(1);
        Element elemAsk = elements.get(2);
        Element elemDividend12 = elements.get(3);
        Element elemDividend13 = elements.get(4);
        Element elemDividend14 = elements.get(5);
        Element elemDividendYield12 = elements.get(6);
        Element elemDividendYield13 = elements.get(7);
        Element elemDividendYield14 = elements.get(8);
        Element elemPe12 = elements.get(9);
        Element elemPe13 = elements.get(10);
        Element elemPe14 = elements.get(11);
        Element elemClose13 = elements.get(12);
        Element elemClose14 = elements.get(13);


        if ( null != elemName ) name = elemName.text();
        else name = elemNameAndTicker.text();
        if ( null != elemTicker ) ticker = elemTicker.text();
        if ( null != elemBid && !"-".equals(elemBid.text()) ) bid = Double.parseDouble(elemBid.text());
        if ( null != elemAsk && !"-".equals(elemAsk.text()) ) ask = Double.parseDouble(elemAsk.text());
        if ( null != elemDividend12 && !"-".equals(elemDividend12.text()) ) dividend12 = Double.parseDouble(elemDividend12.text());
        if ( null != elemDividend13 && !"-".equals(elemDividend13.text()) ) dividend13 = Double.parseDouble(elemDividend13.text());
        if ( null != elemDividend14 && !"-".equals(elemDividend14.text()) ) dividend14 = Double.parseDouble(elemDividend14.text());
        if ( null != elemDividendYield12 && !"-".equals(elemDividendYield12.text()) ) dividendYield12 = Double.parseDouble(elemDividendYield12.text());
        if ( null != elemDividendYield13 && !"-".equals(elemDividendYield13.text()) ) dividendYield13 = Double.parseDouble(elemDividendYield13.text());
        if ( null != elemDividendYield14 && !"-".equals(elemDividendYield14.text()) ) dividendYield14 = Double.parseDouble(elemDividendYield14.text());
        if ( null != elemPe12 && !"-".equals(elemPe12.text()) ) pe12 = Double.parseDouble(elemPe12.text());
        if ( null != elemPe13 && !"-".equals(elemPe13.text()) ) pe13 = Double.parseDouble(elemPe13.text());
        if ( null != elemPe14 && !"-".equals(elemPe14.text()) ) pe14 = Double.parseDouble(elemPe14.text());
        if ( null != elemClose13 && !"-".equals(elemClose13.text()) ) close13 = new SimpleDateFormat("dd.MM.yyyy").parse(elemClose13.text());
        if ( null != elemClose14 && !"-".equals(elemClose14.text()) ) close14 = new SimpleDateFormat("dd.MM.yyyy").parse(elemClose14.text());



    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public Double getBid() {
        return bid;
    }

    public void setBid(Double bid) {
        this.bid = bid;
    }

    public Double getAsk() {
        return ask;
    }

    public void setAsk(Double ask) {
        this.ask = ask;
    }

    public Double getDividend12() {
        return dividend12;
    }

    public void setDividend12(Double dividend12) {
        this.dividend12 = dividend12;
    }

    public Double getDividend13() {
        return dividend13;
    }

    public void setDividend13(Double dividend13) {
        this.dividend13 = dividend13;
    }

    public Double getDividend14() {
        return dividend14;
    }

    public void setDividend14(Double dividend14) {
        this.dividend14 = dividend14;
    }

    public Double getDividendYield12() {
        return dividendYield12;
    }

    public void setDividendYield12(Double dividendYield12) {
        this.dividendYield12 = dividendYield12;
    }

    public Double getDividendYield13() {
        return dividendYield13;
    }

    public void setDividendYield13(Double dividendYield13) {
        this.dividendYield13 = dividendYield13;
    }

    public Double getDividendYield14() {
        return dividendYield14;
    }

    public void setDividendYield14(Double dividendYield14) {
        this.dividendYield14 = dividendYield14;
    }

    public Double getPe12() {
        return pe12;
    }

    public void setPe12(Double pe12) {
        this.pe12 = pe12;
    }

    public Double getPe13() {
        return pe13;
    }

    public void setPe13(Double pe13) {
        this.pe13 = pe13;
    }

    public Double getPe14() {
        return pe14;
    }

    public void setPe14(Double pe14) {
        this.pe14 = pe14;
    }

    public Date getClose14() {
        return close14;
    }

    public void setClose14(Date close14) {
        this.close14 = close14;
    }

    public Date getClose13() {
        return close13;
    }

    public void setClose13(Date close13) {
        this.close13 = close13;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SmallLettersIssue that = (SmallLettersIssue) o;

        if (!ticker.equals(that.ticker)) return false;

        return true;
    }

    public Double getSpread() {
        if ( null == bid || null == ask ) return 0.0;
        double spread = ask - bid;
        if ( 0 > spread ) spread = spread * -1;
        return spread;
    }

    @Override
    public int hashCode() {
        return ticker.hashCode();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SmallLettersIssue{");
        sb.append("ticker='").append(ticker).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
