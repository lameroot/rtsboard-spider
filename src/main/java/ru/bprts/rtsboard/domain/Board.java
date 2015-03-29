package ru.bprts.rtsboard.domain;

import org.jsefa.csv.annotation.CsvDataType;
import org.jsefa.csv.annotation.CsvField;
import org.jsefa.flr.annotation.FlrDataType;
import org.jsefa.flr.annotation.FlrField;

import java.util.Date;

/**
 * Created by lameroot on 28.03.15.
 * moment;full_name;name_full_eng;issue_type;issue;nominal;currency;price_currency;bid;ask;first_price;first_volume;max_price;min_price;
 * last_price;last_volume;money_volume;volume;trades_num;capitalization
 *
 * 26.03.2015;"ќткрытое акицонерное общество јрсеньевска€ авиационна€ корпораци€ ""ѕрогресс"" им.Ќ.».—азыкина";"Arsenyev Aircraft Company ""Progress"" in honor N.I.Sazykin";C;aakp;1,00;;
 * USD;1,00000;0,00000;0,00000;0;0,00000;0,00000;0,00000;0;0,00;0;0;204587815,24
 */
@CsvDataType
public class Board {

    @CsvField(format = "dd.MM.yyyy",pos = 1)
    private Date moment;
    @CsvField(pos = 2)
    private String fullName;
    @CsvField(pos = 3)
    private String nameFullEng;
    @CsvField(pos = 4)
    private String issueType;
    @CsvField(pos = 5)
    private String issue;
    @CsvField(pos = 6)
    private String nominal;
    @CsvField(pos = 7)
    private String currency;
    @CsvField(pos = 8)
    private String priceCurrency;
    @CsvField(pos = 9)
    private String bid;
    @CsvField(pos = 10)
    private String ask;
    @CsvField(pos = 11)
    private String firstPrice;
    @CsvField(pos = 12)
    private String firstVolume;
    @CsvField(pos = 13)
    private String maxPrice;
    @CsvField(pos = 14)
    private String minPrice;
    @CsvField(pos = 15)
    private String lastPrice;
    @CsvField(pos = 16)
    private String lastVolume;
    @CsvField(pos = 17)
    private String moneyVolume;
    @CsvField(pos = 18)
    private String volume;
    @CsvField(pos = 19)
    private Integer tradesNum;
    @CsvField(pos = 20)
    private String capitalization;


    public Date getMoment() {
        return moment;
    }

    public void setMoment(Date moment) {
        this.moment = moment;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNameFullEng() {
        return nameFullEng;
    }

    public void setNameFullEng(String nameFullEng) {
        this.nameFullEng = nameFullEng;
    }

    public String getIssueType() {
        return issueType;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }

    public String getPriceCurrency() {
        return priceCurrency;
    }

    public void setPriceCurrency(String priceCurrency) {
        this.priceCurrency = priceCurrency;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getAsk() {
        return ask;
    }

    public void setAsk(String ask) {
        this.ask = ask;
    }

    public String getFirstPrice() {
        return firstPrice;
    }

    public void setFirstPrice(String firstPrice) {
        this.firstPrice = firstPrice;
    }

    public String getFirstVolume() {
        return firstVolume;
    }

    public void setFirstVolume(String firstVolume) {
        this.firstVolume = firstVolume;
    }

    public String getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public String getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(String lastPrice) {
        this.lastPrice = lastPrice;
    }

    public String getLastVolume() {
        return lastVolume;
    }

    public void setLastVolume(String lastVolume) {
        this.lastVolume = lastVolume;
    }

    public String getMoneyVolume() {
        return moneyVolume;
    }

    public void setMoneyVolume(String moneyVolume) {
        this.moneyVolume = moneyVolume;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public Integer getTradesNum() {
        return tradesNum;
    }

    public void setTradesNum(Integer tradesNum) {
        this.tradesNum = tradesNum;
    }

    public String getCapitalization() {
        return capitalization;
    }

    public void setCapitalization(String capitalization) {
        this.capitalization = capitalization;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Board{");
        sb.append("moment=").append(moment);
        sb.append(", fullName='").append(fullName).append('\'');
        sb.append(", nameFullEng='").append(nameFullEng).append('\'');
        sb.append(", issueType='").append(issueType).append('\'');
        sb.append(", issue='").append(issue).append('\'');
        sb.append(", nominal='").append(nominal).append('\'');
        sb.append(", currency='").append(currency).append('\'');
        sb.append(", priceCurrency='").append(priceCurrency).append('\'');
        sb.append(", bid='").append(bid).append('\'');
        sb.append(", ask='").append(ask).append('\'');
        sb.append(", firstPrice='").append(firstPrice).append('\'');
        sb.append(", firstVolume='").append(firstVolume).append('\'');
        sb.append(", maxPrice='").append(maxPrice).append('\'');
        sb.append(", minPrice='").append(minPrice).append('\'');
        sb.append(", lastPrice='").append(lastPrice).append('\'');
        sb.append(", lastVolume='").append(lastVolume).append('\'');
        sb.append(", moneyVolume='").append(moneyVolume).append('\'');
        sb.append(", volume='").append(volume).append('\'');
        sb.append(", tradesNum=").append(tradesNum);
        sb.append(", capitalization='").append(capitalization).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
