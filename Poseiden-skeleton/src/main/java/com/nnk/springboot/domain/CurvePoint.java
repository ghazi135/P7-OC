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

    // DONE: Map columns in data table CURVEPOINT with corresponding java fields
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "CurveId is mandatory")  //not null pour valider tous les champs
    private Integer curveId;
    @NotNull(message = "AsOfDate is mandatory")
    private Timestamp asOfDate;
    @NotNull(message = "Term is mandatory")
    private Double term;
    @NotNull(message = "Value is mandatory")
    private Double value;
    @NotNull(message = "CreationDate is mandatory")
    private Timestamp creationDate;



    public CurvePoint(Integer curveId, Timestamp asOfDate, Double term, Double value, Timestamp creationDate) {


        this.curveId      = curveId;
        this.asOfDate     = asOfDate;
        this.term         = term;
        this.value        = value;
        this.creationDate = creationDate;
    }

    public CurvePoint() {


    }


    // DONE: Map columns in data table CURVEPOINT with corresponding java fields
}
