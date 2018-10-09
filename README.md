# metrics
На данный момент реализовано: 
 - сохранение метрик по пути: localhost:8080/api/v1/metrics/post
 - получение, сохраненных метрик, по пути: localhost:8080/api/v1/metrics/get/{metric name}/{count}
 - получение трех последних метрик по пути: localhost:8080/api/v1/metrics/get/{metric name}
Поддерживаемые метрики: "cpu", "hdd", "ssd", "memory", "taskcount". Так же можно сделать запрос по всем метрикам, подставив вместо имени метрики "all".

Для тестирования функционала необходимо:
 - Создать локальную PostgreSQL базу "metrics" с username: postgres, password: postgres
 - Собрать проект, используя Maven, c помощью команды "install"
 - Запустить в IDE MetricsApplication
 - Используя REST API, указанный выше, выполнить POST request, например, с примером ниже:
 {
"metrics": [
 { 
"name": "cpu",
"value": "86.98",
"params": "AMD A8 4500M APU with Radeon™ HD Graphics",
"timestamp": "2018-09-21T17:32:30.354+03:00",
"requestTimestamp": "2018-09-21T17:32:30.354+03:00"
 },
 {
"name": "memory",
"value": "78.9",
"params": "Slot1: SO-DIMM DDR3L 1600 MHz 4GiB",
"timestamp": "2018-09-21T17:32:30.354+03:00",
"requestTimestamp": "2018-09-21T17:32:30.354+03:00"
},
{
"name": "hdd",
"value": "36.6",
"params": "TOSHIBA MQ 01ABF050",
"timestamp": "2018-09-21T17:32:30.354+03:00",
"requestTimestamp": "2018-09-21T17:32:30.354+03:00"
 },
 {
"name": "ssd",
"value": "85",
"params": "KINGSTON MLG1267",
"timestamp": "2018-09-21T17:32:30.354+03:00",
"requestTimestamp": "2018-09-21T17:32:30.354+03:00"
},
{
"name": "taskCount",
"value": "12",
"timestamp": "2018-09-21T17:32:30.354+03:00",
"requestTimestamp": "2018-09-21T17:32:30.354+03:00"
   }
  ]
}
 - Поля name, value, requestTimestamp являются обязательными
 - После этого можно выполнить GET request для получения метрик, как из REST API выше
 
 В дальнейшем добавлю Swagger.
