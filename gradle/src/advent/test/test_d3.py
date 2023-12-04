from ..main.code.d3 import find_gears, part_one, part_two, sum_gear_ratios, sum_part_numbers, find_symbols, tripletwise
def test_find_gear_ratios():
    data = (
        '12...',
        '..*..',
        '...32'
    )
    assert [((1, 2), [(-1, -1), (1, 1)])] == find_gears(data)
    data = (
        '123..',
        '..*..',
        '...32'
    )
    assert [((1, 2), [(-1, 0), (1, 1)])] == find_gears(data)


def test_part_one():
    assert 520135 == part_one()


def test_part_two():
    assert None is part_two()


def test_failing_find_ratios():
    data = """.........798...145.........629....579.....455.....................130.............243.................154........167........................
............*.....*...........*...&...179.*........737...194.........*854........./...........52..560*............................699...&...
........459..489.817........880.........*..996........*....*........................................................................*.36....
""".split()
    assert ((1, 12), [(-1, -1), (1, 1)]) in find_gears(data)
    assert ((1, 18), [(-1, -1), (1, 0)]) in find_gears(data)
    assert ((1, 30), [(-1, -1), (1, 0)]) in find_gears(data)


def test_example_input_for_part_two():
    data = """467..114..
...*......
..35..633.
......#...
617*......
.....+.58.
..592.....
......755.
...$.*....
.664.598..""".split('\n')
    assert 467835 == sum_gear_ratios(data)


def test_example_input_for_part_two_short():
    data = """467..114..
...*......
..35..633.""".split('\n')
    assert 16345 == sum_gear_ratios(data)


def test_example_input():
    data = """467..114..
...*......
..35..633.
......#...
617*......
.....+.58.
..592.....
......755.
...$.*....
.664.598..""".split('\n')
    assert 4361 == sum_part_numbers(data)


def test_sum_part_numbers():
    assert 123 == sum_part_numbers(['*123...321'])


def test_find_symbols():
    data = [
        '....',
        'ZXC.',
        '...*'
    ]
    iterator = find_symbols(*data)
    current, symbol_nearby = next(iterator)
    assert current == 'Z'
    assert not symbol_nearby
    current, symbol_nearby = next(iterator)
    assert current == 'X'
    assert not symbol_nearby

    current, symbol_nearby = next(iterator)
    assert current == 'C'
    assert symbol_nearby


def test_tripletwise():
    data = 'abc'
    trio = tripletwise(data)
    first, second, third = next(trio)
    assert first is None
    assert second == 'a'
    assert third == 'b'
    first, second, third = next(trio)
    assert first == 'a'
    assert second == 'b'
    assert third == 'c'
    first, second, third = next(trio)
    assert third is None

    try:
        next(trio)
        raise AssertionError('StopIteration should have happened')
    except StopIteration:
        pass


def test_find_symbols_with_none():
    data = (None, 'abcdefg', None)
    iterator = find_symbols(*data)
    for char in data[1]:
        assert next(iterator) == (char, False)
