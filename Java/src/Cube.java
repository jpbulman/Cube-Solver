/**
 * Created by JP Bulman on 9/25/2017.
 */
public class Cube {

    public Cube(Edge e1, Edge e2, Edge e3, Edge e4, Edge e5, Edge e6, Edge e7, Edge e8, Edge e9, Edge e10, Edge e11, Edge e12,
    Corner c1,Corner c2,Corner c3,Corner c4,Corner c5,Corner c6,Corner c7,Corner c8, String solution){
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

    public static void main(String[] args){
        Cube Solved = new Cube(YG,YO,YB,YR,GO,BO,BR,GR,WB,WO,WG,WR,YGO,YGR,YBO,YBR,WBO,WBR,WGO,WGR,"");

    }

}
