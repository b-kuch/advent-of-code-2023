data = []

with open('in1', 'r') as f:
    for line in f:
        data.append(line)

digits = ['zero', 'one', 'two', 'three', 'four', 'five', 'six', 'seven', 'eight', 'nine']

def find_first_string(s: str, strings: [str]):
    first_idx = len(s)
    first_pattern = None
    for digit in strings:
        idx = s.find(digit)
        if idx != -1 and idx < first_idx:
            first_idx = idx
            first_pattern = digit
    return first_pattern

def to_int(s: str):
    if s.isdigit():
        return int(s)
    return int(digits.index(s))

def coordinate(s: str) -> int:
    reversed_digits = [digit[::-1] for digit in digits]

    first = find_first_string(s, digits + [str(i) for i in range(10)])
    last = find_first_string(s[::-1], reversed_digits + [str(i) for i in range(10)])
    last = last[::-1]
    return to_int(first) * 10 + to_int(last)

print(sum(coordinate(line) for line in data))
