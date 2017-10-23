package no.fint;

import com.hazelcast.core.HazelcastInstance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Map;

@Slf4j
@EnableScheduling
@SpringBootApplication
public class Application {

    @Autowired
    private HazelcastInstance hazelcastInstance;

    private String id = String.valueOf(System.currentTimeMillis());

    @Scheduled(fixedDelay = 5000L)
    public void init() {
        Map<String, Integer> map = hazelcastInstance.getMap("test-map");
        if (!map.containsKey(id)) {
            map.put(id, 0);
        }

        int value = map.get(id);
        map.put(id, ++value);

        log.info("Node: {}, Map size: {}, content: {}", id, map.size(), map.entrySet());
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
