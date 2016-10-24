package ru.neoflex.courses14;

public class EntityNotFoundException extends Exception {
    public EntityNotFoundException(){
    }
    public EntityNotFoundException(String string){
        super(string);
    }
}
