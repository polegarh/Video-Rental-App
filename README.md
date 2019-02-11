# Video Rental Service - Demonstration of Design Patterns in OOP
This is a GUI menu where a person can perform several actions, such as add a video to the inventory, check out a video, check in a video, redo and undo the actions. A lot of the code is pretty cool, as it introduces several design patterns. 

## Creational design patterns:
### Builder 
I used Builder creational design pattern in shop.ui package before.
### Factory Method
I used Factory Method design pattern in shop.ui package in class UIFactory.java.

## Structural design patterns:
### Composite 
I used Composite design pattern in shop.main, in MenuEnum.java, which can also be argued for Flyweight instead.
### Flyweight 
I used Flyweight design pattern in VideoObj.java when I call String.intern(). That is evident in java docs, as it is described there that String.intern() implements flyweight design pattern. However, one can argue that MenuEnum.java can also implement flyweight structural design pattern.

## Behavioral design patterns:
### Command 
I used Command behavioral design pattern in shop.command and shop.data packages. It is evident in classes CmdIn, CmdAdd, and so on.
### Iterator 
I used Iterator behavioral design pattern in shop.data package in InventorySet.java. 
I also used this behavioral design in MenuEnums.java for top 10 menu command.
### Observer 
I could use Observer pattern with the state, where Control would act as the
observer and I would create a separate Observable class.
### State 
I used State behavioral design pattern in shop.main where I have StateEnum, MenuEnum and Control. There I clearly do different actions depending on state.
