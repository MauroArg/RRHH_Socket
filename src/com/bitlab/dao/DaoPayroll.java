/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.dao;

import com.bitlab.entity.Payroll;
import com.bitlab.entity.PayrollDetail;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Gustavo Gómez
 * class: DaoPayroll
 * fecha: 2020-07-02
 */
public class DaoPayroll {
    private static final double minSueldo = 338.6666666666667;
    private static final double medSueldo = 761.905;
    private static final double topSueldo = 1904.761666666667;
    
    
    PreparedStatement ps;
    ResultSet rs;
    Payroll py;
    PayrollDetail pd;
    
    
}
