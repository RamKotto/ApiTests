package utils;

import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import pojo.user.UserRequest;

public class UserGenerator {

    public static Supplier<UserRequest> generateUserRequest = () -> UserRequest.builder()
        .name(generateWord(10))
        .job(generateWord(10))
        .build();

    public static String generateWord(int length) {
        StringBuilder sb = new StringBuilder();
        IntStream.range(0, length).forEach(l -> {
            int i = new Random().nextInt(26);
            char letter = (char) (97 + i);
            sb.append(letter);
        });
        return sb.toString();
    }
}
