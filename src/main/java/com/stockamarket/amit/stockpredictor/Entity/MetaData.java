package com.stockamarket.amit.stockpredictor.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class MetaData implements Serializable {
    @SerializedName("1. Information")
    @Expose
    private String information;
    @Expose
    @SerializedName("2. Symbol")
    private String symbol;
    @Expose
    @SerializedName("3. Last Refreshed")
    private String last_refreshed;
    @Expose
    @SerializedName("4. Output Size")
    private String output_size;
    @Expose
    @SerializedName("5. Time Zone")
    private String timezone;
}
