rest/meals
curl -XGET -H "Content-type: application/json" http://localhost:8080/topjava/rest/meals

rest/meals/100009
curl -XGET -H "Content-type: application/json" http://localhost:8080/topjava/rest/meals/100009

rest/meals/delete
curl -XDELETE -H "Content-type: application/json" http://localhost:8080/topjava/rest/meals/100009

rest/meals/update
curl -XPUT -H "Content-type: application/json" -d '{ "id": 100008, "dateTime": "2024-01-21T12:00:00", "description": "Updated dinner", "calories": 800, "user": null }' 'http://localhost:8080/topjava/rest/meals/100008'

rest/meals/create
curl -XPOST -H "Content-type: application/json" -d '{ "dateTime": "2024-01-21T09:00:00", "description": "Created Breakfast", "calories": 200, "user": null }' 'http://localhost:8080/topjava/rest/meals'

rest/meals/filter
curl -XGET -H "Content-type: application/json" 'http://localhost:8080/topjava/meals/filter?startDate=2024-01-21&endDate=2024-01-21&startTime=&endTime='