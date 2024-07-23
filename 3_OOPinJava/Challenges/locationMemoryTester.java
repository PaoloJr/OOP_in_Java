public class locationMemoryTester {
	public static void main(String[] args) {
		SimpleLocation ucsd = new SimpleLocation(32.9, -117.2);
		SimpleLocation kumamoto = new SimpleLocation(32.0, 130.0);
		ucsd = kumamoto;
		kumamoto = ucsd;
		System.out.println("UCSD: " + ucsd.latitude + ", longitude " + ucsd.longitude);
		System.out.println("Kumamoto: " + kumamoto.latitude + ", longitude " + kumamoto.longitude);
	}
	
}