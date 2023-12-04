from collections import Counter
from functools import reduce

class Game:
    def __init__(self, game_str: str) -> None:
        
        self.game, self.rounds_str = game_str.split(':')
        
        self.id = int(self.game[4:])
        self.rounds = self.split_rounds()
        self.summary = sum(self.rounds, Counter())
    
    def split_rounds(self):
        rounds = []
        for round in self.rounds_str.split(';'):
            rounds.append(Counter({
            color.split()[1]: int(color.split()[0]) for color in round.split(',')
        }))
        return rounds

    def is_possible_with(self, bag: dict):
        return all(all(round[color] <= color_count for color, color_count in bag.items()) for round in self.rounds)

    def color_power_set(self):
        result = self.rounds[0].copy()
        for round in self.rounds:
            for color, count in round.items():
                result[color] = max(result[color], count)

        return reduce(lambda a, b: a*b, result.values())

if __name__=='__main__':
    data = []
    with open('../src/main/resources/input/in2', 'r') as f:
        for line in f:
            data.append(line)

    games = [Game(datum) for datum in data]
    bag = {'red': 12, 'green': 13, 'blue': 14}
    possible_games = [game for game in games if game.is_possible_with(bag)]
    id_sum = sum(game.id for game in possible_games)
    print(id_sum)

    print(sum(game.color_power_set() for game in games))