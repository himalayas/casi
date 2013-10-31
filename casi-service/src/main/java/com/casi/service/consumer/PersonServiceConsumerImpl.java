package com.casi.service.consumer;

import com.casi.service.PersonService;

/**
 * User: Think
 * Date: 13-10-31
 * Time: 下午11:24
 */
public class PersonServiceConsumerImpl implements PersonServiceConsumer {
    PersonService ps;
    @Override
    public String getPerson(String name) {
        String str;
        try {
            return ps.getPersion(name);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public PersonService getPs() {
        return ps;
    }

    public void setPs(PersonService ps) {
        this.ps = ps;
    }
}
