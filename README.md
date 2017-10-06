Domala Play Sample
------------------

### Run

```sh
sbt run
```

```sh
#DB create
curl http://localhost:9000/@evolutions/apply/default?redirect=/persons

#select All
curl http://localhost:9000/persons

#select by ID
curl http://localhost:9000/persons/1

#insert
curl -X POST -H "Content-Type: application/json" -d '{"name":"WARD","age":20,"address":{"city":"Fukuoka","street":"Gion"}}' http://localhost:9000/persons

#update
curl -X PUT -H "Content-Type: application/json" -d '{"id":1,"name":"SMITH","age":30,"address":{"city":"Tokyo","street":"Marunouchi"},"version":0}' http://localhost:9000/persons

#delete
curl -X DELETE  http://localhost:9000/persons/2

```
