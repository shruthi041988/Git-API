package resources;

public enum APIResources {
	
	AddPlaceAPI("/maps/api/place/add/json"),
	getPlaceAPI("/maps/api/place/get/json"),
	deletePlaceAPI("/maps/api/place/delete/json");
	
	private String resource;

	APIResources(String resource)
	{
		
		this.resource=resource;
	}
	
	public String getResource()
	{
		
		return resource;
	}

}
// enum is a collection of constants or methods