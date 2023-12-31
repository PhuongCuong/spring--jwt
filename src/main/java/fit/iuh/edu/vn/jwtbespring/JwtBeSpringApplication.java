package fit.iuh.edu.vn.jwtbespring;

import fit.iuh.edu.vn.jwtbespring.modules.Group;
import fit.iuh.edu.vn.jwtbespring.responsitory.GroupResponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class JwtBeSpringApplication {

	@Autowired
	private GroupResponsitory groupResponsitory;

	public static void main(String[] args) {
		SpringApplication.run(JwtBeSpringApplication.class, args);
	}

//	@Bean
	CommandLineRunner commandLineRunner(){
		return args -> {
			List<Group> groups = new ArrayList<>();

			Group group1 = new Group("user","user");
			Group group2 = new Group("admin","admin");

			groups.add(group1);
			groups.add(group2);
			for (Group group:groups
				 ) {
				groupResponsitory.save(group);
			}

		};
	}

}
