# Curl sampling

* Use bash for Windows

#### 1. *Get All Meals*
```bash
curl -XGET -H "Content-type: application/json"
http://localhost:8080/topjava/rest/meals
```

#### 2. *Get meals with id=100009*
```bash
curl -XGET -H "Content-type: application/json" 
http://localhost:8080/topjava/rest/meals/100009
```

#### 3. *Delete meal with id=100009*
```bash
curl -XDELETE -H "Content-type: application/json" 
http://localhost:8080/topjava/rest/meals/100009
```

#### 4. *Update meal with id=100008*
```bash
curl -XPUT -H "Content-type: application/json" -d '{ "id": 100008, "dateTime": "2024-01-21T12:00:00", "description": "Updated dinner", "calories": 800, "user": null }' 
http://localhost:8080/topjava/rest/meals/100008
```

#### 5. *Create new meal*
```bash
curl -XPOST -H "Content-type: application/json" -d '{ "dateTime": "2024-01-21T09:00:00", "description": "Created Breakfast", "calories": 200, "user": null }' 
http://localhost:8080/topjava/rest/meals
```

#### 6. *Filter meals by date and time*
```bash
curl -XGET -H "Content-type: application/json" 
"http://localhost:8080/topjava/meals/filter?startDate=2024-01-21&endDate=2024-01-21&startTime=&endTime="
```