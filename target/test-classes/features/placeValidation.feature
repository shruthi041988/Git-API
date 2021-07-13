Feature: Validating place API's

@AddPlace @Regression
Scenario Outline: Verify if place is successfully added using AddPlaceAPI
		Given Add Place Payload "<name>" "<language>" "<address>"
		When User calls "AddPlaceAPI" with "Post" http request 
		Then the API call is successful with success code 200
		And "status" in response body is "OK"
		And "scope" in response body is "APP"
		And verify if the place_id created maps to "<name>" using "getPlaceAPI"

Examples:
	|name   | language |       address           |
	|AAhouse| English  |   World class centre    |
#	|BBhouse| Spanish  |	Sea cross centre     |

@DeletePlace @Regression
Scenario: Verify delete place functionality is working
		Given Delete Place Payload 
		When User calls "deletePlaceAPI" with "Post" http request 
		Then the API call is successful with success code 200
		And "status" in response body is "OK"
		
		