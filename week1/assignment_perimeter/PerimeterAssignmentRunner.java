import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int totalPoints = 0;
        for (Point p : s.getPoints()) {
            int point = 1;
            totalPoints = totalPoints + point; 
        }
        return totalPoints;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        int totalSides = getNumPoints(s);
        double totalLength = getPerimeter(s);
        double avgSideLength = totalLength/totalSides;
        return avgSideLength;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        double maxLength = 0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
                if (currDist > maxLength) {
                maxLength = currDist;
            }
            prevPt = currPt;  
        }
        return maxLength;
    }

    
    public double getLargestX(Shape s) {
        // Put code here
        double maxX = 0;
        for (Point currPt : s.getPoints()) {
            double firstX = currPt.getX();
            //System.out.println(firstX);
            if (firstX > maxX) {
                maxX = firstX;
            }
        }
    return maxX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        return 0.0;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File temp = null;    // replace this code
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter of the shape = " + length);
        int numPoints = getNumPoints(s); 
        System.out.println("number of points of the shape = " + numPoints);        
        double avgLength = getAverageLength(s);
        System.out.println("average length of a side of the shape = " + avgLength);        
        double maxLength = getLargestSide(s);
        System.out.println("the longest side of the shape = " + maxLength);
        double maxXvalue = getLargestX(s);        
        System.out.println("the largest x-value for the points of the shape = " + maxXvalue);
        
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}