/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniCalcMatriz;

import java.util.Scanner;
import java.util.Random;
import java.text.DecimalFormat;

/**
 *
 * @author goldner
 */
public class Fração {
    private Scanner ler;
    private Random gerar;
    private DecimalFormat df;
    
    private double numerador;
    private double denominador;
    
    public Fração(double n, double d) {
        double mdc = mdc(n, d);
        
        this.numerador = n / mdc;
        this.denominador = d / mdc;
        
        if(this.denominador < 0) {
            this.numerador = -this.numerador;
            this.denominador = -this.denominador;
        }
    }
    
    public Fração(double n) {
        this(n, 1);
    }
    
    public Fração() {
        geraFração();
    }
    
    public Fração(String f) {
        if(f.contains("/")) {
            double n = Integer.parseInt(f.substring(0, f.indexOf("/"))); 
            double d = Integer.parseInt(f.substring(f.indexOf("/") + 1));
            
            double mdc = mdc(n, d);
        
            if(d < 0) {
                this.numerador = -n / mdc;
                this.denominador = -d / mdc;
            }
            else {
                this.numerador = n / mdc;
                this.denominador = d / mdc;
            }
        }
        
        else {
            double n = Integer.parseInt(f);
            
            this.numerador = n;
            this.denominador = 1;
        }
    }
    
    private double mdc(double n, double d) {
        if(d != 0)
            return (n % d == 0) ? d : mdc(d, n % d);
        return 1;
    }
    
    @Override
    public String toString() {
        if (this.denominador == 1 || this.numerador == 0) {
            this.df = new DecimalFormat("####.####");
            return df.format(this.numerador);
        } 
        else {
            this.df = new DecimalFormat("#.##");
            return df.format(this.numerador) + "/" + df.format(this.denominador);
        }
    }
    
    public boolean igual(Fração f2) {
        return (this.numerador == f2.numerador) && (this.denominador == f2.denominador);
    }
    
    public Fração modulo() {
        return new Fração(Math.abs(this.numerador), Math.abs(this.denominador));
    }
    
    public boolean maior(Fração f2) {
        double d1 = this.denominador;
        double r1 = this.numerador / d1;
        
        double d2 = f2.denominador;
        double r2 = f2.numerador / d2;
        
        return r1 > r2;
    }
    
    public void leFração() {
        this.ler = new Scanner(System.in);
        
        System.out.println("n = ");
        double n = ler.nextInt();
        
        System.out.println("d = ");
        double d = ler.nextInt();
        
        double mdc = mdc(n, d);
        
        this.numerador = n / mdc;
        this.denominador = d / mdc;
    }
    
    public void geraFração() {
        this.gerar = new Random();
        
        double n = gerar.nextInt(10);
        double d = 1;
        
        double mdc = mdc(n, d);
        
        this.numerador = n / mdc;
        this.denominador = d / mdc;
    }
    
    public Fração adição(Fração f2) {
        double n = (this.numerador * f2.denominador) + (f2.numerador * this.denominador);
        double d = this.denominador * f2.denominador;
        
        return (n == 0 || d == 0) ? new Fração(0) : new Fração(n, d);
    }
   
    public Fração subtração(Fração f2) {
        double n = (this.numerador * f2.denominador) - (f2.numerador * this.denominador);
        double d = this.denominador * f2.denominador;
        
        return (n == 0 || d == 0) ? new Fração(0) : new Fração(n, d);
    }
    
    public Fração multiplicação(Fração f2) {
        double n = this.numerador * f2.numerador;
        double d = this.denominador * f2.denominador;
        
        return new Fração(n, d);
    }
    
    public Fração divisão(Fração f2) {
        if(f2.numerador != 0) {
            double n = this.numerador * f2.denominador;
            double d = this.denominador * f2.numerador;

            return new Fração(n, d);
        }
        return null;
    }

    public Fração raiz() {
        return new Fração(Math.sqrt(this.numerador), Math.sqrt(this.denominador));
    }
    
    public double getNumerador() {
        return numerador;
    }

    public double getDenominador() {
        return denominador;
    }
}
