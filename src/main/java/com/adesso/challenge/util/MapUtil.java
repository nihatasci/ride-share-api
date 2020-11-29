package com.adesso.challenge.util;

import org.springframework.util.StringUtils;

public class MapUtil {
	
	public static String cities[][] = {
            {"a1", "a2","a3","a4","a5","a6","a7","a8","a9","a10",},
            {"b1", "b2","b3","b4","b5","b6","b7","b8","b9","b10",},
            {"c1", "c2","c3","c4","c5","c6","c7","c8","c9","c10",},
            {"d1", "d2","d3","d4","d5","d6","d7","d8","d9","d10",},
            {"e1", "e2","e3","e4","e5","e6","e7","e8","e9","e10",}
        };
	
	public static boolean isCityExists(String city) {
		if(!StringUtils.hasText(city))
			return false;
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j<10; j++) {
				if(cities[i][j].equals(city)) {
					return true;
				}
			}
		}
		return false;
	}

}
