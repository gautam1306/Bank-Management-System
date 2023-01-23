package com.bank;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class Routine
 *
 */
@WebListener
public class Routine implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public Routine() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }
    public void contextInitialized(ServletContextEvent sce)  { 
    	ScheduledExecutorService scheduler =Executors.newSingleThreadScheduledExecutor();
    	LocalDateTime now=LocalDateTime.now();
    	long time =60*60*24-now.getHour()*60*60-now.getMinute()*60-now.getSecond();
    	scheduler.scheduleAtFixedRate(new DailyRoutine(),time,86400 ,TimeUnit.SECONDS);
    	LocalDate today = LocalDate.now();
    	LocalDate endOfMonth = today.withDayOfMonth(today.lengthOfMonth());
    	long daysBetween = ChronoUnit.DAYS.between(today, endOfMonth);
    	scheduler.scheduleAtFixedRate(new MonthlyRoutine(), daysBetween, 30, TimeUnit.DAYS);
    }
	
}
