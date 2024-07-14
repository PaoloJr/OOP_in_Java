import java.util.*;
import edu.duke.*;
public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
        FileResource fr = new FileResource(filename);
        for(String line : fr.lines()) {
            LogEntry logLine = WebLogParser.parseEntry(line);
            records.add(logLine);
        }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs() {
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le : records) {
            String ipAddr = le.getIpAddress();
            if (!uniqueIPs.contains(ipAddr)) {
                uniqueIPs.add(ipAddr);
            }
        }
        return uniqueIPs.size();
     }
     
     public void printAllHigherThanNum(int num) {
        for (LogEntry le : records) {
            if(le.getStatusCode() > num) {
                System.out.println(le);
            }
        }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
        ArrayList<String> uniqueIPlog = new ArrayList<String>();
        for (LogEntry le : records) {
            String ipAddr = le.getIpAddress();
            String accessDate = le.getAccessTime().toString();
            // System.out.println(accessDate);
            // System.out.println(le.getAccessTime());
            if (!uniqueIPlog.contains(ipAddr) && accessDate.contains(someday)) {
                uniqueIPlog.add(ipAddr);
            }
        }
        return uniqueIPlog;
     }
     
     public int countUniqueIPsInRange(int low, int high) {
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for (LogEntry le : records) {
             String ipAddr = le.getIpAddress();
             int statusCode = le.getStatusCode();
             if (!uniqueIPs.contains(ipAddr) && statusCode >= low && statusCode <= high) {
                 uniqueIPs.add(ipAddr);   
             }
         }         
         return uniqueIPs.size();
     }
     
     public HashMap<String, Integer> countVisitsPerIP() {
         HashMap<String, Integer> counts = new HashMap<String, Integer>();
         for (LogEntry le : records) {
             String ip = le.getIpAddress();
             if(!counts.containsKey(ip)) {
                 counts.put(ip, 1);   
             } else {
                 counts.put(ip, counts.get(ip) + 1);   
             }
         }
         return counts;
     }
     
     public int mostNumberVisitsByIP(HashMap<String, Integer> map) {
        int max = 0;
        for(String s : map.keySet()) {
            int count = map.get(s);
            if (count > max) {
                max = count;
            }
        }
        return max;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> map) {
         ArrayList<String> IPs = new ArrayList<String>();
         int mostVisits = mostNumberVisitsByIP(map);
         for(String s : map.keySet()) {
             int count = map.get(s);             
             if (count >= mostVisits) {
                IPs.add(s);
             }
         }
         return IPs;
     }
     
     public HashMap<String, ArrayList<String>> iPsForDays() {
         HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
         for(LogEntry le : records) {
             String key = le.getAccessTime().toString().substring(4,10);
             if(!map.containsKey(key)) {
                 ArrayList<String> IPs = new ArrayList<String>();
                 IPs.add(le.getIpAddress());
                 map.put(key, IPs);
             } else {
                    ArrayList<String> currIPs = map.get(key);
                    currIPs.add(le.getIpAddress());
                    map.put(key, currIPs);                    
                }
         }
         return map;
     }
     
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> map) {
        int maxIPcount = 0;
        String maxDay = "";
        for(String s : map.keySet()) {
            int size = map.get(s).size();
            if(size > maxIPcount) {
                maxIPcount = size;
                maxDay = s;
            }
        }
        return maxDay;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> map, String day) {
        ArrayList<String> iPList = map.get(day);
        HashMap<String, Integer> iPCounts = new HashMap<String, Integer>();
        for(String s : iPList) {
            if (!iPCounts.containsKey(s)) {
                iPCounts.put(s, 1);
            } else {
                iPCounts.put(s, iPCounts.get(s) + 1);
            }
        }
        ArrayList<String> returnedIPs = iPsMostVisits(iPCounts);
        return returnedIPs;   
     }    
     }     
