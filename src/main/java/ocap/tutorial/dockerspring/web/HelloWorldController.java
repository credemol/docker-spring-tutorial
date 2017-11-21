package ocap.tutorial.dockerspring.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path="/helloworld")
public class HelloWorldController {

	@RequestMapping(method=RequestMethod.GET, path="/simple")
	public @ResponseBody String 
		sayHello(@RequestParam(name="name", defaultValue="World") String name) {
		
		String msg = "Hello " + name;
		return "<h1>" + msg  + "</h1>";
	}
	
/*	
	@GetMapping("/mvc")
	public String 
		sayHelloMVC(@RequestParam(name="name", defaultValue="World") String name,
				Model model) {
		System.out.println("MVC");
		String msg = "Hello " + name;
		model.addAttribute("msg", msg);
		
		return "helloworldmvc";
	}
*/	
}
