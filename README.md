# Propose
- MVC example
- http4s/circe/quill
- mysql

# How to
## Data source
```
cd mvc-quill/src/main/resources
mysql -uroot -p mvc_quill < ./categories.sql
mysql -uroot -p mvc_quill < ./questions.sql
mysql -uroot -p mvc_quill < ./answers.sql
```
## Run
```
sbt run
```

## Access
```
curl http://localhost:8000/quiz?category_id=15
```
