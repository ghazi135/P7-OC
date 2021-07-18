package com.nnk.springboot.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;


@Entity
@Getter
@Setter
@ToString
@Table(name = "trade")
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer tradeId;
    @NotNull(message = "Account is mandatory")
    private String  account;
    @NotNull(message = "Type is mandatory")
    private String  type;
    @NotNull(message = " BuyQuantity is mandatory")
    private Double  buyQuantity;

    private Double    sellQuantity;
    private Double    buyPrice;
    private Double    sellPrice;
    private String    benchmark;
    private Timestamp tradeDate;
    private String    security;
    private String    status;
    private String    trader;
    private String    book;
    private String    creationName;
    private Timestamp creationDate;
    private String    revisionName;
    private Timestamp revisionDate;
    private String    dealName;
    private String    dealType;
    private String    sourceListId;
    private String    side;

    public Trade(String trade_account, String type) {

        this.account = trade_account;
        this.type    = type;
    }

    public Trade() {
        //
    }


    // TODO: Map columns in data table TRADE with corresponding java fields
}
