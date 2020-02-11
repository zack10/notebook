package com.oracle.notebook;

import com.oracle.notebook.controller.NoteBookController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextClosedEvent;

@SpringBootApplication
public class NotebookApplication {

	private static Logger logger = LoggerFactory.getLogger(NotebookApplication.class);

	/**
	 * @param args
	 * @return
	 */
	public static ConfigurableApplicationContext startApplication(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(NotebookApplication.class, args);
		ctx.addApplicationListener((ContextClosedEvent arg0) -> ctx.close());
		return ctx;
	}

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = NotebookApplication.startApplication(args);
		if (ctx.isActive() && logger.isInfoEnabled()) {
			logger.info("NotebookApplication started successfully");
		}
	}

}
