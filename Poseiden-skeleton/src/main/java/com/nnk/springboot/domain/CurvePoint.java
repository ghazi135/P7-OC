package com.nnk.springboot.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;


@Entity
@Getter
@Setter
@ToString
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


    // TODO: Map columns in data table CURVEPOINT with corresponding java fields
}
