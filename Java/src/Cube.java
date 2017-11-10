import javax.swing.*;
import javax.swing.text.Position;

/**
 * Created by JP Bulman on 9/25/2017.
 */


//Overall/General notes for this document
    //Todo: Cleanup



//Reasons and misc explanations:
    //The cycling of nested if's with the moves is kind of gross, but works fairly well. Essentially, this is a search, check, and then if that fails,
    // check a new spot until the piece desired is found.
    //I tried using switch statements instead, but these resulted in non-constant expressions, which is something that Java was not thrilled with
    //One of my goals is to try and find a workaround this, or to try and see if there is another alternative that is more condensed
    //Making some of the functions more abstract could be advantageous

//General overview of solving process:

    //Method: Beginners

        //Cross:
            //BWE:
                //Looks for the blue-white edge, finds where it is, and then does setup moves to put it in a position where it will then be solved
            //RWE,GWE,OWE: Same principle as BWE except with the remaining

        //First layer corners:
            //Corners
                //Uses generative recursion
                    //It solves a first layer corner each time
                    //The termination case is when all of the first layer corners are solved
                    //This will always terminate because the function goes through the possible states and solves corners and only finishes when
                        //all of them are solved. It also has catch cases that prevent things like an adjacent swap happening

        //Second layer edges
            //SecondLayerEdges
                //Uses generative recursion
                    //It solves the middle layer edges
                    //The base case is when all 4 of the equator edges are solved
                    //This will always terminate because the function only finishes when all of the edges are solved

        //OLL Edges
            //Basic beginner style strategy with generative recursion
            //Looks for one case, otherwise cycles until it reaches that point

        //OLL Corners
            //Standard algorithm for orientation R' D' R D
            //Base case is when the yellow face is done

        //PLL Corners
            //Looks for the only 3 possible cases: solved, adjacent, or opposite

        //PLL Edges
            //This was a bit harder because of all the different edge cases
            //Essentially, it looks for weird cases first, then, if none of those are true, it looks for a u-perm
            //If none of these are true, it does a U until one of them is

        //AUF
            //Pretty straight forward, just does one turn move until the cube is finished


        /*
    Color key
    1-White
    2-Yellow
    3-Blue
    4-Green
    5-Red
    6-Orange

    Corners have sticker1 that is the sticker on the top | bottom face
    Corners have sticker2 that is the sticker on the front | back face
    Corners have sticker3 that is the sticker on the left | right face

    Edges have sticker1 on whichever sticker is on the 'oriented side' with respect to a YB orientation
    Sticker2 is the other on side of the edge

    */


public class Cube {

    private Edge e1,e2,e3,e4,e5,e6,e7,e8,e9,e10,e11,e12;
    private Corner c1,c2,c3,c4,c5,c6,c7,c8;
    private String solution;

    public Cube(Edge e1, Edge e2, Edge e3, Edge e4, Edge e5, Edge e6, Edge e7, Edge e8, Edge e9, Edge e10, Edge e11, Edge e12,
    Corner c1,Corner c2,Corner c3,Corner c4,Corner c5,Corner c6,Corner c7,Corner c8, String solution){
        this.e1 = e1;
        this.e2 = e2;
        this.e3 = e3;
        this.e4 = e4;
        this.e5 = e5;
        this.e6 = e6;
        this.e7 = e7;
        this.e8 = e8;
        this.e9 = e9;
        this.e10 = e10;
        this.e11 = e11;
        this.e12 = e12;

        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
        this.c4 = c4;
        this.c5 = c5;
        this.c6 = c6;
        this.c7 = c7;
        this.c8 = c8;

        this.solution = solution;

    }

    //Some possible edges and corners are predefined here for the solved cube and for when they come up in scrambled cubes

    static Edge YG = new Edge(2,4);
    static Edge YO = new Edge(2,6);
    static Edge YB = new Edge(2,3);
    static Edge YR = new Edge(2,5);

    static Edge GO = new Edge(4,6);
    static Edge BO = new Edge(3,6);
    static Edge BR = new Edge(3,5);
    static Edge GR = new Edge(4,5);

    static Edge WB = new Edge(1,3);
    static Edge WO = new Edge(1,6);
    static Edge WG = new Edge(1,4);
    static Edge WR = new Edge(1,5);

    static Corner YGO = new Corner(2,4,6);
    static Corner YGR = new Corner(2,4,5);
    static Corner YBO = new Corner(2,3,6);
    static Corner YBR = new Corner(2,3,5);

    static Corner WBO = new Corner(1,3,6);
    static Corner WBR = new Corner(1,3,5);
    static Corner WGO = new Corner(1,4,6);
    static Corner WGR = new Corner(1,5,4);



    //Here are the move functions for the cubesolver
        //They take in a cube and return a new cube
        //All inverse move or 'p' (stands for prime) moves are the same as doing its normal move three times
        //Since the computation is very simple and not expensive, it is much cleaner to write it as three of its normal moves rather than its own move
        //Then, during the end when the solution is process, the three moves in a row (and many other things) are filtered out and replaced with
        //proper notation.
        //Same principal with '2' moves, the same thing as the normal move, but done twice

    public Cube R(){
        return new Cube(this.e1, this.e2, this.e3, this.e7, this.e5,this.e6,this.e12,this.e4,this.e9,this.e10,this.e11,this.e8,
               this.c1, new Corner(this.c4.csticker2,this.c4.csticker1,this.c4.csticker3),this.c3,
               new Corner(this.c6.csticker2,this.c6.csticker1,this.c6.csticker3),this.c5,
               new Corner(this.c8.csticker2,this.c8.csticker1,this.c8.csticker3),this.c7,
               new Corner(this.c2.csticker2,this.c2.csticker1,this.c2.csticker3),this.solution.concat(" R"));
    }

    public Cube Rp(){
        return R().R().R();
    }

    public Cube R2(){
        return R().R();
    }

    public Cube U(){
        return new Cube(this.e2,this.e3,this.e4,this.e1,this.e5,this.e6,this.e7,this.e8,this.e9,this.e10,this.e11,this.e12,
                new Corner(this.c3.csticker1,this.c3.csticker3,this.c3.csticker2),
                new Corner(this.c1.csticker1,this.c1.csticker3,this.c1.csticker2),
                new Corner(this.c4.csticker1,this.c4.csticker3,this.c4.csticker2),
                new Corner(this.c2.csticker1,this.c2.csticker3,this.c2.csticker2),
                this.c5,this.c6,this.c7,this.c8,this.solution.concat(" U"));
    }

    public Cube Up(){
        return U().U().U();
    }

    public Cube U2(){
        return U().U();
    }

    public Cube F(){
        return new Cube(this.e1,this.e2, new Edge(this.e6.esticker2,this.e6.esticker1),this.e4,this.e5,
                new Edge(this.e9.esticker2,this.e9.esticker1), new Edge(this.e3.esticker2,this.e3.esticker1),
                this.e8,new Edge(this.e7.esticker2,this.e7.esticker1),this.e10,this.e11,this.e12,
                this.c1,this.c2,new Corner(this.c5.csticker3,this.c5.csticker2,this.c5.csticker1),
                new Corner(this.c3.csticker3,this.c3.csticker2,this.c3.csticker1),
                new Corner(this.c6.csticker3,this.c6.csticker2,this.c6.csticker1),
                new Corner(this.c4.csticker3,this.c4.csticker2,this.c4.csticker1),
                this.c7,this.c8,this.solution.concat(" F"));
    }

    public Cube Fp(){
        return F().F().F();
    }

    public Cube F2(){
        return F().F();
    }


    public Cube L(){
        return new Cube(this.e1,this.e5,this.e3,this.e4,this.e10,this.e2,this.e7,this.e8,this.e9,this.e6,this.e11,this.e12,
                new Corner(this.c7.csticker2,this.c7.csticker1,this.c7.csticker3),this.c2,
                new Corner(this.c1.csticker2,this.c1.csticker1,this.c1.csticker3),this.c4,
                new Corner(this.c3.csticker2,this.c3.csticker1,this.c3.csticker3),this.c6,
                new Corner(this.c5.csticker2,this.c5.csticker1,this.c5.csticker3),this.c8,this.solution.concat(" L"));
    }

    public Cube Lp(){
        return L().L().L();
    }

    public Cube L2(){
        return L().L();
    }

    public Cube D(){
        return new Cube(this.e1,this.e2,this.e3,this.e4,this.e5,this.e6,this.e7,this.e8,this.e10,this.e11,this.e12,this.e9,
                this.c1,this.c2,this.c3,this.c4,
                new Corner(this.c7.csticker1,this.c7.csticker3,this.c7.csticker2),
                new Corner(this.c5.csticker1,this.c5.csticker3,this.c5.csticker2),
                new Corner(this.c8.csticker1,this.c8.csticker3,this.c8.csticker2),
                new Corner(this.c6.csticker1,this.c6.csticker3,this.c6.csticker2),this.solution.concat(" D"));
    }

    public Cube Dp(){
        return D().D().D();
    }

    public Cube D2(){
        return D().D();
    }

    public Cube B(){
        return new Cube(new Edge(this.e8.esticker2,this.e8.esticker1), this.e2,this.e3,this.e4,new Edge(this.e1.esticker2,this.e1.esticker1),
                this.e6,this.e7,new Edge(this.e11.esticker2,this.e11.esticker1),this.e9,this.e10,new Edge(this.e5.esticker2,this.e5.esticker1),
                this.e12,new Corner(this.c2.csticker3,this.c2.csticker2,this.c2.csticker1),
                new Corner(this.c8.csticker3,this.c8.csticker2,this.c8.csticker1),
                this.c3,this.c4,this.c5,this.c6,
                new Corner(this.c1.csticker3,this.c1.csticker2,this.c1.csticker1),
                new Corner(this.c7.csticker3,this.c7.csticker2,this.c7.csticker1),this.solution.concat(" B"));
    }

    public Cube Bp(){
        return B().B().B();
    }

    public Cube B2(){
        return B().B();
    }


    //Solving functions
    //This explained a lot more up top, but essentially, most functions are designed with generative recursion so that the pieces are solved

    public Cube BWE(int Uacc,int Dacc){
        if(this.e9.esticker1==1 && this.e9.esticker2==3){return this;}

        //If the U and the D layer do not have the edge, it puts the equator edges into the U/D layers and searches again
        if(Dacc==4){
            return L().R().BWE(0,0);
        }

        //If the U layer does not  have it, it starts to search the D-layer
        if(Uacc == 4){
            if (this.e9.esticker1==3 && this.e9.esticker2==1){
                return D().R().F();
            }
            else {return D().BWE(Uacc,(Dacc + 1));}
        }

        //Searches the U-layer for the WB edge
        if((this.e1.esticker1==1 && this.e1.esticker2==3)||(this.e1.esticker1==3 && this.e1.esticker2==1)){
            if(this.e1.esticker1==1 && this.e1.esticker2==3){return U2().F2();}
            else {return U().Rp().F();}
        }
        else {return U().BWE(Uacc + 1, Dacc);}

    }


    public Cube RWE(int Uacc){

        //If the U layer does not have it, the function checks the D-layer, and if it is not there, it cycles the remaining edges into the U and
        //D layers and starts the search over. This only has a 4 in 11 chance of happening, and if it does happen, the function only has to
        //re-cycle once.

        if(this.e12.esticker1==1&&this.e12.esticker2==5){return this;}

        if (Uacc==4){
            if((this.e10.esticker1==1 && this.e10.esticker2==5)||(this.e10.esticker1==5 && this.e10.esticker2==1)){return L2().RWE(0);}
            else if((this.e11.esticker1==1 && this.e11.esticker2==5)||(this.e11.esticker1==5 && this.e11.esticker2==1)){return B2().RWE(0);}
            else if(this.e12.esticker1==5 && this.e12.esticker2==1){return R2().RWE(0);}
            else {return L().R().RWE(0);}
        }

        //Searches the U layer for the Red White edge
        if ((this.e1.esticker1==1 && this.e1.esticker2==5)||(this.e1.esticker1==5 && this.e1.esticker2==1)){
            if (this.e1.esticker1==1 && this.e1.esticker2==5){return U().R2();}
            else {return Bp().R();}
        }
        else {return U().RWE(Uacc + 1);}


    }

    public Cube GWE(int Uacc){

        if(this.e11.esticker1==1&&this.e11.esticker2==4){return this;}

        if (Uacc==4) {
            if((this.e5.esticker1==1 && this.e5.esticker2==4)||(this.e5.esticker1==4 && this.e5.esticker2==1)){return Bp().GWE(0);}
            else if((this.e6.esticker1==1 && this.e6.esticker2==4)||(this.e6.esticker1==4 && this.e6.esticker2==1)){return Lp().GWE(0);}
            else if((this.e7.esticker1==1 && this.e7.esticker2==4)||(this.e7.esticker1==4 && this.e7.esticker2==1)){return R().U().Rp().GWE(0);}
            else if((this.e8.esticker1==1 && this.e8.esticker2==4)||(this.e8.esticker1==4 && this.e8.esticker2==1)){return B().GWE(0);}
            else {return L2().B2().GWE(0);}
        }

        if((this.e1.esticker1==1 && this.e1.esticker2==4)||(this.e1.esticker1==4 && this.e1.esticker2==1)){
            if (this.e1.esticker1==1 && this.e1.esticker2==4){return B2();}
            else {return Up().Lp().B();}
        }
        else{return U().GWE(Uacc + 1);}
    }

    public Cube OWE(int Uacc){

        if ((this.e10.esticker1==1 && this.e10.esticker2==6)||(this.e10.esticker1==6 && this.e10.esticker2==1)){
            if((this.e10.esticker1==1 && this.e10.esticker2==6)){return this;}
            else {return L2().Up().Fp().L().F();}
        }

        if((this.e7.esticker1==1 && this.e7.esticker2==6)||(this.e7.esticker1==6 && this.e6.esticker2==1)){
            return R().U().Rp().OWE(0);
        }

        if((this.e8.esticker1==1 && this.e8.esticker2==6)||(this.e8.esticker1==6 && this.e8.esticker2==1)){
            return Rp().Up().R().OWE(0);
        }

        if (Uacc == 4){return L().OWE(0);}

        if ((this.e1.esticker1==1 && this.e1.esticker2==6)||(this.e1.esticker1==6 && this.e1.esticker2==1)){
            if (this.e1.esticker1==1 && this.e1.esticker2==6){return Up().L2();}
            else {return B().Lp().Bp();}
        }
        else {return U().OWE(Uacc + 1);}
    }

    //This is the function that solves all of the first layer corners
    //Instead of doing search checks, it uses a generative recursion style template combined with a context preserving accumulator
    //The function looks takes the first white corner it finds and then puts it in the correct position.
    //It then calls itself until it finds and permutes every first layer corner.
    //The trivial case for the generative recursion is just if the cube is solved, that is what the first 'if' block is looking at
    //If the function is called and the corners are solved, then it just returns
    public Cube Corners(int Uacc){

        //Rare cases?

        //Base Case
      if(this.c5.csticker2==3 && this.c5.csticker3==6 && this.c6.csticker2==3 && this.c6.csticker3==5 && this.c7.csticker2==4 && this.c7.csticker3==6
              && this.c8.csticker2==4&&this.c8.csticker3==5){return this;}

     if (Uacc==4){
        if (this.c5.csticker2!=3||this.c5.csticker1!=1){return Lp().U().L().Corners(0);}
        else if (this.c6.csticker2!=3||this.c6.csticker1!=1){return R().U().Rp().Corners(0);}
        else if (this.c7.csticker2!=4||this.c7.csticker1!=1){return L().U().Lp().Corners(0);}
        else {return Rp().Up().R().Corners(0);}

      }


        //Uses its own slot to solve
        if(this.c1.csticker1==1 || this.c1.csticker2==1 || this.c1.csticker3==1){
          if(this.c1.csticker1==1){
              if(this.c1.csticker2==5){return R().U().Rp().U().R().Up().Rp().Corners(0);}//
              else if (this.c1.csticker2==3){return Up().Lp().U().L().F().U2().Fp().Corners(0);}
              else if (this.c1.csticker2==6){return L().Up().Lp().Bp().U2().B().Corners(0);}
              else {return U().Rp().U().R().B().U2().Bp().Corners(0);}
          }

          else if(this.c1.csticker2==1){
              if(this.c1.csticker1==3){return R().U2().Rp().Corners(0);}
              else if (this.c1.csticker1==6){return F().Up().Fp().Corners(0);}
              else if (this.c1.csticker1==4){return Bp().Up().B().Corners(0);}
              else {return U().Rp().Up().R().Corners(0);}
          }

          else{
              if(this.c1.csticker1==5){return Fp().U2().F().Corners(0);}
              else if (this.c1.csticker1==3){return U2().Lp().U().L().Corners(0);}
              else if (this.c1.csticker1==6){return L().U().Lp().Corners(0);}
              else {return Rp().U().R().Corners(0);}
          }
      }
      else {return U().Corners(Uacc + 1);}

    }

    public Cube SecondLayerEdges(int Uacc){

        //Extreme cases?

        //Base case, is it solved?

        if(this.e5.esticker1==4 && this.e5.esticker2==6 && this.e6.esticker1==3 && this.e6.esticker2==6 && this.e7.esticker1==3
                && this.e7.esticker2==5 && this.e8.esticker1==4 && this.e8.esticker2==5){return this;}

        if(this.e7.esticker1==5&&this.e7.esticker2==3&&this.c6.csticker1==1)return Fp().R().U().Rp().Up().Rp().F().R().SecondLayerEdges(0);
        else if(this.e8.esticker1==5&&this.e8.esticker2==4&&this.c8.csticker1==1){return Rp().B().U().Bp().Up().Bp().R().B().SecondLayerEdges(0);}
        else if(this.e5.esticker1==6&&this.e5.esticker2==4&&this.c7.csticker1==1){return Bp().L().U().Lp().Up().Lp().B().L().SecondLayerEdges(0);}
        else if(this.e6.esticker1==6&&this.e6.esticker2==4&&this.c5.csticker1==1){return Lp().F().U().Fp().Up().Fp().L().F().SecondLayerEdges(0);}

        /*
        if(Uacc==4){
            if(this.e7.esticker1==5&&this.e7.esticker2==3)return Fp().R().U().Rp().Up().Rp().F().R().SecondLayerEdges(0);
            else if(this.e8.esticker1==5&&this.e8.esticker2==4){return Rp().B().U().Bp().Up().Bp().R().B().SecondLayerEdges(0);}
            else if(this.e6.esticker1==6&&this.e6.esticker2==4){return Bp().L().U().Lp().Up().Lp().B().L().SecondLayerEdges(0);}
            else {return Lp().F().U().Fp().Up().Fp().L().F().SecondLayerEdges(0);}
        }*/

        if(Uacc==4){return this;}


        if(this.e1.esticker1 != 2 && this.e1.esticker2 != 2){

            if(this.e1.esticker1==3){
                if (this.e1.esticker2==6){return F().Up().Fp().Up().Lp().U().L().SecondLayerEdges(0);}
                else {return Fp().R().U().Rp().Up().Rp().F().R().SecondLayerEdges(0);}
            }

            else if(this.e1.esticker1==4){
                if (this.e1.esticker2==6){return U2().Bp().L().U().Lp().Up().Lp().B().L().SecondLayerEdges(0);}
                else{return U().Rp().U().R().U().Rp().U().R().Up().Rp().Up().R().SecondLayerEdges(0);}
            }

            else if(this.e1.esticker1==5){
                if (this.e1.esticker2==3){return Up().R().Up().Rp().F().Rp().Fp().R().SecondLayerEdges(0);}
                else {return Up().Rp().U().R().U().B().Up().Bp().SecondLayerEdges(0);}
            }

            else{
                if(this.e1.esticker2==3){return U().Lp().U().L().U().F().Up().Fp().SecondLayerEdges(0);}
                else {return U().L().Up().Lp().Up().Bp().U().B().SecondLayerEdges(0);}
            }

        }
        else{return U().SecondLayerEdges(Uacc + 1);}

    }

    public Cube OLLedges(){

        //base case, if all 4 edges are already oriented
        if(this.e1.esticker1==2&&this.e2.esticker1==2&&this.e3.esticker1==2&&this.e4.esticker1==2){return this;}

        if(this.e1.esticker1==2||this.e2.esticker1==2||this.e3.esticker1==2||this.e4.esticker1==2){

            if (this.e1.esticker1==2&&this.e2.esticker1==2){return F().R().U().Rp().Up().Fp().OLLedges();}
            else if (this.e2.esticker1==2&&this.e4.esticker1==2){return F().R().U().Rp().Up().Fp().OLLedges();}
            else {return U().OLLedges();}

        }
        else {return F().R().U().Rp().Up().Fp().OLLedges();}}

    public Cube OllCorners(){

        if(this.c1.csticker1==2&&this.c2.csticker1==2&&this.c3.csticker1==2&&this.c4.csticker1==2){return this;}
        else if (this.c4.csticker1==2){return U().OllCorners();}
        else {return Rp().Dp().R().D().OllCorners();}

    }

    public Cube Tperm(){
        return R().U().Rp().Up().Rp().F().R2().Up().Rp().Up().R().U().Rp().Fp();
    }

    public Cube Yperm(){
        return F().R().Up().Rp().Up().R().U().Rp().Fp().R().U().Rp().Up().Rp().F().R().Fp();
    }

    public Cube PLLCorners(){

        if((this.c1.csticker3==this.c3.csticker3)&&(this.c2.csticker3==this.c4.csticker3)){return this;}

        if (this.c1.csticker3==this.c3.csticker3 && (this.c2.csticker3 != this.c4.csticker3)){return Tperm().PLLCorners();}
        else if ((Math.abs(this.c1.csticker3-this.c3.csticker3)==1)&&(Math.abs(this.c2.csticker3-this.c4.csticker3)==1)){return Yperm().PLLCorners();}
        else {return U().PLLCorners();}

    }

    public Cube Uperm1(){
        return R().Up().R().U().R().U().R().Up().Rp().Up().R2();
    }

    public Cube PLLEdges(int Uacc){

        if (((this.c1.csticker2==this.e1.esticker2)&&(this.e1.esticker2==this.c2.csticker2))&&
                ((this.c1.csticker3==this.e2.esticker2)&&(this.e2.esticker2==this.c3.csticker3))){return this;}

        if((this.c1.csticker2==this.e1.esticker2)&&(this.e1.esticker2==this.c2.csticker2)){return Uperm1().PLLEdges(Uacc);}
        else if(((this.e3.esticker2==this.c2.csticker3)&&this.e3.esticker2==this.c4.csticker3)&&
                ((this.e4.esticker2==this.c3.csticker2)&&this.e4.esticker2==this.c4.csticker2)){return Uperm1().Up().Uperm1().PLLEdges(Uacc);}
        else if((Math.abs(this.e1.esticker2-this.c1.csticker2)==1)&&(Math.abs(this.e3.esticker2-this.c3.csticker2)==1)){return Uperm1().U().Uperm1()
                .PLLEdges(Uacc);}
        else {return U().PLLCorners();}

    }

    //This function aligns the last layer so the cube is solved
    public Cube AUF(){

        //Base case, checks to see if it is solved or not
        if (this.e3.esticker2==this.c5.csticker2){return this;}
        else {return U().AUF();}

    }

    int currentTestMove = 0;

    int currentCrossMoves = 8;

    /*
        1-White
    2-Yellow
    3-Blue
    4-Green
    5-Red
    6-Orange
    */

    boolean isSolved(){
        return (this.c1.csticker1==2&&this.e1.esticker1==2&&this.c2.csticker1==2&&this.e2.esticker1==2&&this.e4.esticker1==2&&
        this.c3.csticker1==2&&this.e3.esticker1==2&&this.c4.csticker1==2&&this.c5.csticker1==1&&this.e9.esticker1==1&&this.c6.csticker1==1&&
        this.e10.esticker1==1&&this.e12.esticker1==1&&this.c7.csticker1==1&&this.e11.esticker1==1&&this.c8.csticker1==1&&
        this.c1.csticker3==6&&this.e2.esticker2==6&&this.c3.csticker3==6&&
        this.c3.csticker2==3&&this.e3.esticker2==3&&this.c4.csticker2==3&&
        this.c4.csticker3==5&&this.e4.esticker2==5&&this.c2.csticker3==5&&
        this.c2.csticker2==4&&this.e1.esticker2==4&&this.c1.csticker2==4&&
        this.c7.csticker3==6&&this.e10.esticker2==6&&this.c5.csticker3==6&&
        this.c5.csticker2==3&&this.e9.esticker2==3&&this.c6.csticker2==3&&
        this.c6.csticker3==5&&this.e12.esticker2==5&&this.c8.csticker3==5&&
        this.c7.csticker2==4&&this.e11.esticker2==4&&this.c8.csticker2==4&&
        this.e6.esticker2==6&&this.e6.esticker1==3&&this.e7.esticker1==3&&this.e7.esticker2==5);
    }


    public Cube optimalCross(){

        //0-Blank/Nothing
        //1-R,2-R',3-R2
        //4-L,5-L',6-L2
        //7-F,8-F',9-F2
        //10-B,11-B',12-B2
        //13-U,14-U',15-U2
        //16-D,17-D',18-D2

        return this;
    }

    public static void main(String[] args){
        Cube Solved = new Cube(YG,YO,YB,YR,GO,BO,BR,GR,WB,WO,WG,WR,YGO,YGR,YBO,YBR,WBO,WBR,WGO,WGR,"");
        Cube ToSolve = new Cube(YB,YO,YG,YR,GO,BR,WB,GR,BO,WO,WG,WR,YGO,YGR,YBO,YBR,WBO,WBR,WGO,WGR,"");
        Cube CrossSolved = ToSolve.BWE(0,0);

        //U2 L2 U F2 L2 R2 U' R2 B2 D2 F L2 R' F' D' F U' L B2 R
        Cube Scrambled = new Cube(new Edge(6,3),WR,GR,new Edge(5,3),YB,WB,new Edge(6,4),WO,
                YO,new Edge(5,2),YG,WG,YGO,new Corner(3,2,5),new Corner(4,5,2),
                new Corner(5,1,4),new Corner(6,1,3),new Corner(3,5,1),
                new Corner(3,6,2),new Corner(6,4,1),"");

        //B2 U B2 U' F2 L2 F2 R2 D' F2 U2 L U2 R2 F' L' B' D2 L D' R2
        Cube Scrambled2 = new Cube(YG, new Edge(6,4),BR,WO, new Edge(5,1),GR,new Edge(4,1),
                new Edge(3,2),new Edge(6,2),new Edge(3,1),new Edge(6,3),
                new Edge(5,2),new Corner(4,6,2),YGR,new Corner(5,1,3),
                new Corner(3,2,6), YBR,WGO,WGR,new Corner(6,1,3),"");

        //B' U2 F D2 F' L2 D2 B' D2 F D B2 F L' U R B' R2 F R'
        Cube Scrambledn = new Cube(new Edge(5,1),new Edge(4,1),new Edge(6,2),new Edge(6,1),
                BO, GO, new Edge(3,2),new Edge(4,2),new Edge(5,2),WB,GR,new Edge(5,3),
                new Corner(5,1,4),WGO,new Corner(3,1,6),new Corner(5,4,2),
                new Corner(3,5,2), YBO,new Corner(2,6,4),new Corner(5,3,1),"");

        //ORDER OF REPLACEMENT DOES MATTER HERE
        //Replaces the repeated moves with their proper notation symbol
        //Ex: R R --> R2
        System.out.println(Scrambledn.BWE(0,0).RWE(0).GWE(0).OWE(0).Corners(0).SecondLayerEdges(0).OLLedges().OllCorners().PLLCorners().PLLEdges(0).AUF().solution.replaceAll(" U U U U", "").replaceAll(" D D D D", "")
                .replaceAll(" F F F F", "").replaceAll(" B B B B", "").replaceAll(" R R R R", "".replaceAll(" L L L L", ""))
                .replaceAll(" U U U", " U'").replaceAll(" D D D", " D'").replaceAll(" F F F", " F'").replaceAll(" B B B", " B'")
                .replaceAll(" L L L", " L'").replaceAll(" R R R", " R'")
                .replaceAll(" U U", " U2").replaceAll(" D D", " D2").replaceAll(" F F", " F2").replaceAll(" B B", " B2")
                .replaceAll(" L L", " L2").replaceAll(" R R", " R2")
        );

        System.out.println("Move count: " +  Scrambledn.BWE(0,0).RWE(0).GWE(0).OWE(0).Corners(0).SecondLayerEdges(0).OLLedges().OllCorners().PLLCorners().PLLEdges(0).AUF().solution.replaceAll(" U U U U", "").replaceAll(" D D D D", "")
                .replaceAll(" F F F F", "").replaceAll(" B B B B", "").replaceAll(" R R R R", "".replaceAll(" L L L L", ""))
                .replaceAll(" U U U", " U'").replaceAll(" D D D", " D'").replaceAll(" F F F", " F'").replaceAll(" B B B", " B'")
                .replaceAll(" L L L", " L'").replaceAll(" R R R", " R'")
                .replaceAll(" U U", " U2").replaceAll(" D D", " D2").replaceAll(" F F", " F2").replaceAll(" B B", " B2")
                .replaceAll(" L L", " L2").replaceAll(" R R", " R2").replaceAll("'","").replaceAll(" ", "").length()
        );

        //System.out.println(Scrambledn.isSolved());

        //System.out.println(Solved.GWE(0).solution);

        Cube newer = new Cube(YG,YO,YB,YR,GO,BO,BR,GO,WB,WO,WG,WR,YGO,YGR,YBO,YBR,WBO,WBR,WGO,
                new Corner(5,1,4),"");

        Cube newer1 = new Cube(YG,YO,YB,new Edge(5,2),GO,BO,BR,new Edge(5,4),WB,WO,WG,WR,YGO,YGR,YBO,YBR,WBO,WBR,WGO,
                WGR,"");

        //System.out.println(newer.Corners(0).solution);

        System.out.println(newer1.e8.esticker1);
        System.out.println(newer1.e8.esticker2);

        System.out.println(newer1.SecondLayerEdges(0).solution);

        //System.out.println(newer1.U().U().e1.esticker1);
        System.out.println(newer.SecondLayerEdges(0).solution);

    }

}
