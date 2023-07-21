/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab1p2_diegovasquez;

import java.util.Scanner;

/**
 *
 * @author Diego Vasquez
 */
public class Lab1P2_DiegoVasquez {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner lea = new Scanner(System.in);
        boolean seguir = true;
        while(seguir){
            System.out.println("""
                               =MENU=
                               1.Newton-Raphson
                               2.Serie de Taylor
                               3.Salir
                               Ingrese una opcion: """);
            int opcion = lea.nextInt();
            switch(opcion){
                case 1:{
                    //Newton
                    System.out.println("Ingrese valor de a: ");
                    int a = lea.nextInt();
                    while(a <= 0){
                        System.out.println("El numero debe de ser mayor a 0 \n intentelo de nuevo: ");
                        a = lea.nextInt();
                    }//fin validacion
                    System.out.println("Ingrese valor de b: ");
                    int b = lea.nextInt();
                    System.out.println("Ingrese el valor de c: ");
                    int c = lea.nextInt();
                    double vertX = (b*-1)/(2*a);
//                    double vertY = (a*Math.pow(vertX, 2))+(b*vertX)+c;
                    double x0 = vertX+200;
                    double Xn = x0 - vertX/ (2*a*vertX+b);
                    double resp = Newton(a, b, c, 200, vertX, 1, 0, x0, Xn);
                    System.out.println("raíz encontrada positiva: "+resp);
                    double neg_x0 = vertX-200;
                    double neg_Xn = neg_x0 - vertX/ (2*a*vertX+b);
                    double neg_resp = NegNewton(a, b, c, 200, vertX, 1, 0, neg_x0, neg_Xn);
                    System.out.println("raiz encontrada negativa: "+neg_resp);
                }
                break;
                case 2:{
                    //taylor
                    System.out.println("Ingrese el limite: ");
                    int lim = lea.nextInt();
                    while(lim <=0){
                        System.out.println("El limite debe de ser mayor a 0 \n intentelo de nuevo");
                        lim = lea.nextInt();
                    }//fin validacion
                    System.out.println("Ingrese el valor de x: ");
                    int valorX = lea.nextInt();
                    double resp_sen = TaylorSen(lim, valorX, 0, 0);
                    System.out.println("Sen x = "+resp_sen);
                    double resp_cos = TaylorCos(lim, valorX, 0, 0);
                    System.out.println("Cos x = "+resp_cos);
                    double resp_tan = TaylorTan(lim, valorX, 0, 1);
                    System.out.println("Tan x ="+resp_tan);
                    
                }
                break;
                case 3:{
                    seguir = false;
                }
                break;
                default:{
                    System.out.println("Opcion invalida, Intentelo de nuevo");
                }
                break;
            }//fin switch
            
        }//fin while seguir
    }//fin main
    
    public static double Newton(int a, int b, int c, int desplazamiento, double vertX, int cont, double resp, double x0, double Xn){
        if(cont == 200){
          return Xn;  
        }else{
            Xn = x0 - Xn;
            return resp = Newton(a, b, c, 200, vertX, cont+1, 0, x0, Xn);
            
        }
    }
    public static double NegNewton(int a, int b, int c, int desplazamiento, double vertX, int cont, double resp, double x0, double Xn){
        if(cont == 0){
          return Xn;  
        }else{
            Xn = x0 - Xn;
            return resp = Newton(a, b, c, 200, vertX, cont-1, 0, x0, Xn);
            
        }
    }
    
    public static double TaylorSen(int lim, int x, double suma, int n){
        //sen x
        
        if(lim == n){
            //caso base

            return suma;
        }else{
            //logica

            double numerador = Math.pow(-1, n);
            int parentesis = 2*n+1;
            int factorial =1;
            for (int i = 1; i <= parentesis; i++) {
            factorial = factorial*i;
            }
            
            double div = numerador/factorial;
            double mult = div * Math.pow(x, 2*n+1);
            suma = suma+mult;
            return TaylorSen(lim, x, suma, n+1);
            
            
        }//fin if
        
    }//fin recursiva sen
    
    public static double TaylorCos(int lim, int x, double suma, int n){
        //cos x
        if(lim == n){
            return suma;
        }else{
            double numerador = Math.pow(-1, n);
            double parentesis = 2*n;
            double factorial = 1;
            for (int i = 1; i <= parentesis; i++) {
                factorial = factorial*i;
            }
            double div = numerador/factorial;
            double mult = div * Math.pow(x, 2*n);
            suma += mult;
            return TaylorCos(lim, x, suma, n+1);
        }//fin if cos
    }//fin recursiva cos
    
    public static double TaylorTan(int lim, int x, double suma, int n){
        //tan x
        if(lim == n){
            return suma;
        }else{
            double B = Math.pow(2, n);
            double numerador = B * Math.pow(-4, n)*(1-Math.pow(4, n));
            double parentesis = 2*n;
            double factorial = 1;
            for (int i = 1; i <= parentesis; i++) {
                factorial = factorial*i;
            }
            double div = numerador/factorial;
            double mult = div * Math.pow(x, 2*n-1);
            suma += mult;
            return TaylorTan(lim, x, suma, n+1);
        }
    }//fin recursiva tan
    
    
}//fin clase
