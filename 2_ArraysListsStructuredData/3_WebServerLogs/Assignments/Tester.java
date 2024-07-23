import java.util.*;
public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("../short-test_log");
        la.printAll();
    }
    
    public void testCountUniqueIPs() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("../weblog2_log");
        int unique = la.countUniqueIPs();
        System.out.println("uniqueIPs = " + unique);
    }
    
    public void testPrintAllHigherThanNum() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("../weblog1_log");
        la.printAllHigherThanNum(400);
    }
    
    public void testUniqueIPvisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("../weblog2_log");
        String date = "Sep 24";
        ArrayList<String> uniqueIPday = la.uniqueIPVisitsOnDay(date);
        System.out.println(uniqueIPday);
        System.out.println(uniqueIPday.size());
    }
    
    public void testCountUniqueIPsInRange() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("../weblog2_log");
        int low = 200;
        int high = 299;
        int low2 = 400;
        int high2 = 499;
        
        int uniqueInRange = la.countUniqueIPsInRange(low, high);
        System.out.println(uniqueInRange);
        int uniqueInRange2 = la.countUniqueIPsInRange(low2, high2);
        System.out.println(uniqueInRange2);        
    }
    
    public void testCountVisitsPerIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("../short-test_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        System.out.println(counts);
        for (String s : counts.keySet()) {
            System.out.println(s + ": " + counts.get(s));
        }
    }
    
    public void testMostNumberVisitsByIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("../weblog2_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        System.out.println(counts);
        int getMax = la.mostNumberVisitsByIP(counts);
        System.out.println("max visits = " + getMax);
    }
    
    public void testiPsMostVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("../weblog2_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        System.out.println(counts);
        ArrayList<String> mostVisitsIP = la.iPsMostVisits(counts);
        System.out.println(mostVisitsIP);
    }
    
    public void testiPsForDays() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("../weblog3-short_log");
        HashMap<String, ArrayList<String>> map = la.iPsForDays();
        System.out.println(map);
        // System.out.println(map.size());
        // for(String s : map.keySet()) {
            // int size = map.get(s).size();
            // System.out.println(size);            
        // }
    }
    
    public void testDayWithMostIPvisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("../weblog2_log");
        HashMap<String, ArrayList<String>> IPsPerDay = la.iPsForDays();
        System.out.println(IPsPerDay);
        for(String s: IPsPerDay.keySet()) {
            int size = IPsPerDay.get(s).size();
            System.out.println("ArrayList size = " + size);
        }
        String maxDate = la.dayWithMostIPVisits(IPsPerDay);
        System.out.println("day with most IP visits = " + maxDate);        
    }
    
    public void testIPsWithMostVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("../weblog2_log");
        String day = "Sep 30";
        HashMap<String, ArrayList<String>> ipForDays = la.iPsForDays();
        System.out.println("IPs on " + day + ": " + ipForDays.get(day));
        ArrayList<String> iPsMostVisitsOnDay = la.iPsWithMostVisitsOnDay(ipForDays, day);
        System.out.println("IPs with most visits on " + day + ": " + iPsMostVisitsOnDay);
    }
}
