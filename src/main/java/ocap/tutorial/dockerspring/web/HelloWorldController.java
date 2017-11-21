package ocap.tutorial.dockerspring.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/helloworld")
public class HelloWorldController {

	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody String 
		sayHello(@RequestParam(name="name", defaultValue="World") String name) {
		
		String msg = "Hello " + name;
		return "<h1>" + msg  + "</h1>";
	}
}
