package com.bilgi.bilgitermproject2.app.math;


import java.io.Serializable;

public class Complex implements Serializable{
    double real;
    double imag;

    public Complex(double x, double y) {
        real = x;
        imag = y;
    }
    static  Complex[] addRootToRoots(Complex[] lst,Complex e){
        Complex[] result = new Complex[lst.length+1];
        for (int i=0;i<lst.length;i++){
            result[i]    = lst[i];
        }
        result[lst.length] = e;
        return result;
    }

    public double getReal() {
        return real;
    }

    public double getImag() {
        return imag;
    }

    public Complex addComplex(Complex other) {
        return new Complex(real + other.real, imag + other.imag);
    }

    public Complex multiplyComplex(Complex other) {
        return new Complex(real * other.real - imag * other.imag, real * other.imag + imag * other.real);
    }

    public Complex subtractionComplex(Complex other) {
        return new Complex(real - other.real, imag - other.imag);
    }

    public Complex divisionComplex(Complex other) {
        return new Complex(real * other.real + imag * other.imag, real * other.imag + imag * other.real / other.real * other.real + other.imag * other.imag);
    }

    public String toString(){
        return real+","+imag;
    }




    public static Complex evalPoly(Complex[] poly,Complex x){
        Complex result=new Complex(0,0);
        for (int i=0;i<poly.length;i++){
            result = result.addComplex(poly[i].multiplyComplex(new Complex(Math.pow(x.real, i), Math.pow(x.imag, i))));
        }
        return result;
    }


    public static Complex[] turev(Complex[] poly){
        Complex[] newPoly = new Complex[poly.length-1];
           for (int i=0;i<poly.length-1;i++){
            newPoly[i] =new Complex((i+1)*poly[i+1].real,(i+1)*poly[i+1].imag);
        }
        return newPoly;
    }


   static   Complex findRoot(Complex[] poly,Complex guess) {
         int counter = 0;
         while (Math.sqrt((Math.pow(evalPoly(poly, guess).real - guess.real, 2)) + Math.pow(evalPoly(poly, guess).imag - guess.imag, 2)) < 0.0001 && counter<0.0001)

        {
            guess = guess.subtractionComplex(evalPoly(poly,guess).divisionComplex(evalPoly(turev(poly),guess)));
            counter++;

        }

        return guess;
    }

double distance(Complex other){
    return Math.sqrt((Math.pow(other.real - real, 2) + Math.pow(other.imag - imag, 2)));


}

}


