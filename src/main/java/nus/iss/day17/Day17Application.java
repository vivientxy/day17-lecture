package nus.iss.day17;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import nus.iss.day17.model.GiphyImage;
import nus.iss.day17.service.GiphyService;

@SpringBootApplication
public class Day17Application implements CommandLineRunner {

	@Autowired
	GiphyService giphyService;

	public static void main(String[] args) {
		SpringApplication.run(Day17Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// List<GiphyImage> images = giphyService.search("polar bear");
		// System.out.println(images.toString());
		// System.exit(0);
	}

}
