package app.labs.linksy.Util;  // 패키지 경로 설정

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class HashtagExtractor {
    public static List<String> extractHashtags(String content) {
        List<String> hashtags = new ArrayList<>();
        Pattern pattern = Pattern.compile("#(\\w+)");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            hashtags.add(matcher.group(1)); // 해시태그 텍스트만 추출
        }
        return hashtags;
    }
}

