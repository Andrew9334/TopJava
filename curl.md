#### curl sampling

### get All Meals
curl -XGET -H "Content-type: application/json" http://localhost:8080/topjava/rest/meals

### get Meals With ID=100009
curl -XGET -H "Content-type: application/json" http://localhost:8080/topjava/rest/meals/100009

### delete Meal With ID=100009
curl -XDELETE -H "Content-type: application/json" http://localhost:8080/topjava/rest/meals/100009

### update Meal With ID=100008
curl -XPUT -H "Content-type: application/json" -d '{ "id": 100008, "dateTime": "2024-01-21T12:00:00", "description": "Updated dinner", "calories": 800, "user": null }' 'http://localhost:8080/topjava/rest/meals/100008'

### create New Meal
curl -XPOST -H "Content-type: application/json" -d '{ "dateTime": "2024-01-21T09:00:00", "description": "Created Breakfast", "calories": 200, "user": null }' 'http://localhost:8080/topjava/rest/meals'

### filter Meals By Date And Time
curl -XGET -H "Content-type: application/json" 'http://localhost:8080/topjava/meals/filter?startDate=2024-01-21&endDate=2024-01-21&startTime=&endTime='