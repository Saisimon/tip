# -*- coding: utf-8 -*-
import hashmap

states = hashmap.new()
hashmap.set(states, 'Oregon', 'OR')
hashmap.set(states, 'Florida', 'FL')
hashmap.set(states, 'California', 'CA')
hashmap.set(states, 'New York', 'NY')
hashmap.set(states, 'Michigan', 'MI')
hashmap.set(states, '湖北', 'HB')
hashmap.set(states, '广东', 'GD')

cities = hashmap.new()
hashmap.set(cities, 'CA', 'San Francisco')
hashmap.set(cities, 'MI', 'Detroit')
hashmap.set(cities, 'FL', 'Jacksonville')
hashmap.set(cities, 'NY', 'New York')
hashmap.set(cities, 'OR', 'Portland')
hashmap.set(cities, 'HB', '武汉')
hashmap.set(cities, 'GD', '深圳')

print '-' * 10
print "NY State has: %s" % hashmap.get(cities, 'NY')
print "OR State has: %s" % hashmap.get(cities, 'OR')
print "广东省有%s市" % hashmap.get(cities, 'GD')

print '-' * 10
print "Michigan's abbreviation is: %s" % hashmap.get(states, 'Michigan')
print "Florida's abbreviation is: %s" % hashmap.get(states, 'Florida')

print '-' * 10
print "Michigan has: %s" % hashmap.get(cities, hashmap.get(states, 'Michigan'))
print "Florida has: %s" % hashmap.get(cities, hashmap.get(states, 'Florida'))

print '-' * 10
hashmap.list(states)

print '-' * 10
hashmap.list(cities)

print '-' * 10
state = hashmap.get(states, 'Texas')
if not state:
    print "Sorry, no Texas."

city = hashmap.get(cities, 'TX', 'Does Not Exist')
print "The city for the state 'TX' is: %s" % city

back_cities = hashmap.dump(cities)

hashmap.delete(cities, "NY")
print '-' * 10
hashmap.list(cities)

print '-' * 10
hashmap.list(back_cities)
