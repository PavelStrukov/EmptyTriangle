
package emptytriangle;


import java.util.ArrayList;
import java.util.Scanner;

/**
 * На плоскости отмечены несколько точек. Определить, можно ли по трем, 
 * произвольно выбранным точкам построить треугольник, 
 * который не содержал бы внутри себя остальные точки. 
 * Если точка лежит на стороне треугольника, то считается, 
 * что она находится внутри треугольника.
 *
 * @author Dell
 */
public class Main {
    
    static boolean isTriangle(Dot one,Dot two, Dot three){
        int x1 = one.x;
        int y1 = one.y;
        int x2 = two.x;
        int y2 = two.y;
        int x3 = three.x;
        int y3 = three.y;
        
        int S = Math.abs((x1-x3)*(y2-y3)-(x2-x3)*(y1-y3));
        
        if (S == 0) {
            return false;
        } else{
            return true;
        }
    }
    
    static boolean checkTriangle(Triangle triangle, Dot dot){//true - если точка внутри треугольника
        int x1 = triangle.dot1.x;
        int y1 = triangle.dot1.y;
        int x2 = triangle.dot2.x;
        int y2 = triangle.dot2.y;
        int x3 = triangle.dot3.x;
        int y3 = triangle.dot3.y;
        
        int x0 = dot.x;
        int y0 = dot.y;
        
        int value1 = (x1-x0)*(y2-y1)-(x2-x1)*(y1-y0);
        int value2 = (x2-x0)*(y3-y2)-(x3-x2)*(y2-y0);
        int value3 = (x3-x0)*(y1-y3)-(x1-x3)*(y3-y0);
        
        return (value1>0 && value2>0 && value3>0) || (value1<0 && value2<0 && value3<0); //точка внутри треугольника
    }
    
    
    
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        if (N<3){
            System.out.println("NO");
        } else{
            Dot[] dots = new Dot[N];
            for (int i=0; i<N; i++) {
                int x = in.nextInt();
                int y = in.nextInt();
                dots[i] = new Dot(x, y);
            }
        
            ArrayList<Triangle> triangles = new ArrayList<>();
            for (int i = 0; i < dots.length-2; i++) {
                for (int j = i+1; j < dots.length-1; j++) {
                    for (int k = j+1; k < dots.length; k++) {
                        if (isTriangle(dots[i], dots[j], dots[k])) {
                            triangles.add(new Triangle(dots[i], dots[j], dots[k], dots[i].x, dots[i].y));
                        }
                    
                    }
                
                }
            }
            
            boolean exists = false;//существует искомый треугольник
            for (Triangle triangle : triangles) {
                for (Dot dot : dots) {
                    if ((triangle.dot1.equals(dot)) || (triangle.dot2.equals(dot)) || triangle.dot3.equals(dot)) {
                        continue;//чтобы не попасть в вершины
                    }
                    if (!checkTriangle(triangle, dot)) {
                        exists = true;
                    }
                }
            }
            if (exists) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}

class Triangle extends Dot{
    Dot dot1;
    Dot dot2;
    Dot dot3;

    public Triangle(Dot dot1, Dot dot2, Dot dot3, int x, int y) {
        super(x, y);
        this.dot1 = dot1;
        this.dot2 = dot2;
        this.dot3 = dot3;
    }
    
}

class Dot {
    int x;
    int y;

    public Dot(int x, int y) {
        setDots(x, y);
    }
  
    public void setDots(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public int[] getDots(){
        int[] temp = new int[2];
        temp[0] = this.x;
        temp[1] = this.y;
        return temp;
    }
    
}
