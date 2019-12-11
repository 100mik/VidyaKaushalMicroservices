# docker-logging-fluentd
docker-compose starter for fluentd with EFK Stack for Docker logging

## Environment Variables
Elasticsearch & Kibana versions are set to 6.0.0

You may alter these versions on the .env file

## Deployment

Contents of Fluentd configuration file in_docker.conf 
```
<source>
  @type forward
  port 24224
  bind 0.0.0.0
</source>

<match **>
  @type copy
  <store>
    @type elasticsearch
    host localhost
    port 9200
    logstash_format true
    logstash_prefix logstash
    logstash_dateformat %Y-%m-%d
    include_tag_key true
  </store>
  <store>
    @type stdout
  </store>
</match>
```

Start fluentd Agent with

```
fluentd -c <path_to_fluentd_config_file>\in_docker.conf
```

Start the compose with
```
docker-compose up
```
