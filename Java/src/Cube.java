import javax.swing.*;
import javax.swing.text.Position;

/**
 * Created by JP Bulman on 9/25/2017.
 */


//Overall/General notes for this document
    //Todo:



//Reasons and misc explanations:
    //The cycling of nested if's with the moves is kind of gross, but works fairly well. Essentially, this is a search, check, and then if that fails,
    // check a new spot until the piece desired is found.
    //I tried using switch statements instead, but these resulted in non-constant expressions, which is something that Java was not thrilled with
    //One of my goals is to try and find a workaround this, or to try and see if there is another alternative that is more condensed
    //Making some of the functions more abstract could be advantageous





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

    */

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
    static Corner WGR = new Corner(1,4,5);

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
                new Edge(this.e9.esticker2,this.e9.esticker1), new Edge(this.e4.esticker2,this.e4.esticker1),
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


    public Cube BWE(int Uacc,int Dacc){

        //If the U and the D layer do not have the edge, it puts the equator edges into the U/D layers and searches again
        if(Dacc==4){
            return L().R().BWE(0,0);
        }

        //If the U layer does not  have it, it starts to search the D-layer
        if(Uacc == 4){
            if ((this.e9.esticker1==1 && this.e9.esticker2==3)||(this.e9.esticker1==3 && this.e9.esticker2==1)){
                if(this.e9.esticker1==1 && this.e9.esticker2==3){return this;}
                else {return D().R().F();}
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
        if (Uacc==4){
            if((this.e10.esticker1==1 && this.e10.esticker2==5)||(this.e10.esticker1==5 && this.e10.esticker2==1)){return L2().RWE(0);}
            else if((this.e11.esticker1==1 && this.e11.esticker2==5)||(this.e11.esticker1==5 && this.e11.esticker2==1)){return B2().RWE(0);}
            else if((this.e12.esticker1==1 && this.e12.esticker2==5)||(this.e12.esticker1==5 && this.e12.esticker2==1)){return R2().RWE(0);}
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

        //Base Case
      if(this.c5.csticker2==3 && this.c5.csticker3==6 && this.c6.csticker2==3 && this.c7.csticker2==4 && this.c7.csticker3==6
              && this.c8.csticker2==4){return this;}

      if(Uacc == 4){return R().U().Rp().Corners(0);}

      //Uses its own slot to solve
      if(this.c1.csticker1==1 || this.c1.csticker2==1 || this.c1.csticker3==1){
          if(this.c1.csticker1==1){
              if(this.c1.csticker2==5){return R().U().Rp().U().R().Up().Rp().Corners(0);}
              else if (this.c1.csticker2==3){return Up().Lp().U().L().F().U2().Fp().Corners(0);}
              else if (this.c1.csticker2==6){return L().Up().Lp().Bp().U2().B().Corners(0);}
              else {return U().Rp().U().R().B().U2().Bp().Corners(0);}
          }

          if(this.c1.csticker2==1){
              if(this.c1.csticker1==3){return R().U2().Rp().Corners(0);}
              else if (this.c1.csticker1==6){return F().Up().Fp().Corners(0);}
              else if (this.c1.csticker1==4){return Bp().Up().B().Corners(0);}
              else {return U().Rp().Up().R().Corners(0);}
          }

          if(this.c1.csticker3==1){
              if(this.c1.csticker1==5){return Fp().U2().F().Corners(0);}
              else if (this.c1.csticker1==3){return U2().Lp().U().L().Corners(0);}
              else if (this.c1.csticker1==6){return Up().Bp().U().B().Corners(0);}
              else {return Rp().U().R().Corners(0);}
          }
      }
      else {return U().Corners(Uacc + 1);}

    return this;}

    public Cube SecondLayerEdges(int Uacc){

        //Base case, is it solved?
        if(this.e5.esticker1==4 && this.e5.esticker2==6 && this.e6.esticker1==3 && this.e6.esticker2==6 && this.e7.esticker1==3
                && this.e7.esticker2==5 && this.e8.esticker1==4 && this.e8.esticker2==5){return this;}

        if(Uacc==4){}


    return this;}


    public static void main(String[] args){
        Cube Solved = new Cube(YG,YO,YB,YR,GO,BO,BR,GR,WB,WO,WG,WR,YGO,YGR,YBO,YBR,WBO,WBR,WGO,WGR,"");
        Cube ToSolve = new Cube(YB,YO,YG,YR,GO,BR,WB,GR,BO,WO,WG,WR,YGO,YGR,YBO,YBR,WBO,WBR,WGO,WGR,"");
        Cube CrossSolved = ToSolve.BWE(0,0);

        //U2 L2 U F2 L2 R2 U' R2 B2 D2 F L2 R' F' D' F U' L B2 R
        Cube Scrambled = new Cube(new Edge(6,3),WR,GR,new Edge(3,5),YB,WB,new Edge(6,4),WO,
                YO,new Edge(5,2),YG,WG,YGO,new Corner(3,2,5),new Corner(4,5,2),
                new Corner(5,1,4),new Corner(6,1,3),new Corner(3,5,1),
                new Corner(3,6,2),new Corner(6,4,1),"");

        System.out.println(Solved.c2.csticker1);
        System.out.println(Solved.R().U2().Rp().c4.csticker3);
        System.out.println(Solved.Rp().c2.csticker1);
        System.out.println(Solved.R2().c2.csticker1);

        Corner test = new Corner(1,3,6);

        System.out.println(Scrambled.BWE(0,0).RWE(0).GWE(0).OWE(0).Corners(0).solution.replaceAll(" U U U U", "").replaceAll(" D D D D", "")
                .replaceAll(" F F"," F2").replaceAll(" L L", " L2").replaceAll(" U U"," U2").replaceAll(" R R", " R2").replaceAll(" B B", " B2")
                .replaceAll(" U2 U", " U'").replaceAll(" R2 R", " R'").replaceAll(" L2 L", " L'").replaceAll(" F2 F", " F'")
                .replaceAll(" B2 B", " B'"));

        //System.out.println(Solved.BWE(0,0).RWE(0).GWE(0).OWE(0).Corners(0).solution);


        //Cube ne = Solved.Rt(Solved);

        //System.out.println(ne.solution);



    }

}
