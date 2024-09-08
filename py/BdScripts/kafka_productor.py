import json
from kafka import KafkaProducer
from kafka.errors import KafkaError
import time
import random

producer = KafkaProducer(bootstrap_servers='master:9092,slave1:9092,slave2:9092')

topic = 'luxu'
dic = {"order_id": "1707804", "user_id": "1126", "eval_set": "prior", "order_number": "2", "order_dow": "2",
       "hour": "17", "day": "20.0"}

while True:
    msg1_keys = list(dic.keys())
    msg1_key = random.choice(msg1_keys)
    msg1 = dict([(msg1_key, dic[msg1_key])])
    producer.send(topic, json.dumps(msg1).encode())
    time.sleep(1)
    print(msg1)

producer.close()
