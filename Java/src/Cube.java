/**
 * Created by JP Bulman on 9/25/2017.
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
               new Corner(this.c2.csticker2,this.c2.csticker1,this.c2.csticker3),"");
    }

    public static void main(String[] args){
        Cube Solved = new Cube(YG,YO,YB,YR,GO,BO,BR,GR,WB,WO,WG,WR,YGO,YGR,YBO,YBR,WBO,WBR,WGO,WGR,"");

        System.out.println(Solved.c2.csticker3);
        System.out.println(Solved.R().c2.csticker3);




    }

}
