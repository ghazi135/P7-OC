package com.nnk.springboot.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@ToString
@Table(name = "rulename")
public class RuleName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "Description is mandatory")
    private String description;
    @NotBlank(message = "Json is mandatory")
    private String json;
    @NotBlank(message = "Template is mandatory")
    private String template;
    @NotBlank(message = "SqlStr is mandatory")
    private String sqlStr;
    @NotBlank(message = "SqlPart is mandatory")
    private String sqlPart;

    public RuleName(String rule_name, String description, String json, String template, String sql, String sql_part) {
        this.name = rule_name;
        this.description = description;
        this.json = json;
        this.template = template;
        this.sqlStr = sql;
        this.sqlPart = sql_part;

    }

    public RuleName() {
        //
    }



    // TODO: Map columns in data table RULENAME with corresponding java fields
}
