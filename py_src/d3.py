from itertools import chain, cycle
from typing import Iterable, Iterator

from py_src.util import load_file
from util import input_file


def part_one():
    data = load_data()
    return sum_part_numbers(data)


def part_two():
    data = load_data()
    return sum_gear_ratios(data)


def load_data():
    data = []
    file_name = input_file('in3')
    return load_file(data, file_name)


def tripletwise(iterable: Iterable):
    if iterable is None:
        return cycle([None])
    a, b, c = chain([None], iterable), iterable, chain(iterable[1:], [None])
    return zip(a, b, c)


symbols = '=-@%/+&$*#'


def find_symbols(prev: str, curr: str, nxt: str) -> Iterator[tuple[str, bool]]:
    for window in zip(tripletwise(prev), tripletwise(curr), tripletwise(nxt)):
        current = window[1][1]
        symbol_in_window = False
        for line in window:
            if line is not None:
                for char in line:
                    if char is not None and char in symbols:
                        symbol_in_window = True
        yield current, symbol_in_window


def sum_part_numbers(data: [str]) -> int:
    part_numbers_sum = 0

    current_number = []
    is_current_number_part = False
    for three_rows in tripletwise(data):
        for current, symbol_nearby in find_symbols(*three_rows):
            if current.isdigit():
                current_number.append(current)
                if symbol_nearby:
                    is_current_number_part = True
            else:
                if is_current_number_part and len(current_number) > 0:
                    part_numbers_sum += int(''.join(current_number))
                current_number = []
                is_current_number_part = False
    return part_numbers_sum


def find_nearby_numbers(prev, curr, nxt):
    for window in zip(tripletwise(prev), tripletwise(curr), tripletwise(nxt)):
        current = window[1][1]
        if current == '*':
            yield find_in_window(window)
        yield []


directions = [
    (-1, -1),
    (-1, 0),
    (-1, 1),
    (0, -1),
    (0, 1),
    (1, -1),
    (1, 0),
    (1, 1),
]


def find_in_window(window):
    nearby_digits = []
    for direction in directions:
        if (
                window[1 + direction[0]] is not None and
                window[1 + direction[0]][1 + direction[1]] is not None and
                window[1 + direction[0]][1 + direction[1]].isdigit()):
            nearby_digits.append(direction)

    if (-1, 0) in nearby_digits:
        if (-1, -1) in nearby_digits:
            nearby_digits.remove((-1, -1))
        if (-1, 1) in nearby_digits:
            nearby_digits.remove((-1, 1))
    if (1, 0) in nearby_digits:
        if (1, -1) in nearby_digits:
            nearby_digits.remove((1, -1))
        if (1, 1) in nearby_digits:
            nearby_digits.remove((1, 1))

    return nearby_digits


def my_enumerate(x):
    for y in enumerate(x):
        print(f'in enumerate {y}')
        yield y


def scan_rows(row_number, prv_row, cur_row, nxt_row):
    locations = []
    for column_number, (prv, cur, nxt) in enumerate(
            zip(tripletwise(prv_row), tripletwise(cur_row), tripletwise(nxt_row))):
        if cur[1] == '*':
            gear_numbers = find_in_window([prv, cur, nxt])
            if len(gear_numbers) == 2:
                locations.append(((row_number, column_number), gear_numbers))
            if len(gear_numbers) > 2:
                raise ValueError('EEEEEE')
    return locations


def find_gears(data: [str]) -> list:
    gears = []
    for idx, three_rows in enumerate(tripletwise(data)):
        new_gears = scan_rows(idx, *three_rows)
        gears.extend(new_gears)
    return gears


def sum_gear_ratios(data: [str]) -> int:
    gears = find_gears(data)

    gear_ratios_sum = 0
    for gear in gears:
        gear_ratios_sum += find_ratio(data, gear)
    return gear_ratios_sum


def find_ratio(data: [str], gear):
    gear_position, dirs = gear
    ratio = 1
    for direction in dirs:
        number_line, number_start = gear_position[0] + direction[0], gear_position[1] + direction[1]
        number_end = gear_position[1] + direction[1]

        while number_start > 0 and data[number_line][number_start - 1].isdigit():
            number_start -= 1

        while number_end + 1 < len(data[0]) and data[number_line][number_end + 1].isdigit():
            number_end += 1

        ratio *= int(data[number_line][number_start:number_end + 1])
    return ratio
