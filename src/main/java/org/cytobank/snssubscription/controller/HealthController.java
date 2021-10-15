package org.cytobank.snssubscription.controller;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/health")
@Log4j2
public class HealthController {

    @Autowired
    private HttpServletRequest request;

    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKey;
    @Value("${cloud.aws.credentials.secretKey}")
    private String secretKey;
    @Value("${cloud.aws.region.static}")
    private String region;

    private MemoryInfo memoryInfo = new MemoryInfo();

    @GetMapping("/clear")
    public int clearCount() {
        return RequestCounterInteger.clear();
    }

    @GetMapping("/count")
    public int countRequest() {
        return RequestCounterInteger.showCount();
    }

    @SneakyThrows
    @GetMapping("/long-req")
    public void longRequest() {
        log.info("request {} started", RequestCounterInteger.count());
        TimeUnit.HOURS.sleep(1);
    }

    @SneakyThrows
    @GetMapping("/long-req-1")
    public void longRequest1() {
        String t = request.getParameter("t");
        if(Objects.nonNull(t)) {
            log.info("request map {} started", RequestCounterMap.count(t));
        } else {
            return;
        }
        TimeUnit.HOURS.sleep(1);
    }

    @GetMapping("/clear-map")
    public int clearCountMap() {
        return RequestCounterMap.clear();
    }


    @GetMapping("/count-map")
    public Map<String, Integer> countRequestMap() {
        return RequestCounterMap.showCount();
    }

    @GetMapping("/metadata")
    public Metadata getMetadata() {
        log.info("Health checking");
        Metadata metadata = new Metadata();
        metadata.setRegion(region);
        metadata.setAccessKey(accessKey);
        metadata.setSecretKey(secretKey);
        return metadata;
    }

    @GetMapping("/info")
    public MemoryInfo info() {
        return memoryInfo.info();
    }

    @Data
    private class Metadata {
        private String accessKey;
        private String secretKey;
        private String region;
    }

}
