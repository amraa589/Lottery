# Lottery web

## Release
```bash
   npm run build
```
Build - с гарсан файлуудыг Spring  boot project - ийн ```src/main/resources/static``` руу хуулна
Үүний дараа Spring boot project - ийн root хавтаст дараах коммандыг ажиллуулга

```bash
.\gradlew build
```
## Heroku deployment
Gradlew build коммандаар гарсан файлыг heroku  руу доор коммандаар оруулна
```bash
   heroku war:deploy lottery-0.0.1.war --app lotteryfront
```