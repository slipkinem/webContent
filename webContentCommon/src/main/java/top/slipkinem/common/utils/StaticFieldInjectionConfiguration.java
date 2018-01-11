package top.slipkinem.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by slipkinem on 1/11/2018.
 */
@Component
public class StaticFieldInjectionConfiguration {
    @Autowired
    MessageSource messageSource;

    @PostConstruct
    private void init () {
        System.out.println("---StaticFieldInjectionConfiguration---");
        CheckUtil.setMessageSource(messageSource);
    }
}
