import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.ArrayList;

public class OrderManager{
   public static double PriceForOneThridLb=9.50; 
   public static double PriceForTwoThridLb=11.50; 
   public static double PriceForOneLb=15.50; 
   public static double PriceForBowl=1.00; 
   public static double PriceForExtraCheese=1.00; 
   public static double PriceForExtraTopping=0.75; 
   public static double PriceForPremiumTopping=1.50; 
   public static double PriceForExtraSauce=0.75; 
   public static HashMap<Integer,String> MeatMenuMap;
   public static HashMap<Integer,String> WeightMenuMap;
   public static HashMap<Integer,String> BunOrBowlMenuMap;
   public static HashMap<Integer,String> CheeseMenuMap;
   public static HashMap<Integer,String> ToppingMenuMap;
   public static HashMap<Integer,String> PremiumToppingMenuMap;
   public static HashMap<Integer,String> SauceMenuMap;
   
   public OrderManager(){
       setMeatMenuMap();
       setWeightMenuMap();
       setBunOrBowlMenuMap();
       setCheeseMenuMap();
       setToppingMenuMap();
       setPremiumToppingMenuMap();
       setSauceMenuMap();
    }
    
    
    
    
    
   public static void printOrderAndPrice(){  //just for test
       Burger yourburger=new AddSauceBurger(new AddToppingBurger(new AddCheeseBurger(new BaseBurger())));
       double FinalPrice= yourburger.calPrice();
       String FinalDescription= yourburger.orderDescription();
       System.out.println( "\nBuild Your Own Burger---------");
       System.out.println( FinalDescription);
       System.out.println( "---------- $" + FinalPrice );
              
    }
    
    
    
    
    
    
   
   public void makeOrderAndPrintOrderAndPrice(){
       
       try{
       System.out.println( "\nWelcome to Build Your Burger, Start to build---------");
       
       BaseBurger yourBaseBurger = chooseBaseBurger();
       AddCheeseBurger yourAddCheeseBurger= chooseAddCheeseBurger(yourBaseBurger);
       AddToppingBurger yourAddToppingBurger= chooseAddToppingBurger(yourAddCheeseBurger);
       AddSauceBurger yourAddSauceBurger= chooseAddSauceBurger(yourAddToppingBurger);
       Burger yourburger=yourAddSauceBurger;
       
       double FinalPrice= yourburger.calPrice();
       String FinalDescription= yourburger.orderDescription();
       System.out.println( "\nYour Built Burger---------");
       System.out.println( FinalDescription);
       System.out.println( "---------- $" + FinalPrice );
       
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
        }    
               
    } 
    
    
    
    
    
    
    
    
    
    
    
   
   public BaseBurger chooseBaseBurger() throws Exception{
       System.out.println("\nChoose A Burger(Protein): ");
       System.out.println("Choose Meat: ");
       String yourmeat = showMenuAndMakeChoice(MeatMenuMap);
      
       System.out.println("Choose Weight: ");
       String yourweight=showMenuAndMakeChoice(WeightMenuMap); 
       
       System.out.println("Choose Bowl(+$1.00) or Bun: ");
       String yourbunOrBowl=showMenuAndMakeChoice(BunOrBowlMenuMap);
       
       BaseBurger yourBaseBurger= new BaseBurger(yourmeat, yourweight, yourbunOrBowl);
       return yourBaseBurger;
    }
   
    
    
    
    
    
    
    
   public AddCheeseBurger chooseAddCheeseBurger( Burger yourOldBurger) throws Exception{
       System.out.println("\nChoose a Cheese (Extra cheese +$"+PriceForExtraCheese+"): ");
       ArrayList<String> yourCheeseList = showMenuAndMakeMultipleChoice(CheeseMenuMap);
       
       AddCheeseBurger yourAddCheeseBurger= new AddCheeseBurger(yourOldBurger,yourCheeseList);
       return yourAddCheeseBurger;
       
    }
   
   public AddToppingBurger chooseAddToppingBurger( Burger yourOldBurger) throws Exception{
       System.out.println("\nChoose up to 4 Toppings (Extra topping +$"+PriceForExtraTopping+"): ");
       ArrayList<String> yourToppingList = showMenuAndMakeMultipleChoice(ToppingMenuMap);
       System.out.println("Choose Premium Topping ($"+PriceForPremiumTopping+"): ");
       ArrayList<String> yourPremiumToppingList = showMenuAndMakeMultipleChoice(PremiumToppingMenuMap);
       
       AddToppingBurger yourAddToppingBurger= 
                    new AddToppingBurger(yourOldBurger,yourToppingList,yourPremiumToppingList);
       return yourAddToppingBurger;
       
    } 
    
   public AddSauceBurger chooseAddSauceBurger( Burger yourOldBurger) throws Exception{
       System.out.println("\nChoose a Sauce (Extra sauce +$"+PriceForExtraSauce+"):");
       ArrayList<String> yourSauceList = showMenuAndMakeMultipleChoice(SauceMenuMap);
       
       AddSauceBurger yourAddSauceBurger= new AddSauceBurger(yourOldBurger,yourSauceList);
       return yourAddSauceBurger;
       
    } 
    
   public String showMenuAndMakeChoice(HashMap<Integer,String> menuMap)throws Exception{
       String yourMenu=""; 
       for(int i=1; i<=menuMap.size();i++){
          yourMenu += i +", " + menuMap.get(i);
          if(menuMap==WeightMenuMap){  //just for Weight Menu
              if(menuMap.get(i)=="1/3lb") yourMenu += "($" + PriceForOneThridLb +"); ";
              if(menuMap.get(i)=="2/3lb") yourMenu += "($" + PriceForTwoThridLb +"); ";
              if(menuMap.get(i)=="1lb") yourMenu += "($" + PriceForOneLb +"); ";
            }
          else yourMenu += "; ";
        }
       int choiceNumber = getInputNumber (yourMenu);
       String result=menuMap.get(1); //default choice
       if(choiceNumber<=menuMap.size() && choiceNumber>=1)
           result=menuMap.get(choiceNumber);         
       return result;
       
    }
    
   public ArrayList<String> showMenuAndMakeMultipleChoice(HashMap<Integer,String> menuMap) throws Exception{
       String yourMenu=""; 
       String breakline="";
       for(int i=1; i<=menuMap.size();i++){
           if(i%3==0) breakline="\n";
           else breakline="";
          yourMenu += i +", " + menuMap.get(i) + "; " + breakline;
        }
       yourMenu += "0 for end of choice: " ;
       ArrayList<String> result=new ArrayList<String> ();
       System.out.println(yourMenu);
       int choiceNumber = getInputNumber ("Make your choice:");
       while (choiceNumber!=0){
           String choice=menuMap.get(1); //default choice
           if(choiceNumber<=menuMap.size() && choiceNumber>=1)
                choice=menuMap.get(choiceNumber);
           if (!result.contains(choice))     
                result.add(choice);
           choiceNumber = getInputNumber ("Make more choice:");
        }
       return result;
       
    } 
    
   public static int getInputNumber(String promt){
        BufferedReader stdin=new BufferedReader( new InputStreamReader(System.in));
        System.out.println(promt);
        System.out.flush();
        
        try {
            int result =Integer.parseInt(stdin.readLine());
            
            return result; 
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
            return 0;
        }
    }
    
    public void setMeatMenuMap(){ 
       HashMap<Integer,String> map=new HashMap<Integer,String>();
       map.put(1,"Beef");
       map.put(2,"Chicken");
       map.put(3,"Turkey");
       map.put(4,"Veggie");
       MeatMenuMap=map;
    }

   public void setWeightMenuMap(){ 
       HashMap<Integer,String> map=new HashMap<Integer,String>();
       map.put(1,"1/3lb");
       map.put(2,"2/3lb");
       map.put(3,"1lb");
       WeightMenuMap=map;
    }
    
   public void setBunOrBowlMenuMap(){ 
       HashMap<Integer,String> map=new HashMap<Integer,String>();
       map.put(1 ,"On A Bun");
       map.put(2 ,"In A Bowl with Lettuce Blend");
       map.put(3 ,"In A Bowl with Organic Mixed Greens");
       BunOrBowlMenuMap=map;
    }
   
   public void setCheeseMenuMap(){
       HashMap<Integer,String> map=new HashMap<Integer,String>();
       map.put(1, "Danish Blue Cheese");
       map.put(2, "Horseradish Cheddar");
       map.put(3, "Soft Ripened Brie");
       map.put(4, "Greek Feta");
       map.put(5, "Imported Swiss");
       map.put(6, "Tillmmook Cheddar");
       CheeseMenuMap=map;
    }
   
   public void setToppingMenuMap(){
       HashMap<Integer,String> map=new HashMap<Integer,String>();
       map.put(1, "Bermuda Red Onion");
       map.put(2, "Black Olives");
       map.put(3, "Coleslaw");
       map.put(4, "Carrot String");
       map.put(5, "Dill Pickle Chips");
       map.put(6, "Dried Cranberries");
       ToppingMenuMap=map;       
    }

   public void setPremiumToppingMenuMap(){
       HashMap<Integer,String> map=new HashMap<Integer,String>();
       map.put(1, "Applewood Smoked Bacon");
       map.put(2, "Avocado");
       map.put(3, "Black Forest Ham");
       PremiumToppingMenuMap=map;
    }
   
   public void setSauceMenuMap(){
       HashMap<Integer,String> map=new HashMap<Integer,String>();
       map.put(1, "Apricot Sauce");
       map.put(2, "Basil Pesto");
       map.put(3, "Chipotle Aioli");
       map.put(4, "Classic Caesar");
       map.put(5, "Country Buttermilk Ranch");
       SauceMenuMap=map;
    }  
    


    
}
