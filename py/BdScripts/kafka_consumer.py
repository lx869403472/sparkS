from kafka import KafkaConsumer
import json

'''
5     消费者demo
6     消费test_lyl2主题中的数据
7     注意事项：如需以json格式读取数据需加上value_deserializer参数
8 '''

consumer = KafkaConsumer('luxu',
                         # group_id='tmp',
                         bootstrap_servers='master:9092')

for message in consumer:
    print(message.value)
