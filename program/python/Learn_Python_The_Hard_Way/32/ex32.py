hairs = ["brown","blond","red"]
eyes = ["brown","blue","green"]
weights = [1,2,3,4]

the_count = [1,2,3,4,5]
fruits = ["apples","orange","pears","apricots"]
change = [1,"pennies",2,"dimes",3,"quarters"]

for number in the_count:
    print "This is count %d" % number

for fruit in fruits:
    print "A fruit of type: %s" % fruit

for i in change:
    print "I got %r" % i

elements = []

elements = range(0, 6)

for i in range(0, 6):
    print "Adding %d to the list." % i
    elements.append(i)

for i in elements:
    print "Element was: %d" % i

print elements.pop(1)
print elements.pop(-1)

arrays = [[1,2,3],[4,5,6],[7,8,9]]
for array in arrays:
    for number in array:
        print number