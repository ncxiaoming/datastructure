package algorithm;

import java.util.*;

/**
 * @author: liming
 * @Date: 2020/10/28 15:19
 * @Description: 贪心算法
 */

public class GreedyAlgorithm {

    public static void main(String[] args) {

        Map<String, Set<String>> broadcastingStation = getBroadcastingStation();

        Set<String> allCity = getAllCity(broadcastingStation);

        Set<String> strings = greedyAlgorithm(broadcastingStation, allCity);

        for (String string : strings) {
            System.out.println(string);
        }

    }

    public static Set<String> greedyAlgorithm(Map<String, Set<String>> map, Set<String> allCity) {


        Set<Map.Entry<String, Set<String>>> entries = map.entrySet();

        Set<String> result = new TreeSet<>();

        while (!allCity.isEmpty()) {

            String maxNext = null;

            for (Map.Entry<String, Set<String>> entry : entries) {

                Set<String> tempSet = new TreeSet<>(entry.getValue());

                tempSet.retainAll(allCity);

                if (tempSet.size() > 0 && (maxNext == null || tempSet.size() > map.get(maxNext).size())) {
                    maxNext = entry.getKey();
                }

            }

            if (maxNext != null) {
                result.add(maxNext);

                allCity.removeAll(map.get(maxNext));
            }

        }

        return result;
    }

    public static Map<String, Set<String>> getBroadcastingStation() {

        Set<String> k1 = new TreeSet<>();
        k1.add("北京");
        k1.add("上海");
        k1.add("深圳");


        Set<String> k2 = new TreeSet<>();
        k2.add("北京");
        k2.add("上海");
        k2.add("深圳");

        Set<String> k3 = new TreeSet<>();
        k3.add("成都");
        k3.add("上海");
        k3.add("杭州");

        Set<String> k4 = new TreeSet<>();
        k4.add("上海");
        k4.add("天津");

        Set<String> k5 = new TreeSet<>();
        k5.add("杭州");
        k5.add("大连");

        Map<String, Set<String>> map = new HashMap<>(8);
        map.put("k1", k1);
        map.put("k2", k2);
        map.put("k3", k3);
        map.put("k4", k4);
        map.put("k5", k5);

        return map;
    }

    public static Set<String> getAllCity(Map<String, Set<String>> map) {
        Set<String> allCity = new TreeSet<>();

        Set<Map.Entry<String, Set<String>>> entries = map.entrySet();
        for (Map.Entry<String, Set<String>> entry : entries) {
            allCity.addAll(entry.getValue());
        }

        return allCity;
    }
}
