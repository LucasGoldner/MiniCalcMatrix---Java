/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniCalcMatriz;

import java.util.Scanner;

/**
 *
 * @author goldner
 */
public class Matriz {
    
    private Scanner ler;
    
    private int linha;
    private int coluna;

    private Fracao[][] matriz;

    public Matriz(int i, int j) {

        linha = i;
        coluna = j;

        matriz = new Fracao[linha][coluna];

        geraMatriz();
    }

    public Matriz(int k) {

        this(k, k);
    }

    private void geraMatriz() {

        for (int i = 0; i < linha; i++) {

            for (int j = 0; j < coluna; j++) {

                matriz[i][j] = new Fracao();
            }
        }
    }

    public void criaMatriz() {
        this.ler = new Scanner(System.in); 
        
        for (int i = 0; i < linha; i++) {

            for (int j = 0; j < coluna; j++) {

                System.out.println("["+ (i+1) + "]" + "["+ (j+1) + "] = ");
                String f = ler.nextLine();
                matriz[i][j] = new Fracao(f);
            }
        }

        System.out.println("");

    }

    @Override
    public String toString() {
        String s = "";
        
        for (int i = 0; i < linha; i++) {

            s += "| ";

            for (int j = 0; j < coluna; j++) {
                s += matriz[i][j];
                
                if(j == coluna - 1) {
                    s += " ";
                } else {
                    s += ", ";
                }
            }

            s += "|\n";
        }

        return s + "\n";
    }
    
    public Matriz adicao(Matriz m) {

        Matriz nova = new Matriz(this.linha, this.coluna);

        if (this.linha == m.linha && this.coluna == m.coluna) {

            for (int i = 0; i < this.linha; i++) {

                for (int j = 0; j < this.coluna; j++) {

                    nova.matriz[i][j] = this.matriz[i][j].adicao(m.matriz[i][j]);
                }
            }
        } else {

            System.out.println("Erro na operacao!!");
            System.exit(0);
        }

        return nova;
    }

    public Matriz subtracao(Matriz m) {

        Matriz nova = new Matriz(this.linha, this.coluna);

        if (this.linha == m.linha && this.coluna == m.coluna) {

            for (int i = 0; i < this.linha; i++) {

                for (int j = 0; j < this.coluna; j++) {

                    nova.matriz[i][j] = this.matriz[i][j].subtracao(m.matriz[i][j]);
                }
            }
        } else {

            System.out.println("Erro na operacao!!");
            System.exit(0);
        }

        return nova;
    }

    public Matriz multiplicacao(Fracao f1) {

        Matriz nova = new Matriz(this.linha, this.coluna);

        for (int i = 0; i < this.linha; i++) {

            for (int j = 0; j < this.coluna; j++) {

                nova.matriz[i][j] = f1.multiplicacao(this.matriz[i][j]);
            }
        }

        return nova;
    }
    
    public Matriz multiplicacao(Matriz m) {

        Matriz nova = new Matriz(this.linha, m.coluna);

        if (this.coluna == m.linha) {

            for (int i = 0; i < this.linha; i++) {

                for (int j = 0; j < m.coluna; j++) {

                    Fracao soma = new Fracao(0, 1);

                    for (int k = 0; k < this.coluna; k++) {
                        soma = soma.adicao(this.matriz[i][k].multiplicacao(m.matriz[k][j]));
                    }

                    nova.matriz[i][j] = soma;
                }
            }
        } else {

            System.out.println("Erro na operacao!!");
            System.exit(0);
        }

        return nova;
    }

    public Matriz transposta() {

        Matriz nova = new Matriz(this.coluna, this.linha);

        for (int i = 0; i < linha; i++) {

            for (int j = 0; j < coluna; j++) {

                nova.matriz[j][i] = this.matriz[i][j];
            }
        }

        return nova;
    }

    public Fracao determinante() {

        Fracao det = new Fracao(0, 1);

        if (this.linha == this.coluna) {

            if (this.linha == 2) {

                det = this.matriz[0][0].multiplicacao(this.matriz[1][1]).subtracao((this.matriz[1][0].multiplicacao(this.matriz[0][1])));
            } else {

                Matriz nova = new Matriz(this.linha - 1);
                Fracao exp;
                
                for (int k = 0; k < this.coluna; k++) {

                    int m = 0;

                    for (int i = 1; i < this.linha; i++) {

                        int n = 0;

                        for (int j = 0; j < this.coluna; j++) {

                            if (j != k) {

                                nova.matriz[m][n] = this.matriz[i][j];
                                n += 1;
                            }
                        }
                        m += 1;
                    }

                    if (k % 2 == 0) {
                        exp = new Fracao(1);
                    } else {
                        exp = new Fracao(-1);
                    }

                    det = det.adicao(this.matriz[0][k].multiplicacao((exp.multiplicacao(nova.determinante()))));
                }
            }

        } else {

            System.out.println("Erro na operacao!!");
            System.exit(0);
        }

        return det;
    }

    public Matriz adjunta() {
        /*
        k,l linha e coluna da matriz auxiliar para o cofator
		  k so muda seu valor quando o controle é 1
		  a matriz auxiliar so recebe o valor quando o m,n sao diferentes de i,j
        */
        Matriz nova = new Matriz(this.linha);

        if (this.linha == this.coluna) {

            Matriz auxiliar = new Matriz(this.linha - 1);

            Fracao exp;

            for (int i = 0; i < this.linha; i++) {

                for (int j = 0; j < this.coluna; j++) {

                    int k = 0;

                    for (int m = 0; m < this.linha; m++) {

                        int l = 0;
                        int controle = 0;

                        for (int n = 0; n < this.coluna; n++) {

                            if (m != i && n != j) {

                                auxiliar.matriz[k][l] = this.matriz[m][n];

                                l += 1;
                                controle = 1;
                            }
                        }

                        if (controle == 1) {
                            k += 1;
                        }
                    }

                    if ((i + j) % 2 == 0) {
                        exp = new Fracao(1);
                    } else {
                        exp = new Fracao(-1);
                    }

                    nova.matriz[i][j] = exp.multiplicacao(auxiliar.determinante());
                }
            }

        } else {

            System.out.println("Erro na operacao!!");
            System.exit(0);
        }

        return nova.transposta();
    }

    public Matriz inversa() {
        
        Fracao det1 = this.determinante();
        Fracao det2 = new Fracao(det1.getDenominador(), det1.getNumerador());
        
        Matriz nova = this.adjunta().multiplicacao(det2);

        return nova;
    }
    
    public void eliminacaoGaussiana(Matriz b, boolean pivoteamantoParcial) {
        for(int k = 0; k < this.linha; k++) {

           if(pivoteamantoParcial) {
                int linha1 = this.encotraPivo(k);
                
                if(linha1 != k) {
                    this.trocaLinha(linha1, k);
                    b.trocaLinha(linha1, k);
                }
           }
            
            for(int i = k+1; i < this.linha; i++) {
                
                if(this.matriz[k][k].igual(new Fracao(0))) {
                    this.trocaLinha(k, k+1);
                }
                
                Fracao m = this.matriz[i][k].divisão(this.matriz[k][k]);

                for(int j = k; j < this.linha; j++) {
                    this.matriz[i][j] = this.matriz[i][j].subtracao(m.multiplicacao(this.matriz[k][j]));
                }
                
                b.matriz[i][0] = b.matriz[i][0].subtracao(m.multiplicacao(b.matriz[k][0]));
            }
        }
    }
    
    private void trocaLinha(int linha1, int linha2) {
        Matriz aux = new Matriz(1,this.coluna);
        
        for(int j = 0; j < this.coluna; j++) {
            aux.matriz[0][j] = this.matriz[linha1][j];
            this.matriz[linha1][j] = this.matriz[linha2][j];
            this.matriz[linha2][j] = aux.matriz[0][j]; 
        }
    }
    
    private int encotraPivo(int k) {
        Fracao maior = new Fracao(0);
        int linha1 = 0;
        
        for(int i = k; i < this.linha; i++) {
            Fracao aux = this.matriz[i][k].modulo();
            
            if(aux.maior(maior)) {
                maior = this.matriz[i][k];
                linha1 = i;
            }
        }
        
        return linha1;
    }
    
    public void decomposicaoLU() {
        Matriz l = this.criaMatrizZero(this.linha, true);
        Matriz u = this.recebeMatriz();
        
        for(int k = 0; k < u.linha; k++) {

            for(int i = k+1; i < u.linha; i++) {
                
                if(u.matriz[k][k].igual(new Fracao(0))) {
                    u.trocaLinha(k, k+1);
                }
                
                Fracao m = u.matriz[i][k].divisão(u.matriz[k][k]);
                l.matriz[i][k] = m;

                for(int j = k; j < u.linha; j++) {
                    u.matriz[i][j] = u.matriz[i][j].subtracao(m.multiplicacao(u.matriz[k][j]));
                }
            }
        }
        
        System.out.println(u);
        System.out.println(l);
    }
    
    private Matriz criaMatrizZero(int k, boolean unitaria) {
        Matriz nova = new Matriz(k);
        for(int i = 0; i < nova.linha; i++) {
            for(int j = 0; j < nova.coluna; j++) {
                if(unitaria) {
                    if(i == j)
                        nova.matriz[i][j] = new Fracao(1);
                    else
                        nova.matriz[i][j] = new Fracao(0);
                }
                else
                    nova.matriz[i][j] = new Fracao(0);
            }
        }
        
        return nova;
    }
    
    public Matriz recebeMatriz() {
        Matriz nova = new Matriz(this.linha, this.coluna);
        
        for(int i = 0; i < this.linha; i++) {
            for(int j = 0; j < this.coluna; j++) {
                nova.matriz[i][j] = this.matriz[i][j];
            }
        }
        
        return nova;
    }
    
    public boolean simetrica() {
        Matriz transposta = this.transposta();
        
        for(int i = 0; i < this.linha; i++) {
            for(int j = 0; j < this.coluna; j++) {
                if(!this.matriz[i][j].igual(transposta.matriz[i][j]))
                    return false;
            }
        }
        return true;
    }
    
    public boolean positiva() {
        if(!this.matriz[0][0].maior(new Fracao(0)))
            return false;
        
        for(int k = 1; k < this.linha; k++) {
            Matriz aux = new Matriz(k+1);
            
            for(int i = 0; i < aux.linha; i++) {
                for(int j = 0; j < aux.linha; j++) {
                    aux.matriz[i][j] = this.matriz[i][j];
                }
            }
            
            if(!aux.determinante().maior(new Fracao(0)))
                return false;
        }
        
        return true;
    }
    
    public void decomposicaoCholesky() {
        if(this.simetrica() && this.positiva()) {
            Matriz g = criaMatrizZero(this.linha, false);
            
            for(int i = 0; i < this.linha; i++) {
                for(int j = 0; j < this.coluna; j++) {
                    if(i == j) {
                        Fracao soma = new Fracao(0);
                        
                        for(int k = 0; k < i; k++) {
                            soma = soma.adicao(g.matriz[i][k].multiplicacao(g.matriz[i][k]));
                        }
                        
                        g.matriz[i][j] = (this.matriz[i][j].subtracao(soma)).raiz();
                    }
                    
                    else if(i > j) {
                        Fracao soma = new Fracao(0);
                        
                        for(int k = 0; k < j; k++) {
                            soma = soma.adicao(g.matriz[i][k].multiplicacao(g.matriz[j][k]));
                        }
                        
                        g.matriz[i][j] = (this.matriz[i][j].subtracao(soma)).divisão(g.matriz[j][j]);
                    }
                }
            }
            
            System.out.println(g);
            System.out.println(g.transposta());
        }
    }
}
