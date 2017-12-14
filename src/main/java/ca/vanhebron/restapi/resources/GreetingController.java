package ca.vanhebron.restapi.resources;

import ca.vanhebron.restapi.models.Greeting;
import ca.vanhebron.restapi.models.Hosting;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * Created by rocky.lee on 2017-11-28.
 */

@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@RequestMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {

		List<Hosting> list = new ArrayList<>();
		list.add(new Hosting(1, "rocky.com", 800000));
		list.add(new Hosting(2, "22222rocky.com", 900000));

		Map<Integer, String> result1 = list.stream().collect(
				Collectors.toMap(Hosting::getId, Hosting::getName));

		Map<String, Long> result2 = list.stream().collect(
				Collectors.toMap(Hosting::getName, Hosting::getWebsite, (oldValue, newValue) -> newValue));

		Map<Integer, String> result3 = list.stream().collect(
				Collectors.toMap(x -> x.getId(), x -> x.getName()));



		List<String> listtest = Arrays.asList("test","java","eeweee");
		String resu333lt2 = String.join(", ",listtest);



		return new Greeting(counter.incrementAndGet(),
				String.format(template, name));
	}

}
