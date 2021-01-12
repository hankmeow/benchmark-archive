package com.hankmew.benchmark.retrofit.dnspod;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class InfoVersionRes {
    private Status status;

    @Data
    public static class Status {
        private String code;
        private String message;
        @JsonProperty("created_at")
        private String createdAt;
    }
}
