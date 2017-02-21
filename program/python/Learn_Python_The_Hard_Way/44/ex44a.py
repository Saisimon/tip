class Parent(object):
    def implicit(self):
        print "PERENT implicit"

class Chlidren(Parent):
    pass

class Son(Parent):
    # Override
    def implicit(self):
        print "SON implicit"

class Child(Parent):
    def implicit(self):
        print "Before"
        super(Child, self).implicit()
        print "After"

Parent().implicit()
Chlidren().implicit()
Son().implicit()
Child().implicit()