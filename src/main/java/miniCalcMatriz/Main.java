/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniCalcMatriz;

/**
 *
 * @author goldner
 */
public class Main {
    public static void main(String[] args) {
        Matriz a = new Matriz(5);
        System.out.println(a);
        
        a.decomposiçãoLU();
        
    }
}
