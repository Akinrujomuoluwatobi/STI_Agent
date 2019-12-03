package com.example.sti_agent.Model.Marine;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MarineQuote implements Serializable {

    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("conversion_rate")
    @Expose
    private String conversionRate;
    private final static long serialVersionUID = 867158594389092554L;

    /**
     * No args constructor for use in serialization
     */
    public MarineQuote() {
    }

    /**
     * @param value
     * @param conversionRate
     */
    public MarineQuote(String value, String conversionRate) {
        super();
        this.value = value;
        this.conversionRate = conversionRate;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(String conversionRate) {
        this.conversionRate = conversionRate;
    }

}