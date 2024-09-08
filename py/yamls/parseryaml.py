import json,yaml

test_yaml_file="config.yaml"


with open(test_yaml_file, 'r', encoding='utf-8') as f:
    file_content = f.read()
content = yaml.load(file_content, yaml.FullLoader)
print(content)