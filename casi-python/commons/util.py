__author__ = 'Think'
def getSizeForString(s):
    return len(s)
class MyClass:
    name='xiujguo'
    age=10
    def __init__(self,age,name):
        self.age=age
        self.name=name
    def getAge(self):
        return self.age
    def setAge(self,age):
        self.age=age
    def toString(self):
        return {'age':self.age,'name':self.name}