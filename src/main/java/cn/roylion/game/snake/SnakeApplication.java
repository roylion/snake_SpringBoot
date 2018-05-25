package cn.roylion.game.snake;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SnakeApplication {

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(SnakeApplication.class);
		ApplicationContext context = builder.headless(false).run(args);

		SnakeJPanel snakeJPanle = context.getBean("jpanel", SnakeJPanel.class);
		snakeJPanle.init();
	}
}
