animals = ["bear","tiger","penguin","zebra"]
bear = animals[0]

for animal in animals:
    print animal

animals = ["bear","python","peacock","kangaroo","whale","platypus"]

i = 0
while i < len(animals):
    print "The animal is at %d and is a %s" % (i, animals[i])
    i += 1
