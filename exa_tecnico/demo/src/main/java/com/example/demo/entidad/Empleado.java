package com.example.demo.entidad;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "employees")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    private Integer JOB_ID;
    private String NAME;
    private String LAST_NAME;
    private Date BIRTHDATE;
    private Integer GENDER_ID;
    public Integer getID() {
        return ID;
    }
    public void setID(Integer iD) {
        ID = iD;
    }
    public Integer getJOB_ID() {
        return JOB_ID;
    }
    public void setJOB_ID(Integer jOB_ID) {
        JOB_ID = jOB_ID;
    }
    public String getNAME() {
        return NAME;
    }
    public void setNAME(String nAME) {
        NAME = nAME;
    }
    public String getLAST_NAME() {
        return LAST_NAME;
    }
    public void setLAST_NAME(String lAST_NAME) {
        LAST_NAME = lAST_NAME;
    }
    public Date getBIRTHDATE() {
        return BIRTHDATE;
    }
    public void setBIRTHDATE(Date bIRTHDATE) {
        BIRTHDATE = bIRTHDATE;
    }
    public Integer getGENDER_ID() {
        return GENDER_ID;
    }
    public void setGENDER_ID(Integer gENDER_ID) {
        GENDER_ID = gENDER_ID;
    }

    
}
