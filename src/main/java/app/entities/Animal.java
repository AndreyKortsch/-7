package app.entities;

import javax.faces.bean.ManagedBean;
import java.util.Date;
import java.util.List;

@ManagedBean(name = "animal")
public class Animal
{
 private int Animal_ID;
    public void setAnimal_ID(int Animal_ID) {
        this.Animal_ID = Animal_ID;
    }
    public int getAnimal_ID()
    {
        return Animal_ID;
    }
  private int Number;
    public void setNumber(int Number) {
        this.Number = Number;
    }
    public int getNumber()
    {
        return Number;
    }
 private String Name;
    public void setName(String Name) {
        this.Name = Name;
    }
 public String getName()
 {
        return Name;
 }
 private int Vids_ID;
    public void setVids_ID(int Vids_ID) {
        this.Vids_ID = Vids_ID;
    }
    public int getVids_ID()
    {
        return Vids_ID;
    }
 private Date Date;
    public void setDate(Date Date) {
        this.Date = Date;
    }
    public Date getDate()

    {
        return Date;
    }
 public Animal(int Animal_ID, int Number, String Name, int Vids_ID, Date Date){
     this.Animal_ID=Animal_ID;
     this.Number=Number;
     this.Name=Name;
     this.Vids_ID=Vids_ID;
     this.Date=Date;
 }
     public Animal(int Number, String Name, int Vids_ID, Date Date){
         this.Number=Number;
         this.Name=Name;
         this.Vids_ID=Vids_ID;
         this.Date=Date;
     }
    public Animal(){

    }
 public List<Animal> AnimalList;

 @Override
 public String toString(){
     String lineFormat = "%s %s %s %s %s";
     return (String.format(lineFormat,Animal_ID,Number,Name,Vids_ID,Date));
 }
 }
