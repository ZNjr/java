/**
 * Created by z1140463 on 28.02.2017.
 */
class Point2D {
    public double x;
    public double y;

    Point2D(){
        this.x=Math.random();
        this.y=Math.random();
    }
   double dist(Point2D other){
       return  Math.sqrt(Math.pow(x-other.x,2)+Math.pow(y-other.y,2));
   }
}
class Test02 {
    public static void main(String[] args) {
        Point2D[] points = new Point2D[100];
        int[] hist = new int[14];

         for(int i=0;i<14;i++){
             hist[i]=0;
         }
        for (int i = 0; i < 100; i++)
            points[i] = new Point2D();

        double min = Math.sqrt(2);
        for (int i = 0; i < 100; i++) {
            for (int j = i+1; j < 100; j++) {
                    if(points[i].dist(points[j])<min) min = points[i].dist(points[j]);
                    int index = (int)(points[i].dist(points[j])*10);
                    hist[index]++;
            }
        }
        System.out.println(min);
        for(int i=0;i<14;i++){
            System.out.println(hist[i]);
        }
    }
}

