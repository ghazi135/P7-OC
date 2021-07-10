package com.nnk.springboot.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@ToString
@Table(name = "bidlist")
public class BidList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer BidListId;
    @NotBlank(message = "Account is mandatory")
    private String account;
    @NotBlank(message = "Type is mandatory")
    private String type;
    @NotNull(message = "BidQuantity is mandatory")
    private Double bidQuantity;
    private Double askQuantity;
    private Double bid;
    private Double ask;
    private String benchmark;
    private Timestamp bidListDate;
    private String commentary;
    private String security;
    private String status;
    private String trader;
    private String book;
    private String creationName;
    private Timestamp creationDate;
    private String revisionName;
    private Timestamp revisionDate;
    private String dealName;
    private String dealType;
    private String sourceListId;
    private String side;



    public BidList(Integer bidListId, String account, String type, Double bid, Double ask,
            Timestamp creationDate, Timestamp revisionDate) {
        BidListId = bidListId;
        this.account = account;
        this.type = type;
        this.bid = bid;
        this.ask = ask;
        this.creationDate = creationDate;
        this.revisionDate = revisionDate;
    }

    public BidList() {
        //
    }

    public BidList(String account_test, String type_test, double v) {
        this.account = account_test;
        this.type = type_test;
        this.bidQuantity = v;
    }



    // TODO: Map columns in data table BIDLIST with corresponding java fields
}
