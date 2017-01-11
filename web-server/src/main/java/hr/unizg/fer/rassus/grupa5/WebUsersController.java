package hr.unizg.fer.rassus.grupa5;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/users/*")
public class WebUsersController {

	@Autowired
	protected WebUserService usersService;
	
	public WebUsersController(WebUsersServiceImpl usersService) {
		this.usersService = usersService;
	}
	
	@RequestMapping(value = "/active", method = RequestMethod.GET)
	public String activeUsers(Model model) {
		List<User> users = new ArrayList<User>();
//		users = usersService.getAll();
		for (int i = 0; i < 4; ++i) {
			User user = new User();
			user.setFirstName("Probnoime"); //Ideja je dohvatiti ime psa preko dogs servisa za dog ID - webDogsService.findNameById(dogId)
			user.setLastName("probnoprezime"); //Isto tako dohvatiti ime vlasnika za owner ID - webPeopleService.findNameById(ownerId)
			user.setNumOfDogs(i);
			user.setUsername("korisnickoime");
			users.add(user);
		}
		model.addAttribute("user", new User());
		model.addAttribute("activeusers", users);
		return "active-users";
	}
		
	
	@RequestMapping("/{userId}")
	public String byUser(Model model, @PathVariable("userId") Long Id){
		User user = usersService.findById(Id);
//		User user = new User();
//		user.setId(Id);
		model.addAttribute("users", user);
		return "users-list";
		
	}
	
//	@RequestMapping(path="/users/{id}", method=RequestMethod.GET,
//			produces = {MediaType.APPLICATION_JSON_VALUE})
//	public User getUserById(@PathVariable long id){
//		return usersService.findById(id);
//	}
}
