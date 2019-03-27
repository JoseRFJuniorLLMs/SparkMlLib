package com.raul.ML.SparkMlLib.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix="spark")
@Data
public class SparkConfigurations {

	private Map<String,String> config = new HashMap<>();
}
