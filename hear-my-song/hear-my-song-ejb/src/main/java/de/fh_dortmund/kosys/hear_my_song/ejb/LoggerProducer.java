package de.fh_dortmund.kosys.hear_my_song.ejb;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerProducer {

    @Produces
    public Logger getLogger(InjectionPoint injectionPoint) {
        Class<?> clazz;
        if (injectionPoint.getBean() != null) {
            clazz = injectionPoint.getBean().getBeanClass();
        } else {
            clazz = injectionPoint.getMember().getDeclaringClass();
        }
        return LoggerFactory.getLogger(clazz);
    }
}