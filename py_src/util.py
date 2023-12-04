def input_file(file_name):
    return f'../src/main/resources/input/{file_name}'


def load_file(data, file_name):
    with open(file_name, 'r') as f:
        for line in f:
            data.append(line[:-1])
    return data
