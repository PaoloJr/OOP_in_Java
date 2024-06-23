
/**
 * Write a description of PerimeterRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class PerimeterRunner {
	public double getPerimeter(Shape s) {
	// start with `totalPerim` = 0
	double totalPerim = 0;
	// Start with `prevPt` = the last point
	Point prevPt = s.getLastPoint();
	// `For each` point `currPt` in the shape
	for(Point currPt: s.getPoints()) {
		// find distance from `prevPt` pt to `currPt`, name it `currDist`
		double currDist = prevPt.distance(currPt);
		// update `totalPerim` to be `totalPerim` + `currDist`
		totalPerim = totalPerim + currDist;
		// update `prevPt` to be `currPt`
		prevPt = currPt;
		}
		// `totalPerim` is the answer
		return totalPerim;
	}	
	public void testPerimeter () {
		FileResource fr = new FileResource();
		Shape s = new Shape(fr);
		double length = getPerimeter(s);
		System.out.println("perimeter = " + length);
	}
	public static void main (String[] args) {
		PerimeterRunner pr = new PerimeterRunner();
		pr.testPerimeter();
	}
}
