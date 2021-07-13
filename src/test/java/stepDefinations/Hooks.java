package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	@Before("@DeletePlace")
	
	public void beforeScenario() throws IOException
	{
	 stepDefinition step=new stepDefinition();
	 if (stepDefinition.place_id==null)
	 {
	 step.add_place_payload("Shree", "English", "12Lamington");
	 step.user_calls_with_post_http_request("AddPlaceAPI", "Post");
	 step.verify_if_the_place_id_created_maps_to_using("Shree", "getPlaceAPI");
	 }
		
  }

}
