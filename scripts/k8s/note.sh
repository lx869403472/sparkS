#!/bin/zsh

# 断口映射 累死docker -p
kubectl port-forward test-at-cc88cc7cd-5xncv 8081:8080

kubectl get deployment test-at-cc88cc7cd-5xncv -o yaml >> app.yaml

# Deployment 适合无状态的应用，与pod等价，可替代
# StatefuSet 有状态应用，适合数据库这类应用
# DaemonSet  在每一个节点上跑一个Pod,适合做节点监控，节点日志收集等
# Job & CronJob Job是用来表达一次的任务，而CronJob会根据其时间规划反复运行
#