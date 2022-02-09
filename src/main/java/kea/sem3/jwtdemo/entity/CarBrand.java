package kea.sem3.jwtdemo.entity;

public enum CarBrand{

    /*An enum is a special "class" that represents a group of constants (unchangeable variables, like final variables).*/

    VOLVO("Volvo"),
    TOYOTA("Toyata"),
    WW("WW"),
    FORD("Ford"),
    SUZUKI("Suzuki");

    public final String printName;

    private CarBrand(String printName){
        this.printName = printName;
    }

}
