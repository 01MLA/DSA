package interviewProblems;

import java.util.LinkedList;

public class Queue {
    public static void main(String[] args) {
        AnimalShelter animalShelter = new AnimalShelter();
        animalShelter.enQueue(new Dog("dog_1"));
        animalShelter.enQueue(new Dog("dog_2"));
        animalShelter.enQueue(new Dog("dog_3"));
        animalShelter.enQueue(new Cat("cat_1"));
        animalShelter.enQueue(new Cat("cat_2"));
        animalShelter.enQueue(new Cat("cat_3"));

        System.out.println(animalShelter.peekAny().getName());
        System.out.println(animalShelter.deQueueCat().getName());
        System.out.println(animalShelter.deQueueDog().getName());
        System.out.println(animalShelter.deQueueDog().getName());
    }
}

class AnimalShelter {
    private LinkedList<Dog> dogs = new LinkedList<>();
    private LinkedList<Cat> cats = new LinkedList<>();
    private int order = 0;

    public void enQueue(Animal animal) {
        animal.setOrder(order);
        order++;
        if (animal instanceof Dog) dogs.add((Dog) animal);
        else if (animal instanceof Cat) cats.add((Cat) animal);
    }

    public int getSize() {
        return dogs.size() + cats.size();
    }

    public Dog deQueueDog() {
        return dogs.poll();
    }

    public Dog peekDogs() {
        return dogs.peek();
    }

    public Cat deQueueCat() {
        return cats.poll();
    }

    public Cat peekCats() {
        return cats.peek();
    }

    public Animal deQueueAny() {
        if (dogs.size() == 0) return deQueueCat();
        else if (cats.size() == 0) return deQueueDog();
        Cat cat = cats.peek();
        Dog dog = dogs.peek();
        if (cat.isOlderThan(dog)) return cats.poll();
        else return dogs.poll();
    }

    public Animal peekAny() {
        if (dogs.size() == 0) return cats.peek();
        else if (cats.size() == 0) return dogs.peek();
        Cat cat = cats.peek();
        Dog dog = dogs.peek();
        if (cat.isOlderThan(dog)) return cats.peek();
        else return dogs.peek();
    }
}

abstract class Animal {
    private int order;
    protected String name;

    public Animal(String animal) {
        this.name = animal;
    }

    public abstract String getName();

    public void setOrder(int order) {
        this.order = order;
    }

    public int getOrder() {
        return this.order;
    }

    public boolean isOlderThan(Animal a) {
        return this.order < a.getOrder();
    }
}

class Cat extends Animal {
    public Cat(String cat) {
        super(cat);
    }

    @Override
    public String getName() {
        return "Cat: " + name;
    }
}

class Dog extends Animal {
    public Dog(String dog) {
        super(dog);
    }

    @Override
    public String getName() {
        return "Dog: " + name;
    }
}