package com.nnk.springboot.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;


@Entity
@Table(name = "curvepoint")
public class CurvePoint {

    @Id
    Integer curveId;
    Timestamp asOfDate;
    Double term;
    Double value;
    Timestamp creationDate;

    public CurvePoint(Integer curveId, Timestamp asOfDate, Double term, Double value, Timestamp creationDate) {


        this.curveId      = curveId;
        this.asOfDate     = asOfDate;
        this.term         = term;
        this.value        = value;
        this.creationDate = creationDate;
    }

    public CurvePoint() {


    }

    public Integer getCurveId() {

        return curveId;
    }

    public void setCurveId(Integer curveId) {

        this.curveId = curveId;
    }

    public Timestamp getAsOfDate() {

        return asOfDate;
    }

    public void setAsOfDate(Timestamp asOfDate) {

        this.asOfDate = asOfDate;
    }

    public Double getTerm() {

        return term;
    }

    public void setTerm(Double term) {

        this.term = term;
    }

    public Double getValue() {

        return value;
    }

    public void setValue(Double value) {

        this.value = value;
    }

    public Timestamp getCreationDate() {

        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {

        this.creationDate = creationDate;
    }
    // TODO: Map columns in data table CURVEPOINT with corresponding java fields
}
