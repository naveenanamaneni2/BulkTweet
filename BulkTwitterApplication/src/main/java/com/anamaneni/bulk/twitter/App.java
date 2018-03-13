package com.anamaneni.bulk.twitter;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    @SuppressWarnings("resource")
	public static void main( String[] args )
    {
        new ClassPathXmlApplicationContext("application.xml");
    }
}
