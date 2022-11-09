package br.com.liferay.liferaypdbackend.services.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Utility class to ease up spring boot log utilization
 */
@Component
public class ConsoleLogUtil {
    //region VARIABLES
    public static final Logger log = LoggerFactory.getLogger(ConsoleLogUtil.class);
    //endregion
}
