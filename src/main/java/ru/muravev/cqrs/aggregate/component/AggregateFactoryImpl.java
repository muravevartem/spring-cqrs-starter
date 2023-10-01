package ru.muravev.cqrs.aggregate.component;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import ru.muravev.cqrs.AggregateRoot;

@Component
public class AggregateFactoryImpl implements ProjectFactory, ApplicationContextAware {
    private ApplicationContext applicationContext;


    @Override
    public <T extends AggregateRoot> T create(Class<T> tClass) {
        return applicationContext.getBean(tClass);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
