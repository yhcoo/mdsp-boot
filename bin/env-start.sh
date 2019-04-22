#!/usr/bin/env bash

brew services start rabbitmq
brew services start redis
#./redis-sentinel
sudo mysql.server start

/usr/local/Cellar/nacos-saas/bin/startup.sh -m standalone


#/usr/local/Cellar/nacos-fisher/bin/startup.sh -m standalone


# 部署 Sentinel 的命令
#java -Dserver.port=8080 -Dcsp.sentinel.dashboard.server=localhost:8080 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard.jar