# Kubernetes Minion Example

## Docker

### Build
$ docker build -t pauloneves/minion:0.0.1 .

### Run
$ docker run -p 8200:8080 -d --name minion  pauloneves/minion:0.0.1