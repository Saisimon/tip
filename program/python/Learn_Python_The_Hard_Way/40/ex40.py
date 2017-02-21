# -*- coding: utf-8 -*-
class MyStuff(object):
    def __init__(self):
        self.tangerine = "And now a thousand years between"

    def apple(self):
        print "I AM CLASSY APPLES!"

thing = MyStuff()
thing.apple()
print thing.tangerine

class Song(object):
    def __init__(self, lyrics):
        self.lyrics = lyrics

    def sing_me_a_song(self):
        for line in self.lyrics:
            print line
        print ""

happy_bday = Song(
    [
        "Happy birthday to you",
        "I don't want to get sued",
        "So I'll stop right there"
    ]
)

bull_on_parade = Song(
    [
        "They rally around the family",
        "With pockets full of shells"
    ]
)

cute_woman_lyrics = [
    "想要有直升机",
    "想要和你飞到宇宙去",
    "想要和你融化在一起",
    "融化在银河里",
    "我每天每天每天在想想想想着你",
    "这样的甜蜜",
    "让我开始相信命运",
    "感谢地心引力",
    "让我碰到你",
    "漂亮的让我面红的可爱女人",
    "温柔的让我心疼的可爱女人",
    "透明的让我感动的可爱女人",
    "坏坏的让我疯狂的可爱女人",
    "漂亮的让我面红的可爱女人",
    "温柔的让我心疼的可爱女人",
    "透明的让我感动的可爱女人",
    "坏坏的让我疯狂的可爱女人"
]
jay = Song(cute_woman_lyrics)

happy_bday.sing_me_a_song()
bull_on_parade.sing_me_a_song()
jay.sing_me_a_song()