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

// random result
{"questions":[{"id":307,"text":"Which of the following former Yugoslavian states is landlocked?","possibleAnswer":[{"id":1148,"text":"Bosnia and Herzegovina"},{"id":1149,"text":"Croatia"},{"id":1150,"text":"Montenegro"},{"id":1151,"text":"Serbia"}]},{"id":306,"text":"Enzo Ferrari was originally an auto racer for what manufacturer before founding his own car company?","possibleAnswer":[{"id":1144,"text":"Bentley"},{"id":1145,"text":"Auto Union"},{"id":1146,"text":"Alfa Romeo"},{"id":1147,"text":"Mercedes Benz"}]},{"id":338,"text":"What three movies, in order from release date, make up the &quot;Dollars Trilogy&quot;?","possibleAnswer":[{"id":1256,"text":"&quot;A Fistful of Dollars&quot;, &quot;For a Few Dollars More&quot;, &quot;The Good, the Bad, and the Ugly&quot;"},{"id":1257,"text":"&quot;For a Few Dollars More&quot;, &quot;The Good, the Bad, and the Ugly&quot;, &quot;A Fistful of Dollars&quot;"},{"id":1258,"text":"&quot;The Good, the Bad, and the Ugly&quot;, &quot;For A Few Dollars More&quot;, &quot;A Fistful of Dollars&quot;"},{"id":1259,"text":"&quot;For a Few Dollars More&quot;, &quot;A Fistful of Dollars&quot;, &quot;The Good, the Bad, and the Ugly&quot;"}]},{"id":318,"text":"What company develops the Rock Band series of rhythm games?","possibleAnswer":[{"id":1188,"text":"Electronic Arts"},{"id":1189,"text":"Harmonix"},{"id":1190,"text":"Activision"},{"id":1191,"text":"Konami"}]},{"id":329,"text":"Which operation in &quot;Tom Clancy&#039;s Rainbow Six Siege&quot; introduced the &quot;Skyscraper&quot; map?","possibleAnswer":[{"id":1226,"text":"Red Crow"},{"id":1227,"text":"Skull Rain"},{"id":1228,"text":"Velvet Shell"},{"id":1229,"text":"Dust Line"}]},{"id":314,"text":"Generally, which component of a computer draws the most power?","possibleAnswer":[{"id":1172,"text":"Power Supply"},{"id":1173,"text":"Video Card"},{"id":1174,"text":"Processor"},{"id":1175,"text":"Hard Drive"}]},{"id":304,"text":"What was the world&#039;s first video game?","possibleAnswer":[{"id":1136,"text":"Spacewar!"},{"id":1137,"text":"Tennis for Two"},{"id":1138,"text":"Pong"},{"id":1139,"text":"Space Travel"}]},{"id":325,"text":"Who created Ultron of Earth-616?","possibleAnswer":[{"id":1214,"text":"Reed Richards"},{"id":1215,"text":"Henry Pym"},{"id":1216,"text":"Tony Stark"},{"id":1217,"text":"Amadeus Cho"}]},{"id":342,"text":"Why was The Green Monster at Fenway Park was originally built?","possibleAnswer":[{"id":1272,"text":"To make getting home runs harder."},{"id":1273,"text":"To provide extra seating."},{"id":1274,"text":"To prevent viewing games from outside the park."},{"id":1275,"text":"To display advertisements."}]},{"id":315,"text":"How many cores does the Intel i7-6950X have?","possibleAnswer":[{"id":1176,"text":"4"},{"id":1177,"text":"8"},{"id":1178,"text":"10"},{"id":1179,"text":"12"}]}]}
```
